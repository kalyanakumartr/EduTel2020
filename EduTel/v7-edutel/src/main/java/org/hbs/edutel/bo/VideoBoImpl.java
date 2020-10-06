package org.hbs.edutel.bo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.kafka.common.errors.InvalidRequestException;
import org.hbs.core.beans.model.Users;
import org.hbs.core.dao.UserDao;
import org.hbs.core.security.resource.IPath;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EnumInterface;
import org.hbs.edutel.VideoFormBean;
import org.hbs.edutel.beans.path.IErrorEduTel;
import org.hbs.edutel.dao.VideoDao;
import org.hbs.edutel.model.DataTable;
import org.hbs.edutel.model.Video;
import org.hbs.edutel.model.VideoAttachments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class VideoBoImpl implements VideoBo, IErrorEduTel, IPath
{
	private static final long	serialVersionUID	= 2298236709894523727L;

	@Value("${server.edutel.video.resource.handler}")
	protected String			serverResourceHandler;

	@Value("${server.temp.directory}")
	protected String			serverTempDirectory;

	@Value("${server.edutel.video.directory}")
	protected String			serverVideoDirectory;

	@Value("${admin.update.delay.in.seconds:120}") // 2 minutes default update
	private int					updateDelay;

	@Autowired
	protected VideoDao			videoDao;
	
	@Autowired
	protected UserDao			userDao;

	@Override
	public EnumInterface blockVideo(Authentication auth, VideoFormBean vfBean) throws InvalidRequestException
	{

		if (isRecentlyUpdated(auth, vfBean))
		{
			try
			{
				// logger.info("Inside VideoBoImpl blockVideo ::: ", vfBean.user.getVideoId());
				vfBean.repoVideo.setStatus(!vfBean.repoVideo.getStatus());// Negate Current Status
				videoDao.save(vfBean.repoVideo);

				vfBean.clearForm();
				vfBean.messageCode = VIDEO_BLOCKED_SUCCESSFULLY;

				return EReturn.Success;
			}
			finally
			{
				vfBean.video = null;
			}
		}
		throw new InvalidRequestException(VIDEO_DATA_UPDATED_RECENTLY);

	}

	@Override
	public EnumInterface deleteVideo(Authentication auth, VideoFormBean vfBean) throws InvalidRequestException
	{
		if (isRecentlyUpdated(auth, vfBean))
		{
			try
			{
				// logger.info("Inside VideoBoImpl blockVideo ::: ", vfBean.user.getVideoId());
				videoDao.delete(vfBean.repoVideo);

				vfBean.clearForm();
				vfBean.messageCode = VIDEO_DELETED_SUCCESSFULLY;

				return EReturn.Success;
			}
			finally
			{
				vfBean.video = null;
			}
		}
		throw new InvalidRequestException(VIDEO_DATA_UPDATED_RECENTLY);
	}

	@Override
	public boolean isRecentlyUpdated(Authentication auth, VideoFormBean vfBean)
	{
		// logger.info("Inside VideoBoImpl isRecentlyUpdated ::: ", vfBean.user.getVideoId());
		if (CommonValidator.isNotNullNotEmpty(vfBean.video))
		{
			vfBean.repoVideo = videoDao.findById(vfBean.video.getVideoId()).get();
		}
		else if (CommonValidator.isNotNullNotEmpty(vfBean.searchParam))
		{
			List<Video> videoList = videoDao.getVideo(vfBean.searchParam);
			if (CommonValidator.isListFirstNotEmpty(videoList) && videoList.size() == 1)
				vfBean.repoVideo = videoList.iterator().next();
		}

		if (CommonValidator.isNotNullNotEmpty(vfBean.repoVideo))
		{
			if ((System.currentTimeMillis() - vfBean.repoVideo.getModifiedDate().getTime()) > (updateDelay * 1000))
			{
				vfBean.repoVideo.updateDisplayInfoOfProducersAndDateTime();
				vfBean.repoVideo.modifiedUserInfo(auth);
				return true;
			}
			return false;
		}
		throw new InvalidRequestException(VIDEO_NOT_FOUND);
	}

	@Override
	public EnumInterface saveVideo(Authentication auth, VideoFormBean vfBean) throws InvalidRequestException, InvalidKeyException, IOException
	{
		List<String> videoNameList = videoDao.checkVideoName(vfBean.video.getDisplayName(), vfBean.video.getSubject());
		if (videoNameList == null || videoNameList.isEmpty())
		{
			vfBean.video.createdUserAndProducerInfo(auth);
			vfBean.video.setStatus(false);
			vfBean.video.setVideoId(vfBean.video.getBusinessKey());

			File srcFolder = createVideoAttachments(auth, vfBean);

			vfBean.video = videoDao.save(vfBean.video);

			if (CommonValidator.isNotNullNotEmpty(vfBean.video))
			{
				vfBean.clearForm();
				vfBean.messageCode = VIDEO_CREATED_SUCCESSFULLY;
				try
				{
					if (srcFolder != null && srcFolder.exists())
					{
						FileUtils.cleanDirectory(srcFolder);
						srcFolder.delete();
					}

				}
				catch (Exception e)
				{
				}
				return EReturn.Success;
			}
		}
		throw new InvalidKeyException(VIDEO_ALREADY_EXISTS);
	}

	private File createVideoAttachments(Authentication auth, VideoFormBean vfBean) throws IOException
	{
		List<String> fileList = Arrays.asList(vfBean.uploadedFiles);

		System.out.println(">>>>>>>>>>>>>>>addVideo>>>>>>>>>>>> ");

		File srcFolder = new File(serverTempDirectory + SLASH + vfBean.random);
		File destFolder = new File(serverVideoDirectory + SLASH + vfBean.random);

		if (!destFolder.exists())
			destFolder.mkdirs();

		VideoAttachments _VATT = null;

		for (File file : srcFolder.listFiles())
		{
			if (file.isFile() && fileList.contains(file.getName()))
			{
				Path srcPath = Paths.get(serverTempDirectory, vfBean.random, file.getName());
				Path destPath = Paths.get(serverVideoDirectory, vfBean.random, file.getName());

				Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
				_VATT = new VideoAttachments();
				_VATT.createdUserAndProducerInfo(auth);
				_VATT.setStatus(true);
				_VATT.setUploadFileName(file.getName());
				_VATT.setUploadFileSize(file.length());
				_VATT.setUploadFileFolderURL(vfBean.random);
				_VATT.setUploadResourceHandler(serverResourceHandler.trim());
				_VATT.setVideo(vfBean.video);
				vfBean.video.getAttachmentList().add(_VATT);
			}
		}
		return srcFolder;
	}

	@Override
	public EnumInterface updateVideo(Authentication auth, VideoFormBean vfBean) throws InvalidRequestException, InvalidKeyException
	{
		if (isRecentlyUpdated(auth, vfBean))
		{
			// logger.info("VideoBoImpl updateVideo starts ::: ");
			vfBean.updateRepoVideo(auth);
			videoDao.save(vfBean.video);
			vfBean.clearForm();
			vfBean.messageCode = VIDEO_UPDATED_SUCCESSFULLY;
			// logger.info("VideoBoImpl updateVideo ends ::: ");
			return EReturn.Success;
		}
		throw new InvalidRequestException(VIDEO_DATA_UPDATED_RECENTLY);
	}

	@Override
	public List<Video> searchVideo(DataTable dataTable) throws InvalidRequestException
	{
		return videoDao.getVideoList(dataTable.meta.query, dataTable.pageable);
	}

	@Override
	public long searchVideoCount(DataTable dataTable)
	{
		return videoDao.getVideoCount(dataTable.meta.query);
	}

	@Override
	public Video getVideoById(VideoFormBean vfBean)
	{
		return videoDao.findById(vfBean.video.getVideoId()).get();
	}

	@Override
	public List<Video> allVideo() throws InvalidRequestException
	{
		return videoDao.getVideoList();
	}

	@Override
	public Users saveUser(Users formUser)
	{
		return userDao.save(formUser);
	}

}

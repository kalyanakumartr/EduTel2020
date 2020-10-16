package org.hbs.edutel.bo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.Comparator;
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
	protected UserDao			userDao;

	@Autowired
	protected VideoDao			videoDao;

	@Override
	public List<Video> allVideo() throws InvalidRequestException
	{
		return videoDao.getVideoList();
	}

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
	public void cleanAndDelete(final String folder)
	{
		new Thread(new Runnable() {

			@Override
			public void run()
			{
				try
				{
					File file = new File(folder);

					if (file != null && file.exists())
					{
						FileUtils.cleanDirectory(file);
						file.delete();
					}
				}
				catch (Exception e)
				{
					System.out.println(">>>>>>NOT Able to clean folder>>>>>>>>> " + folder);
				}

			}
		}).start();
	}

	private void createVideoAttachments(Authentication auth, VideoFormBean vfBean) throws IOException
	{
		String baseFolder = serverTempDirectory + SLASH + EAuth.User.getUserId(auth);
		vfBean.folderPath = baseFolder + SLASH + vfBean.random; // Helps to clean folder on exception
		
		File srcFolder = new File(vfBean.folderPath);
		File destFolder = new File(serverVideoDirectory + SLASH + vfBean.random);

		if (!destFolder.exists())
			destFolder.mkdirs();

		VideoAttachments _VATT = null;
		File[] uploadedFileList = srcFolder.listFiles();
		sortFileByNumberEndsWith(uploadedFileList);

		if (CommonValidator.isArrayFirstNotNull(uploadedFileList))
		{
			List<String> fileList = Arrays.asList(vfBean.uploadedFiles);
			
			for (File file : uploadedFileList)
			{
				if (file.isFile() && fileList.contains(file.getName()))
				{
					Path srcPath = Paths.get(baseFolder, vfBean.random, file.getName());
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
		}
	}

	@Override
	public EnumInterface deleteVideo(Authentication auth, VideoFormBean vfBean) throws InvalidRequestException
	{
		if (isRecentlyUpdated(auth, vfBean))
		{

			try
			{
				if (CommonValidator.isSetFirstNotEmpty(vfBean.repoVideo.getAttachmentList()))
				{
					String folder = vfBean.repoVideo.getAttachmentList().iterator().next().getUploadFileFolderURL();
					folder = serverVideoDirectory + SLASH + folder;
					
					cleanAndDelete(folder);
				}
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
	public Video getVideoById(VideoFormBean vfBean)
	{
		return videoDao.findById(vfBean.video.getVideoId()).get();
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
	public Users saveUser(Users formUser)
	{
		return userDao.save(formUser);
	}

	@Override
	public EnumInterface saveVideo(Authentication auth, VideoFormBean vfBean) throws InvalidRequestException, InvalidKeyException
	{
		List<String> videoNameList = videoDao.checkVideoName(vfBean.video.getDisplayName(), vfBean.video.getSubject());
		if (videoNameList == null || videoNameList.isEmpty())
		{
			try
			{
				vfBean.video.createdUserAndProducerInfo(auth);
				vfBean.video.setStatus(false);
				vfBean.video.setVideoId(vfBean.video.getBusinessKey());
				
				createVideoAttachments(auth, vfBean);
				
				vfBean.video = videoDao.save(vfBean.video);
				if (CommonValidator.isNotNullNotEmpty(vfBean.video))
				{
					vfBean.clearForm();
					vfBean.messageCode = VIDEO_CREATED_SUCCESSFULLY;
					return EReturn.Success;
				} 
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				cleanAndDelete(vfBean.folderPath); // On any scenario, we will get temporary folder to delete
			}
		}
		throw new InvalidKeyException(VIDEO_ALREADY_EXISTS);
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

	public void sortFileByNumberEndsWith(File[] files)
	{
		Arrays.sort(files, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2)
			{
				int n1 = extractNumber(o1.getName());
				int n2 = extractNumber(o2.getName());
				return n1 - n2;
			}

			private int extractNumber(String name)
			{
				int i = 0;
				try
				{
					int s = name.lastIndexOf('_') + 1;
					int e = name.lastIndexOf('.');
					String number = name.substring(s, e);
					i = Integer.parseInt(number);
				}
				catch (Exception e)
				{
					i = 0; // if filename does not match the format
							// then default to 0
				}
				return i;
			}
		});

		for (File f : files)
		{
			System.out.println(f.getName());
		}
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

}

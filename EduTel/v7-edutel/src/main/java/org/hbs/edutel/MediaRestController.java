package org.hbs.edutel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.util.encoders.Base64;
import org.hbs.core.beans.UserFormBean;
import org.hbs.core.beans.model.IUsersBase.EUserStatus;
import org.hbs.core.beans.model.IUsersBase.EUserType;
import org.hbs.core.beans.model.Roles;
import org.hbs.core.beans.model.UserRoles;
import org.hbs.core.beans.model.UsersMedia;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EnumInterface;
import org.hbs.edutel.model.DataTable;
import org.hbs.edutel.model.IVideoAttachments;
import org.hbs.edutel.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MediaRestController extends MediaControllerBase implements IMediaRestController
{
	private static final long	serialVersionUID	= 3917655796383463162L;
	public static final String	VIDEO				= "/videos";

	public static final String	CONTENT_TYPE		= "Content-Type";
	public static final String	CONTENT_LENGTH		= "Content-Length";
	public static final String	VIDEO_CONTENT		= "video/mp4";
	public static final String	CONTENT_RANGE		= "Content-Range";
	public static final String	ACCEPT_RANGES		= "Accept-Ranges";
	public static final String	BYTES				= "bytes";
	public static final int		BYTE_RANGE			= 1024;

	@Autowired
	MessageSource				messageSource;

	@Override
	public ResponseEntity<?> processUpload(@PathVariable("random") String random, @RequestParam("files[]") MultipartFile[] files)
	{
		for (MultipartFile multiFile : files)
		{
			try
			{
				File folder = new File(serverTempDirectory + SLASH + random);

				if (!folder.exists())
					folder.mkdirs();
				String absolutePath = random + SLASH + multiFile.getOriginalFilename();

				InputStream is = multiFile.getInputStream();
				Files.copy(is, Paths.get(serverTempDirectory + SLASH + absolutePath), StandardCopyOption.REPLACE_EXISTING);

			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> addVideo(Authentication auth, @RequestParam("mediaForm") String mediaForm) // Authentication
	// auth,
	{
		// logger.info("MediaRestController addVideo starts ::: ");

		VideoFormBean vfBean = new VideoFormBean();
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			vfBean = mapper.readValue(mediaForm, VideoFormBean.class);

			videoBo.saveVideo(auth, vfBean);
			return new ResponseEntity<>(vfBean, HttpStatus.OK);
		}
		catch (Exception excep)
		{
			vfBean.clearForm();
			vfBean.messageCode = excep.getLocalizedMessage();
			// logger.error("Exception in VideoController addVideo ::: ", excep);
			return new ResponseEntity<>(vfBean, HttpStatus.BAD_REQUEST);
		}
		finally
		{
			// logger.info("addVideo ends ::: ");
		}
	}

	@Override
	public ResponseEntity<?> blockVideo(Locale locale, Authentication auth, @RequestBody VideoFormBean vfBean) //
	{
		try
		{
			// logger.info("VideoController blockVideo starts ::: ", vfBean.user.getVideoId());
			if (CommonValidator.isNotNullNotEmpty(vfBean.video))
			{
				return new ResponseEntity<EnumInterface>(videoBo.blockVideo(auth, vfBean), HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);
		}
		catch (Exception excep)
		{
			vfBean.clearForm();
			vfBean.messageCode = messageSource.getMessage(excep.getLocalizedMessage(), null, locale);
			// logger.error("Exception in VideoController blockVideo ::: ", excep);
			return new ResponseEntity<>(vfBean, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * NOT Yet Implemented...
	 */
	@Override
	public ResponseEntity<?> deleteVideo(Locale locale, Authentication auth, @RequestBody VideoFormBean vfBean)//
	{
		try
		{
			// logger.info("VideoController deleteVideo starts ::: ", vfBean.user.getVideoId());
			if (CommonValidator.isNotNullNotEmpty(vfBean.video))
			{
				return new ResponseEntity<EnumInterface>(videoBo.deleteVideo(auth, vfBean), HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);

		}
		catch (Exception excep)
		{
			vfBean.clearForm();
			vfBean.messageCode = messageSource.getMessage(excep.getLocalizedMessage(), null, locale);
			// logger.error("Exception in VideoController deleteVideo ::: ", excep);
			return new ResponseEntity<>(vfBean, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> updateVideo(Authentication auth, @RequestBody VideoFormBean vfBean)
	{
		try
		{
			// logger.info("Inside VideoController updateVideo ::: ");
			if (CommonValidator.isNotNullNotEmpty(vfBean, vfBean.video))
			{
				videoBo.updateVideo(auth, vfBean);
				return new ResponseEntity<>(vfBean, HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);

		}
		catch (Exception excep)
		{
			vfBean.clearForm();
			vfBean.messageCode = excep.getLocalizedMessage();
			// logger.error("Exception in VideoController updateVideo ::: ", excep);
			return new ResponseEntity<>(vfBean, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> searchVideo(HttpServletRequest request)
	{
		try
		{

			DataTable dataTable = new DataTable(request).build();
			List<Video> videoList = videoBo.searchVideo(dataTable);

			dataTable.meta.total = videoBo.searchVideoCount(dataTable);

			for (Video video : videoList)
			{
				video.createdDateByTimeZone();
				video.modifiedDateByTimeZone();
				dataTable.data.add(video);
			}

			// logger.info("Inside UserController search ::: ");
			return new ResponseEntity<DataTable>(dataTable, HttpStatus.OK);
		}
		catch (Exception excep)
		{
			excep.printStackTrace();
			// logger.error("Exception in UserController search ::: ", excep);
			return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ModelAndView endUserViewVideo(Authentication auth, String accessToken, String subjects, String whom)
	{
		ModelAndView modelView = new ModelAndView(ENDUSER_VIDEOS_PAGE);
		try
		{
			modelView.addObject("videoList", videoBo.allVideo());
			modelView.addObject("subjects", subjects);
			modelView.addObject("view", whom);
			modelView.addObject("accessToken", new String(Base64.decode(accessToken)));

			return modelView;
		}
		catch (Exception excep)
		{
			return new ModelAndView(ENDUSER_VIDEOS_PAGE);
		}
		finally
		{

		}
	}

	@Override
	public ModelAndView viewVideo(Authentication auth, String videoId, long attachmentId)
	{
		ModelAndView modelView = new ModelAndView(VIEW_VIDEOS_PAGE);
		try
		{
			if (CommonValidator.isNotNullNotEmpty(videoId))
			{
				VideoFormBean vfBean = new VideoFormBean(videoId);
				Video video = videoBo.getVideoById(vfBean);

				modelView.addObject("displayName", video.getDisplayName());
				modelView.addObject("tutorName", video.getTutorName());
				modelView.addObject("subject", video.getSubject());

				Set<IVideoAttachments> videoList = video.getAttachmentList(attachmentId);
				modelView.addObject("videoList", videoList);
				IVideoAttachments vat = videoList.iterator().next();

				modelView.addObject("firstVideo", vat.getUploadFileFolderURL() + SLASH + vat.getUploadFileName());

				return modelView;
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);
		}
		catch (Exception excep)
		{
			return new ModelAndView(VIEW_VIDEOS_PAGE);
		}
		finally
		{

		}
	}

	@Override
	public ResponseEntity<byte[]> streamVideo(final HttpServletResponse response, @RequestHeader(value = "Range", required = false) String range, String videoPath, String videoName)
	{
		long rangeStart = 0;
		long rangeEnd;
		byte[] data;
		Long fileSize = 0l;
		try
		{
			fileSize = getFileSize(videoPath, videoName);
			if (range == null)
			{
				return ResponseEntity.status(HttpStatus.OK)//
						.header(CONTENT_TYPE, VIDEO_CONTENT)//
						.header(CONTENT_LENGTH, String.valueOf(fileSize))//
						.body(readByteRange(videoPath, videoName, rangeStart, fileSize - 1));
			}
			String[] ranges = range.split("-");
			rangeStart = Long.parseLong(ranges[0].substring(6)); // bytes=0-
			if (ranges.length > 1)
			{
				rangeEnd = Long.parseLong(ranges[1]);
			}
			else
			{
				rangeEnd = fileSize - 1;
			}
			if (fileSize < rangeEnd)
			{
				rangeEnd = fileSize - 1;
			}
			data = readByteRange(videoPath, videoName, rangeStart, rangeEnd);
		}
		catch (IOException e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		String contentLength = String.valueOf((rangeEnd - rangeStart) + 1);
		return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)//
				.header(CONTENT_TYPE, VIDEO_CONTENT)//
				.header(ACCEPT_RANGES, BYTES)//
				.header(CONTENT_LENGTH, contentLength)//
				.header(CONTENT_RANGE, BYTES + " " + rangeStart + "-" + rangeEnd + "/" + fileSize)//
				.body(data);
	}

	private String getFilePath(String videoPath)
	{
		return new File(serverVideoDirectory + SLASH + videoPath).getAbsolutePath();
	}

	public Long getFileSize(String videoPath, String fileName)
	{
		return Optional.ofNullable(fileName).map(file -> Paths.get(getFilePath(videoPath), file)).map(this::sizeFromFile).orElse(0L);
	}

	private Long sizeFromFile(Path path)
	{
		try
		{
			return Files.size(path);
		}
		catch (IOException ioException)
		{
		}
		return 0L;
	}

	public byte[] readByteRange(String videoPath, String filename, long start, long end) throws IOException
	{
		Path path = Paths.get(getFilePath(videoPath), filename);
		try (InputStream inputStream = (Files.newInputStream(path)); ByteArrayOutputStream bufferedOutputStream = new ByteArrayOutputStream())
		{
			byte[] data = new byte[4096];
			int nRead;
			while ( (nRead = inputStream.read(data, 0, data.length)) != -1 )
			{
				bufferedOutputStream.write(data, 0, nRead);
			}
			bufferedOutputStream.flush();
			byte[] result = new byte[(int) (end - start) + 1];
			System.arraycopy(bufferedOutputStream.toByteArray(), (int) start, result, 0, result.length);
			return result;
		}
	}

	@Override
	public ResponseEntity<?> addEduTelUser(Authentication auth, UserFormBean ufBean)
	{
		try
		{
			ufBean.formUser.createdUserAndProducerInfo(auth);
			ufBean.formUser.setStatus(true);
			ufBean.formUser.setUserStatus(EUserStatus.Activated);
			ufBean.formUser.setUserPwdModFlag(false);
			ufBean.formUser.setUserPwd("$2a$10$z/h4gKgrVrSp9C1LQhxpPec0TT/EIa1FyDMKvP/xe7NOCnG3Qiv8q");
			ufBean.formUser.getBusinessKey();// Initialize Primary Key

			for (UsersMedia _UM : ufBean.formUser.getMediaList())
			{
				_UM.setUsers(ufBean.formUser);
			}

			UserRoles _UR = new UserRoles();
			_UR.setUsers(ufBean.formUser);
			
			if(ufBean.formUser.getUserType() == EUserType.Consumer)
				_UR.setRoles(new Roles(ERole.Consumer.name()));
			else
				_UR.setRoles(new Roles(ERole.Employee.name()));
			
			ufBean.formUser.getUserRoleses().add(_UR);

			ufBean.user = videoBo.saveUser(ufBean.formUser);
			
			return new ResponseEntity<>(EReturn.Success, HttpStatus.OK);
			
		}
		catch (Exception excep)
		{
			ufBean.messageCode = excep.getLocalizedMessage();
			return new ResponseEntity<>(EReturn.Failure, HttpStatus.BAD_REQUEST);
		}
	}
}
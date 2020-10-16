package org.hbs.edutel;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbs.core.beans.UserFormBean;
import org.hbs.edutel.beans.path.IPathEduTel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface IMediaRestController extends IPathEduTel
{
	@PostMapping
	@RequestMapping(value = TEMP_UPLOAD)
	@PreAuthorize(HAS_AUTHORITY_BOTH)
	ResponseEntity<?> processUpload(Authentication auth, @PathVariable String random, @RequestParam("files") MultipartFile[] files);
	
	@PostMapping
	@RequestMapping(value = ADD_EDUTEL_USER, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_AUTHORITY_BOTH)
	ResponseEntity<?> addEduTelUser(Authentication auth, @RequestBody UserFormBean ufBean);

	@PostMapping
	@RequestMapping(value = ADD_VIDEOS, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_AUTHORITY_BOTH)
	ResponseEntity<?> addVideo(Authentication auth, @RequestParam("mediaForm") String mediaForm); // Authentication
																									// auth,

	@PostMapping
	@RequestMapping(value = BLOCK_VIDEOS, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_AUTHORITY_BOTH)
	ResponseEntity<?> blockVideo(Locale locale, Authentication auth, VideoFormBean vfBean);

	@PostMapping
	@RequestMapping(value = DELETE_VIDEOS, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_AUTHORITY_BOTH)
	ResponseEntity<?> deleteVideo(Locale locale, Authentication auth, VideoFormBean vfBean);

	@PostMapping
	@RequestMapping(value = UPDATE_VIDEOS, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_AUTHORITY_BOTH)
	ResponseEntity<?> updateVideo(Authentication auth, VideoFormBean vfBean);

	@PostMapping
	@RequestMapping(value = VIEW_VIDEOS, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_ALL_AUTHORITY)
	ModelAndView viewVideo(Authentication auth, @PathVariable("videoId") String videoId, @PathVariable("attachmentId") long attachmentId);

	@PostMapping
	@RequestMapping(value = SEARCH_VIDEOS, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(HAS_AUTHORITY_BOTH)
	ResponseEntity<?> searchVideo(HttpServletRequest request);

	@GetMapping
	@RequestMapping(value = STREAM_VIDEOS, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize(HAS_AUTHORITY_BOTH)
	ResponseEntity<byte[]> streamVideo(HttpServletResponse response, @RequestHeader(value = "Range", required = false) String range, @PathVariable("videoPath") String videoPath, @PathVariable("videoName") String videoName);

	@PostMapping
	@RequestMapping(value = ENDUSER_VIDEOS, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize(HAS_AUTHORITY_BOTH)
	ModelAndView endUserViewVideo(Authentication auth, @PathVariable("accessToken") String accessToken, @PathVariable("subjects") String subjects, @PathVariable("whom") String whom);
}
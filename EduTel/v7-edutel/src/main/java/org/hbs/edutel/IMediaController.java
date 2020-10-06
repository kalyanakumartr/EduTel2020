package org.hbs.edutel;

import org.hbs.edutel.beans.path.IPathEduTel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

public interface IMediaController extends IPathEduTel
{
	@GetMapping(path = PRE_ADD_VIDEOS)
	@PreAuthorize(HAS_AUTHORITY_BOTH)
	ModelAndView preAddVideo();
	
	@GetMapping(path = PRESEARCH_VIDEOS)
	//@PreAuthorize(HAS_AUTHORITY_BOTH)
	ModelAndView preSearchVideo(@PathVariable("accessToken") String accessToken);
	
	@PostMapping(path = PREUPDATE_VIDEOS)
	//@PreAuthorize(HAS_AUTHORITY_BOTH)
	ModelAndView preUpdateVideo(Authentication auth, @RequestBody VideoFormBean vfBean);
	
	@GetMapping(path = VIEW_ENDUSER_VIDEOS)
	//@PreAuthorize(HAS_AUTHORITY_BOTH)
	ModelAndView viewEndUserVideo(@PathVariable("accessToken") String accessToken, @PathVariable("subjects") String subjects, @PathVariable("whom") String whom);
}


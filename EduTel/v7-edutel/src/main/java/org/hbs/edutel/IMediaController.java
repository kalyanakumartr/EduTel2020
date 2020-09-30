package org.hbs.edutel;

import org.hbs.edutel.beans.path.IPathEduTel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

public interface IMediaController extends IPathEduTel
{
	@GetMapping(path = PRE_ADD_VIDEOS)
	//@PreAuthorize(HAS_AUTHORITY_BOTH)
	ModelAndView preAddVideo();
	
	@GetMapping(path = PRESEARCH_VIDEOS)
	//@PreAuthorize(HAS_AUTHORITY_BOTH)
	ModelAndView preSearchVideo();
	
	@PostMapping(path = PREUPDATE_VIDEOS)
	//@PreAuthorize(HAS_AUTHORITY_BOTH)
	ModelAndView preUpdateVideo(Authentication auth, @RequestBody VideoFormBean vfBean);
	
}


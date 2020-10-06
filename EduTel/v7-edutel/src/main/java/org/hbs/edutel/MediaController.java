package org.hbs.edutel;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MediaController extends MediaControllerBase implements IMediaController
{
	private static final long	serialVersionUID	= 8649418474712747923L;

	@Value("${tutor.names.list: 'Add Tutor Names In Properties'}")
	private String				tutorList;

	@Override
	public ModelAndView preAddVideo()
	{
		ModelAndView modelView = new ModelAndView(ADD_VIDEOS_PAGE);
		modelView.addObject("tutorList", StringUtils.trimArrayElements(tutorList.split(COMMA_SPACE.trim())));
		modelView.addObject("mediaForm", new VideoFormBean());
		return modelView;
	}

	@Override
	public ModelAndView preSearchVideo(String accessToken)
	{
		ModelAndView modelView = new ModelAndView(SEARCH_VIDEOS_PAGE);
		modelView.addObject("tutorList", StringUtils.trimArrayElements(tutorList.split(COMMA_SPACE.trim())));
		modelView.addObject("accessToken", new String(Base64.decode(accessToken)));
		modelView.addObject("tokenBinder", accessToken);
		modelView.addObject("dashBoardURL", websiteURL + "/dashBoardEmployee.do");
		modelView.addObject("logoutURL", websiteURL + "/logoutPage.do");
		modelView.addObject("preSearchVideoURL", "/preSearchVideo/" + accessToken);
		return modelView;
	}

	@Override
	public ModelAndView preUpdateVideo(Authentication auth, @RequestBody VideoFormBean vfBean)
	{
		ModelAndView modelView = new ModelAndView("redirect:" + ADD_VIDEOS_PAGE);
		try
		{
			modelView.addObject("tutorList", StringUtils.trimArrayElements(tutorList.split(COMMA_SPACE.trim())));
			modelView.addObject("mediaForm", new VideoFormBean(videoBo.getVideoById(vfBean)));

			return modelView;
		}
		catch (Exception excep)
		{
			vfBean.clearForm();
			vfBean.messageCode = excep.getLocalizedMessage();
			// logger.error("Exception in VideoController addVideo ::: ", excep);
			return new ModelAndView("redirect:" + ADD_VIDEOS_PAGE);
		}
		finally
		{
			// logger.info("preUpdateVideo ends ::: ");
		}
	}

	@Override
	public ModelAndView viewEndUserVideo(String accessToken, String subjects, String whom)
	{
		ModelAndView modelView = new ModelAndView(VIEWENDUSER_VIDEOS_PAGE);
		try
		{
			modelView.addObject("dashBoardURL", websiteURL + "/dashBoardStudent.do?p=1");
			modelView.addObject("logoutURL", websiteURL + "/logoutPage.do");
			modelView.addObject("endUserVideoURL", "/endUserVideo/" + accessToken + "/" + subjects + "/" + whom);
			modelView.addObject("accessToken", new String(Base64.decode(accessToken)));

			return modelView;
		}
		catch (Exception excep)
		{
			return new ModelAndView(VIEWENDUSER_VIDEOS_PAGE);
		}
		finally
		{

		}
	}

}
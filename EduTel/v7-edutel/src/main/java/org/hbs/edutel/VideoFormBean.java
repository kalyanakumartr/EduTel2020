package org.hbs.edutel;

import org.hbs.core.beans.APIStatus;
import org.hbs.edutel.model.Video;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class VideoFormBean extends APIStatus
{

	private static final long	serialVersionUID	= 7656144989864353914L;
	public String				searchParam;
	public String				random				= System.currentTimeMillis() + "";
	public Video				video				= new Video();
	public Video				repoVideo			= new Video();
	public String[]				uploadedFiles;

	public VideoFormBean(Video video)
	{
		random = System.currentTimeMillis() + "";
		this.video = video;
	}
	public VideoFormBean(String videoId)
	{
		random = System.currentTimeMillis() + "";
		this.video = new Video(videoId);
	}

	public VideoFormBean()
	{
		random = System.currentTimeMillis() + "";
		this.video = new Video();
		this.repoVideo = new Video();
		this.searchParam = null;
		this.uploadedFiles = null;
	}

	@Override
	public void clearForm()
	{
		random = System.currentTimeMillis() + "";
		this.video = null;
		this.repoVideo = null;
		this.searchParam = null;
		this.uploadedFiles = null;
	}

	public String getSearchParam()
	{
		return searchParam;
	}

	public void setSearchParam(String searchParam)
	{
		this.searchParam = searchParam;
	}

	public String getRandom()
	{
		return random;
	}

	public void setRandom(String random)
	{
		this.random = random;
	}

	public Video getVideo()
	{
		return video;
	}

	public void setVideo(Video video)
	{
		this.video = video;
	}

	public String[] getUploadedFiles()
	{
		return uploadedFiles;
	}

	public void setUploadedFiles(String[] uploadedFiles)
	{
		this.uploadedFiles = uploadedFiles;
	}

	public void updateRepoVideo(Authentication auth)
	{
		video.setTutorName(repoVideo.getTutorName());
		video.setDisplayName(repoVideo.getDisplayName());
		video.setSubject(repoVideo.getSubject());
		video.setDescription(repoVideo.getDescription());
	}

}

package org.hbs.edutel.beans.path;

import org.hbs.core.beans.path.IErrorAdmin;
import org.hbs.core.security.resource.EnumResourceInterface;
import org.hbs.core.security.resource.IPath;

public interface IPathEduTel extends IPath, IErrorAdmin
{
	public String	PRE_ADD_VIDEOS			= "/preAddVideo";
	public String	PREUPDATE_VIDEOS		= "/preUpdateVideo";
	public String	ADD_VIDEOS				= "/addVideo";
	public String	UPDATE_VIDEOS			= "/updateVideo";
	public String	BLOCK_VIDEOS			= "/blockVideo";
	public String	DELETE_VIDEOS			= "/deleteVideo";
	public String	ENDUSER_VIDEOS			= "/endUserVideo";
	public String	VIEW_VIDEOS				= "/viewVideo/{videoId}/{attachmentId}";
	public String	STREAM_VIDEOS			= "/streamVideo/{videoPath}/{videoName}";
	public String	ADD_VIDEOS_PAGE			= "edutel_online_video";
	public String	SEARCH_VIDEOS_PAGE		= "edutel_online_video_search";
	public String	VIEW_VIDEOS_PAGE		= "edutel_online_video_view";
	public String	ENDUSERVIEW_VIDEOS_PAGE	= "edutel_online_video_enduser_view";
	public String	PRESEARCH_VIDEOS		= "/preSearchVideo";
	public String	SEARCH_VIDEOS			= "/searchVideo";
	public String	TEMP_UPLOAD				= "/tempUpload/{random}";

	public enum EPathEduTel implements EnumResourceInterface
	{
		// PreSearchVideos(PRESEARCH_VIDEOS, ERole.Administrator, ERole.Employee, ERole.Consumer),
		PreAddVideos(PRE_ADD_VIDEOS), //
		PreSearchVideos(PRESEARCH_VIDEOS), //
		StreamVideos(STREAM_VIDEOS), //
		EndUserVideos(ENDUSER_VIDEOS, ERole.Administrator, ERole.Employee), //
		PreUpdateVideos(PREUPDATE_VIDEOS, ERole.Administrator, ERole.Employee), //
		AddVideos(ADD_VIDEOS, ERole.Administrator, ERole.Employee), //
		UpdateVideos(UPDATE_VIDEOS, ERole.Administrator, ERole.Employee), //
		BlockVideos(BLOCK_VIDEOS, ERole.Administrator, ERole.Employee), //
		DeleteVideos(DELETE_VIDEOS, ERole.Administrator, ERole.Employee), //
		ViewVideos(VIEW_VIDEOS, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		// StreamVideos(STREAM_VIDEOS, ERole.Administrator, ERole.Employee, ERole.Consumer), //
		SearchVideos(SEARCH_VIDEOS, ERole.Administrator, ERole.Employee), //
		TempUpload(TEMP_UPLOAD, ERole.Administrator, ERole.Employee); //

		String	path;

		ERole	roles[];

		EPathEduTel(String path, ERole... roles)
		{
			this.path = path;
			this.roles = roles;
		}

		@Override
		public String getPath()
		{
			return this.path;
		}

		@Override
		public ERole[] getRoles()
		{
			return this.roles;
		}
	}

}

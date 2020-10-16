package org.hbs.edutel.bo;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.util.List;

import org.apache.kafka.common.errors.InvalidRequestException;
import org.hbs.core.beans.model.Users;
import org.hbs.core.util.EnumInterface;
import org.hbs.edutel.VideoFormBean;
import org.hbs.edutel.model.DataTable;
import org.hbs.edutel.model.Video;
import org.springframework.security.core.Authentication;

public interface VideoBo extends Serializable
{
	EnumInterface blockVideo(Authentication auth, VideoFormBean ufBean) throws InvalidRequestException;

	EnumInterface deleteVideo(Authentication auth, VideoFormBean ufBean) throws InvalidRequestException;

	EnumInterface saveVideo(Authentication auth, VideoFormBean vfBean) throws InvalidRequestException, InvalidKeyException, IOException;

	EnumInterface updateVideo(Authentication auth, VideoFormBean vfBean) throws InvalidRequestException, InvalidKeyException;

	List<Video> searchVideo(DataTable dataTable) throws InvalidRequestException;

	List<Video> allVideo() throws InvalidRequestException;

	boolean isRecentlyUpdated(Authentication auth, VideoFormBean vfBean);

	long searchVideoCount(DataTable dataTable);

	Video getVideoById(VideoFormBean vfBean);

	Users saveUser(Users formUser);

	void cleanAndDelete(String folder);
}

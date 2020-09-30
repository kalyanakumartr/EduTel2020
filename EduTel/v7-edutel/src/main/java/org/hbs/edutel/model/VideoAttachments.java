package org.hbs.edutel.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.core.beans.model.CommonFileUpload;
import org.hbs.core.beans.model.CreatedModifiedUsers;
import org.hbs.core.security.resource.IPath.EAuth;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "_videoattachments")
@JsonInclude(Include.NON_NULL)
public class VideoAttachments extends CommonFileUpload implements IVideoAttachments
{
	private static final long		serialVersionUID	= 917678364001988324L;
	protected CreatedModifiedUsers	byUser				= new CreatedModifiedUsers();
	protected Video			video;

	public void createdUserAndProducerInfo(Authentication auth)
	{
		this.byUser.setCreatedUser(EAuth.User.getUser(auth));
		this.createdDate = new Timestamp(System.currentTimeMillis());
		this.byUser.setModifiedUser(EAuth.User.getUser(auth));
		this.modifiedDate = new Timestamp(System.currentTimeMillis());
	}

	@ManyToOne(targetEntity = Video.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "videoId")
	@JsonIgnore
	public Video getVideo()
	{
		return video;
	}

	public void setVideo(Video video)
	{
		this.video = video;
	}

	@JsonIgnore
	public CreatedModifiedUsers getByUser()
	{
		return byUser;
	}

	@Override
	@Transient
	@JsonIgnore
	public String getCountryTimeZone()
	{
		return this.byUser.getCountryId();
	}

	public void setByUser(CreatedModifiedUsers byUser)
	{
		this.byUser = byUser;
	}

}

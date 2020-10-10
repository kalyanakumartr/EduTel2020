package org.hbs.edutel.model;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.core.beans.model.CommonDateAndStatusFields;
import org.hbs.core.beans.model.CreatedModifiedUsers;
import org.hbs.core.security.resource.IPath.EAuth;
import org.hbs.core.util.ICRUDBean;
import org.hbs.core.util.EBusinessKey.EKey;
import org.hbs.edutel.dao.IEduTelVideo;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "_video")
public class Video extends CommonDateAndStatusFields implements IEduTelVideo, ICRUDBean
{
	private static final long		serialVersionUID	= 7704616914582453844L;
	protected CreatedModifiedUsers	byUser				= new CreatedModifiedUsers();
	private String					description;
	private String					displayName;
	private String					subject;
	private String					tutorName;
	private Set<IVideoAttachments>	attachmentList		= new LinkedHashSet<IVideoAttachments>(0);
	private String					videoId;

	public Video()
	{
		super();
		this.videoId = getBusinessKey();
	}

	public Video(String videoId)
	{
		super();
		this.videoId = videoId;
	}

	public Video(CreatedModifiedUsers byUser, String description, String displayName, String subject, String tutorName)
	{
		super();
		this.byUser = byUser;
		this.description = description;
		this.displayName = displayName;
		this.subject = subject;
		this.tutorName = tutorName;
	}

	@Transient
	@JsonIgnore
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto();
	}

	public void createdUserAndProducerInfo(Authentication auth)
	{
		this.byUser.setCreatedUser(EAuth.User.getUser(auth));
		this.createdDate = new Timestamp(System.currentTimeMillis());
		this.byUser.setModifiedUser(EAuth.User.getUser(auth));
		this.modifiedDate = new Timestamp(System.currentTimeMillis());
	}

	public CreatedModifiedUsers getByUser()
	{
		return byUser;
	}

	@Id
	@Column(name = "videoId", nullable = false)
	public String getVideoId()
	{
		return videoId;
	}

	public void setVideoId(String videoId)
	{
		this.videoId = videoId;
	}

	@Override
	@Transient
	@JsonIgnore
	public String getCountryTimeZone()
	{
		return this.byUser.getCountryId();
	}

	@Column(name = "description")
	public String getDescription()
	{
		return description;
	}

	@Column(name = "displayName")
	public String getDisplayName()
	{
		return displayName;
	}

	@Column(name = "subject")
	public String getSubject()
	{
		return subject;
	}

	@Column(name = "tutorName")
	public String getTutorName()
	{
		return tutorName;
	}

	public void modifiedUserInfo(Authentication auth)
	{
		this.byUser.setModifiedUser(EAuth.User.getUser(auth));
		this.modifiedDate = new Timestamp(System.currentTimeMillis());
	}

	public void setByUser(CreatedModifiedUsers byUser)
	{
		this.byUser = byUser;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public void setTutorName(String tutorName)
	{
		this.tutorName = tutorName;
	}

	public void updateDisplayInfoOfProducersAndDateTime()
	{
		createdDateByTimeZone();
		modifiedDateByTimeZone();
	}

	@OneToMany(targetEntity = VideoAttachments.class, fetch = FetchType.EAGER, mappedBy = "video", cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@OrderBy("autoId ASC")
	// @JsonIgnore
	public Set<IVideoAttachments> getAttachmentList()
	{
		return attachmentList;
	}

	public Set<IVideoAttachments> getAttachmentList(long autoId)
	{
		Set<IVideoAttachments> selectedList = new LinkedHashSet<IVideoAttachments>(0);

		for (IVideoAttachments _VA : attachmentList)
		{
			if (_VA.getAutoId() == autoId)
				selectedList.add(_VA);
		}
		
		if (autoId == 0)
			return attachmentList;
		return selectedList;
	}

	public void setAttachmentList(Set<IVideoAttachments> attachmentList)
	{
		this.attachmentList = attachmentList;
	}

}

package org.hbs.edutel.model;

import java.io.Serializable;

public class Meta implements Serializable
{
	private static final long	serialVersionUID	= -8132760248886817629L;
	public int					page				= 0;
	public int					pages				= 0;
	public int					perpage				= 0;
	public long					total				= 0;
	public String				sort				= "desc";
	public String				field				= "modifiedDate";
	public String				query				= "";
}
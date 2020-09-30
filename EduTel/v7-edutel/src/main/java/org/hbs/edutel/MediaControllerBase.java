package org.hbs.edutel;

import org.hbs.edutel.bo.VideoBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class MediaControllerBase
{

	@Value("${server.temp.directory}")
	protected String	serverTempDirectory;
	
	@Value("${server.edutel.video.directory}")
	protected String	serverVideoDirectory;
	
	@Autowired
	protected VideoBo	videoBo;

	public MediaControllerBase()
	{
		super();
	}

}
package org.hbs.security.resource;

import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.IConstProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer, IConstProperty
{

	private static final long	serialVersionUID	= -7514925071729240560L;

	@Value("${application.physical.paths}")
	private String				applicationPhysicalPaths;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		if (CommonValidator.isNotNullNotEmpty(applicationPhysicalPaths) && applicationPhysicalPaths.contains(HASH))
		{
			String[] resource = null;
			for (String resources : applicationPhysicalPaths.split(COMMA_SPACE.trim()))
			{
				resource = resources.split(HASH);
				registry.addResourceHandler(SLASH + resource[0].trim() + SLASH_STARS).addResourceLocations(resource[1].trim());
			}
		}
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}

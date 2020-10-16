package org.hbs.security.resource;

import java.util.Locale;

import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.IConstProperty;
import org.hbs.edutel.beans.path.IPathEduTel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer, IConstProperty, IPathEduTel
{

	private static final long	serialVersionUID	= -7514925071729240560L;

	@Value("${application.physical.paths}")
	private String				applicationPhysicalPaths;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		// Adding Base Folder
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

		if (CommonValidator.isNotNullNotEmpty(applicationPhysicalPaths) && applicationPhysicalPaths.contains(HASH))
		{
			String[] resource = null;
			for (String resources : applicationPhysicalPaths.split(COMMA_SPACE.trim()))
			{
				resource = resources.split(HASH);
				registry.addResourceHandler(SLASH + resource[0].trim() + SLASH_STARS).addResourceLocations(resource[1].trim());
			}
		}
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
	{
		configurer.enable();
	}

	@Bean
	public ViewResolver getViewResolver()
	{
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(TEMPLATES_PREFIX);
		resolver.setSuffix(TEMPLATES_SUFFIX);

		return resolver;
	}

	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers("/resources/**", "/videos/**", "/resources/assets/**");
	}

	@Bean
	public LocaleResolver localeResolver()
	{
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>I am LocaleResolver <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor()
	{
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setIgnoreInvalidLocale(true);
		lci.setParamName("lang");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>I am LocaleChangeInterceptor <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(localeChangeInterceptor());
	}

	
	@Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        source.setCacheSeconds(3600); // Refresh cache once per hour.
        return source;
    }
}

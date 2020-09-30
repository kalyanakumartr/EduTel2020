package org.hbs.edutel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.hbs.core.util.CommonValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataTable implements Serializable
{

	private static final long				serialVersionUID	= 3173751830477365811L;
	private HttpServletRequest				request;

	@JsonIgnore
	public Pageable							pageable;
	public Meta								meta;
	public List<Object>						data				= new ArrayList<Object>();

	@JsonIgnore
	public LinkedHashMap<String, Object>	searchValueMap		= new LinkedHashMap<String, Object>();

	public DataTable(HttpServletRequest request)
	{
		this.request = request;
		this.meta = new Meta();
	}

	public DataTable build()
	{

		// Page
		Object object = request.getParameter("pagination[page]");

		if (CommonValidator.isNotNullNotEmpty(object))
		{
			meta.page = Integer.parseInt(object.toString());
			System.out.println(">>>>>>>>>page>>>>>>>>>> " + meta.page);
		}

		// Per Page
		object = request.getParameter("pagination[perpage]");

		if (CommonValidator.isNotNullNotEmpty(object))
		{
			meta.perpage = Integer.parseInt(object.toString());
			System.out.println(">>>>>>>>>perpage>>>>>>>>>> " + meta.perpage);
		}

		// No Of Pages
		object = request.getParameter("pagination[pages]");

		if (CommonValidator.isNotNullNotEmpty(object))
		{
			meta.pages = Integer.parseInt(object.toString());
			System.out.println(">>>>>>>>>pages>>>>>>>>>> " + meta.pages);
		}

		// Sort
		object = request.getParameter("sort[sort]");

		if (CommonValidator.isNotNullNotEmpty(object))
		{
			meta.sort = object.toString();
			System.out.println(">>>>>>>>>sort>>>>>>>>>> " + meta.sort);
		}

		// Sorting Field
		object = request.getParameter("sort[field]");

		if (CommonValidator.isNotNullNotEmpty(object))
			meta.field = object.toString();

		// Search Query
		object = request.getParameter("query[generalSearch]");

		if (CommonValidator.isNotNullNotEmpty(object))
			meta.query = object.toString();

		// Custom Parameter
		if (CommonValidator.isNotNullNotEmpty(request.getParameter("searchCriteria")))
		{
			searchValueMap = new LinkedHashMap<String, Object>();
			ObjectMapper mapper = new ObjectMapper();
			try
			{
				searchValueMap = mapper.readValue(new String(Base64.decodeBase64(request.getParameter("searchCriteria"))), new TypeReference<Map<String, Object>>() {
				});
			}
			catch (Exception excep)
			{
				excep.printStackTrace();
			}
		}

		this.pageable = new Pageable() {

			Direction direction = (CommonValidator.isEqual(meta.sort, Sort.Direction.DESC.name()) ? Sort.Direction.DESC : Sort.Direction.ASC);

			@Override
			public Pageable previousOrFirst()
			{
				return PageRequest.of(meta.page, meta.perpage, Sort.by(direction, meta.field));
			}

			@Override
			public Pageable next()
			{
				return PageRequest.of(meta.page, meta.perpage, Sort.by(direction, meta.field));
			}

			@Override
			public boolean hasPrevious()
			{
				return meta.page > 0;
			}

			@Override
			public Sort getSort()
			{
				return Sort.by(direction, meta.field);
			}

			@Override
			public int getPageSize()
			{
				return meta.perpage;
			}

			@Override
			public int getPageNumber()
			{
				return meta.page;
			}

			@Override
			public long getOffset()
			{
				return (((meta.page - 1) * meta.perpage ));
			}

			@Override
			public Pageable first()
			{
				return PageRequest.of(0, meta.perpage, Sort.by(direction, meta.field));
			}
		};

		return this;
	}
}

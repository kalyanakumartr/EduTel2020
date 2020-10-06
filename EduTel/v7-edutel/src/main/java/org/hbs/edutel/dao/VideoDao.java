package org.hbs.edutel.dao;

import java.util.List;

import org.hbs.edutel.model.Video;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoDao extends PagingAndSortingRepository<Video, String>
{

	@Query("select V FROM Video V WHERE 1=1 AND (V.tutorName Like %:searchParam% OR V.subject Like %:searchParam% OR V.displayName Like %:searchParam% OR V.byUser.modifiedUser.userName Like %:searchParam% )")
	List<Video> getVideo(@Param("searchParam") String searchParam);
	
	@Query("select Count(V) FROM Video V WHERE 1=1 AND (V.tutorName Like %:searchParam% OR V.subject Like %:searchParam% OR V.displayName Like %:searchParam% OR V.byUser.modifiedUser.userName Like %:searchParam% )")
	Long getVideoCount(@Param("searchParam") String searchParam);

	@Query("select V FROM Video V WHERE 1=1 AND (V.tutorName Like %:searchParam% OR V.subject Like %:searchParam% OR V.displayName Like %:searchParam% OR V.byUser.modifiedUser.userName Like %:searchParam% )")
	List<Video> getVideoList(@Param("searchParam") String searchParam,  Pageable pageable);

	@Query("select V.displayName FROM Video V Where 1=1 AND V.displayName = :displayName AND V.subject = :subject")
	List<String> checkVideoName(@Param("displayName") String displayName, @Param("subject") String subject);
	
	@Query("select V FROM Video V WHERE V.status=true Order By V.modifiedDate Desc")
	List<Video> getVideoList();

}

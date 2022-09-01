package collectNews.devwonny.mapper;

import collectNews.devwonny.domain.site.Site;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SiteMapper {

    void save(Site item);

    void update(@Param("siteId") Long siteId, @Param("updateParam") Site updateParam);

    Optional<Site> findById(Long siteId);

    Optional<Site> findByName(String siteName);

    List<Site> findAll();
}

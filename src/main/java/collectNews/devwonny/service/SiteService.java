package collectNews.devwonny.service;

import collectNews.devwonny.domain.site.Site;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface SiteService {

    Site save(Site item);

    void update(Long siteId, Site updateParam);

    Optional<Site> findById(Long siteId);

    Optional<Site> findByName(String siteName);

    List<Site> findAll();

    int delete(long siteId);
}

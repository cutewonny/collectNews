package collectNews.devwonny.domain.site;

import collectNews.devwonny.domain.keyword.Keyword;
import collectNews.devwonny.domain.keyword.KeywordUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface SiteRepository {

    Site save(Site item);

    void update(Long siteId, Site updateParam);

    Optional<Site> findById(Long siteId);

    Optional<Site> findByName(String siteName);

    List<Site> findAll();
}

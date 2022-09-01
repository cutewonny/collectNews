package collectNews.devwonny.mybatis;

import collectNews.devwonny.domain.site.Site;
import collectNews.devwonny.domain.site.SiteRepository;
import collectNews.devwonny.mapper.SiteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisSiteRepository implements SiteRepository {

    private final SiteMapper siteMapper;

    @Override
    public Site save(Site item) {

        siteMapper.save(item);
        return item;
    }

    @Override
    public void update(Long siteId, Site updateParam) {
        siteMapper.update(siteId, updateParam);
    }

    @Override
    public Optional<Site> findById(Long siteId) {

        return siteMapper.findById(siteId);
    }

    @Override
    public Optional<Site> findByName(String siteName) {

        return siteMapper.findByName(siteName);
    }

    @Override
    public List<Site> findAll() {

        return siteMapper.findAll();
    }
}

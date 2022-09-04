package collectNews.devwonny.service;

import collectNews.devwonny.domain.site.Site;
import collectNews.devwonny.domain.site.SiteRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SiteServiceImpl implements SiteService{


    private final SiteRepository siteRepository;

    @Override
    public Site save(Site item) {
        return siteRepository.save(item);
    }

    @Override
    public void update(Long siteId, Site updateParam) {
        siteRepository.update(siteId, updateParam);
    }

    @Override
    public Optional<Site> findById(Long siteId) {
        return siteRepository.findById(siteId);
    }

    @Override
    public Optional<Site> findByName(String siteName) {
        return siteRepository.findByName(siteName);
    }

    @Override
    public List<Site> findAll() {
        return siteRepository.findAll();
    }

    @Override
    public int delete(long siteId) {
        return siteRepository.delete(siteId);
    }
}

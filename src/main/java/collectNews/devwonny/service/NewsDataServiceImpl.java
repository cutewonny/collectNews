package collectNews.devwonny.service;

import collectNews.devwonny.domain.newsData.NewsData;
import collectNews.devwonny.domain.newsData.NewsDataRepository;
import collectNews.devwonny.domain.newsData.NewsDataResult;
import collectNews.devwonny.mapper.NewsDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsDataServiceImpl implements NewsDataService{

    private final NewsDataRepository newsDataRepository;

    @Override
    public NewsData save(NewsData newsData) {
        return newsDataRepository.save(newsData);
    }

    @Override
    public void update(Long newsDataCd, NewsData updateParam) {
        newsDataRepository.update(newsDataCd, updateParam);
    }

    @Override
    public Optional<NewsData> findByNewsDataCd(Long newsDataCd) {
        return newsDataRepository.findByNewsDataCd(newsDataCd);
    }

    @Override
    public Optional<NewsData> findByKeywordId(String keywordId) {
        return newsDataRepository.findByKeywordId(keywordId);
    }

    @Override
    public List<NewsDataResult> findAll() {
        return newsDataRepository.findAll();
    }
}

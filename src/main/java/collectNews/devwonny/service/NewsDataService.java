package collectNews.devwonny.service;

import collectNews.devwonny.domain.newsData.NewsData;

import java.util.List;
import java.util.Optional;

public interface NewsDataService {

    NewsData save(NewsData newsData);

    void update(Long newsDataCd, NewsData updateParam);

    Optional<NewsData> findByNewsDataCd(Long newsDataCd);

    Optional<NewsData> findByKeywordId(String keywordId);

    List<NewsData> findAll();
}

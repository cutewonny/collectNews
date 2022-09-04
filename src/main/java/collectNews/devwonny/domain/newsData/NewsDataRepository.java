package collectNews.devwonny.domain.newsData;

import collectNews.devwonny.domain.keyword.Keyword;
import collectNews.devwonny.domain.keyword.KeywordUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface NewsDataRepository {

    NewsData save(NewsData newsData);

    void update(Long newsDataCd, NewsData updateParam);

    Optional<NewsData> findByNewsDataCd(Long newsDataCd);

    Optional<NewsData> findByKeywordId(String keywordId);

    List<NewsDataResult> findAll();

}

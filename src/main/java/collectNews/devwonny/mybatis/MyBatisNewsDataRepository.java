package collectNews.devwonny.mybatis;

import collectNews.devwonny.domain.newsData.NewsData;
import collectNews.devwonny.domain.newsData.NewsDataRepository;
import collectNews.devwonny.mapper.NewsDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisNewsDataRepository implements NewsDataRepository {

    private final NewsDataMapper newsDataMapper;

    @Override
    public NewsData save(NewsData newsData) {
        newsDataMapper.save(newsData);
        return  newsData;
    }

    @Override
    public void update(Long newsDataCd, NewsData updateParam) {
        newsDataMapper.update(newsDataCd, updateParam);
    }

    @Override
    public Optional<NewsData> findByNewsDataCd(Long newsDataCd) {
        return newsDataMapper.findById(newsDataCd);
    }

    @Override
    public Optional<NewsData> findByKeywordId(String keywordId) {

        return newsDataMapper.findByKeywordId(keywordId);
    }

    @Override
    public List<NewsData> findAll() {

        return newsDataMapper.findAll();
    }
}

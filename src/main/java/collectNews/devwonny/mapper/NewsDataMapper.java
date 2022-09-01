package collectNews.devwonny.mapper;

import collectNews.devwonny.domain.newsData.NewsData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NewsDataMapper {

    void save(NewsData newsData);

    void update(@Param("newsDataCd") Long newsDataCd, NewsData updateParam);

    Optional<NewsData> findById(Long newsDataCd);

    Optional<NewsData> findByKeywordId(String keywordId);

    List<NewsData> findAll();

}

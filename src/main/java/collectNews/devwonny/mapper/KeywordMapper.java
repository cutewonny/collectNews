package collectNews.devwonny.mapper;

import collectNews.devwonny.domain.keyword.Keyword;
import collectNews.devwonny.domain.keyword.KeywordUpdateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;


@Mapper
public interface KeywordMapper {

    void save(Keyword item);

    void update(@Param("keywordId") Long keywordId, @Param("updateParam") KeywordUpdateDTO updateParam);

    Optional<Keyword> findById(Long keywordId);

    Optional<Keyword> findByKeyword(String keywordName);

    List<Keyword> findAll();

}

package collectNews.devwonny.domain.keyword;

import collectNews.devwonny.domain.keyword.Keyword;
import collectNews.devwonny.domain.keyword.KeywordSearchCond;
import collectNews.devwonny.domain.keyword.KeywordUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface KeywordRepository {


    Keyword save(Keyword item);

    void update(Long keywordId, Keyword updateParam);

    Optional<Keyword> findById(Long keywordId);

    Optional<Keyword> findByKeyword(String keywordName);

    List<Keyword> findAll();

    int delete(Long itemId);

}

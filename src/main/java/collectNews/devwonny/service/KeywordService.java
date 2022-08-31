package collectNews.devwonny.service;

import collectNews.devwonny.domain.keyword.Keyword;
import collectNews.devwonny.domain.keyword.KeywordSearchCond;
import collectNews.devwonny.domain.keyword.KeywordUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface KeywordService {

    Keyword save(Keyword item);

    void update(Long keywordId, KeywordUpdateDTO updateParam);

    Optional<Keyword> findById(Long keywordId);

    Optional<Keyword> findByKeyword(String keywordName);

    List<Keyword> findAll();
}
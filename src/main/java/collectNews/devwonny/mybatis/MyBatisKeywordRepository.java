package collectNews.devwonny.mybatis;

import collectNews.devwonny.domain.keyword.Keyword;
import collectNews.devwonny.domain.keyword.KeywordUpdateDTO;
import collectNews.devwonny.mapper.KeywordMapper;
import collectNews.devwonny.domain.keyword.KeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisKeywordRepository implements KeywordRepository {

    private final KeywordMapper keywordMapper;

    @Override
    public Keyword save(Keyword item) {
        log.warn("KeywordMapper class={}>>>>  item>>>>{}>>>", keywordMapper.getClass(), item);
        keywordMapper.save(item);
        return item;
    }

    @Override
    public void update(Long keywordId, KeywordUpdateDTO updateParam) {
        keywordMapper.update(keywordId, updateParam);
    }

    @Override
    public Optional<Keyword> findById(Long keywordId) {
        return keywordMapper.findById(keywordId);
    }

    @Override
    public Optional<Keyword> findByKeyword(String keywordName) {
        return keywordMapper.findByKeyword(keywordName);
    }

    @Override
    public List<Keyword> findAll() {
        return keywordMapper.findAll();
    }
}
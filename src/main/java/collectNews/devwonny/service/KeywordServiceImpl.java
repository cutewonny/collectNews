package collectNews.devwonny.service;

import collectNews.devwonny.domain.keyword.Keyword;
import collectNews.devwonny.domain.keyword.KeywordUpdateDTO;
//import collectNews.devwonny.domain.keyword.KeywordRepository;
import collectNews.devwonny.domain.keyword.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KeywordServiceImpl implements KeywordService {

    private final KeywordRepository keywordRepository;

    @Override
    public Keyword save(Keyword item) {

        return keywordRepository.save(item);
    }

    @Override
    public void update(Long itemId, Keyword updateParam) {

        keywordRepository.update(itemId, updateParam);
    }

    @Override
    public Optional<Keyword> findById(Long id) {

        return keywordRepository.findById(id);
    }

    @Override
    public Optional<Keyword> findByKeyword(String keywordName) {

        return keywordRepository.findByKeyword(keywordName);
    }

    @Override
    public List<Keyword> findAll() {

        return keywordRepository.findAll();
    }

    public int delete(Long itemId){
        return keywordRepository.delete(itemId);
    }

}

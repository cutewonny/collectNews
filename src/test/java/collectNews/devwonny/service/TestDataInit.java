package collectNews.devwonny.service;

import collectNews.devwonny.domain.keyword.Keyword;
import collectNews.devwonny.domain.keyword.KeywordRepository;
import collectNews.devwonny.domain.member.Member;
import collectNews.devwonny.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final KeywordRepository keywordRepository;
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init(){
        keywordRepository.save(new Keyword(14L, "aaaa",false));
        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test");
        member.setName("주전부리");
        memberRepository.save(member);
    }

}

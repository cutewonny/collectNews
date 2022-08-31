package collectNews.devwonny.config;

import collectNews.devwonny.domain.member.MemberRepository;
import collectNews.devwonny.mapper.KeywordMapper;
import collectNews.devwonny.domain.keyword.KeywordRepository;
import collectNews.devwonny.mapper.MemberMapper;
import collectNews.devwonny.mybatis.MyBatisKeywordRepository;
import collectNews.devwonny.mybatis.MyBatisMemberRepository;
import collectNews.devwonny.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final KeywordMapper keywordMapper;
    private final MemberMapper memberMapper;

    @Bean
    public KeywordService keywordService() {

        return new KeywordServiceImpl(keywordRepository());
    }

    @Bean
    public KeywordRepository keywordRepository() {

        return new MyBatisKeywordRepository(keywordMapper);
    }

    @Bean
    public MemberRepository memberRepository(){

        return new MyBatisMemberRepository(memberMapper);
    }

    @Bean
    public MemberService memberService(){

        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public LoginService loginService(){
        return new LoginServiceImpl(memberRepository());
    }

}

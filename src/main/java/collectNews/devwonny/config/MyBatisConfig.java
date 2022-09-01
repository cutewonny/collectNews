package collectNews.devwonny.config;

import collectNews.devwonny.domain.member.MemberRepository;
import collectNews.devwonny.domain.newsData.NewsDataRepository;
import collectNews.devwonny.domain.site.SiteRepository;
import collectNews.devwonny.mapper.KeywordMapper;
import collectNews.devwonny.domain.keyword.KeywordRepository;
import collectNews.devwonny.mapper.MemberMapper;
import collectNews.devwonny.mapper.NewsDataMapper;
import collectNews.devwonny.mapper.SiteMapper;
import collectNews.devwonny.mybatis.MyBatisKeywordRepository;
import collectNews.devwonny.mybatis.MyBatisMemberRepository;
import collectNews.devwonny.mybatis.MyBatisNewsDataRepository;
import collectNews.devwonny.mybatis.MyBatisSiteRepository;
import collectNews.devwonny.scraping.NewsScrapingJob;
import collectNews.devwonny.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final KeywordMapper keywordMapper;
    private final MemberMapper memberMapper;
    private final NewsDataMapper newsDataMapper;
    private final SiteMapper siteMapper;


    @Bean
    public SiteRepository siteRepository(){
        return new MyBatisSiteRepository(siteMapper);
    }

    @Bean
    public SiteService siteService(){
        return new SiteServiceImpl(siteRepository());
    }

    @Bean
    public NewsDataService newsDataService(){
        return new NewsDataServiceImpl(newsDataRepository());
    }

    @Bean
    public NewsDataRepository newsDataRepository(){
        return new MyBatisNewsDataRepository(newsDataMapper);
    }

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

    @Bean
    public NewsScrapingJob newsScrapingJobCall(){
        return new NewsScrapingJob(keywordRepository(),siteRepository(), newsDataRepository());
    }

}

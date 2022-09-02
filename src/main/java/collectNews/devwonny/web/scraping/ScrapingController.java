package collectNews.devwonny.web.scraping;

import collectNews.devwonny.domain.keyword.Keyword;
import collectNews.devwonny.scraping.NewsScrapingJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/scraping")
public class ScrapingController {


    private final NewsScrapingJob newsScrapingJob;

    @GetMapping
    public void scraping() throws IOException {

        newsScrapingJob.doWork();
    }

    @GetMapping("/test")
    public void testOne() throws IOException {
        Keyword keyword = new Keyword();
        keyword.setKeywordId(1L);
        keyword.setUsingKeyword(true);
        keyword.setKeywordName("코로나");
        newsScrapingJob.doWorkOne(keyword);
    }

}

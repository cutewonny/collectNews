package collectNews.devwonny.scraping;

import collectNews.devwonny.domain.keyword.Keyword;
import collectNews.devwonny.domain.keyword.KeywordRepository;
import collectNews.devwonny.domain.newsData.NewsData;
import collectNews.devwonny.domain.newsData.NewsDataRepository;
import collectNews.devwonny.domain.site.Site;
import collectNews.devwonny.domain.site.SiteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsScrapingJob {
    private static final int socketTimeout = 60000;

    private final KeywordRepository keywordRepository;
    private final SiteRepository siteRepository;

    private final NewsDataRepository newsDataRepository;

    public void doWorkOne(Keyword keyword) throws IOException {
        log.error("test >>>>>> keyword>>>>>>>>> {}",keyword);

        //사이트 목록 가져오기
        List<Site> siteTrueList = siteRepository.findByUsingSite(true);

        log.info("siteTrueList>>>>>>>>>>> {}",siteTrueList);



//        Site site = new Site();
//        site.setSiteName("naver");
//        site.setSiteUrl("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=");


        for(Site site : siteTrueList){

            List<NewsData> targets = null;

            if(site.getSiteName().equals("naver")){
                targets = parseNaver(keyword, site);


            }else if(site.getSiteName().equals("daum")){
                targets = parseDaum(keyword, site);

            }

            // 파싱한 데이터 저장하기

            for(NewsData target: targets){
                target.setKeywordId(keyword.getKeywordId());
                target.setSiteId(site.getSiteId());

                if(target!= null){
                    newsDataRepository.save(target);
                }

            }

            log.info("test >>>>>> keyword>>>>>>>>> done");
        }

    }

    public List<Keyword> doWork() throws IOException {

        List<Keyword> keywords = keywordRepository.findAll().stream()
                .filter(k->k.getUsingKeyword().booleanValue()==true)
                .collect(Collectors.toList());

        List<Site> sites = siteRepository.findAll().stream()
                .filter(s-> s.getUsingSite().booleanValue()==true)
                .collect(Collectors.toList());


        log.error("keywords>>>>>>>>> "+keywords);
        log.error("sites>>>>>>>>> "+sites);

        if(keywords != null){
            for(Keyword keyword : keywords){

                log.info("scraping start:: keyword={}", keyword.getKeywordName());

                if(sites !=null){
                    for(Site site : sites){
                        log.info("scraping start:: site={}", site.getSiteName());
                        if(site.getSiteName().equals("naver")){
                            parseNaver(keyword, site);
                        }
                    }
                }

            }
        }
        return keywords;
    }


    private List<NewsData> parseDaum(Keyword keyword, Site site) throws IOException {

        List<NewsData> resultList = new ArrayList<NewsData>();

        String resultUrl = site.getSiteUrl() +keyword.getKeywordName();

        URI uri = UriComponentsBuilder.fromUriString(resultUrl).build().encode().toUri();
        Document doc = null;
        String body = "";

        doc = Jsoup.connect(uri.toString())
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36")
                .timeout(socketTimeout).get();

        Elements list_news = doc.getElementsByClass("list_news");
//        log.info("list_news>> {}",list_news);//ok

        Elements wrap_cont = list_news.get(0).getElementsByClass("wrap_cont");
//        log.info("wrap_cont>> {}",wrap_cont);//ok

        Iterator<Element> aTag = wrap_cont.select("a[class=tit_main fn_tit_u]").iterator();
        Iterator<Element> pDesc = wrap_cont.select("p[class=desc]").iterator();
        //desc

        while (aTag.hasNext()){
            NewsData result = new NewsData();
            Element tempEl = aTag.next();
            String title = tempEl.text();
            result.setMediaTitle(title);
            log.error("title>>>>>>>>> {}", title);

            String href = tempEl.attr("href");
            result.setMediaUrl(href);
            log.error("href>>>>> {}",href);


            String desc = pDesc.next().text();
            log.error("desc>>>>>>>>>>>>>>>> {}",desc);
            result.setMediaContent(desc);
            resultList.add(result);
        }

//        for(Element a: aTag){
//            String title = a.text();
//            String href = a.attr("href");
//            log.info("title={}, href={}", title, href);
//        }



//        Elements script = doc.select("div #newsColl > div .cont_divider");
//        log.info("scirpt>>> {}",script);//작동 안함

//        Element newsColl = doc.getElementById("newsColl");
//        Elements cont_divider = newsColl.getElementsByClass("cont_divider");
//        log.info("newsColl>>>>>>>> {}, cont_divider>>>>>>>> {}", newsColl,cont_divider);//작동함

//        Elements a = list_news.get(0).getElementsByTag("a").select(".tit_main fn_tit_u");
//        log.info("a>>{}",a);//작동 안함

//        Elements title = list_news.get(0).select("a.tit_main fn_tit_u");
//        log.info("title>>>>> {}", title);

//        Elements tit_main = doc.select("a.tit_main fn_tit_u");
//        log.info("tit_main fn_tit_u>>>{}", tit_main);


        return resultList;
    }

    private List<NewsData> parseNaver(Keyword keyword, Site site) throws IOException {

        List<NewsData> resultList = new ArrayList<NewsData>();

        String resultUrl = site.getSiteUrl() +keyword.getKeywordName();
//        log.info("resultUrl>>>>{}",resultUrl);

        URI uri = UriComponentsBuilder.fromUriString(resultUrl).build().encode().toUri();
        Document doc = null;
        String body = "";


        doc = Jsoup.connect(uri.toString())
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36")
                .timeout(socketTimeout).get();// 영상이 안보임

        Elements script = doc.select("div.group_news");


//        Elements temp = script.select(".news_area > a.news_tit");
//        Elements temp2 = script.select(".news_area > div.news_dsc > div.dsc_wrap > a");


        //제목, href
        Iterator<Element> temp = script.select(".news_area > a.news_tit").iterator();
        Iterator<Element> temp2 = script.select(".news_area > div.news_dsc > div.dsc_wrap > a").iterator();

        while (temp.hasNext()){
        NewsData result = new NewsData();
        Element tempEl = temp.next();
            String title = tempEl.text();
            result.setMediaTitle(title);
//            log.error("title>>>>>>>>> {}", title);

            String href = tempEl.attr("href");
            result.setMediaUrl(href);
//            log.error("href>>>>> {}",href);


            String desc = temp2.next().text();
//            log.error("desc>>>>>>>>>>>>>>>> {}",desc);
            result.setMediaContent(desc);
            resultList.add(result);
        }


//        for(Element t: temp){
//            NewsData result = new NewsData();
//            log.info(t.text());
//            result.setMediaTitle(t.text());
//            String href = t.attr("href");
//            log.info(href);
//            result.setMediaUrl(href);
//
//            resultList.add(result);
//        }

//        //기사 내용
//        for(Element t2: temp2){
//            log.info(t2.text());
//            result.setMediaContent(t2.text());
//        }

//        log.info("resultList>>>>>>>>>>>>>>>{}",resultList);

        return resultList;
    }



}

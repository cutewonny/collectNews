package collectNews.devwonny.web.newsData;

import collectNews.devwonny.domain.newsData.NewsData;
import collectNews.devwonny.domain.newsData.NewsDataRepository;
import collectNews.devwonny.domain.newsData.NewsDataResult;
import collectNews.devwonny.service.NewsDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/newsData")
@RequiredArgsConstructor
public class newsDataController {

    private final NewsDataService newsDataService;

    private final NewsDataRepository newsDataRepository;


    @GetMapping
    public String newsDatas(Model model){
        List<NewsDataResult> newsDatas = newsDataService.findAll();
        model.addAttribute("newsDatas", newsDatas);
        return "newsdatas/newsdatas";
    }

    @ResponseBody
    @PostMapping("/add")
    public NewsData addNewsData(@ModelAttribute("newsData") NewsData item, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return null;
        }

        NewsData savedNewsData = new NewsData();
        savedNewsData.setKeywordId(item.getKeywordId());
        savedNewsData.setSiteId(item.getSiteId());
        savedNewsData.setMediaTitle(item.getMediaTitle());
        savedNewsData.setMediaUrl(item.getMediaUrl());
        savedNewsData.setMediaContent(item.getMediaContent());

        NewsData doneNewsData = newsDataRepository.save(savedNewsData);
        return doneNewsData;
    }
}

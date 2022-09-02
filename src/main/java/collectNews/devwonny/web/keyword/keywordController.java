package collectNews.devwonny.web.keyword;

import collectNews.devwonny.domain.keyword.Keyword;
import collectNews.devwonny.domain.keyword.KeywordRepository;
import collectNews.devwonny.scraping.NewsScrapingJob;
import collectNews.devwonny.service.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Slf4j
@Controller
@RequestMapping("/keywords")
@RequiredArgsConstructor
public class keywordController {

    private final KeywordService keywordService;
//    private final KeywordRepository keywordRepository;
    private final NewsScrapingJob newsScrapingJob;

    @GetMapping
    public String items(Model model) {
//        Optional<Keyword> items = keywordService.findById(Long.valueOf(1));
        List<Keyword> items = keywordService.findAll();
        log.warn(String.valueOf(items));
        model.addAttribute("items", items);
        return "items/items";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Keyword());
        return "items/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") Keyword form, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

//        //특정 필드 예외가 아닌 전체 예외
//        if (form.getPrice() != null && form.getQuantity() != null) {
//            int resultPrice = form.getPrice() * form.getQuantity();
//            if (resultPrice < 10000) {
//                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
//            }
//        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "items/addForm";
        }
        //성공 로직
        Keyword item = new Keyword();
        item.setKeywordName(form.getKeywordName());
        item.setUsingKeyword(true);
        log.warn(">>>>>>>>>>>> "+form.getKeywordName());

        Keyword savedItem = keywordService.save(item);
//        if (!savedItem.isPresent()) {
//            bindingResult.reject("loginFail", "키워드가 중복됩니다.");
//            return "items/addForm";
//        }
        Optional<Keyword> findKeyword = keywordService.findByKeyword(savedItem.getKeywordName());
        log.warn("findKeyword>>>>>>>{}",findKeyword);

        newsScrapingJob.doWorkOne(findKeyword.get());
//        redirectAttributes.addAttribute("itemId", savedItem.getId());
//        redirectAttributes.addAttribute("status", true);
//        return "redirect:/items/{itemId}";
        return "redirect:/keywords";
    }
}

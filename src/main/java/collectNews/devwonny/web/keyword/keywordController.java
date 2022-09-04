package collectNews.devwonny.web.keyword;

import collectNews.devwonny.domain.keyword.Keyword;
import collectNews.devwonny.domain.keyword.KeywordUpdateDTO;
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

import javax.servlet.http.HttpServletResponse;
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


    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        //로그인 여부 체크
        Optional<Keyword> item = keywordService.findById(itemId);
        log.info("item>>>>> {}",item);
        model.addAttribute("item", item.get());
        return "items/item";
    }

//    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
    @DeleteMapping("/{itemId}")
    public String delete(@PathVariable("itemId") long itemId, Model model){

        int wasOk = keywordService.delete(itemId);
        log.info("delete>>>>>>> itemId>>{}",itemId);
        if (wasOk!=1) {
            return "error/error";
        }
        return "redirect:/keywords";
    }


    @PostMapping("/delete")
    public String deletePost(@RequestParam final Long id) {
        keywordService.delete(id);
        return "redirect:/keywords";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Optional<Keyword> item = keywordService.findById(itemId);
        model.addAttribute("item", item.get());
        return "items/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("item") Keyword form, BindingResult bindingResult) {

        String keyName = form.getKeywordName().trim();
        if(keyName.isBlank()){
            bindingResult.reject("keywordNameEmpty", "키워드 이름이 공백입니다.");
            return "items/editForm";
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "items/editForm";
        }

        Keyword itemParam = new Keyword();
        itemParam.setKeywordName(keyName);
        itemParam.setUsingKeyword(form.getUsingKeyword());

        keywordService.update(itemId, itemParam);
        return "redirect:/keywords/{itemId}";
    }


    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Keyword());
        return "items/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") Keyword form, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        String keyName = form.getKeywordName().trim();
        if(keyName.isBlank()){
            bindingResult.reject("keywordNameEmpty", "키워드 이름이 공백입니다.");
            return "items/addForm";
        }


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

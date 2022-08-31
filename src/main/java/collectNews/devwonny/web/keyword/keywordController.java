package collectNews.devwonny.web.keyword;

import collectNews.devwonny.domain.keyword.Keyword;
import collectNews.devwonny.domain.keyword.KeywordRepository;
import collectNews.devwonny.service.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/keywords")
@RequiredArgsConstructor
public class keywordController {

    private final KeywordService keywordService;
    private final KeywordRepository keywordRepository;

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
    public String addItem(@Validated @ModelAttribute("item") Keyword form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

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

        Keyword savedItem = keywordRepository.save(item);
        log.warn("save~~ "+savedItem);
//        redirectAttributes.addAttribute("itemId", savedItem.getId());
//        redirectAttributes.addAttribute("status", true);
//        return "redirect:/items/{itemId}";
        return "redirect:/keywords";
    }


}

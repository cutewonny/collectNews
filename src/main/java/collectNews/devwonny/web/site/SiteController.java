package collectNews.devwonny.web.site;

import collectNews.devwonny.domain.keyword.Keyword;
import collectNews.devwonny.domain.site.Site;
import collectNews.devwonny.domain.site.SiteRepository;
import collectNews.devwonny.service.SiteService;
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
@RequestMapping("/site")
@RequiredArgsConstructor
public class SiteController {
    private final SiteService siteService;
    private final SiteRepository siteRepository;

    @GetMapping
    public String sites(Model model){
        List<Site> sites = siteService.findAll();
        log.warn(String.valueOf(sites));
        model.addAttribute("sites", sites);
        return "sites/sites";
    }

    //상세화면
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        //로그인 여부 체크
        Optional<Site> item = siteService.findById(itemId);
        log.info("item>>>>> {}",item);
        model.addAttribute("item", item.get());
        return "sites/site";
    }

    //삭제
    @PostMapping("/delete")
    public String deletePost(@RequestParam final Long id) {
        siteService.delete(id);
        return "redirect:/site";
    }

    //수정 페이지
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Optional<Site> item = siteService.findById(itemId);
        model.addAttribute("item", item.get());
        return "sites/editSite";
    }


    //수정
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("item") Site form, BindingResult bindingResult) {

        String siteName = form.getSiteName().trim();
        if(siteName.isBlank()){
            bindingResult.reject("siteNameEmpty", "사이트 이름이 공백입니다.");
            return "sites/editSite";
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "sites/editForm";
        }

        Site itemParam = new Site();
        itemParam.setSiteName(siteName);
        itemParam.setSiteUrl(form.getSiteUrl());
        itemParam.setUsingSite(form.getUsingSite());

        log.info("itemId>>>{}, itemParam={}",itemId, itemParam);

        siteService.update(itemId, itemParam);
        return "redirect:/site/{itemId}";
    }

    // 추가 페이지
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Site());
        return "sites/addSite";
    }

    //추가
    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") Site form, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        String keyName = form.getSiteName().trim();
        if(keyName.isBlank()){
            bindingResult.reject("keywordNameEmpty", "키워드 이름이 공백입니다.");
            return "sites/addSite";
        }


        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "sites/addSite";
        }
        //성공 로직
        Site item = new Site();
        item.setSiteName(keyName);
        item.setSiteUrl(form.getSiteUrl());
        item.setUsingSite(true);

        Site savedItem = siteService.save(item);

        Optional<Site> findSite = siteService.findByName(savedItem.getSiteName());
        log.warn("findSite>>>>>>>{}",findSite);

//        redirectAttributes.addAttribute("itemId", savedItem.getId());
//        redirectAttributes.addAttribute("status", true);
//        return "redirect:/items/{itemId}";
        return "redirect:/site";
    }

}

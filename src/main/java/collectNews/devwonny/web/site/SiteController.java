package collectNews.devwonny.web.site;

import collectNews.devwonny.domain.site.Site;
import collectNews.devwonny.domain.site.SiteRepository;
import collectNews.devwonny.service.SiteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}

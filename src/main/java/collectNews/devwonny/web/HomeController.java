package collectNews.devwonny.web;

import collectNews.devwonny.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Controller
public class HomeController {

//    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String homeLogin(HttpServletRequest req, Model model){
        HttpSession session = req.getSession();

        if(session==null){
            return "home";
        }

        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        log.error("HomeController   loginMember>>>>>>>>> "+loginMember);

        if(loginMember== null){
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }


    @GetMapping("/error")
    public String error(){
        return "error/error";
    }


}
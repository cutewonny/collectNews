package collectNews.devwonny.web.login;

import collectNews.devwonny.domain.member.Member;
import collectNews.devwonny.service.LoginService;
import collectNews.devwonny.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
//    private final SessionManager sessionManager;


    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }


//    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse res){
//        if(bindingResult.hasErrors()){
//            return "login/loginForm";
//        }
//
//        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
//
//        log.info("login? {}", loginMember);
//
//        if(loginMember == null){
//            bindingResult.reject("loginFail", "아이디 또는 비번이 맞지 않습니다.");
//            return "login/loginForm";
//        }
//
//        HttpSession session = req.getSession();
//        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);//세션저장
//
////        return "redirect:/";
//        return "loginHome";
//    }

    @PostMapping("/login")
    public String loginV3(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/newsData";

    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest req){
        HttpSession session = req.getSession(false);

        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
}

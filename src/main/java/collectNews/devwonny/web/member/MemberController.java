package collectNews.devwonny.web.member;

import collectNews.devwonny.domain.member.Member;
import collectNews.devwonny.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member")Member member){
        //@ModelAttribute : 파라미터 값을 객체로 매핑
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }
        memberRepository.save(member);
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/all")
    public List<Member> showAllMemember(){

        return memberRepository.findAll();
    }

//    @ResponseBody
//    @GetMapping("/{memberId}")
//    public Optional<Member> findById(@PathVariable Long memberId){
//        return memberRepository.findById(memberId);
//    }

    @ResponseBody
    @GetMapping("/{loginId}")
    public Optional<Member> findByLoginId(@PathVariable String loginId){
        return memberRepository.findByLoginId(loginId);
    }

}

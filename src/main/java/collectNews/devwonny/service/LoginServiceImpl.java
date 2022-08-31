package collectNews.devwonny.service;

import collectNews.devwonny.domain.member.Member;
import collectNews.devwonny.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final MemberRepository memberRepository;


    @Override
    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(mem->mem.getPassword().equals(password))
                .orElse(null);
    }
}

package collectNews.devwonny.service;

import collectNews.devwonny.domain.member.Member;
import collectNews.devwonny.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Optional<Member> findMember(Long memberId) {

        return memberRepository.findById(memberId);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}

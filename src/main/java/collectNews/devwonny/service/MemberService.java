package collectNews.devwonny.service;

import collectNews.devwonny.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    void join(Member member);

    Optional<Member> findMember(Long memberId);

    Optional<Member> findByLoginId(String loginId);

    List<Member> findAll();
}

package collectNews.devwonny.domain.member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Optional<Member> findById(Long memberId);

    Optional<Member> findByLoginId(String loginId);

    List<Member> findAll();
}

package collectNews.devwonny.mapper;

import collectNews.devwonny.domain.member.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    void save(Member member);

    Optional<Member> findById(Long memberId);

    Optional<Member> findByLoginId(String loginId);

    List<Member> findAll();
}

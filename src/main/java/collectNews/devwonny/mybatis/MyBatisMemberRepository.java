package collectNews.devwonny.mybatis;

import collectNews.devwonny.domain.member.Member;
import collectNews.devwonny.domain.member.MemberRepository;
import collectNews.devwonny.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisMemberRepository implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public void save(Member member) {
        log.error("MyBatisMemberRepository member>>>>>>>>>"+member);
        memberMapper.save(member);

    }

    @Override
    public Optional<Member> findById(Long memberId) {

        return memberMapper.findById(memberId);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return memberMapper.findByLoginId(loginId);
    }

    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }
}

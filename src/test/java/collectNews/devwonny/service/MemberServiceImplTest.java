package collectNews.devwonny.service;

import collectNews.devwonny.domain.member.Member;
import collectNews.devwonny.domain.member.MemberRepository;
import collectNews.devwonny.mapper.MemberMapper;
import collectNews.devwonny.mybatis.MyBatisMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {

    @Autowired
    MemberMapper memberMapper;
//    MemberRepository memberRepository = new MyBatisMemberRepository(memberMapper);
//
//    MemberService memberService = new MemberServiceImpl(memberRepository);

    @Test
    void join(){

        //given
        Member member = new Member();
        member.setLoginId("wonny");
        member.setName("한정원");
        member.setPassword("asdf");

        //when
//        memberService.join(member);
//        Member findMember = memberService.findMember(1L);
//        memberMapper.save(member);
        System.out.println("mapper done");

    }

}
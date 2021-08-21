package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() {
        //GIVEN
        Member member = new Member();
        member.setName("spring");

        //WHEN
        Long savedId = memberService.join(member);

        //THEN
        Member findMember = memberService.findOne(member).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void join_exception() {
        //GIVEN
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //WHEN
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
    }
}
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //GIVEN
        Member member = new Member();
        member.setName("spring");

        //WHEN
        Long savedId = memberService.join(member);

        //THEN
        assertThat(memberService.findOne(member).isPresent());
    }

    @Test
    void join_exception(){
        //GIVEN
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //WHEN
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, ()->{
           memberService.join(member2);
        });
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
/*

        try {
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
        }
*/

        //THEN
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
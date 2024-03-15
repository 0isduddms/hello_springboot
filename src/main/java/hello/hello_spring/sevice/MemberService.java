package hello.hello_spring.sevice;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
//클래스 내에서 Ctrl+Shift+t를 누르면 테스트 클래스를 만들 수 있음
    private final MemberRepository memberRepository;
//Alt+Ins로 생성자나 getter, setter 삽입 가능
    //한 개의 MemberRepository를 사용하기 위해 외부에서 받아오는 메서드
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
        회원가입
         */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원 X
        validateBuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateBuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /*
    전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}

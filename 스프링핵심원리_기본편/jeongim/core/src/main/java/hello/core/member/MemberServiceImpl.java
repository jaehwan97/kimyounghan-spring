package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    // 이렇게 선언하고 아래의 생성자를 만들면 MemberServiceImpl은 MemberRepository 인터페이스에만 의존하게 되므로 DIP를 지킴
    // 구체적인 구현체를 AppConfig 에서 생성하기 때문에 이게 바로 생성자 주입

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

package hello.core.Discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}

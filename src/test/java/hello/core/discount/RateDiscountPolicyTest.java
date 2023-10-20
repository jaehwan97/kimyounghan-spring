package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy=new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o(){
        Member member =new Member(1L,"memberVIP", Grade.VIP);

        int discount = discountPolicy.discount(member,10000);

        org.assertj.core.api.Assertions.assertThat(discount).isEqualTo(1000);
    }
    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x(){
        Member member =new Member(2L,"memberBASIC", Grade.BASIC);

        int discount = discountPolicy.discount(member,10000);

        org.assertj.core.api.Assertions.assertThat(discount).isEqualTo(1000);
    }

}
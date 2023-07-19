package com.penekhun.afirstcomefirstservedcoupon1.coupon.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CouponTest {

  @Test
  @DisplayName("정적 팩토리 메서드를 통해 모든 값이 정상일때 Coupon 객체를 생성할 수 있다.")
  void can_create_coupon_using_factory_method() {
    // given
    String name = "테스트 쿠폰";
    String description = "테스트 쿠폰입니다.";
    Integer remainCount = 100;

    // when & then
    assertThat(Coupon.createNewCoupon(name, description, remainCount))
        .isInstanceOf(Coupon.class)
        .extracting("name", "description", "remainCount")
        .containsExactly(name, description, remainCount);
  }

  @Test
  @DisplayName("쿠폰의 잔여갯수가 0일 경우 Coupon 객체를 생성할 수 없다.")
  void cant_create_coupon_remain_eq_zero() {
    // given
    Integer remainCount = 0;

    // when & then
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> Coupon.createNewCoupon("테스트 쿠폰", "테스트 쿠폰 입니다.", remainCount),
        "남은 쿠폰의 갯수는 0보다 커야 합니다."
    );
  }

  @Test
  @DisplayName("쿠폰의 잔여갯수가 0 미만일 경우 Coupon 객체를 생성할 수 없다.")
  void cant_create_coupon_remain_under_zero() {
    // given
    Integer remainCount = -1;

    // when & then
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> Coupon.createNewCoupon("테스트 쿠폰", "테스트 쿠폰 입니다.", remainCount),
        "남은 쿠폰의 갯수는 0보다 커야 합니다."
    );
  }
}
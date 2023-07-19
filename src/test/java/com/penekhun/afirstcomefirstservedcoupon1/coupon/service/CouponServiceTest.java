package com.penekhun.afirstcomefirstservedcoupon1.coupon.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CouponServiceTest {

  @Autowired
  private CouponService couponService;

  @Test
  @DisplayName("존재하지 않는 쿠폰 ID를 조회시 에러가 뜬다.")
  void occur_exception_when_not_exist_coupon_id() {
    // given
    UUID notExistCouponId = UUID.randomUUID();

    // when & then
    assertThrows(
        IllegalArgumentException.class,
        () -> couponService.getCoupon(notExistCouponId.toString()),
        "존재하지 않는 쿠폰입니다.");
  }
}
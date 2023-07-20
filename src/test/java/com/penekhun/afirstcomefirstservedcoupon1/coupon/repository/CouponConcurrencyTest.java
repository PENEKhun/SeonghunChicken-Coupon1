package com.penekhun.afirstcomefirstservedcoupon1.coupon.repository;

import static com.penekhun.afirstcomefirstservedcoupon1.coupon.entity.GeneratedCoupon.createGeneratedCoupon;
import static org.assertj.core.api.Assertions.assertThat;

import com.penekhun.afirstcomefirstservedcoupon1.coupon.entity.Coupon;
import com.penekhun.afirstcomefirstservedcoupon1.coupon.service.CouponService;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class CouponConcurrencyTest {

  @Autowired
  CouponService couponService;

  @Autowired
  CouponRepository couponRepository;

  @Autowired
  GeneratedCouponRepository generatedCouponRepository;

  @Test
  @DisplayName("동시성 테스트")
  @RepeatedTest(5)
  void test() throws InterruptedException {
    // given
    int couponQuantity = 100;
    int tryCouponIssue = 160;
    Coupon coupon = couponRepository.saveAndFlush(
        Coupon.createNewCoupon("테스트 쿠폰", "테스트 쿠폰입니다.", couponQuantity)
    );
    UUID couponType = coupon.getId();

    ExecutorService executor = Executors.newFixedThreadPool(32);
    CountDownLatch latch = new CountDownLatch(tryCouponIssue);

    // when
    for (int i = 1; i <= tryCouponIssue; i++) {
      executor.submit(() -> {
        try {
          if (coupon.makeAvailable()) {
            coupon.decreaseRemainCount();
            generatedCouponRepository.save(createGeneratedCoupon(coupon.getId()));
          }
        } finally {
          latch.countDown();
        }
      });
    }
    latch.await();

    // then
    // 쿠폰 발급이 동시성 제어를 통해 올바르게 이루어졌는지 확인합니다.
    assertThat(generatedCouponRepository.countByCouponOriginId(couponType))
        .isEqualTo(couponQuantity);
  }
}
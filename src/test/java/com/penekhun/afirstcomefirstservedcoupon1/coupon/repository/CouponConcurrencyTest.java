package com.penekhun.afirstcomefirstservedcoupon1.coupon.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.penekhun.afirstcomefirstservedcoupon1.coupon.entity.Coupon;
import com.penekhun.afirstcomefirstservedcoupon1.coupon.service.CouponService;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootTest
class CouponConcurrencyTest {

  @Autowired
  CouponService couponService;

  @Autowired
  TransactionTemplate transactionTemplate;

  @Autowired
  CouponRepository couponRepository;

  @Autowired
  GeneratedCouponRepository generatedCouponRepository;

  @RepeatedTest(3)
  @DisplayName("쿠폰은 동시성 제어를 통해 정해진 수량만큼 발급됩니다.")
  void test() throws InterruptedException {
    // given
    int couponQuantity = 30;
    int tryCouponIssue = 50;
    UUID testCouponId = couponRepository.saveAndFlush(
        Coupon.createNewCoupon("테스트 쿠폰", "테스트 쿠폰입니다.", couponQuantity)
    ).getId();

    ExecutorService executor = Executors.newFixedThreadPool(13);
    CountDownLatch latch = new CountDownLatch(tryCouponIssue);

    // when
    for (int i = 1; i <= tryCouponIssue; i++) {
      executor.submit(() -> {
        try {
          // todo : test클래스에 @Transactional을 사용하면 couponIssue 메소드의 findBy가 동작하지 않습니다... 뭐지....?
          couponService.couponIssue(testCouponId.toString());
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          latch.countDown();
        }
      });
    }
    latch.await();

    // then
    // 쿠폰 발급이 동시성 제어를 통해 올바르게 이루어졌는지 확인합니다.
    assertThat(generatedCouponRepository.countByCouponOriginId(testCouponId))
        .isEqualTo(couponQuantity);
  }
}
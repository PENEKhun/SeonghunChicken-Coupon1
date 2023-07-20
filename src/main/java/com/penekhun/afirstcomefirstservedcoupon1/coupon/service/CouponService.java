package com.penekhun.afirstcomefirstservedcoupon1.coupon.service;

import static com.penekhun.afirstcomefirstservedcoupon1.coupon.entity.GeneratedCoupon.createGeneratedCoupon;

import com.penekhun.afirstcomefirstservedcoupon1.coupon.entity.Coupon;
import com.penekhun.afirstcomefirstservedcoupon1.coupon.entity.GeneratedCoupon;
import com.penekhun.afirstcomefirstservedcoupon1.coupon.repository.CouponRepository;
import com.penekhun.afirstcomefirstservedcoupon1.coupon.repository.GeneratedCouponRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CouponService {

  private final CouponRepository couponRepository;
  private final GeneratedCouponRepository generatedCouponRepository;

  public List<Coupon> getCouponList() {
    return couponRepository.findAll();
  }

  public Coupon getCoupon(String uuidStr) {
    return couponRepository.findById(UUID.fromString(uuidStr))
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쿠폰입니다."));
  }

  @Transactional
  public GeneratedCoupon couponIssue(String uuidStr) {
    Coupon coupon = couponRepository.findById(UUID.fromString(uuidStr))
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쿠폰입니다."));

    if (!coupon.makeAvailable()) {
      throw new IllegalArgumentException("선착순 쿠폰이 모두 소진되었습니다.");
    }

    coupon.decreaseRemainCount();
    return generatedCouponRepository.save(createGeneratedCoupon(coupon.getId()));
  }
}

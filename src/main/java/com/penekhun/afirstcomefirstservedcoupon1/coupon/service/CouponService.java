package com.penekhun.afirstcomefirstservedcoupon1.coupon.service;

import com.penekhun.afirstcomefirstservedcoupon1.coupon.entity.Coupon;
import com.penekhun.afirstcomefirstservedcoupon1.coupon.repository.CouponRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {

  private final CouponRepository couponRepository;

  public List<Coupon> getCouponList() {
    return couponRepository.findAll();
  }

  public Coupon getCoupon(String uuidStr) {
    UUID uuid = UUID.fromString(uuidStr);
    return couponRepository.findById(uuid)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쿠폰입니다."));
  }
}

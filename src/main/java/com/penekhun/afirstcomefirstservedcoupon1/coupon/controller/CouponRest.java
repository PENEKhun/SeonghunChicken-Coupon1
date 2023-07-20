package com.penekhun.afirstcomefirstservedcoupon1.coupon.controller;

import com.penekhun.afirstcomefirstservedcoupon1.coupon.entity.GeneratedCoupon;
import com.penekhun.afirstcomefirstservedcoupon1.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CouponRest {

  private final CouponService couponService;

  @GetMapping("/coupon-issue/{couponId}")
  public GeneratedCoupon couponIssue(@PathVariable String couponId) {
    return couponService.couponIssue(couponId);
  }
}

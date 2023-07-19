package com.penekhun.afirstcomefirstservedcoupon1.coupon.controller;

import com.penekhun.afirstcomefirstservedcoupon1.coupon.dto.CouponResponse;
import com.penekhun.afirstcomefirstservedcoupon1.coupon.entity.Coupon;
import com.penekhun.afirstcomefirstservedcoupon1.coupon.service.CouponService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CouponPage {

  private final CouponService couponService;

  @GetMapping("/")
  public String index(Model model) {
    List<CouponResponse> couponResponses = couponService.getCouponList()
        .stream()
        .map(Coupon::toResponse)
        .toList();
    model.addAttribute("couponList", couponResponses);
    return "coupon-list";
  }
}

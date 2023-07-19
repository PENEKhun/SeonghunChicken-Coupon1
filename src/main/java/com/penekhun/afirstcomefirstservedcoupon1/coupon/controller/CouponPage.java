package com.penekhun.afirstcomefirstservedcoupon1.coupon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CouponPage {
  @GetMapping("/")
  public String index() {
    return "index";
  }
}

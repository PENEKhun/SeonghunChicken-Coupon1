package com.penekhun.afirstcomefirstservedcoupon1.coupon.dto;

import lombok.Getter;

@Getter
public class CouponResponse {

  private final String id;
  private final String name;
  private final Integer remainCount;

  public CouponResponse(String id, String name, Integer remainCount) {
    this.id = id;
    this.name = name;
    this.remainCount = remainCount;
  }
}

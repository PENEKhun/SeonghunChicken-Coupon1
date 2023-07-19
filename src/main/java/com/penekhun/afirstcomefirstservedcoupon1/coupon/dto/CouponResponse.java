package com.penekhun.afirstcomefirstservedcoupon1.coupon.dto;

import lombok.Getter;

@Getter
public class CouponResponse {

  private final String id;
  private final String name;
  private final String description;
  private final Integer remainCount;

  public CouponResponse(String id, String name, String description, Integer remainCount) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.remainCount = remainCount;
  }
}

package com.penekhun.afirstcomefirstservedcoupon1.coupon.repository;

import com.penekhun.afirstcomefirstservedcoupon1.coupon.entity.GeneratedCoupon;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneratedCouponRepository extends JpaRepository<GeneratedCoupon, UUID> {

  long countByCouponOriginId(UUID couponOriginId);

}

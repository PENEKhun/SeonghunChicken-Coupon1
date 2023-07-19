package com.penekhun.afirstcomefirstservedcoupon1.coupon.repository;

import com.penekhun.afirstcomefirstservedcoupon1.coupon.entity.Coupon;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, UUID> {

}

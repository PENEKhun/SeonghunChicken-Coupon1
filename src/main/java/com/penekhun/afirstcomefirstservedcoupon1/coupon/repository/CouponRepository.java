package com.penekhun.afirstcomefirstservedcoupon1.coupon.repository;

import com.penekhun.afirstcomefirstservedcoupon1.coupon.entity.Coupon;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface CouponRepository extends JpaRepository<Coupon, UUID> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("select c from Coupon c where c.id = :id")
  Optional<Coupon> findCouponForUpdate(UUID id);

}

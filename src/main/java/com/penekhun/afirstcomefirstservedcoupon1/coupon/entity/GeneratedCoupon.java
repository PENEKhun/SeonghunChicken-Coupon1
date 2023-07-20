package com.penekhun.afirstcomefirstservedcoupon1.coupon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "generated_coupon")
public class GeneratedCoupon {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", nullable = false, columnDefinition = "BINARY(16)")
  private UUID id;

  @Column(name = "coupon_config_id", length = 16)
  private UUID couponOriginId;

  private GeneratedCoupon(UUID id, UUID couponOriginId) {
    this.id = id;
    this.couponOriginId = couponOriginId;
  }

  public static GeneratedCoupon createGeneratedCoupon(UUID couponOriginId) {
    return new GeneratedCoupon(UUID.randomUUID(), couponOriginId);
  }
}
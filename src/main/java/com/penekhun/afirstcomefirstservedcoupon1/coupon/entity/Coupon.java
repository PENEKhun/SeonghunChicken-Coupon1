package com.penekhun.afirstcomefirstservedcoupon1.coupon.entity;

import com.penekhun.afirstcomefirstservedcoupon1.coupon.dto.CouponResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "coupon_config")
public class Coupon {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", nullable = false, columnDefinition = "BINARY(16)", unique = true)
  private UUID id;

  @Column(name = "name", length = 30, columnDefinition = "VARCHAR(30)")
  @Comment("발급될 쿠폰의 이름")
  private String name;

  @Column(name = "description", length = 500, columnDefinition = "VARCHAR(500)")
  @Comment("발급될 쿠폰의 설명")
  private String description;

  @Column(name = "remain_count", nullable = false)
  @Comment("남은 쿠폰의 갯수")
  private Integer remainCount;

  private Coupon(String name, String description, Integer remainCount) {
    this.name = name;
    this.description = description;
    this.remainCount = remainCount;
  }

  public boolean isAvailable() {
    return this.remainCount > 0;
  }

  public void decreaseRemainCount() {
    this.remainCount--;
  }

  public static Coupon createNewCoupon(String name, String description, Integer remainCount) {
    if (remainCount <= 0) {
      throw new IllegalArgumentException("남은 쿠폰의 갯수는 0보다 커야 합니다.");
    }
    return new Coupon(name, description, remainCount);
  }

  public CouponResponse toResponse() {
    return new CouponResponse(this.id.toString(), this.name, this.description, this.remainCount);
  }
}
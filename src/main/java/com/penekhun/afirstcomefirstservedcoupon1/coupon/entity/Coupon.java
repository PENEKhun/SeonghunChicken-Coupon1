package com.penekhun.afirstcomefirstservedcoupon1.coupon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Table(name = "coupon_config")
public class Coupon {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "id", nullable = false, length = 16, columnDefinition = "BINARY(16)")
  private UUID id;

  @Column(name = "name", length = 30, columnDefinition = "VARCHAR(30)")
  @Comment("발급될 쿠폰의 이름 또는 설명")
  private String name;

  @Column(name = "remain_count", nullable = false)
  @Comment("남은 쿠폰의 갯수")
  private Integer remainCount;

  private Coupon(UUID id, String name, Integer remainCount) {
    this.id = id;
    this.name = name;
    this.remainCount = remainCount;
  }

  private Coupon() {
  }

  public static Coupon createNewCoupon(String name, Integer remainCount) {
    if (remainCount <= 0) {
      throw new IllegalArgumentException("남은 쿠폰의 갯수는 0보다 커야 합니다.");
    }
    return new Coupon(UUID.randomUUID(), name, remainCount);
  }
}
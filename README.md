> **선착순 쿠폰 발급** 구현으로 간단히 알아보는 동시성 제어

MySQL의 Lock을 이용한 동시성 제어 예시입니다.  
본 예제는 간단한 HTML 페이지도 포함되어 있습니다.

✏️ Blog Post - [선착순 쿠폰 발급을 구현해보며 배우는 동시성 제어](https://penekhun.github.io/posts/%EC%84%A0%EC%B0%A9%EC%88%9C-%EC%BF%A0%ED%8F%B0-%EB%B0%9C%EA%B8%89%EC%9D%84-%EA%B5%AC%ED%98%84%ED%95%B4%EB%B3%B4%EB%A9%B0-%EB%B0%B0%EC%9A%B0%EB%8A%94-%EB%8F%99%EC%8B%9C%EC%84%B1-%EC%A0%9C%EC%96%B4-(%EB%B9%84%EA%B4%80%EC%A0%81-%EB%9D%BD)/)

# 프로젝트 구조

## 테이블 구성
<img width="332" alt="image" src="https://github.com/PENEKhun/SeonghunChicken-Coupon1/assets/13290706/3a3824a6-eb9b-4604-857f-8dd74b8fe0e0">

## 화면 구성
### 메인 화면
<img width="649" alt="image" src="https://github.com/PENEKhun/SeonghunChicken-Coupon1/assets/13290706/601be5ac-cde6-4722-9661-a1afdf0e3b6e">

### 쿠폰 발급
<img width="713" alt="image" src="https://github.com/PENEKhun/SeonghunChicken-Coupon1/assets/13290706/8d189491-4456-4be4-a9be-ba22092d4946">

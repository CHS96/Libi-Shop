<h1> # Libi Shop #</h1>  
<h3>간단한 쇼핑몰을 구현해보면서 SpringBoot, JPA를 이해하고 BackEnd 역량을 향상시키는 ToyProject </h3>
<br>
<h3>사용 스택</h3>  
IntelliJ, Java, Sptring Boot, JPA, H2 Database, Thymeleaf, Junit5  
<br>
<br>
<h3># Update History</h3>  

* **version 1**
  - Member, Item 구현 : JPA를 배우기 전이기 때문에 Memory로 생성, 다형성을 위해 Interface로 구현했으며, 나중에 JPA로 업그레이드 할 예정
  - 회원가입, 로그인 등 기본적인 MVC 구현
  
* **version 2**
  - Member 등급 생성 : Manager(관리자), User(고객)
  - 메시지, 국제화(KOREA, ENGLISH) 적용 
  - 쿠키, 세션 적용 : 로그인 상태 유지
  - 회원가입 로직 구현, 중복 아이디 검사 적용
  - @Vaild를 통해 검증 적용 - BindingResult로 검증하여 결과를 폼에 뿌려서 Client에게 알려줌
  
* **version 3**
  - 인터셉터 적용(LoginCheck, ManagerCheck) - 권한 없는 Member는 권한이 필요한 URL에 접근 불가
  - 필터 vs 인터셉터 => 웬만하면 인터셉터로 처리!
  - errorPage 등록 - BasicErrorController(404, 4xx, 500)
  - Thymeleaf footer를 적용하여 View 업데이트

* **version 4**
  - package 구조 변경 : config, domain, web => 의존성을 분리하여 수정 용이하도록 설계
  - 각 Domain에 Service 생성
  - 상품 등록 및 수정 MVC 생성
  - 회원 관리 MVC 생성(GRADE.MANAGER만 가능)
  - EntityManger, @Entity, @Transactional 등 기본적인 JPA 학습
  - 초기 데이터 문제 발생 : @PostConstruct vs @Transactional의 문제
  
* **version 5**
  - 장바구니, 상품 결제, 상품 후기 시스템 구현
  - 모든 Domain의 Controller, Service, Repository 분리 : 가독성 향상, 유지보수 용이
  - 설계했던 기본적인 쇼핑몰 기능 구현 완료
  - 메시지큐, 프록시 서버 등 다양한 기술 사용할 예정
  
* **version 6**
  - 상품 리뷰 UD, 게시판 CRUD 구현
  
<br>
<h3># DB 테이블 </h3>

![db 테이블](https://user-images.githubusercontent.com/53072057/130027046-9cc49678-f46d-4fca-a144-faaf12cb0e8d.JPG)  
<br>
<br>
<h3># 메인 페이지</h3>  

![메인페이지](https://user-images.githubusercontent.com/53072057/129016350-ee502013-4fd0-4d49-bba2-6c0eb7350ee5.JPG)  
<br>
<br>
<h3># 회원 가입</h3>  

![회원가입](https://user-images.githubusercontent.com/53072057/129016362-d368af2d-b73e-4acf-848e-678a83ddfc82.JPG)  
<br>
<br>
<h3># 로그인(관리자, 고객)</h3>  

![로그인](https://user-images.githubusercontent.com/53072057/129016344-7d1139e3-0626-4764-9ccd-163ba9228c2c.JPG)  
<br>
<br>
<br>
<h3># 관리자 기능</h3>  

![관리자 권한](https://user-images.githubusercontent.com/53072057/129016341-39bbf60d-0aa4-491a-af92-a61b7a2e51a8.JPG)  
<br>
<br>
<h3># 상품 등록 및 삭제</h3>  

![상품 등록](https://user-images.githubusercontent.com/53072057/129016353-a74e4a25-2ad4-4e6f-93c8-1115cd7003f8.JPG)
<br>
<br>
<h3># 상품 상세 보기 및 수정</h3>  

![상품 상세   수정](https://user-images.githubusercontent.com/53072057/129016356-4e67fe11-8d4d-47da-a75e-37b7f57588f7.JPG)  
<br>
<br>
<br>
<h3># 고객 기능</h3>  

![고객 권한](https://user-images.githubusercontent.com/53072057/129016338-fdda58d8-f583-414c-bb36-1d3150e89199.JPG)  
<br>
<br>
<h3># 장바구니 상품 등록 및 수정</h3>  

![장바구니 등록   수정](https://user-images.githubusercontent.com/53072057/129016360-88b09e24-9262-44ac-8103-ae3af9230010.JPG)
<br>
<br>
<h3># 장바구니 결제</h3>  

![장바구니 결제](https://user-images.githubusercontent.com/53072057/129016359-0994ee12-ebb9-4c08-ab58-b43fe745357e.JPG)  
<br>
<br>
<h3># 리뷰 등록 및 상세 보기</h3>  

![리뷰 등록   보기](https://user-images.githubusercontent.com/53072057/129016347-5efd46ec-5e6c-4faf-9b7b-a3cf2c1a41d9.JPG)  
<br>
<br>
<h3># 리뷰 수정(내가 작성한 글)</h3>  

![상품 후기 수정(내가 작성한 글)](https://user-images.githubusercontent.com/53072057/130030384-885792c2-c123-4dba-a7c0-6c6d8e56ad6a.JPG)  
<br>
<br>
<h3># 게시판 등록 및 상세 보기</h3>  

![게시판 등록 및 상세 보기](https://user-images.githubusercontent.com/53072057/130029246-469d89ae-c442-4264-8539-3d028eb82562.JPG)  
<br>
<br>
<h3># 게시판 수정(내가 작성한 글)</h3>  

![게시판 수정(내가 작성한 글)](https://user-images.githubusercontent.com/53072057/130029242-fc48c920-14b0-49c4-90df-21f4fe283f03.JPG)  
<br>
<br>



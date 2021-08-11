<h1>[ ToyProject for BackEnd Engineer ]</h1>
<br>

**스택** : IntelliJ, Java, Sptring Boot, JPA, Thymeleaf, Junit5  
<br>
**<h3>[ Project Plan ]</h3>**  
* JPA를 활용하여 테이블 설계 및 구현  
* 대용량 트래픽 발생 대비 - 메시지큐, 프록시서버 등 시도할 예정  
<br>

* **version 1**
  - Member, Item Entity 생성 - 아직 JPA를 배우기 전이기 때문에 Memory로 생성, 다형성을 위해 Interface로 구현했으며, 나중에 JPA로 업그레이드 할 예정
  - 회원가입, 로그인 폼 생성
  - 기본적인 MVC 구현
  
* **version 2**
  - Member 등급 생성 - Manager(관리자), User(고객)
  - 메시지, 국제화(KOREA, ENGLISH) 적용
  - 쿠키, 세션 적용 - 로그인 상태 유지
  - 회원가입 로직 구현, 중복 아이디 검사 적용
  - @Vaild를 통해 검증 적용 - BindingResult로 검증하여 결과를 화면에 뿌림
  
* **version 3**
  - 인터셉터 적용(LoginCheck, ManagerCheck) - 권한 없는 Member는 권한이 필요한 URL에 접근 불가
  - 필터 vs 인터셉터 => 웬만하면 인터셉터로 처리!
  - errorPage 등록 - BasicErrorController
  - Thymeleaf footer를 적용하여 View 업데이트

* **version 4**
  - package 구조 변경 : config, domain, web => 의존성을 분리하여 수정 용이하도록 설계
  - Service 생성
  - 상품 등록 및 수정 MVC 생성
  - 회원 관리 MVC 생성(GRADE.MANAGER만 가능)
  
* **version 5**
  - JPA 적용 : MemoryMemberRepository -> JpaMemberRepository
  - EntityManger, @Entity, @Transactional 등 기본적인 JPA 학습
  - 초기 데이터 문제 발생 - @PostConstruct vs @Transactional의 문제였음 => **해결**

#continue
<br>

<h3>메인 페이지</h3>
![메인페이지](https://user-images.githubusercontent.com/53072057/129015666-95d0a96b-3de9-4391-b689-4a3ac737970f.JPG)  

<h3>회원 가입</h3>
![회원가입](https://user-images.githubusercontent.com/53072057/129015674-b7d99985-6293-451e-a43b-c030aebdf23d.JPG)  


<br>

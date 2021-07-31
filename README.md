<h1>[ ToyProject for BackEnd Engineer ]</h1>
<br>

**사용 스택** : IntelliJ, Java, Sptring Boot, JPA, Thymeleaf, Junit5  
<br>
**<h3>[ Project Plan ]</h3>**  
* JPA를 활용하여 테이블 설계 및 구현  
* 대용량 트래픽 발생 대비 - 메시지큐, 프록시서버 등 시도할 예정  
<br>

![myservice01](https://user-images.githubusercontent.com/53072057/127731878-7d83e3c1-92a4-4da9-9f31-eda9d17e3d25.JPG)  
![myservice02](https://user-images.githubusercontent.com/53072057/127731880-d3dfd56c-39c4-48cd-bb6b-93bf29c62abc.JPG)  
![myservice03](https://user-images.githubusercontent.com/53072057/127731883-9baef624-cde2-406f-a2c7-cb50c246e9e0.JPG)  
![myservice04](https://user-images.githubusercontent.com/53072057/127731884-231366dd-3332-4bab-9664-30419fb54756.JPG)  
![myservice05](https://user-images.githubusercontent.com/53072057/127731885-0be22d9c-5b60-45cd-863e-150033e1febc.JPG)  
![myservice06](https://user-images.githubusercontent.com/53072057/127731886-b4c0024a-d14e-41f8-ac34-51e56d3e0a75.JPG)  
![myservice07](https://user-images.githubusercontent.com/53072057/127731887-398bd784-0fb0-4fa4-935e-dff915070914.JPG)  
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

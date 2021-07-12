# 무신사 BackEnd 과제 (상품 카테고리를 구현하세요.)

## 필요사항
1. Java1.8
2. Gradle
3. MySql5

## 빌드
* Gradle 을 통한 빌드 (./gradlew build)

## 실행
* java -jar ./build/libs/musinsa-1.0.jar --spring.profiles.active=local
  * 실행전 application-local.yml 에 사용하시는 디비의 계정과 비밀번호를 기입 부탁드리며 테이블초기화에 필요한 sql 스크립트는 resources 폴더 확인부탁드립니다
    * (ddl-auto 미적용)

## 테스트
*  ./gradlew test
   * 테스트 실행전 sql 스크립트 실행 부탁드립니다
   * (ddl-auto 미적용)

또는

* 스웨거를 통한 API 테스트가 가능합니다 (서버 구동후 http://localhost:8080/swagger-ui.html# 접속)

## 과제에 대하여
* 카테고리 테이블 구조는 id와 pid 컬럼을 통해 리커시브하게 조회하여 카테고리의 Depth를 사용할수있게끔 설계하였습니다
  * 카테고리 테이블의 최초 데이터는 root 데이터가 들어가며 이때의 pid는 null 이 되어야합니다
  * 이후 insert되는 데이터는 root 데이터 혹은 그 자식 데이터의 id 를 pid 로 사용하는 구조가 됩니다.
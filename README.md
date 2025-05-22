# nts-park-geonwoo
엔테크서비스 신입개발자 채용 사전과제

## 프로젝트 개발환경

- 백엔드 : SpringBoot3.4, JAVA21, SpringDataJpa, MySQL
- 프론트엔드 : Vue3

## 프로젝트 사용법

### 사전 설치

- Node.js (v18 이상) 혹은 npm 10이상
- Java 21
- MySQL 

### 실행 방법

Repository를 clone 합니다. `git clone https://github.com/gomudayya/nts-park-geonwoo.git` 

백엔드 서버를 실행하기 전에 데이터베이스를 실행해야 합니다.

MySQL 3306 포트에 `ntsboard` 데이터베이스를 생성합니다 (username : root, password : 1234)

#### 백엔드 실행
```
cd backend
./gradlew build
java -jar build/libs/*.jar
```

#### 프론트엔드 실행

터미널을 하나 더 켜서 프론트엔드 서버도 실행합니다. 
```
cd frontend
npm install
npm run dev
```

localhost:5173 으로 접속하면 프론트엔드 화면을 볼 수 있습니다.


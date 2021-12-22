# CLC_SERVER

- [Use](#use)
    + [기타](#기타)
- [API Specification](#api-specification)
  * [전체 영화 조회](#전체-영화-조회)
  * [영화 단일 조회](#영화-단일-조회)
  * [글 추가](#글-추가)
  * [글 수정](#글-수정)
  * [글 삭제](#글-삭제)
  * [오류 발생](#오류-발생)

## Use
### 1. Clone
```shell
~$ git clone https://github.com/poby123/CLC_server.git
```

<br/>

### 2. Install h2 & Start
```shell
~$ sudo apt-get update
~$ wget https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/h2database/h2-2014-01-18.zip
~$ unzip h2-2014-01-18.zip
~$ chmod +x ~/h2/bin/h2.sh
~$ ~/h2/bin/h2.sh -webAllowOthers # -webAllowOthers 옵션은 로컬환경에서는 쓸 필요없고, aws 같은 외부 머신에 올렸을 때 외부에서 접속하기 위해 필요하다.
```

<br/>

### 3. DB init & Run spring boot
#### 1. h2 DB 생성.

![h2](https://user-images.githubusercontent.com/50279318/146384811-893fdf8b-5ef6-4293-b7bd-7cc56ab66474.png) 
<br/>
실행된 h2에 접속해서 위와 같이 입력한 뒤, 연결을 눌러서 새로운 화면으로 들어간다. 

<br/>

#### 2. 실행! 
```shell
~$ cd CLC_server
~/CLC_server$ mvn spring-boot:run
```
위와 같은 방법 등으로 스프링부트 앱을 실행한 후 컨트롤C로 종료시키고, h2에 접속해서 테이블이 생성되었는지 및 movie 테이블에 데이터가 잘 들어갔는지 확인한다.<br/>
잘 되었으면 다음의 2가지 작업을 한다. <br/>

##### `src/main/java/com/example/clc/clc_server/InitDB.java`에서 `@Component` 를 주석처리한다.
이는 데이터가 중복되서 들어가는 것을 막기 위해서이다. <br/>

##### `src/main/resources/application.yml`에서 `ddl-auto`를 `create`가 아닌 `none`으로 설정한다.
이는 애플리케이션이 켜질때마다 테이블을 없애고 새로 만드는 것을 막기 위해서이다. <br/>
이 값이 create로 되어있다면, 켜질 때마다 db의 데이터를 없애고 새로 테이블을 만들 것이다.

<br/>

### 기타
src/main/resources/application.yml 파일을 잘 보자.

#### * DDL-AUTO
ddl-auto 가 `create`로 되어있으면, 서버가 켜질때마다 db 데이터가 날아가므로 주의가 필요하다. <br/>
실제 운용시에는 ddl-auto를 `none`으로 설정하자.

#### * DB 비밀번호
알아서 설정하고, application.yml에 잘 넣어주기 바람.

<hr/>

## API Specification
### 전체 영화 조회 
#### 요청
[GET] /api/v2/movies

#### 응답
```json
{
    "statusMessage": "",
    "data": [
        {
            "id": 1,
            "url": "https://yts.mx/movies/antlers-2021",
            "title": "Antlers",
            "title_english": "Antlers",
            "title_long": "Antlers (2021)",
            "slug": "antlers-2021",
            "year": 2021,
            "rating": 6.3,
            "runtime": 99,
            "summary": "In an isolated Oregon town, a middle-school teacher and her sheriff brother become embroiled with her enigmatic student, whose dark secrets lead to terrifying encounters with an ancestral creature.",
            "description_full": "In an isolated Oregon town, a middle-school teacher and her sheriff brother become embroiled with her enigmatic student, whose dark secrets lead to terrifying encounters with an ancestral creature.",
            "synopsis": "In an isolated Oregon town, a middle-school teacher and her sheriff brother become embroiled with her enigmatic student, whose dark secrets lead to terrifying encounters with an ancestral creature.",
            "background_image": "https://yts.mx/assets/images/movies/antlers_2021/background.jpg",
            "medium_cover_image": "https://yts.mx/assets/images/movies/antlers_2021/medium-cover.jpg",
            "large_cover_image": "https://yts.mx/assets/images/movies/antlers_2021/large-cover.jpg",
            "genres": [
                "Drama",
                "Horror",
                "Mystery"
            ]
        }
    ]
}
```

<hr/>

### 영화 단일 조회
#### 요청
[GET] /api/v2/movie?id=1 <br/>
id: 영화 아이디

#### 응답
```json
{
    "statusMessage": "",
    "data": {
        "id": 1,
        "url": "https://yts.mx/movies/antlers-2021",
        "title": "Antlers",
        "title_english": "Antlers",
        "title_long": "Antlers (2021)",
        "slug": "antlers-2021",
        "year": 2021,
        "rating": 6.3,
        "runtime": 99,
        "summary": "In an isolated Oregon town, a middle-school teacher and her ",
        "description_full": "In an isolated Oregon town, a middle-school teacher and her",
        "synopsis": "In an isolated Oregon town, a middle-school teacher and her",
        "background_image": "https://yts.mx/assets/images/movies/antlers_2021/background.jpg",
        "medium_cover_image": "https://yts.mx/assets/images/movies/antlers_2021/medium-cover.jpg",
        "large_cover_image": "https://yts.mx/assets/images/movies/antlers_2021/large-cover.jpg",
        "genres": ["Drama","Horror","Mystery"],
        "comments": [
            {
                "docId": 56,
                "userId": "test-id",
                "password": "test-pw",
                "title": "test-title3",
                "content": "test-content3",
                "updatedDate": "2021-12-16T11:47:25.663434"
            }
        ]
    }
}
```

<hr/>

### 글 추가
#### 요청 
[POST] /api/review
```json
{
    "movieId": 1,
    "id": "test-id",
    "pw": "test-pw",
    "title": "test-title3",
    "content": "test-content3"
}
``` 

#### 응답 
201 CREATED
```json
{
    "statusMessage":"",
    "data":56
}
```

<hr/>

### 글 수정
#### 요청 
[PUT] /api/review
```json
{
    "docId": 56,
    "movieId": 1,
    "id": "test-id",
    "pw": "test-pw",
    "title": "test-title4",
    "content": "test-content4"
}
``` 

#### 응답 
200 OK
```json
{
    "statusMessage": "resource updated successfully",
    "data": 56
}
```

<hr/>

### 글 삭제
#### 요청 
[DELETE] /api/review
```json
{
    "docId": 56,
}
``` 

#### 응답 
200 OK
```json
{
    "statusMessage": "resource deleted successfully",
    "data": 56
}
```

<hr/>

### 오류 발생
```json
{
    "timestamp": "2021-12-16T13:23:56.532657",
    "status": 404,
    "error": "NOT_FOUND",
    "code": "MOVIE_NOT_FOUND",
    "message": "존재하지 않는 영화입니다."
}
```



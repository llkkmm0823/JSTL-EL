create table member(
	name varchar2(30),
	userid varchar2(30),
	pwd varchar2(30),
	email varchar2(30),
	phone varchar2(30),
	admin number(1) default 0, -- 0:일반사용자 , 1:관리자
	primary key (userid)
);

insert into member values('이소미','somi','1234','gmd@naver.com','010-1234-1234',1);
insert into member values('하상오','sang12','1234','h12@naver.com','010-5555-6666',0);
insert into member values('김윤승','light','1234','yoon1@hanmail.net','010-9999-1111',0);

select*from member;


--가끔 실행 안될때 commit으로 적용 시켜보기.
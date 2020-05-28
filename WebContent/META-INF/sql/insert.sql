/* user 데이터 */
insert into member(id, password, name, nickname, email) values('hyerin', '1004', '김혜린', '혜린', 'hyerin@a.com');
insert into member(id, password, name, nickname, email) values('soyoung', '1004', '남소영', '소영', 'soyoung@a.com');
insert into member(id, password, name, nickname, email) values('jinwon', '1004', '조진원', '진원', 'jinwon@a.com');
insert into member(id, password, name, nickname, email) values('sejong', '1004', '변세종', '세종', 'sejong@a.com');
insert into member(id, password, name, nickname, email) values('dongryul', '1004', '오동률', '동률', 'dongryul@a.com');
select * from member;
commit;

/* 스케쥴 데이터 */
insert into schedule(id, schedule_no, schedule_date, end_time, start_time, schedule_content) values('jinwon', schedule_no_seq.nextval, sysdate, sysdate, sysdate, '내용');
insert into schedule(id, schedule_no, schedule_date, end_time, start_time, schedule_content) values('hyerin', schedule_no_seq.nextval, sysdate, sysdate, sysdate, '내용2');
insert into schedule(id, schedule_no, schedule_date, end_time, start_time, schedule_content) values('soyoung', schedule_no_seq.nextval, sysdate, sysdate, sysdate, '내용3');
insert into schedule(id, schedule_no, schedule_date, end_time, start_time, schedule_content) values('sejong', schedule_no_seq.nextval, sysdate, sysdate, sysdate, '내용4');
insert into schedule(id, schedule_no, schedule_date, end_time, start_time, schedule_content) values('dongryul', schedule_no_seq.nextval, sysdate, sysdate, sysdate, '내용5');
select * from schedule;
commit;

/* board type 데이터 */
insert into board_type(board_code, board_name) values(1, '공시사항');
insert into board_type(board_code, board_name) values(2, '자유게시판');
select * from board_type;
commit;

/* kanban 데이터 */
insert into kanban_group(kanban_code, list_name) 
values (KANBAN_CODE_SEQ.nextval, '문제');
insert into kanban(id, kanban_code, kanban_no, kanban_title, kanban_content, kanban_date) 
values ('hyerin', 1, KANBAN_NO_SEQ.nextval, 'SQL(문자열 함수)', '문자열 함수 문제입니다. 풀어보세요', sysdate);
insert into kanban(id, kanban_code, kanban_no, kanban_title, kanban_content, kanban_date) 
values ('hyerin', 1, KANBAN_NO_SEQ.nextval, 'JavaScript', '자바스크립트 문제입니다. 풀어보세요', sysdate);
insert into kanban(id, kanban_code, kanban_no, kanban_title, kanban_content, kanban_date) 
values ('hyerin', 1, KANBAN_NO_SEQ.nextval, 'Java', '자바 문제입니다. 풀어보세요', sysdate);
insert into kanban(id, kanban_code, kanban_no, kanban_title, kanban_content, kanban_date) 
values ('hyerin', 1, KANBAN_NO_SEQ.nextval, '문제문제', '자바 문제입니다. 풀어보세요', sysdate);

insert into kanban_group(kanban_code, list_name) 
values (KANBAN_CODE_SEQ.nextval, '코드공유');
insert into kanban(id, kanban_code, kanban_no, kanban_title, kanban_content, kanban_date) 
values ('hyerin', 3, KANBAN_NO_SEQ.nextval, '크레인문제코드', '공유합니다', sysdate);
insert into kanban(id, kanban_code, kanban_no, kanban_title, kanban_content, kanban_date) 
values ('hyerin', 3, KANBAN_NO_SEQ.nextval, '더하기문제 코드', '공유합니다', sysdate);

insert into kanban_group(kanban_code, list_name) 
values (KANBAN_CODE_SEQ.nextval, '유용한 주소');
insert into kanban(id, kanban_code, kanban_no, kanban_title, kanban_content, kanban_date) 
values ('hyerin', 5, KANBAN_NO_SEQ.nextval, '아이콘', '아이콘 사이트', sysdate);

select * from kanban_group;
select * from kanban;

commit;
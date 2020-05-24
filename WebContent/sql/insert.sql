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
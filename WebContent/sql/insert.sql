/* user ������ */
insert into member(id, password, name, nickname, email) values('hyerin', '1004', '������', '����', 'hyerin@a.com');
insert into member(id, password, name, nickname, email) values('soyoung', '1004', '���ҿ�', '�ҿ�', 'soyoung@a.com');
insert into member(id, password, name, nickname, email) values('jinwon', '1004', '������', '����', 'jinwon@a.com');
insert into member(id, password, name, nickname, email) values('sejong', '1004', '������', '����', 'sejong@a.com');
insert into member(id, password, name, nickname, email) values('dongryul', '1004', '������', '����', 'dongryul@a.com');
select * from member;
commit;

/* ������ ������ */
insert into schedule(id, schedule_no, schedule_date, end_time, start_time, schedule_content) values('jinwon', schedule_no_seq.nextval, sysdate, sysdate, sysdate, '����');
insert into schedule(id, schedule_no, schedule_date, end_time, start_time, schedule_content) values('hyerin', schedule_no_seq.nextval, sysdate, sysdate, sysdate, '����2');
insert into schedule(id, schedule_no, schedule_date, end_time, start_time, schedule_content) values('soyoung', schedule_no_seq.nextval, sysdate, sysdate, sysdate, '����3');
insert into schedule(id, schedule_no, schedule_date, end_time, start_time, schedule_content) values('sejong', schedule_no_seq.nextval, sysdate, sysdate, sysdate, '����4');
insert into schedule(id, schedule_no, schedule_date, end_time, start_time, schedule_content) values('dongryul', schedule_no_seq.nextval, sysdate, sysdate, sysdate, '����5');
select * from schedule;
commit;

/* board type ������ */
insert into board_type(board_code, board_name) values(1, '���û���');
insert into board_type(board_code, board_name) values(2, '�����Խ���');
select * from board_type;
commit;
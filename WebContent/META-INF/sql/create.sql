/* �Խ��� */
CREATE TABLE board (
	board_code NUMBER NOT NULL, /* �Խ����ڵ� */
	no NUMBER NOT NULL, /* �۹�ȣ */
	id VARCHAR2(50) NOT NULL, /* ȸ�����̵� */
	title VARCHAR2(100) NOT NULL, /* ������ */
	content VARCHAR2(2000) NOT NULL, /* �۳��� */
	views NUMBER default 0, /* ��ȸ�� */
	write_date DATE NOT NULL, /* �ۼ��� */
	comment_count NUMBER default 0 /* ��۰��� */
);

ALTER TABLE board
	ADD
		CONSTRAINT PK_board
		PRIMARY KEY (
			no
		);

/* �Խ������� */
CREATE TABLE board_type (
	board_code NUMBER NOT NULL, /* �Խ����ڵ� */
	board_name VARCHAR2(100) NOT NULL /* �Խ����̸� */
);

ALTER TABLE board_type
	ADD
		CONSTRAINT PK_board_type
		PRIMARY KEY (
			board_code
		);

/* �亯���Խ��� */
CREATE TABLE board_reply (
	reply_no NUMBER NOT NULL, /* �亯�ĺ���ȣ */
	id VARCHAR2(50) NOT NULL, /* ȸ�����̵� */
	no NUMBER NOT NULL, /* �۹�ȣ */
	refer NUMBER default 0, /* �׷��ȣ */
	depth NUMBER default 0, /* �鿩���� */
	step NUMBER default 0 /* �亯���� */
);

ALTER TABLE board_reply
	ADD
		CONSTRAINT PK_board_reply
		PRIMARY KEY (
			reply_no
		);

/* �ڷ�� */
CREATE TABLE board_file (
	file_no NUMBER NOT NULL, /* �ڷ�ǽĺ���ȣ */
	no NUMBER NOT NULL, /* �۹�ȣ */
	file_name VARCHAR2(110) /* �����̸� */
);

ALTER TABLE board_file
	ADD
		CONSTRAINT PK_board_file
		PRIMARY KEY (
			file_no
		);

/* ��� */
CREATE TABLE board_comment (
	id VARCHAR2(50), /* ȸ�����̵� */
	no NUMBER NOT NULL, /* �۹�ȣ */
	comment_no NUMBER NOT NULL, /* ��۽ĺ���ȣ */
	comment_content VARCHAR2(600) NOT NULL, /* ��۳��� */
	comment_date DATE NOT NULL /* ����ۼ��� */
);

ALTER TABLE board_comment
	ADD
		CONSTRAINT PK_board_comment
		PRIMARY KEY (
			comment_no
		);

/* ȸ�� */
CREATE TABLE member (
	id VARCHAR2(50) NOT NULL, /* ȸ�����̵� */
	password VARCHAR2(50) NOT NULL, /* ��й�ȣ */
	name VARCHAR2(50) NOT NULL, /* ȸ���̸� */
	nickname VARCHAR2(50) NOT NULL UNIQUE, /* ȸ���г��� */
	email VARCHAR2(50) NOT NULL UNIQUE, /* �̸��� */
	file_name VARCHAR2(110) /* �����̸� */
);

ALTER TABLE member
	ADD
		CONSTRAINT PK_member
		PRIMARY KEY (
			id
		);

/* �������� */
CREATE TABLE schedule (
	id VARCHAR2(50) NOT NULL, /* ȸ�����̵� */
	schedule_no NUMBER NOT NULL, /* �����ڵ� */
	schedule_date DATE NOT NULL, /* ��¥ */
	end_time DATE NOT NULL, /* �����½ð� */
	start_time DATE NOT NULL, /* ���۽ð� */
	schedule_content VARCHAR2(200) NOT NULL /* ���� */
);

ALTER TABLE schedule
	ADD
		CONSTRAINT PK_schedule
		PRIMARY KEY (
			id,
			schedule_no
		);

/* ���θ���Ʈ */
CREATE TABLE todolist (
	id VARCHAR2(50) NOT NULL, /* ȸ�����̵� */
	todo_no NUMBER NOT NULL, /* ���θ���Ʈ�ĺ���ȣ */
	completed CHAR NOT NULL, /* �ϷῩ�� */
	todo_content VARCHAR2(100) NOT NULL /* ���� */
);

ALTER TABLE todolist
	ADD
		CONSTRAINT PK_todolist
		PRIMARY KEY (
			id,
			todo_no
		);

/* �ϱ��� */
CREATE TABLE diary (
	id VARCHAR2(50) NOT NULL, /* ȸ�����̵� */
	diary_no NUMBER NOT NULL, /* �۹�ȣ */
	diary_title VARCHAR2(100) NOT NULL, /* ������ */
	diary_content VARCHAR2(2000) NOT NULL, /* �۳��� */
	diary_date DATE NOT NULL, /* �ۼ��� */
	diary_refer NUMBER default 0, /* �׷��ȣ */
	diary_depth NUMBER default 0, /* �鿩���� */
	diary_step NUMBER default 0, /* �亯���� */
	diary_file_name VARCHAR2(110), /* �����̸� */
	diary_comment_count NUMBER default 0 /* ��۰��� */
);

ALTER TABLE diary
	ADD
		CONSTRAINT PK_diary
		PRIMARY KEY (
			id,
			diary_no
		);

/* �ϱ����� */
CREATE TABLE diary_comment (
    diary_comment_no NUMBER NOT NULL, /* ���̾��۽ĺ���ȣ */
	id VARCHAR2(50) NOT NULL, /* ȸ�����̵� */
	diary_no NUMBER NOT NULL, /* �۹�ȣ */
	diary_comment_content VARCHAR2(1000) NOT NULL, /* ��۳��� */
	diary_comment_date DATE NOT NULL /* ����ۼ��� */
);

ALTER TABLE diary_comment
	ADD
		CONSTRAINT PK_diary_comment
		PRIMARY KEY (
			diary_comment_no
		);

/* ĭ�ݺ����� */
CREATE TABLE kanban_comment (
	kanban_comment_no NUMBER NOT NULL, /* ĭ�ݴ�۽ĺ���ȣ */
	kanban_no NUMBER NOT NULL, /* �۹�ȣ */
	kanban_comment_content VARCHAR2(1000) NOT NULL, /* ��۳��� */
	kanban_comment_date DATE NOT NULL /* ����ۼ��� */
);

ALTER TABLE kanban_comment
	ADD
		CONSTRAINT PK_kanban_comment
		PRIMARY KEY (
			kanban_comment_no
		);

/* ĭ�ݺ��� */
CREATE TABLE kanban (
	kanban_no NUMBER NOT NULL, /* �۹�ȣ */
	kanban_title VARCHAR2(100) NOT NULL, /* ������ */
	kanban_content VARCHAR2(2000), /* �۳��� */
	kanban_date DATE NOT NULL, /* �ۼ��� */
	kanban_file_name VARCHAR2(110), /* �����̸� */
	kanban_file_count NUMBER  default 0, /* ���ϰ��� */
	kanban_comment_count NUMBER  default 0, /* ��۰��� */
	kanban_code NUMBER  NOT NULL, /* ĭ�ݸ���Ʈ �ڵ� */
	id VARCHAR2(50) NOT NULL /* ȸ�����̵� */
);

ALTER TABLE kanban
	ADD
		CONSTRAINT PK_kanban
		PRIMARY KEY (
			kanban_no
		);

/* ĭ�� �׷� */
CREATE TABLE kanban_group (
	kanban_code NUMBER NOT NULL, /* ĭ�ݸ���Ʈ �ڵ� */
	id VARCHAR2(50) NOT NULL, /* ȸ�����̵� */
	list_name VARCHAR2(100) NOT NULL /* ĭ�ݸ���Ʈ �̸� */
);

ALTER TABLE kanban_group
	ADD
		CONSTRAINT PK_kanban_group
		PRIMARY KEY (
			kanban_code,
			id
		);

ALTER TABLE board
	ADD
		CONSTRAINT FK_board_type_TO_board
		FOREIGN KEY (
			board_code
		)
		REFERENCES board_type (
			board_code
		);

ALTER TABLE board
	ADD
		CONSTRAINT FK_member_TO_board
		FOREIGN KEY (
			id
		)
		REFERENCES member (
			id
		);

ALTER TABLE board_reply
	ADD
		CONSTRAINT FK_board_TO_board_reply
		FOREIGN KEY (
			no
		)
		REFERENCES board (
			no
		);

ALTER TABLE board_reply
	ADD
		CONSTRAINT FK_member_TO_board_reply
		FOREIGN KEY (
			id
		)
		REFERENCES member (
			id
		);

ALTER TABLE board_file
	ADD
		CONSTRAINT FK_board_TO_board_file
		FOREIGN KEY (
			no
		)
		REFERENCES board (
			no
		);

ALTER TABLE board_comment
	ADD
		CONSTRAINT FK_board_TO_board_comment
		FOREIGN KEY (
			no
		)
		REFERENCES board (
			no
		);

ALTER TABLE board_comment
	ADD
		CONSTRAINT FK_member_TO_board_comment
		FOREIGN KEY (
			id
		)
		REFERENCES member (
			id
		);

ALTER TABLE schedule
	ADD
		CONSTRAINT FK_member_TO_schedule
		FOREIGN KEY (
			id
		)
		REFERENCES member (
			id
		);

ALTER TABLE todolist
	ADD
		CONSTRAINT FK_member_TO_todolist
		FOREIGN KEY (
			id
		)
		REFERENCES member (
			id
		);

ALTER TABLE diary
	ADD
		CONSTRAINT FK_member_TO_diary
		FOREIGN KEY (
			id
		)
		REFERENCES member (
			id
		);

ALTER TABLE diary_comment
	ADD
		CONSTRAINT FK_diary_TO_diary_comment
		FOREIGN KEY (
			id,
			diary_no
		)
		REFERENCES diary (
			id,
			diary_no
		);

ALTER TABLE kanban_comment
	ADD
		CONSTRAINT FK_kanban_TO_kanban_comment
		FOREIGN KEY (
			kanban_no
		)
		REFERENCES kanban (
			kanban_no
		);

ALTER TABLE kanban
	ADD
		CONSTRAINT FK_kanban_group_TO_kanban
		FOREIGN KEY (
			kanban_code,
			id
		)
		REFERENCES kanban_group (
			kanban_code,
			id
		) ON DELETE CASCADE;

ALTER TABLE kanban_group
	ADD
		CONSTRAINT FK_member_TO_kanban_group
		FOREIGN KEY (
			id
		)
		REFERENCES member (
			id
		);

create SEQUENCE no_seq;
create SEQUENCE comment_no_seq;
create SEQUENCE file_no_seq;
create SEQUENCE reply_no_seq;
create SEQUENCE diary_no_seq;
create SEQUENCE diary_comment_no_seq;
create SEQUENCE kanban_no_seq;
create SEQUENCE kanban_code_seq;
create SEQUENCE kanban_comment_no_seq;
create SEQUENCE schedule_no_seq;
create SEQUENCE todo_no_seq;
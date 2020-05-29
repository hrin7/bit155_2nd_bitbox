/* 게시판 */
CREATE TABLE board (
	board_code NUMBER NOT NULL, /* 게시판코드 */
	no NUMBER NOT NULL, /* 글번호 */
	id VARCHAR2(50) NOT NULL, /* 회원아이디 */
	title VARCHAR2(100) NOT NULL, /* 글제목 */
	content VARCHAR2(2000) NOT NULL, /* 글내용 */
	views NUMBER default 0, /* 조회수 */
	write_date DATE NOT NULL, /* 작성일 */
	comment_count NUMBER default 0 /* 댓글개수 */
);

ALTER TABLE board
	ADD
		CONSTRAINT PK_board
		PRIMARY KEY (
			no
		);

/* 게시판종류 */
CREATE TABLE board_type (
	board_code NUMBER NOT NULL, /* 게시판코드 */
	board_name VARCHAR2(100) NOT NULL /* 게시판이름 */
);

ALTER TABLE board_type
	ADD
		CONSTRAINT PK_board_type
		PRIMARY KEY (
			board_code
		);

/* 답변형게시판 */
CREATE TABLE board_reply (
	reply_no NUMBER NOT NULL, /* 답변식별번호 */
	id VARCHAR2(50) NOT NULL, /* 회원아이디 */
	no NUMBER NOT NULL, /* 글번호 */
	refer NUMBER default 0, /* 그룹번호 */
	depth NUMBER default 0, /* 들여쓰기 */
	step NUMBER default 0 /* 답변정렬 */
);

ALTER TABLE board_reply
	ADD
		CONSTRAINT PK_board_reply
		PRIMARY KEY (
			reply_no
		);

/* 자료실 */
CREATE TABLE board_file (
	file_no NUMBER NOT NULL, /* 자료실식별번호 */
	no NUMBER NOT NULL, /* 글번호 */
	file_name VARCHAR2(110) /* 파일이름 */
);

ALTER TABLE board_file
	ADD
		CONSTRAINT PK_board_file
		PRIMARY KEY (
			file_no
		);

/* 댓글 */
CREATE TABLE board_comment (
	id VARCHAR2(50), /* 회원아이디 */
	no NUMBER NOT NULL, /* 글번호 */
	comment_no NUMBER NOT NULL, /* 댓글식별번호 */
	comment_content VARCHAR2(600) NOT NULL, /* 댓글내용 */
	comment_date DATE NOT NULL /* 댓글작성일 */
);

ALTER TABLE board_comment
	ADD
		CONSTRAINT PK_board_comment
		PRIMARY KEY (
			comment_no
		);

/* 회원 */
CREATE TABLE member (
	id VARCHAR2(50) NOT NULL, /* 회원아이디 */
	password VARCHAR2(50) NOT NULL, /* 비밀번호 */
	name VARCHAR2(50) NOT NULL, /* 회원이름 */
	nickname VARCHAR2(50) NOT NULL UNIQUE, /* 회원닉네임 */
	email VARCHAR2(50) NOT NULL UNIQUE, /* 이메일 */
	file_name VARCHAR2(110) /* 파일이름 */
);

ALTER TABLE member
	ADD
		CONSTRAINT PK_member
		PRIMARY KEY (
			id
		);

/* 일정관리 */
CREATE TABLE schedule (
	id VARCHAR2(50) NOT NULL, /* 회원아이디 */
	schedule_no NUMBER NOT NULL, /* 일정코드 */
	schedule_date DATE NOT NULL, /* 날짜 */
	end_time DATE NOT NULL, /* 끝나는시간 */
	start_time DATE NOT NULL, /* 시작시간 */
	schedule_content VARCHAR2(200) NOT NULL /* 내용 */
);

ALTER TABLE schedule
	ADD
		CONSTRAINT PK_schedule
		PRIMARY KEY (
			id,
			schedule_no
		);

/* 투두리스트 */
CREATE TABLE todolist (
	id VARCHAR2(50) NOT NULL, /* 회원아이디 */
	todo_no NUMBER NOT NULL, /* 투두리스트식별번호 */
	completed CHAR NOT NULL, /* 완료여부 */
	todo_content VARCHAR2(100) NOT NULL /* 내용 */
);

ALTER TABLE todolist
	ADD
		CONSTRAINT PK_todolist
		PRIMARY KEY (
			id,
			todo_no
		);

/* 일기장 */
CREATE TABLE diary (
	id VARCHAR2(50) NOT NULL, /* 회원아이디 */
	diary_no NUMBER NOT NULL, /* 글번호 */
	diary_title VARCHAR2(100) NOT NULL, /* 글제목 */
	diary_content VARCHAR2(2000) NOT NULL, /* 글내용 */
	diary_date DATE NOT NULL, /* 작성일 */
	diary_refer NUMBER default 0, /* 그룹번호 */
	diary_depth NUMBER default 0, /* 들여쓰기 */
	diary_step NUMBER default 0, /* 답변정렬 */
	diary_file_name VARCHAR2(110), /* 파일이름 */
	diary_comment_count NUMBER default 0 /* 댓글개수 */
);

ALTER TABLE diary
	ADD
		CONSTRAINT PK_diary
		PRIMARY KEY (
			id,
			diary_no
		);

/* 일기장댓글 */
CREATE TABLE diary_comment (
    diary_comment_no NUMBER NOT NULL, /* 다이어리댓글식별번호 */
	id VARCHAR2(50) NOT NULL, /* 회원아이디 */
	diary_no NUMBER NOT NULL, /* 글번호 */
	diary_comment_content VARCHAR2(1000) NOT NULL, /* 댓글내용 */
	diary_comment_date DATE NOT NULL /* 댓글작성일 */
);

ALTER TABLE diary_comment
	ADD
		CONSTRAINT PK_diary_comment
		PRIMARY KEY (
			diary_comment_no
		);

/* 칸반보드댓글 */
CREATE TABLE kanban_comment (
	kanban_comment_no NUMBER NOT NULL, /* 칸반댓글식별번호 */
	kanban_no NUMBER NOT NULL, /* 글번호 */
	kanban_comment_content VARCHAR2(1000) NOT NULL, /* 댓글내용 */
	kanban_comment_date DATE NOT NULL /* 댓글작성일 */
);

ALTER TABLE kanban_comment
	ADD
		CONSTRAINT PK_kanban_comment
		PRIMARY KEY (
			kanban_comment_no
		);

/* 칸반보드 */
CREATE TABLE kanban (
	kanban_no NUMBER NOT NULL, /* 글번호 */
	kanban_title VARCHAR2(100) NOT NULL, /* 글제목 */
	kanban_content VARCHAR2(2000), /* 글내용 */
	kanban_date DATE NOT NULL, /* 작성일 */
	kanban_file_name VARCHAR2(110), /* 파일이름 */
	kanban_file_count NUMBER  default 0, /* 파일개수 */
	kanban_comment_count NUMBER  default 0, /* 댓글개수 */
	kanban_code NUMBER  NOT NULL, /* 칸반리스트 코드 */
	id VARCHAR2(50) NOT NULL /* 회원아이디 */
);

ALTER TABLE kanban
	ADD
		CONSTRAINT PK_kanban
		PRIMARY KEY (
			kanban_no
		);

/* 칸반 그룹 */
CREATE TABLE kanban_group (
	kanban_code NUMBER NOT NULL, /* 칸반리스트 코드 */
	id VARCHAR2(50) NOT NULL, /* 회원아이디 */
	list_name VARCHAR2(100) NOT NULL /* 칸반리스트 이름 */
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
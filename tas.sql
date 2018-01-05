create database tas;
use tas;
create table student(
    id char(10) primary key,
    password varchar(30),
    name varchar(15),
    college varchar(20),
    email varchar(30),
    image_position varchar(60),
    signature varchar(30),
    profile varchar(100),
    major varchar(20),
    grade varchar(5),
    class_number varchar(10),
    phone_number varchar(15)
);
create table teacher(
    id char(6) primary key,
    password varchar(30),
    name varchar(15),
    college varchar(20),
    email varchar(30),
    image_position varchar(60),
    signature varchar(30),
    profile varchar(100),
    title varchar(10),
    phone_number varchar(15)
);
create table admin(
    id char(6) primary key,
    password varchar(30)
);
create table course(
    id int primary key auto_increment,
    code varchar(10),
    name varchar(20),
    credit decimal(2,1),
    college varchar(20),
    semester varchar(15),
    time varchar(15),
    place varchar(20),
    introduction varchar(100),
    like_number int
);
create table take(
    student_id char(10),
    course_id int,
    primary key (student_id, course_id),
    foreign key (student_id) references student(id),
    foreign key (course_id) references course(id)
);  
create table teach(
    teacher_id char(6),
    course_id int,
    primary key (teacher_id, course_id),
    foreign key (teacher_id) references teacher(id),
    foreign key (course_id) references course(id)
);
create table file(
    id int primary key auto_increment,
    name varchar(100),
    location varchar(200),
    size int,
    date datetime,
    course_id int,
    user_id varchar(10),
    foreign key (course_id) references course(id)
);
create table material (
    file_id int(10),
    foreign key (file_id) references file(id)
);
create table video (
    file_id int,
    title varchar(50),
    profile varchar(300),
    foreign key (file_id) references file(id)
);
create table announcement(
    id int primary key auto_increment,
    title varchar(30),
    content varchar(300),
    date datetime,
    course_id int,
    foreign key (course_id) references course(id)
);
create table notice(
    id int primary key auto_increment,
    user_id varchar(10),
    course_id int,
    message varchar(300),
    type int,
    date datetime,
    foreign key (course_id) references course(id)
);
create table comment(
    id int primary key auto_increment,
    user_id varchar(10),
    user_name varchar(15),
    user_image_position varchar(60),
    course_id int,
    content varchar(300),
    date datetime,
    foreign key (course_id) references course(id)
);
CREATE TABLE homework
(
  id          INT AUTO_INCREMENT PRIMARY KEY,
  title       VARCHAR(50)  NOT NULL,
  content     VARCHAR(500) NULL,
  create_date DATE         NULL,
  ddl_date    DATE         NULL,
  score       INT          NULL,
  attachment  VARCHAR(10)  NULL,
  course_id   VARCHAR(10)  NULL,
  semester    VARCHAR(15)  NULL,
  time        VARCHAR(15)  NULL,
  place       VARCHAR(20)  NULL,
  course_id   INT          NOT NULL,
  FOREIGN KEY (course_id) REFERENCES course(id)
);

CREATE TABLE attachment (
  file_id     INT,
  homework_id INT,
  FOREIGN KEY (file_id) REFERENCES file (id),
  FOREIGN KEY (homework_id) REFERENCES homework (id)
);

CREATE TABLE upload_homework(
  id INT AUTO_INCREMENT PRIMARY KEY,
  student_id CHAR(10),
  homework_id INT,
  get_score INT,
  handle_date DATE,
  course_id INT,
  upload_homework_file INT,
  FOREIGN KEY (student_id) REFERENCES student(id),
  FOREIGN KEY (homework_id) REFERENCES homework(id),
  FOREIGN KEY (course_id) REFERENCES course(id),
  FOREIGN KEY (upload_homework_file) REFERENCES file(id)
);

CREATE TABLE online_test(
  id INT AUTO_INCREMENT PRIMARY KEY,
  course_id INT,
  ddl_date DATETIME,
  score INT,
  title VARCHAR(100),
  FOREIGN KEY (course_id) REFERENCES course(id)
);

CREATE TABLE choice_question(
  id INT AUTO_INCREMENT PRIMARY KEY,
  test_id INT,
  score INT,
  title TEXT,
  choice_a VARCHAR(200),
  choice_b VARCHAR(200),
  choice_c VARCHAR(200),
  choice_d VARCHAR(200),
  answer VARCHAR(10),
  FOREIGN KEY (test_id) REFERENCES online_test(id)
);

CREATE TABLE fill_question(
  id INT AUTO_INCREMENT PRIMARY KEY,
  test_id INT,
  score INT,
  title TEXT,
  content TEXT,
  FOREIGN KEY (test_id) REFERENCES online_test(id)
);

insert into student values("3150102100", "123456", "黄雨生", "计算机科学与技术学院", "", "", "", "", "软件工程", "3", "1501", "");
insert into student values("3150101100", "123456", "吴道义", "计算机科学与技术学院", "", "", "", "", "软件工程", "3", "1503", "");
insert into student values("3150102210", "123456", "林世鹏", "计算机科学与技术学院", "", "", "", "", "软件工程", "3", "1502", "");
insert into student values("3150103200", "123456", "张智彬", "计算机科学与技术学院", "", "", "", "", "软件工程", "3", "1503", "");
insert into student values("3150101000", "123456", "张耀心", "计算机科学与技术学院", "", "", "", "", "软件工程", "3", "1502", "");
insert into student values("3150101300", "123456", "吴佳豪", "计算机科学与技术学院", "", "", "", "", "软件工程", "3", "1503", "");

insert into teacher values("300400", "123456", "邢卫", "计算机科学与技术学院", "", "", "", "", "教授", "");
insert into teacher values("300500", "123456", "刘玉生", "计算机科学与技术学院", "", "", "", "", "教授", "");
insert into teacher values("300600", "123456", "林海", "计算机科学与技术学院", "", "", "", "", "教授", "");

insert into admin values("admin1", "admin1");

insert into course(code,name,credit,college,semester,time,place,introduction,like_number) values("Ex100", "数据结构与算法分析", "2.5", "计算机科学与技术学院", "春学期", "周一3、4", "紫金港-东1-104", "", "0");
insert into course(code,name,credit,college,semester,time,place,introduction,like_number) values("Ex100", "数据结构与算法分析", "2.5", "计算机科学与技术学院", "春学期", "周一3、4", "紫金港-东1-204", "", "0");
insert into course(code,name,credit,college,semester,time,place,introduction,like_number) values("Ex200", "软件需求工程", "2", "计算机科学与技术学院", "春学期", "周五3、4、5", "玉泉-教7-103", "", "0");

insert into take values("3150102100", "1");
insert into take values("3150102100", "3");

insert into teach values("300400", "1");
insert into teach values("300500", "2");
insert into teach values("300600", "3");

INSERT INTO tas.homework (id, title, content, create_date, ddl_date, score, attachment, course_id, semester, time, place) VALUES (1, '123change', '123change', '2017-12-16', '2017-12-07', 100, '4', 'Ex100', '春学期', '周一3、4', '紫金港-东1-104');
INSERT INTO tas.homework (id, title, content, create_date, ddl_date, score, attachment, course_id, semester, time, place) VALUES (2, '4774', '黄海波 v 好', '2017-12-17', '2017-12-19', 100, '', 'Ex100', '春学期', '周一3、4', '紫金港-东1-104');
INSERT INTO tas.homework (id, title, content, create_date, ddl_date, score, attachment, course_id, semester, time, place) VALUES (3, 'hbybqwe', 'hbqwyeyy', '2017-12-17', '2017-12-27', 100, '', 'Ex100', '春学期', '周一3、4', '紫金港-东1-104');
INSERT INTO tas.homework (id, title, content, create_date, ddl_date, score, attachment, course_id, semester, time, place) VALUES (5, 'test5', 'test5', '2017-12-17', '2017-12-19', 100, '', 'Ex100', '春学期', '周一3、4', '紫金港-东1-104');
INSERT INTO tas.homework (id, title, content, create_date, ddl_date, score, attachment, course_id, semester, time, place) VALUES (6, 'test6', 'taehbhb', '2017-12-21', '2018-01-17', 100, '', 'Ex100', '春学期', '周一3、4', '紫金港-东1-104');
INSERT INTO tas.homework (id, title, content, create_date, ddl_date, score, attachment, course_id, semester, time, place) VALUES (7, 'test7', 'hbqhwbegv', '2017-12-21', '2017-12-12', 100, '', 'Ex100', '春学期', '周一3、4', '紫金港-东1-104');

INSERT INTO tas.online_test (id, course_id, ddl_date, score, title) VALUES (2, 1, '2018-01-16 16:00:00', 100, 'test1');

INSERT INTO tas.choice_question (id, test_id, score, title, choice_a, choice_b, choice_c, choice_d, answer) VALUES (3, 2, 10, 'qrwqr', 'qwe', '123', '412', '123', 'b');
INSERT INTO tas.choice_question (id, test_id, score, title, choice_a, choice_b, choice_c, choice_d, answer) VALUES (4, 2, 20, '78jhbbh', 'hbhqwe', 'hgqw', 'gvxdj', 'gvgqwe', 'c');

INSERT INTO tas.fill_question (id, test_id, score, title, content) VALUES (1, 2, 30, '4123', '4343133');
INSERT INTO tas.fill_question (id, test_id, score, title, content) VALUES (2, 2, 40, '12477', 'hgybyh');
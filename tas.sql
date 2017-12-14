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
    grade int(1),
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
    title varchar(10)
);
create table course(
    id varchar(10),
    name varchar(20),
    credit decimal(2,1),
    college varchar(20),
    semester varchar(15),
    time varchar(15),
    place varchar(20),
    introduction varchar(100),
    like_number int(4),
    primary key (id, semester, time, place)
);
create table take(
    student_id char(10),
    course_id varchar(10),
    semester varchar(15),
    time varchar(15),
    place varchar(20),
    primary key (student_id, course_id),
    foreign key (student_id) references student(id),
    foreign key (course_id, semester, time, place) references course(id, semester, time, place)
);  
create table teach(
    teacher_id char(6),
    course_id varchar(10),
    semester varchar(15),
    time varchar(15),
    place varchar(20),
    primary key (teacher_id, course_id),
    foreign key (teacher_id) references teacher(id),
    foreign key (course_id, semester, time, place) references course(id, semester, time, place)
);
create table resource(
    type int(1),
    name varchar(100),
    location varchar(200),
    size int(10),
    date datetime,
    course_id varchar(10),
    semester varchar(15),
    time varchar(15),
    place varchar(20),
    title varchar(50),
    profile varchar(300),
    foreign key (course_id, semester, time, place) references course(id, semester, time, place)
);
create table announcement(
    title varchar(30),
    content varchar(300),
    date datetime,
    course_id varchar(10),
    semester varchar(15),
    time varchar(15),
    place varchar(20),
    foreign key (course_id, semester, time, place) references course(id, semester, time, place)
);
create table notice(
    message_id int(10) primary key auto_increment,
    user_id varchar(10),
    course_id varchar(10),
    semester varchar(15),
    time varchar(15),
    place varchar(20),
    message varchar(300),c
    type int(1),
    date datetime,
    foreign key (course_id, semester, time, place) references course(id, semester, time, place)
);
create table comment(
    comment_id int(10) primary key auto_increment,
    user_id varchar(10),
    user_name varchar(15),
    user_image_position varchar(60),
    course_id varchar(10),
    semester varchar(15),
    time varchar(15),
    place varchar(20),
    content varchar(300),
    date datetime,
    foreign key (course_id, semester, time, place) references course(id, semester, time, place)
);

insert into student values("3150102100", "123456", "黄雨生", "计算机科学与技术学院", "", "", "", "", "软件工程", "3", "1501", "");
insert into student values("3150101100", "123456", "吴道义", "计算机科学与技术学院", "", "", "", "", "软件工程", "3", "1503", "");
insert into student values("3150102210", "123456", "林世鹏", "计算机科学与技术学院", "", "", "", "", "软件工程", "3", "1502", "");
insert into student values("3150103200", "123456", "张智彬", "计算机科学与技术学院", "", "", "", "", "软件工程", "3", "1503", "");
insert into student values("3150101000", "123456", "张耀心", "计算机科学与技术学院", "", "", "", "", "软件工程", "3", "1502", "");
insert into student values("3150101300", "123456", "吴佳豪", "计算机科学与技术学院", "", "", "", "", "软件工程", "3", "1503", "");

insert into teacher values("300400", "123456", "邢卫", "计算机科学与技术学院", "", "", "", "", "教授");
insert into teacher values("300500", "123456", "刘玉生", "计算机科学与技术学院", "", "", "", "", "教授");
insert into teacher values("300600", "123456", "林海", "计算机科学与技术学院", "", "", "", "", "教授");

insert into course values("Ex100", "数据结构与算法分析", "2.5", "计算机科学与技术学院", "春学期", "周一3、4", "紫金港-东1-104", "", "0");
insert into course values("Ex100", "数据结构与算法分析", "2.5", "计算机科学与技术学院", "春学期", "周一3、4", "紫金港-东1-204", "", "0");
insert into course values("Ex200", "软件需求工程", "2", "计算机科学与技术学院", "春学期", "周五3、4、5", "玉泉-教7-103", "", "0");

insert into take values("3150102100", "Ex100", "春学期", "周一3、4", "紫金港-东1-104");
insert into take values("3150102100", "Ex200", "春学期", "周五3、4、5", "玉泉-教7-103");

insert into teach values("300400", "Ex100", "春学期", "周一3、4", "紫金港-东1-104");
insert into teach values("300500", "Ex100", "春学期", "周一3、4", "紫金港-东1-204");
insert into teach values("300600", "Ex200", "春学期", "周五3、4、5", "玉泉-教7-103");





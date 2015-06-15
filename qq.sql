/*Users表----------------------------------------------*/
create table users
(
userid int primary key auto_increment,
pwd CHAR(32) not null,
username VARCHAR(50) not null,
nickname VARCHAR(50) NOT NULL,
gender enum('男', '女') NOT NULL,
age TINYINT NOT NULL,
signatrue VARCHAR(100),
constellation enum('射手座', '水瓶座', '双鱼座', '白羊座', '金牛座', 
'双子座', '巨蟹座', '狮子座', '处女座', '天秤座', '天蝎座', '魔蝎座'),
regdate date not null,
isonline tinyint default 0
) auto_increment = 1000;

insert into users values(1000, MD5('1000'), 'shiqiang', 'shiqiang', '男', 21, 'love coding', '魔蝎座', '2013-10-07', 0);
insert into users values(1001, MD5('1001'), 'qiang', 'qiang', '男', 21, 'love coding', '魔蝎座', '2013-10-08', 0);
insert into users values(1002, MD5('1002'), 'A', 'A', '男', 21, 'love coding', '魔蝎座', '2013-10-08', 0);
insert into users values(1003, MD5('1003'), 'B', 'B', '男', 21, 'love coding', '魔蝎座', '2013-10-08', 0);
insert into users values(1004, MD5('1004'), 'C', 'C', '男', 21, 'love coding', '魔蝎座', '2013-10-08', 0);
insert into users values(1005, MD5('1005'), 'D', 'D', '男', 21, 'love coding', '魔蝎座', '2013-10-08', 1);

/*rels表-----------------------------------------------*/
create  table rels
(
relid int primary key auto_increment,
userid1 int,
userid2 int,
createdate date not null,
FOREIGN key(userid1) REFERENCES users(userid) on DELETE CASCADE on UPDATE CASCADE,
FOREIGN key(userid2) REFERENCES users(userid) on DELETE CASCADE on UPDATE CASCADE
);

insert into rels values(1, 1000, 1001, '2013-10-08');
insert into rels values(2, 1000, 1002, '2013-10-08');
insert into rels values(3, 1000, 1003, '2013-10-08');
insert into rels values(4, 1001, 1004, '2013-10-08');
insert into rels values(5, 1001, 1005, '2013-10-08');


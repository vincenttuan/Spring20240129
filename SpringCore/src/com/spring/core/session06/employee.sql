-- 若 employee table 存在則刪除
drop table if exists employee;

-- 建立 employee table
-- 若 employee table 不存在則建立
create table if not exists employee (
	id int auto_increment primary key,
    name varchar(50) not null,
    salary int default 0,
    createtime timestamp default current_timestamp
);

-- 建立資料
insert into employee(name, salary) values('mary', 45000);
insert into employee(name, salary) values('john', 55000);
insert into employee(name, salary) values('jack', 65000);
insert into employee(name, salary) values('rose', 85000);
insert into employee(name, salary) values('bob', 125000);



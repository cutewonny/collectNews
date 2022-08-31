drop table if exists member CASCADE;
create table member
(
    member_id INT NOT NULL AUTO_INCREMENT,
    login_id varchar(20),
    name     varchar(10),
    password  varchar(20),
    primary key (member_id)
);


drop table if exists keyword CASCADE;
create table keyword
(
    keyword_id INT NOT NULL AUTO_INCREMENT,
    keyword_name varchar(20),
    using_keyword boolean,
    primary key (keyword_id)
);

show databases;
use collectNews;
select * from keyword;

select * from member;
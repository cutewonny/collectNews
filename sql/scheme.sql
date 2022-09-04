drop table if exists member CASCADE;
-- auto-generated definition
create table member
(
    member_id int auto_increment
        primary key,
    login_id  varchar(20) null,
    name      varchar(10) null,
    password  varchar(20) null,
    constraint login_id
        unique (login_id)
);



drop table if exists keyword CASCADE;
-- auto-generated definition
create table keyword
(
    keyword_id    int auto_increment
        primary key,
    keyword_name  varchar(20) null,
    using_keyword tinyint(1)  null,
    constraint keyword_name
        unique (keyword_name)
);

drop table if exists news_data CASCADE;
-- auto-generated definition
create table news_data
(
    news_data_cd  int unsigned auto_increment comment 'news 정보 CD'
        primary key,
    keyword_id    int unsigned                  not null comment '키워드 CD (keyword 테이블 참조)',
    site_id       int unsigned                  not null,
    media_title   varchar(255)  default '매체 제목' not null,
    media_url     varchar(1024) default ''      not null comment '매체 URL',
    media_content text                          null comment '매체 내용',
    create_dt     datetime                      null comment '생성일시'
)
    charset = utf8mb3;

drop table if exists site CASCADE;
-- auto-generated definition
create table site
(
    site_id    int auto_increment
        primary key,
    site_name  varchar(20)  null,
    site_url   varchar(100) null,
    using_site tinyint(1)   null,
    constraint site_name
        unique (site_name)
);


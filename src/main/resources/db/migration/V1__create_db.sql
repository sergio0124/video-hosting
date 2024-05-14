create table comment
(
    creation_date timestamp(6),
    comment_id    uuid,
    id            uuid not null,
    user_id       uuid,
    video_id      uuid,
    text          varchar(255),
    primary key (id)
)
create table group_table
(
    creation_date timestamp(6) not null,
    id            uuid         not null,
    user_id       uuid,
    description   varchar(255),
    name          varchar(255),
    primary key (id)
)
create table permission
(
    is_accepted      boolean,
    is_temporary     boolean,
    creation_date    timestamp(6) not null,
    inspiration_date timestamp(6),
    group_id         uuid,
    id               uuid         not null,
    playlist_id      uuid,
    user_id          uuid,
    description      varchar(255),
    primary key (id)
)
create table playlist
(
    is_public     boolean,
    creation_date timestamp(6) not null,
    id            uuid         not null,
    user_id       uuid,
    description   varchar(255),
    name          varchar(255) not null,
    primary key (id)
)
create table time_code
(
    id          uuid not null,
    video_id    uuid,
    description varchar(255),
    time        varchar(255),
    primary key (id)
)
create table usr
(
    is_active         boolean      not null,
    non_locked        boolean      not null,
    registration_date timestamp(6) not null,
    id                uuid         not null,
    fullname          varchar(255) not null,
    image_url         varchar(255),
    mail              varchar(255) unique,
    password          varchar(255) not null,
    role              varchar(255) not null check (role in ('ADMIN', 'CREATOR', 'USER')),
    status            varchar(255),
    username          varchar(255) not null unique,
    primary key (id)
)
create table video
(
    creation_time timestamp(6),
    id            uuid         not null,
    playlist_id   uuid,
    description   varchar(255),
    image_url     varchar(255),
    name          varchar(255) not null,
    video_url     varchar(255) not null,
    primary key (id)
)
create table visit
(
    creation_date timestamp(6),
    id            uuid not null,
    user_id       uuid,
    video_id      uuid,
    primary key (id)
)
alter table if exists comment add constraint FKmk3c8pbvysjndxywunibl2voc foreign key (comment_id) references comment
alter table if exists comment add constraint FKgcgdcgly6u49hf4g8y2di3g4p foreign key (user_id) references usr
alter table if exists comment add constraint FKbsuh08kv1lyh8v6ivufrollr6 foreign key (video_id) references video
alter table if exists group_table add constraint FKre2gdpvcl8fc3tv1y6mpcq6mp foreign key (user_id) references usr
alter table if exists permission add constraint FKo27iwfa9cfgdp31l9g18qppfy foreign key (group_id) references group_table
alter table if exists permission add constraint FKqbbox1mtnjugi853k6rpjo62t foreign key (playlist_id) references playlist
alter table if exists permission add constraint FK736umsr6qhnro8id5r6bhcbrg foreign key (user_id) references usr
alter table if exists playlist add constraint FK6ydwjfkd7amioiq3qkvtwvm7c foreign key (user_id) references usr
alter table if exists time_code add constraint FKao1h678jcp595vm29jr0idpt9 foreign key (video_id) references video
alter table if exists video add constraint FKnis2lq3eppvua68mvgk75e6rg foreign key (playlist_id) references playlist
alter table if exists visit add constraint FK6759a7a4ms6qb2didahveukb foreign key (user_id) references usr
alter table if exists visit add constraint FKlo2r2bmvab9rnlc4n1fdq485d foreign key (video_id) references video
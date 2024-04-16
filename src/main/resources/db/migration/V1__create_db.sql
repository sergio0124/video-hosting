create table vclass
(
    id            uuid         not null,
    creation_date timestamp    not null,
    description   varchar(255),
    name          varchar(255) not null,
    user_id       uuid         not null,
    primary key (id)
);
create table comment
(
    id            uuid         not null,
    creation_date timestamp    not null,
    text          varchar(255) not null,
    user_id       uuid         not null,
    video_id      uuid         not null,
    primary key (id)
);
create table subscribe
(
    id          uuid not null,
    is_accepted boolean,
    class_id    uuid not null,
    user_id     uuid not null,
    primary key (id)
);
create table usr
(
    id                uuid         not null,
    fullname          varchar(255) not null,
    non_locked        boolean,
    password          varchar(255) not null,
    registration_date timestamp    not null,
    role              varchar(255) not null,
    status            varchar(255),
    username          varchar(255) not null,
    primary key (id)
);
create table video
(
    id            uuid         not null,
    creation_time timestamp    not null,
    description   varchar(255),
    folder        varchar(255) not null,
    name          varchar(255) not null,
    video_name    varchar(255) not null,
    class_id      uuid         not null,
    user_id       uuid         not null,
    primary key (id)
);
create table visit
(
    id            uuid      not null,
    creation_date timestamp not null,
    user_id       uuid      not null,
    video_id      uuid      not null,
    primary key (id)
);

alter table if exists usr add constraint UK_dfui7gxngrgwn9ewee3ogtgym unique (username);
alter table if exists vclass add constraint FKfd21drdiq9ml70eycrf5y3q19 foreign key (user_id) references usr;
alter table if exists comment add constraint FKgcgdcgly6u49hf4g8y2di3g4p foreign key (user_id) references usr;
alter table if exists comment add constraint FKbsuh08kv1lyh8v6ivufrollr6 foreign key (video_id) references video;
alter table if exists subscribe add constraint FKp08frxxw5u0v1ff721k0kf853 foreign key (class_id) references vclass;
alter table if exists subscribe add constraint FKk3q6ils8mnp9wykrot3tqkf1n foreign key (user_id) references usr;
alter table if exists video add constraint FKl6nnt7kyda2jvroo0dwn443ld foreign key (class_id) references vclass;
alter table if exists video add constraint FKj9v3v4kkvddqnl418ola2gxxi foreign key (user_id) references usr;
alter table if exists visit add constraint FK6759a7a4ms6qb2didahveukb foreign key (user_id) references usr;
alter table if exists visit add constraint FKlo2r2bmvab9rnlc4n1fdq485d foreign key (video_id) references video;

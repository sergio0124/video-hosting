insert into usr (id, fullname, non_locked, password, registration_date, role, status, username)
values ('33f41160-35d0-4aa5-b706-12822adde9d3', 'Барсук С. А.', True,
        '$2a$10$15v1TuouCdzj21XA.nsvUurMDjZQ6i3snLnvSX2elXFR2UH5fCuDe', CURRENT_TIMESTAMP, 'STUDENT', 'status', '111');

insert into usr (id, fullname, non_locked, password, registration_date, role, status, username)
values ('914446a8-1246-4389-80d3-76e92941374e', 'Енот С. А.', True,
        '$2a$10$15v1TuouCdzj21XA.nsvUurMDjZQ6i3snLnvSX2elXFR2UH5fCuDe', CURRENT_TIMESTAMP, 'TEACHER', 'status', '222');
insert into usr (id, fullname, non_locked, password, registration_date, role, status, username)
values ('d4eaea72-58cd-492c-bd0d-b11dbc4a73b7', 'Конь С. А.', True,
        '$2a$10$15v1TuouCdzj21XA.nsvUurMDjZQ6i3snLnvSX2elXFR2UH5fCuDe', CURRENT_TIMESTAMP, 'TEACHER', 'status', '333');

insert into usr (id, fullname, non_locked, password, registration_date, role, status, username)
values ('37b39513-0a4a-4dae-837d-a7b4b94dc05b', 'Пчела С. А.', True,
        '$2a$10$15v1TuouCdzj21XA.nsvUurMDjZQ6i3snLnvSX2elXFR2UH5fCuDe', CURRENT_TIMESTAMP, 'ADMIN', 'status', '333');


insert into vclass (id, creation_date, description, name, user_id)
values ('6459c017-623f-423c-b2bb-390568cb98d0', CURRENT_TIMESTAMP, 'description Engl',
        'English lang subject', 'd4eaea72-58cd-492c-bd0d-b11dbc4a73b7');

insert into vclass (id, creation_date, description, name, user_id)
values ('4f4ff511-834f-4fb0-a413-c85b92c8de19', CURRENT_TIMESTAMP, 'description Programming',
        'Programming subject', 'd4eaea72-58cd-492c-bd0d-b11dbc4a73b7');

insert into vclass (id, creation_date, description, name, user_id)
values ('35490713-6fe2-45a6-8681-bf3e241b5a51', CURRENT_TIMESTAMP, 'description Building',
        'Building subject', '914446a8-1246-4389-80d3-76e92941374e');


insert into video (id, creation_time, description, folder, name, video_name, class_id, user_id)
values ('3589c097-1843-4b4e-b9d6-5b17ccf4c86f', CURRENT_TIMESTAMP,
        'first lesson english', 'any', 'basics', 'any', '6459c017-623f-423c-b2bb-390568cb98d0',
        'd4eaea72-58cd-492c-bd0d-b11dbc4a73b7');

insert into video (id, creation_time, description, folder, name, video_name, class_id, user_id)
values ('6717a291-0175-4180-bf39-bb9a23c36782', CURRENT_TIMESTAMP, 'second lesson english',
        'any', 'middles', 'any', '6459c017-623f-423c-b2bb-390568cb98d0',
        'd4eaea72-58cd-492c-bd0d-b11dbc4a73b7');

insert into video (id, creation_time, description, folder, name, video_name, class_id, user_id)
values ('cc010e28-8239-4b58-841d-d8909e7a817e', CURRENT_TIMESTAMP,
        'Programming c++', 'any', 'c++ basics', 'any', '4f4ff511-834f-4fb0-a413-c85b92c8de19',
        'd4eaea72-58cd-492c-bd0d-b11dbc4a73b7');

insert into subscribe (id, is_accepted, class_id, user_id)
values ('23ee008c-d039-4b1e-968f-96fec6e5f8e5', TRUE, '6459c017-623f-423c-b2bb-390568cb98d0',
        '33f41160-35d0-4aa5-b706-12822adde9d3');

insert into subscribe (id, is_accepted, class_id, user_id)
values ('2ad4b452-88cc-4356-ba48-868ac12b7728', TRUE, '4f4ff511-834f-4fb0-a413-c85b92c8de19',
        '33f41160-35d0-4aa5-b706-12822adde9d3');

insert into subscribe (id, is_accepted, class_id, user_id)
values ('2ad4b452-88cc-4356-ba48-868ac12b7728', FALSE, '35490713-6fe2-45a6-8681-bf3e241b5a51',
        '33f41160-35d0-4aa5-b706-12822adde9d3');


insert into comment (id, creation_date, text, user_id, video_id)
values ('743cddd9-811c-482c-ae80-10a7a55e3bb0', CURRENT_TIMESTAMP,
        'comment 1', '33f41160-35d0-4aa5-b706-12822adde9d3', '6717a291-0175-4180-bf39-bb9a23c36782');

insert into comment (id, creation_date, text, user_id, video_id)
values ('8ac65311-7322-4839-8a3b-4dfd4733213a', CURRENT_TIMESTAMP,
        'comment 2', '33f41160-35d0-4aa5-b706-12822adde9d3', 'cc010e28-8239-4b58-841d-d8909e7a817e');
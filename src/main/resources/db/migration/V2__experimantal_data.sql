insert into usr(is_active, non_locked, registration_date, id, fullname, mail, password, role, username)
values (True, True, CURRENT_TIMESTAMP, '021a26f4-e4e8-4014-976e-58e2e010db99', 'Сидоров В. В.', 'mail',
        '$2a$12$I2DFllJwoLxcxIwCk2hV..0/S552H6OgfS4I1dP83pQzasWDqRLf.', 'ADMIN', 'admin1');

insert into usr(is_active, non_locked, registration_date, id, fullname, mail, password, role, username)
values (True, True, CURRENT_TIMESTAMP, '021a33f4-e4e8-4014-976e-58e2e010db99', 'Колин В. В.', 'mail1',
        '$2a$12$I2DFllJwoLxcxIwCk2hV..0/S552H6OgfS4I1dP83pQzasWDqRLf.', 'CREATOR', 'creator2');

insert into usr(is_active, non_locked, registration_date, id, fullname, mail, password, role, username)
values (True, True, CURRENT_TIMESTAMP, '021444f4-e4e8-4014-976e-58e2e010db99', 'Танин В. В.', 'mai2',
        '$2a$12$I2DFllJwoLxcxIwCk2hV..0/S552H6OgfS4I1dP83pQzasWDqRLf.', 'CREATOR', 'creator1');

insert into usr(is_active, non_locked, registration_date, id, fullname, mail, password, role, username)
values (True, True, CURRENT_TIMESTAMP, '025526f4-e4e8-4014-976e-58e2e010db99', 'Санин В. В.', 'mail3',
        '$2a$12$I2DFllJwoLxcxIwCk2hV..0/S552H6OgfS4I1dP83pQzasWDqRLf.', 'USER', 'user4');

insert into usr(is_active, non_locked, registration_date, id, fullname, mail, password, role, username)
values (True, True, CURRENT_TIMESTAMP, '066666f4-e4e8-4014-976e-58e2e010db99', 'Лёшин В. В.', 'mail4',
        '$2a$12$I2DFllJwoLxcxIwCk2hV..0/S552H6OgfS4I1dP83pQzasWDqRLf.', 'USER', 'user3');

insert into usr(is_active, non_locked, registration_date, id, fullname, mail, password, role, username)
values (True, True, CURRENT_TIMESTAMP, '02177774-e4e8-4014-976e-58e2e010db99', 'Катин В. В.', 'mail5',
        '$2a$12$I2DFllJwoLxcxIwCk2hV..0/S552H6OgfS4I1dP83pQzasWDqRLf.', 'USER', 'user2');

insert into usr(is_active, non_locked, registration_date, id, fullname, mail, password, role, username)
values (True, True, CURRENT_TIMESTAMP, '021886f4-e4e8-4014-976e-58e2e010db99', 'Васин В. В.', 'mail6',
        '$2a$12$I2DFllJwoLxcxIwCk2hV..0/S552H6OgfS4I1dP83pQzasWDqRLf.', 'USER', 'user1');


insert into group_table(creation_date, id, user_id, description, name)
values (CURRENT_TIMESTAMP, '63d8cca9-29d9-41c5-8aca-4f5aab600c52','021444f4-e4e8-4014-976e-58e2e010db99', 'Группа 1', 'Первая группа'),
       (CURRENT_TIMESTAMP, 'bc090e78-8efe-4e04-a398-9b0dca4e6a2e','021444f4-e4e8-4014-976e-58e2e010db99', 'Группа 2', 'Вторая группа'),
       (CURRENT_TIMESTAMP, 'b639be9c-6ac1-4eff-ad99-5de661e145de','021444f4-e4e8-4014-976e-58e2e010db99', 'Группа 3', 'Третяя группа');

insert into group_user(group_id, user_id)
values ('63d8cca9-29d9-41c5-8aca-4f5aab600c52', '025526f4-e4e8-4014-976e-58e2e010db99'),
       ('63d8cca9-29d9-41c5-8aca-4f5aab600c52', '02177774-e4e8-4014-976e-58e2e010db99'),
       ('63d8cca9-29d9-41c5-8aca-4f5aab600c52', '021886f4-e4e8-4014-976e-58e2e010db99'),
       ('bc090e78-8efe-4e04-a398-9b0dca4e6a2e', '025526f4-e4e8-4014-976e-58e2e010db99'),
       ('bc090e78-8efe-4e04-a398-9b0dca4e6a2e', '02177774-e4e8-4014-976e-58e2e010db99'),
       ('b639be9c-6ac1-4eff-ad99-5de661e145de', '025526f4-e4e8-4014-976e-58e2e010db99');

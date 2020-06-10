insert  into information(id, about_me, birth_date, company, education)
values (1, 'About me', '1990-10-10', 'SENLA', 'GSMU');

insert  into information(id, about_me, birth_date, company, education)
values (2, 'About me', '1990-10-10', 'SENLA', 'GSMU');

insert  into information(id, about_me, birth_date, company, education)
values (3, 'About me', '1990-10-10', 'SENLA', 'GSMU');

insert into usr (id, username, first_name, last_name, email, password, enabled, user_info)
    values (1, 'admin', 'Ivan', 'Ivanov', 'ivan@mail.ru','$2a$08$P4DGkElf/QhgPHUrT5whyezYi2MEUuH1xbi9dgtOPcpL7PyCIIoqa', 'true', 1);

insert into usr (id, username, first_name, last_name, email, password, enabled, user_info)
    values (2, 'user', 'Petr', 'Petrov', 'petr@mail.ru', '$2a$08$P4DGkElf/QhgPHUrT5whyezYi2MEUuH1xbi9dgtOPcpL7PyCIIoqa', 'true',2);

    insert into usr (id, username, first_name, last_name, email, password, enabled, user_info)
    values (3, 'den', 'Denis', 'Rumyancev', 'denis@mail.ru', '$2a$08$P4DGkElf/QhgPHUrT5whyezYi2MEUuH1xbi9dgtOPcpL7PyCIIoqa', 'true', 3);

insert into user_role (user_id, roles)
    values (1, 'ADMIN');

insert into user_role (user_id, roles)
    values (2, 'USER');

insert into user_role (user_id, roles)
    values (3, 'USER');


--     CREATE SEQUENCE users_id_seq START WITH 3 INCREMENT BY 1;
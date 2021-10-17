create table if not exists team (
    id uuid primary key default gen_random_uuid(),
    name varchar not null,
    board_id uuid references board(id)
);

create table if not exists users (
    id uuid primary key default gen_random_uuid(),
    first_name varchar not null,
    last_name varchar not null,
    email varchar not null,
    password varchar not null,
    role varchar not null,
    team_id uuid references team(id)
);

alter table ticket add column user_id uuid references users(id);

insert into users (first_name, last_name, email, password, role, team_id)
VALUES ('admin', 'admin', 'admin@rollingtasks.ua',
        '$2a$12$WTFDdDUl2XzSIp/l3x3z5etiVW8jMod7HnwMPB/0aO3FmHsa/zgg.',
        'SYSTEM_ADMIN', null) ON CONFLICT DO NOTHING ;

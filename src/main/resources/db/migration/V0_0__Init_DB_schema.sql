create table if not exists tag
(
    id uuid not null primary key default gen_random_uuid(),
    name varchar not null unique
);

create table if not exists board
(
    id uuid not null primary key default gen_random_uuid(),
    title varchar not null unique
);

create table if not exists board_column
(
    id uuid not null primary key default gen_random_uuid(),
    title varchar not null,
    color varchar,
    position integer,
    board_id uuid not null,

    foreign key(board_id) references board(id)
);

create table if not exists ticket
(
    id uuid not null primary key default gen_random_uuid(),
    title varchar not null,
    type varchar not null,
    uid varchar not null unique ,
    position integer,
    story_points integer,
    column_id uuid not null,

    foreign key(column_id) references board_column(id)
);

create table if not exists ticket_tag
(
    ticket_id uuid not null,
    tag_id uuid not null,

    constraint unique_ticket_tag_mapping unique (ticket_id, tag_id),
    foreign key(ticket_id) references ticket(id),
    foreign key(tag_id) references tag(id)
);
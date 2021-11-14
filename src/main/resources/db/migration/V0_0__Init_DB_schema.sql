create table if not exists tag
(
    id uuid primary key default gen_random_uuid(),
    name varchar not null unique
);

create table if not exists board
(
    id uuid primary key default gen_random_uuid(),
    title varchar not null
);

create table if not exists board_row
(
    id uuid primary key default gen_random_uuid(),
    title varchar not null,
    color varchar,
    position integer,
    board_id uuid not null,

    foreign key(board_id) references board(id)
);

create table if not exists ticket
(
    id uuid primary key default gen_random_uuid(),
    title varchar not null,
    type varchar not null,
    position integer,
    complexity varchar,
    priority varchar,
    row_id uuid not null,
    user_id bigint,

    foreign key(row_id) references board_row(id)
);

create table if not exists ticket_tag
(
    ticket_id uuid not null,
    tag_id uuid not null,

    constraint unique_ticket_tag_mapping unique (ticket_id, tag_id),
    foreign key(ticket_id) references ticket(id),
    foreign key(tag_id) references tag(id)
);
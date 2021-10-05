create table if not exists tag
(
    id bigserial primary key ,
    name varchar not null unique
);

create table if not exists board
(
    id bigserial primary key,
    title varchar not null,
    code varchar not null
);

create table if not exists board_row
(
    id bigserial primary key,
    title varchar not null,
    color varchar,
    position integer,
    board_id bigint not null,

    foreign key(board_id) references board(id)
);

create table if not exists ticket
(
    id bigserial primary key,
    title varchar not null,
    type varchar not null,
    position integer,
    complexity varchar,
    priority varchar,
    row_id bigint not null,

    foreign key(row_id) references board_row(id)
);

create table if not exists ticket_tag
(
    ticket_id bigint not null,
    tag_id bigint not null,

    constraint unique_ticket_tag_mapping unique (ticket_id, tag_id),
    foreign key(ticket_id) references ticket(id),
    foreign key(tag_id) references tag(id)
);
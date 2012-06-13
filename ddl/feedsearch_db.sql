drop table alert_hit;
drop table alert_term;
drop table feed_alert;
drop table user_account;
drop table synd_feed;

create table synd_feed (
    feed_id         bigserial       not null,
    name            varchar(256)    not null,
    url             varchar(512)    not null,
    enabled         boolean         default true,
    created_on      timestamptz     null,
    updated_on      timestamptz     null,
    primary key(feed_id)
);

create table user_account (
    user_id         bigserial       not null,
    email_address   varchar(512)    not null,
    enc_password    varchar(128)    not null,
    name            varchar(128)    null,
    enabled         boolean         default true,
    created_on      timestamptz     null,
    updated_on      timestamptz     null,
    primary key(user_id)
);

create table feed_alert (
    alert_id        bigserial       not null,
    user_id         bigint          not null,
    feed_id         bigint          not null,
    created_on      timestamptz     null,
    updated_on      timestamptz     null,
    primary key(alert_id),
    foreign key(user_id) references user_account(user_id),
    foreign key(feed_id) references synd_feed(feed_id)
);

create table alert_term (
    term_id         bigserial       not null,
    alert_id        bigint          not null,
    term            varchar(256)    not null,
    primary key(term_id, term),
    foreign key(alert_id) references feed_alert(alert_id)
);

create table alert_hit (
    hit_id          bigserial       not null,
    alert_id        bigint          not null,
    hit_date        timestamptz     not null,
    title           varchar(512)    null,
    link            varchar(512)    not null,
    primary key(hit_id),
    foreign key(alert_id) references feed_alert(alert_id)
);


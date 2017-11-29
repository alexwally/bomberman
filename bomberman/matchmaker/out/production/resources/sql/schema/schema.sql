begin;

drop schema if exists matchmaker cascade;
create schema matchmaker;

drop table if exists matchmaker.user;

drop table if exists matchmaker.gamesession;
create table matchmaker.gamesession (
  id     serial       not null,
  player_count integer not null,

  primary key (id)
);

create table matchmaker.user (
  id    serial             not null,
  name varchar(20) unique not null,
  game_session_id integer not null references matchmaker.gamesession on delete cascade,

  primary key (id)
);

commit;

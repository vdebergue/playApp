# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table adhesion (
  id                        bigint not null,
  intervenant_id            bigint,
  mandat                    integer,
  date_edition              timestamp,
  constraint pk_adhesion primary key (id))
;

create table intervenant (
  id                        bigint not null,
  nom                       varchar(255),
  prenom                    varchar(255),
  email                     varchar(255),
  constraint pk_intervenant primary key (id))
;

create sequence adhesion_seq;

create sequence intervenant_seq;

alter table adhesion add constraint fk_adhesion_intervenant_1 foreign key (intervenant_id) references intervenant (id) on delete restrict on update restrict;
create index ix_adhesion_intervenant_1 on adhesion (intervenant_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists adhesion;

drop table if exists intervenant;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists adhesion_seq;

drop sequence if exists intervenant_seq;


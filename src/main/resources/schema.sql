create table raffleTicket
(

   id integer not null,
   raffleId varchar(255) not null,
   sid varchar(255) not null,
   primary key(id),
   unique key sid_raffle_unique (raffleId, sid)

);
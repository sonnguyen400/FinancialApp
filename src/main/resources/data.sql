insert into branch(name) values("sample1");
insert into membership(id, name, description, saving_limit, loan_limit)
VALUES (1,"STANDARD","STANDARD",100000000,50000000),
       (2,"GOLD","GOLD",500000000,50000000),
       (3,"DIAMOND","DIAMOND",800000000,1000000000),
       (4,"VIP","VIP",1000000000,2000000000);
insert into tier(id, name, overdraft_limit, limit_transaction)
VALUES (1,"SILVER",0,1000000000),
       (2,"GOLD",2000000,1000000000),
       (3,"DIAMOND",10000000,2000000000);

insert into branch(id,name) values(1,"sample1");
insert into account(id, account_number, account_type, balance, open_date, branch_id, status, tier_id) VALUE(1,"00000000",4,99999999,now(),1,1,3);
insert into membership(id, name, description, saving_limit, loan_limit)
VALUES (1,"STANDARD","STANDARD",100000000,50000000),
       (2,"GOLD","GOLD",500000000,50000000),
       (3,"DIAMOND","DIAMOND",800000000,1000000000),
       (4,"VIP","VIP",1000000000,2000000000);
insert into tier(id, name, overdraft_limit, limit_transaction)
VALUES (1,"SILVER",0,1000000000),
       (2,"GOLD",2000000,1000000000),
       (3,"DIAMOND",10000000,2000000000);
insert into savings_setting(id, term,interest_rate)
values (1,6,4),(2,9,4),(3,12,4.8),(4,18,4.8),(5,24,5.7),(6,32,5.7);
insert into loan_setting(id,term,interest_rate)
values (1,12,7.9),(2,36,7.9),(3,60,7.2);


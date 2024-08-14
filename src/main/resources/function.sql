drop function if exists unpaidMonthlyLoanCount;
DELIMITER //
CREATE function unpaidMonthlyLoanCount(
    loan_id_params int
)
    RETURNS INT
    DETERMINISTIC READS SQL DATA
begin
    declare latestPayment datetime;
    declare currentDate datetime default now();
    declare unpaidMonthCount int default 0;
    declare date_ref datetime;

    select payment.payment_date into latestPayment from payment
    where payment.loan_id=loan_id_params order by payment_date desc limit 1;

    if latestPayment is not null then
        set date_ref=latestPayment;
    else
        select loan.approval_date into date_ref from loan  where loan.id=loan_id_params;
    end if;
    while datediff(currentDate,date_ref)>0 do
            set unpaidMonthCount=unpaidMonthCount+1;
            set date_ref=DATE_ADD(date_ref,INTERVAL 1 MONTH);
        end while;
    return unpaidMonthCount;
end //



# select DATE_ADD(str_to_date("31/1/2003","%d/%m/%YYYY"),INTERVAL  2 MONTH );
# select DATE_ADD(str_to_date("1/8/2020","%d/%m/%YYYY"), INTERVAL TIMESTAMPDIFF(MONTH , str_to_date("1/8/2020","%d/%m/%YYYY"), now())+1 MONTH)
#     as nextpayment having datediff(nextpayment,now())=?;
# ;
# select * from loan where DATEDIFF(DATE_ADD(loan.approval_date, INTERVAL TIMESTAMPDIFF(MONTH ,loan.approval_date, now())+? MONTH),?)=?
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
    declare loanCreatedDate datetime;
    select loan.create_at into loanCreatedDate from loan  where loan.id=loan_id_params;
    select payment.payment_date
    into latestPayment
    from payment
    where payment.loan_id=loan_id_params
    order by payment_date desc
    limit 1;
    if latestPayment is not null then
        return TIMESTAMPDIFF(MONTH,latestPayment,currentDate);
    end if;
    return TIMESTAMPDIFF(MONTH,loanCreatedDate,currentDate);
end //
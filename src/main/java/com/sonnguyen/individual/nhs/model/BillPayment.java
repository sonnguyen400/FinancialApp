package com.sonnguyen.individual.nhs.Model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "bill_payment")
public class BillPayment {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(max = 45)
    @Column(name = "payee", length = 45)
    private String payee;

    @Column(name = "amount", precision = 2)
    private Double amount;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Size(max = 45)
    @Column(name = "payment_status", length = 45)
    private String paymentStatus;

    @Column(name = "transaction_id")
    private int transactionId;


}
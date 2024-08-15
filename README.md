#### Love it ? Let this project have one more star



# Bank Simulator
This project simulates a banking app and mimics basic functions. A part of my major assignment
## Function
- Register, login, transfer money, receive money
- Creating savings (Fixed rate), managing savings
- Creating loans (Fixed rate), managing loans, loan payments  , [loan-policy](#loan-policy) 
- Automatically sending warning emails at the loan payment date (default 3 days before the payment date) </li>
- Automatically maturing or making rollover for Savings Account 
- Authentication (PIN, received OTP via email)
- For admin, due to limited working time, this project only allows approving a loan for the admin side



## Architecture
- MVC (Model View Control)
- DAO

## Technology
- Java servlet,Argon (Password Encoder),  Quartz and DeltaSpike,, JSP, Java bean, Weld (CDI), Jackson, Apache tiles (Template Engine), Sendgrid(Mail Service)
- Bootstrap (Quixlab theme), JQuery

## Configuration

Editing `application.properties` located in `/src/main/resource/application.properties` can allow you to config some properties. You then can read values inside by using annotation @Value 


```java
//Example
// Annotation in com.sonnguyen.individual.nhs.context
@Value(name="com.data.mysql.driver")
private String driver;
```

| Property                 | Type    | Default                  | Description                                                                                  |
|--------------------------|---------|--------------------------|----------------------------------------------------------------------------------------------|
| com.data.mysql.driver    | String  | com.mysql.cj.jdbc.Driver | Database Driver <br/>This project are only designed to work with mysql database              |
| com.data.mysql.url       | String  |                          | Database URL                                                                                 |
| com.data.mysql.username  | String  | root                     | Database Username                                                                            |
| com.data.mysql.password  | String  |                          | Database password                                                                            |
| application.debug        | String  | true                     | if enabled whole executed query in runtime process will be shown in console                  |
| service.loan.warn.mail   | Integer | 3                        | Warning notification of loan payment will be send to client's email before 3 days            |
| service.loan.late.charge | Integer | 4                        | Fine applied when client making late loan payment. For more, reading    [Loan Policy](#loan) |


# Function Explanation
## Loan Policy
### Create Loan

In Loan creation page, customer must enter some information include:
- Disbursement Account Number (Beneficiary Account). Account received money once loan is approved
- Term 
- Amount (Limitation depends on customer's Membership)

### Approve Loan
After client creates their loan. Admin can approve or reject them.

If loan is approved, there will have a transaction transferring amount from branch account to Beneficiary Account based on information provided by user before.

If loan is rejected, there will 

### Loan Payment

Customers will be notified before 3 days before payment day. This is one of scheduler duty automatically operating at 3AM every days
Formula:
MonthlyPayment= ( initialAmount * interestRate / term ) + ( initialAmount / term) + (( initialAmount * interestRate / term ) + ( initialAmount / term ))*(latepaymentmonth)*percentfines

## Savings Policy

### Create Savings 

- Source Account: Money will be transferred from this account to new savings account.
- Type: Only support term deposit
- Term: Select term 
- Rollover: Select rollover
- Beneficiary Account: Up to your selected rollover type, money will automatically be transferred to this account.

_**A savings can be early finished than expected if customer want to. Aware it is term deposit. Customers can't be received any profit if they finish savings before the date**_

### Rollover

Rollover executed automatically by scheduler. If the worst case of absence of any incident, customer may have to withdrawal all money from savings account manually.

## Scheduler Service

Powered by apache deltaspike module. Time can be changed by editing [cronExpression](https://www.quartz-scheduler.org/documentation/quartz-2.3.0/tutorials/crontrigger.html).
All scheduler put in scheduler package
- Savings Rollover Scheduler
- Loan Payment Warning Scheduler

## Security
### Authentication Mechanism
Security Service include three primary [SecurityContextHolder](), [UserDetailService]() ,[UserDetail Interface](), [PasswordEncoder]()
#### UserDetail Interface
Providing implement of methods:
- getUsername() return name of authorized object
- getPassword() return password of authorized object
- getAuthorities() return roles or authorities of authorized object

#### UserDetailService
Its duty is retrieving user information from database to get  of UserDetail through Username

#### SecurityContextHolder
SecurityContextHolder encompasses authorized object (implement from UserDetail) and authenticate method.

The "authenticate" method receives a parameter implemented from UserDetail Interface.

The mechanism behinds authenticate is relatively simple. It will compare password got from argument and password from User got from  UserDetailService implement.

If Implementation of UserDetailService doesn't find any user from provided username or the passwords doesn't match, it will throw an AuthenticationException.

### PasswordEncoder
Every Password encoder must be implemented from interface com.sonnguyen.individual.nhs.security.core.PasswordEncoder and annotated as @Model that make it can be managed by CDI.

NoPasswordEncoder will be used if CDI doesn't find any appropriate PasswordEncoder.

In this project, I'm using Argon as default PasswordEncoder.

### Two-Factor Authentication {2FA}
#### PIN

OTP sent via Email or can be observed by console (<span style="color: greenyellow">application.debug=true</span>)

#### OTP

6-digit in length. 

Store in database alongside login data.

# DEMO

This video DOESN'T adequately cover all features of this project.

https://youtu.be/Zv94hqYWBx4?si=FrjPXIWSTKEoU0Un

<iframe width="560" height="315" src="https://www.youtube.com/embed/Zv94hqYWBx4?si=HO5nFbI4-nHmZbcV" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>




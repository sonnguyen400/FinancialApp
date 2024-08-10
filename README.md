
# Bank Simulator
This project simulates a banking app and mimics basic functions
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
- Java servlet,  Quartz and DeltaSpike, JSP, Java bean, Weld (CDI), Jackson, Apache tiles (Template Engine), Sendgrid(Mail Service)
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
Before entranced to the loan creation page. User had to pass [2FA](#Two-Factor Authentication).

Inl Loan creation page, customer must enter some information include:
- Disbursement Account Number (Beneficiary Account). Account received money once loan is approved
- Term (6 or 12 Months)
- Amount (Limitation depends on customer's Membership)

### Approve Loan
After client creates their loan. Admin can approve or reject them.

If loan is approved, there will have a transaction transferring amount from branch account to Beneficiary Account based on information provided by user before.

If loan is rejected, there will 

## Savings Policy

## Scheduler Service
## Savings Rollover Scheduler
## Loan Payment Warning Scheduler

## Security
### Authentication Mechanism
Security Service include three primary [SecurityContextHolder](), [UserDetailService]() ,[UserDetail Interface]()
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

### Two-Factor Authentication {2FA}
#### PIN

OTP sent via Email or can be observed by console (<span style="color: greenyellow">application.debug=true</span>)

#### OTP





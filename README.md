
<h1>Bank Simulator</h1>
This project simulates a banking app and mimics basic functions
<h2>Function</h2>
<ul>
  <li>Register, login, transfer money, receive money</li>
  <li>creating savings (Fixed rate), managing savings,</li>
  <li>Creating loans (Fixed rate), managing loans, loan payments        ( <a href="#loan-policy">Loan Policy</a> )</li>
  <li>Automatically sending warning emails at the loan payment date (default 3 days before the payment date) </li>
  <li>Automatically maturing Savings Account </li>
  <li>Authentication (PIN, received OTP via email)</li>
  <li>For admin, due to limited working time, this project only allows approving a loan for the admin side</li>
</ul>
<h2>Architecture</h2>
<ul>MVC (Model View Control)</ul>
<ul>DAO</ul>
<h2>Technology</h2>
<ul>
  <li>Java servlet,  Quartz and DeltaSpike, JSP, Java bean, Weld (CDI), Jackson, Apache tiles (Template Engine), Sendgrid(Mail Service)</li>
  <li>Bootstap (Quixlab theme), JQuery</li>
</ul>
<h1>Function Explanation</h1>
<div id="authentication">
  <h3>Authentication</h3>
</div>
<div id="loan-policy">
  <h3>Loan Policy</h3>
</div>
<div id="savings-policy">
  <h3>Savings Policy</h3>
</div>
<div>
  <h3>Quartz scheduler and Automatic Function</h3>
</div>
<div>
  <h3>Orther Service</h3>
  <h4>Mail Service</h4>
</div>

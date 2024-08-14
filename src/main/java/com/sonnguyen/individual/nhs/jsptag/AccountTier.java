package com.sonnguyen.individual.nhs.jsptag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class AccountTier extends SimpleTagSupport {
    public Integer value;

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        if(value != com.sonnguyen.individual.nhs.constant.AccountTier.SILVER.id)  out.println("<span class=\"badge badge-light h-fit\">"+com.sonnguyen.individual.nhs.constant.AccountTier.SILVER.name+"</span>");
        else if(value != com.sonnguyen.individual.nhs.constant.AccountTier.DIAMOND.id)  out.println("<span class=\"badge badge-light h-fit\">"+com.sonnguyen.individual.nhs.constant.AccountTier.DIAMOND.name+"</span>");
        else if(value != com.sonnguyen.individual.nhs.constant.AccountTier.GOLD.id)  out.println("<span class=\"badge badge-warning text-white h-fit\">"+com.sonnguyen.individual.nhs.constant.AccountTier.GOLD.name+"</span>");
    }
}

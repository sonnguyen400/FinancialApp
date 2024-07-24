package com.sonnguyen.individual.nhs.jsptag;

import com.sonnguyen.individual.nhs.Constant.MemberShip;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class Membership extends SimpleTagSupport {
    private int value;

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        if(value == MemberShip.STANDARD.value) out.println("<span class=\"badge badge-light\">STANDARD</span>");
        else if(value == MemberShip.GOLD.value) out.println("<span class=\"badge badge-light\">GOLD</span>");
        else if(value == MemberShip.DIAMOND.value) out.println("<span class=\"badge badge-light\">DIAMOND</span>");
        else if(value == MemberShip.VIP.value) out.println("<span class=\"badge badge-light\">VIP</span>");
    }
}

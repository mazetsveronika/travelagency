package by.mazets.travelagency.controller.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;

public class CopyRightsTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException,IOException{
        JspWriter out = getJspContext().getOut();
        out.println("Copyright &copy; TravelAgency.by. 2022. All right reserved");
    }
}
package by.mazets.travelagency.controller.command;

import by.mazets.travelagency.controller.Command;
import by.mazets.travelagency.controller.PageContainer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.jstl.core.Config;

/**
 * Class {@code ChangeLocale}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class ChangeLocale implements Command {


    @Override
    public String execute(HttpServletRequest request) {

        String newLocalization = request.getParameter("localization");

        request.getSession().setAttribute("localization", newLocalization);
        Config.set(request.getSession(), Config.FMT_LOCALE, newLocalization);

        return PageContainer.INDEX_PAGE;
    }
}

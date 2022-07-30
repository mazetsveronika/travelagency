package by.mazets.travelagency.controller.command;


import by.mazets.travelagency.controller.Command;
import by.mazets.travelagency.controller.PageContainer;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class {@code EmptyCommand} the command will be executed if the servlet is accessed without a command
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class EmptyCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return PageContainer.ERROR_PAGE;
    }
}

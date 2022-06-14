package by.mazets.travelagency.command.impl;

import by.mazets.travelagency.command.Command;
import by.mazets.travelagency.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class CancelOrderCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}

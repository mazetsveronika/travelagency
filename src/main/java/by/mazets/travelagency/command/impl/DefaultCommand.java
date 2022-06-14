package by.mazets.travelagency.command.impl;

import by.mazets.travelagency.command.Command;
import by.mazets.travelagency.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {

        return null;
    }

   }
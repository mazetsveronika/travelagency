package by.mazets.travelagency.controller;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Interface {@code Command}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public interface Command {

    String execute(HttpServletRequest request);
}

package by.mazets.travelagency.controller;
import by.mazets.travelagency.controller.command.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Class {@code CommandManager}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class CommandManager {

    private static CommandManager instance = new CommandManager();
    private Map<CommandContainer, Command> commands = new HashMap<>();

    private CommandManager() {
        commands.put(CommandContainer.EMPTY_COMMAND, new EmptyCommand());
        commands.put(CommandContainer.REGISTER, new RegisterUserCommand());
        commands.put(CommandContainer.LOGIN, new LogInCommand());
        commands.put(CommandContainer.LOGOUT, new LogOutCommand());
        commands.put(CommandContainer.TO_REGISTER, new ToRegisterPageCommand());
        commands.put(CommandContainer.TO_LOGIN, new ToLoginPageCommand());
        commands.put(CommandContainer.RU, new ChangeLocale());
        commands.put(CommandContainer.EN, new ChangeLocale());
        commands.put(CommandContainer.CHANGE_LOCALE, new ChangeLocale());
        commands.put(CommandContainer.VIEW_ALL_VOUCHERS, new ViewAllVouchers());
        commands.put(CommandContainer.VIEW_VOUCHERS_BY_COUNTRY, new ViewVouchersByCountry());
        commands.put(CommandContainer.VIEW_VOUCHERS_BY_TOUR_TYPE, new ViewVouchersByTourType());
        commands.put(CommandContainer.CHOOSE_VOUCHER, new ToChooseVoucherCommand());
        commands.put(CommandContainer.BOOK_VOUCHER, new BookVoucherCommand());
        commands.put(CommandContainer.CONTINUE, new ContinueRegisterCommand());
        commands.put(CommandContainer.VIEW_ACCOUNT, new ViewAccountCommand());
        commands.put(CommandContainer.UPDATE_BALANCE, new UpdateBalanceCommand());
        commands.put(CommandContainer.TO_USER_MENU, new ToUserMenu());
        commands.put(CommandContainer.SHOW_ORDER_BY_USER_ID, new ShowOrderByUserId());
        commands.put(CommandContainer.CANCEL_ORDER, new CancelOrderCommand());
        commands.put(CommandContainer.ADD_VOUCHER, new AddVoucherCommand());
        commands.put(CommandContainer.UPDATE_TOUR, new UpdateTourCommand());
        commands.put(CommandContainer.UPDATE_DISCOUNT, new UpdateDiscountCommand());

    }

    public static CommandManager getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) {
        CommandContainer command = CommandContainer.valueOf(commandName.toUpperCase());
        return commands.get(command);
    }
}

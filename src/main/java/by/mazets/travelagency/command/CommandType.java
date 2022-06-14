package by.mazets.travelagency.command;

import by.mazets.travelagency.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CommandType {
        DEFAULT( new DefaultCommand()),
        REGISTER(new RegisterUserCommand()),
        LOGIN(new LogInCommand()),
        LOGOUT(new LogOutCommand()),
       // TO_REGISTER(new ToRegisterPageCommand()),
        TO_LOGIN(new ToLoginPageCommand()),
        RU(new ChangeLocale()),
        EN(new ChangeLocale()),
        CHANGE_LOCALE(new ChangeLocale()),
        VIEW_ALL_VOUCHERS(new ViewAllVouchers()),
        VIEW_VOUCHERS_BY_COUNTRY(new ViewVouchersByCountry()),
        VIEW_VOUCHERS_BY_TOUR_TYPE(new ViewVouchersByTourType()),
        CHOOSE_VOUCHER(new ToChooseVoucherCommand()),
        BOOK_VOUCHER(new BookVoucherCommand()),
        CONTINUE(new ContinueRegisterCommand()),
        VIEW_ACCOUNT(new ViewAccountCommand()),
        UPDATE_BALANCE(new UpdateBalanceCommand()),
        TO_USER_MENU(new ToUserMenu()),
        SHOW_ORDER_BY_USER_ID(new ShowOrderByUserID()),
        CANCEL_ORDER(new CancelOrderCommand()),
        ADD_VOUCHER( new AddVoucherCommand()),
        UPDATE_TOUR( new UpdateTourCommand()),
        UPDATE_DISCOUNT( new UpdateDiscountCommand());
    private static final Logger logger = LogManager.getLogger();
    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public static Command defineCommand(String commandType) {
        if (commandType == null || commandType.isEmpty()) {
            return CommandType.DEFAULT.getCommand();
        }
        try {
            return CommandType.valueOf(commandType.toUpperCase()).getCommand();
        } catch (IllegalArgumentException exception) {
            logger.error("Error has occurred while defining command: " + exception);
            return CommandType.DEFAULT.getCommand();
        }
    }
}

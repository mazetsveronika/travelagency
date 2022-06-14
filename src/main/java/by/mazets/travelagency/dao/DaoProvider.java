package by.mazets.travelagency.dao;

import by.mazets.travelagency.dao.impl.UserDaoImpl;

public class DaoProvider {
    private DaoProvider() {
    }


    public static DaoProvider getInstance() {
        return DaoProviderHolder.instance;
    }

    public UserDao getUserDao(boolean isTransaction) {
        return new UserDaoImpl(isTransaction);
    }

    //todo transactions
    private static class DaoProviderHolder {
        private static final DaoProvider instance = new DaoProvider();
    }
}
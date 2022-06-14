package by.mazets.travelagency.dao.mapper.impl;

import by.mazets.travelagency.dao.mapper.Mapper;
import by.mazets.travelagency.entity.User;
import by.mazets.travelagency.entity.type.RoleType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.mazets.travelagency.command.RequestAttribute.*;

public class UserMapper implements Mapper<User> {
    private static final UserMapper instance = new UserMapper();

    private UserMapper() {
    }

    public static UserMapper getInstance() {
        return instance;
    }

    @Override
    public List<User> retrieve(ResultSet resultSet) throws SQLException {//todo!!
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User.UserBuilder()
                    .setId(resultSet.getInt(USER_ID))
                    .setLogin(resultSet.getString(USER_LOGIN))
                    .setSurname(resultSet.getString(USER_SURNAME))
                    .setName(resultSet.getString(USER_NAME))
                    .setEmail(resultSet.getString(USER_EMAIL))
                    .setPhoneNumber(Integer.valueOf(resultSet.getLong(USER_PHONE_NUMBER)))
                    .setUserRole(RoleType.valueOf(resultSet.getString(USER_ROLE).toUpperCase()))
                    .build();
            users.add(user);
        }
        return users;
    }
}

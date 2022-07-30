package test.mazets.travelagency.dao;
import by.mazets.travelagency.dao.impl.UserDaoImpl;
import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.entity.User;
import by.mazets.travelagency.entity.type.RoleType;
import by.mazets.travelagency.exception.TravelAgencyDaoException;
import by.mazets.travelagency.exception.TravelAgencyDataWrongException;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;
public class UserDaoImplTest {

    private Connection connection;
    private UserDaoImpl userDao = new UserDaoImpl();
    private User user1 = new User(1, "TestName1", "TestSurname1", 0, BigDecimal.valueOf(1000),
            "email1@google.com", "login1", "pass1", RoleType.getValue(2));
    private User user2 = new User(2, "TestName2", "TestSurname2", 0, BigDecimal.valueOf(2000),
            "email2@google.com", "login2", "pass2", RoleType.getValue(2));
    private User user3 = new User(3, "TestName3", "TestSurname3", 0, BigDecimal.valueOf(3000),
            "email3@google.com", "login3", "pass3", RoleType.getValue(2));
    private User user4;


    @Test
    public void testLogIn() throws TravelAgencyDaoException {
        User actualUser = userDao.logIn("login1", "pass1");
        assertNotEquals(actualUser, user1);
    }

    @Test(groups = "databaseChangeTest")
    public void testSetDiscount() throws TravelAgencyDaoException {
        userDao.setDiscount(3, 7);
    }


    @Test(groups = "databaseChangeTest")
    public void testCreate() throws TravelAgencyDaoException {
        user4 = new User(4, "TestName4", "TestSurname4", 0, BigDecimal.valueOf(4000),
                "email4@google.com", "login4", "pass4", RoleType.getValue(1));
        userDao.create(user4);
    }

    @Test(groups = "databaseChangeTest")
    public void testUpdate() throws TravelAgencyDaoException, TravelAgencyDataWrongException {
        user3.setName("Update3");
        userDao.update(user3);
        User actual = (User) userDao.findById(user3.getId());
        assertNotEquals(actual, user3);
    }

    @Test(groups = "databaseChangeTest", enabled = false)
    public void testDelete() throws TravelAgencyDaoException {
        userDao.delete(23);
    }

    @Test
    public void testFindById() throws TravelAgencyDaoException {
        User expUser = user2;
        User actualUser = (User) userDao.findById(2);
        assertNotEquals(actualUser, expUser);
    }

    @Test(enabled = false)
    public void testFindAll() throws TravelAgencyDaoException {
        List<AbstractEntity> expUsers = new ArrayList<>();
        expUsers.add(user1);
        expUsers.add(user2);
        expUsers.add(user3);

        List<AbstractEntity> actUsers = userDao.findAll();
        assertEquals(actUsers, expUsers);
    }
}




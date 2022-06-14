package by.mazets.travelagency.service;

import by.mazets.travelagency.entity.User;
import by.mazets.travelagency.entity.type.RoleType;
import by.mazets.travelagency.exception.TravelAgencyServiceException;


import java.io.InputStream;
import java.util.Map;
import java.util.Optional;


public interface UserService {
    
    Optional<User> findUser(String login, String password) throws TravelAgencyServiceException;

   
    Optional<User> findUser(String surname, String name, RoleType roleType) throws TravelAgencyServiceException;

   
    Optional<User> findUser(String login) throws TravelAgencyServiceException;

   
    Map<User, String> findUsers(RoleType roleType) throws TravelAgencyServiceException;

   
    Map<User, String> findUsers(RoleType roleType, int page) throws TravelAgencyServiceException;

   
    Map<User, String> findUsers(Map<String, String> userData, int page) throws TravelAgencyServiceException;



    boolean isLoginOccupied(String login) throws TravelAgencyServiceException;

   
    boolean isEmailOccupied(String email) throws TravelAgencyServiceException;

    
    boolean registerUser(Map<String, String> userData) throws TravelAgencyServiceException;

   
    boolean checkRoles(Map<String, RoleType> usersRoles) throws TravelAgencyServiceException;



    boolean updateRoles(Map<String, RoleType> usersRoles) throws TravelAgencyServiceException;


    boolean updateUserLogin(Map<String, String> userData) throws TravelAgencyServiceException;

   
    boolean updateUserAccountData(Map<String, String> userData) throws TravelAgencyServiceException;


    boolean updatePicture(String login, InputStream pictureStream) throws TravelAgencyServiceException;

    
    Optional<String> loadPicture(String login) throws TravelAgencyServiceException;
//todo

}
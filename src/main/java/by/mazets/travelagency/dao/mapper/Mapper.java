package by.mazets.travelagency.dao.mapper;

import by.mazets.travelagency.entity.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Mapper<T extends AbstractEntity> {

    List<T> retrieve(ResultSet resultSet) throws SQLException;
}
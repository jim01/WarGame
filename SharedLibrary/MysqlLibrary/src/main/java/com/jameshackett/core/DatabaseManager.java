package com.jameshackett.core;

import com.jameshackett.core.annotations.MysqlField;
import com.jameshackett.core.annotations.MysqlTable;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;


public class DatabaseManager {

    private final Connection db;

    public static DatabaseManager getDatabaseManager(DataSource dataSource) throws SQLException {
        Connection conn = dataSource.getConnection();
        return new DatabaseManager(conn);
    }

    private DatabaseManager(Connection db) {
        this.db = db;
    }

    public Connection getConnection() {
        return db;
    }

    public void close(PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            // log this
        }
    }

}

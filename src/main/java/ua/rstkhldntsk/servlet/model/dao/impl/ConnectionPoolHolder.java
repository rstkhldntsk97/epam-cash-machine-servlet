package ua.rstkhldntsk.servlet.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;
import ua.rstkhldntsk.servlet.constants.DatabaseProps;
import ua.rstkhldntsk.servlet.model.dao.DaoFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static ua.rstkhldntsk.servlet.constants.DatabaseProps.*;

public class ConnectionPoolHolder {

    private static volatile ConnectionPoolHolder pool;
    private final BasicDataSource dataSource;

    private ConnectionPoolHolder(){
        BasicDataSource ds = new BasicDataSource();

        try (InputStream inputStream = ConnectionPoolHolder.class.getClassLoader().getResourceAsStream(DATABASE_PROPERTIES_PATH)){
            Properties prop = new Properties();
            prop.load(inputStream);
            ds.setUrl(DATABASE_URL);
            ds.setUsername(DATABASE_USERNAME);
            ds.setPassword(DATABASE_PASSWORD);
            ds.setMinIdle(5);
            ds.setInitialSize(10);
            ds.setMaxIdle(1);
            ds.setMaxOpenPreparedStatements(100);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dataSource = ds;
    }

    public static ConnectionPoolHolder poolHolder() {
        if (pool == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (pool == null) {
                    pool = new ConnectionPoolHolder();
                }
            }
        }
        return pool;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

}

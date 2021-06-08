package ua.rstkhldntsk.servlet.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static ua.rstkhldntsk.servlet.constants.DatabaseProps.*;

public class ConnectionPoolHolder {

    private static volatile DataSource dataSource;

    private static final Logger LOGGER = Logger.getLogger(ConnectionPoolHolder.class);

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (DataSource.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    try (InputStream input = ConnectionPoolHolder.class.getClassLoader().getResourceAsStream(DATABASE_PROPERTIES_PATH)) {
                        Properties prop = new Properties();
                        prop.load(input);
                        ds.setDriverClassName(DATABASE_DRIVER);
                        ds.setUrl(prop.getProperty(DATABASE_URL));
                        ds.setUsername(prop.getProperty(DATABASE_USERNAME));
                        ds.setPassword(prop.getProperty(DATABASE_PASSWORD));
                        ds.setMinIdle(5);
                        ds.setMaxIdle(10);
                        ds.setMaxOpenPreparedStatements(100);
                        dataSource = ds;
                    } catch (IOException ex) {
                        LOGGER.warn(ex);
                    }

                }
            }
        }
        return dataSource;
    }
}

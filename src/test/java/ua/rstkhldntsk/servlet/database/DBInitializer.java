package ua.rstkhldntsk.servlet.database;

import ua.rstkhldntsk.servlet.dao.ConnectionPoolHolder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {

    public static void initializeDatabase() {
        String sql = getQueries();
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getQueries() {
        String sql = "";
        try (FileReader reader = new FileReader("./src/test/resources/before.sql");
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            String line;
            StringBuilder lineBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                lineBuilder.append(line);
            }
            sql = lineBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sql;
    }

}

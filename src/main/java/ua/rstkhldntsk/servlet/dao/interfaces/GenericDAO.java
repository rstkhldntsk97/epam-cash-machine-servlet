package ua.rstkhldntsk.servlet.dao.interfaces;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface GenericDAO<T> {
    /**
     * saves new entity to database
     *
     * @param entity entity
     */
    boolean create(T entity);

    /**
     * updates the entity
     *
     * @param entity entity
     */
    boolean update(T entity);

    /**
     * deletes entity from database
     *
     * @param entity entity
     */
    boolean delete(T entity);

    /**
     * finds all entities
     *
     * @return list of all entities
     */
    List<T> findAll();

    /**
     * closes statement
     */
    default void close(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * closes result set
     */
    default void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * closes connection
     */
    default void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

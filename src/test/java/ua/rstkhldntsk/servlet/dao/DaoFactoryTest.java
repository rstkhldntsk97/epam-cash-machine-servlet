package ua.rstkhldntsk.servlet.dao;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DaoFactoryTest {

    @Test
    public void getInstance() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        assertNotNull(daoFactory);
    }

}

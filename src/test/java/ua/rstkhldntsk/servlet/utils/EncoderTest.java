package ua.rstkhldntsk.servlet.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EncoderTest {

    @Test
    public void encodeTest(){
        assertEquals(Encoder.encodePassword("password"), "5f4dcc3b5aa765d61d8327deb882cf99");
    }

}

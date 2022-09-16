package JavaTest;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.junit.Assert;
import org.junit.Test;

public class TestLocalTime {

    private static String toLocalDate(String deliveryDate) {
        return ZonedDateTime.parse(deliveryDate).toLocalDate().toString();
    }

    @Test
    public void test1(){

        Assert.assertEquals("2021-03-23", toLocalDate("2021-03-23T23:59Z"));

    }
}

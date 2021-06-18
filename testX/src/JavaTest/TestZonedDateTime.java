package JavaTest;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class TestZonedDateTime {

    @Test
    public void test(){
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        ZonedDateTime zonedDateTimeFromLocal = ZonedDateTime.of(localDateTimeNow, ZoneId.of("UTC"));


        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now();
        ZonedDateTime localZonedDateTime = ZonedDateTime.parse("2021-02-23T23:59:00-08:00");
        zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));

        assertTrue(ZonedDateTime.parse("2021-02-23T23:59:00-08:00").isEqual(ZonedDateTime.parse("2021-02-23T23:59:00Z").withZoneSameLocal(ZoneId.of("America/Los_Angeles"))));

        assertTrue(ZonedDateTime.parse("2021-02-23T15:59:00-08:00").isEqual(ZonedDateTime.parse("2021-02-23T23:59:00Z").withZoneSameInstant(ZoneId.of("America/Los_Angeles"))));


    }

    @Test
    public void testAutoBoxingSum(){

        List<Integer> list = Arrays.asList(1,2,3);
        int sum = list.stream().sorted().collect(Collectors.summingInt(Integer::intValue));
    }

    @Test
    public void testOpitonalFlatMap(){
        Object res = Optional.of(2).flatMap(f -> Optional.of(3).flatMap(s -> Optional.of(f + s)));

    }

}

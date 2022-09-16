package JavaTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.Test;

public class TestDecimal {

    @Test
    public void test1() {
        BigDecimal total = new BigDecimal("608.88");
        BigDecimal adminFeeTotal = new BigDecimal("73.49");
        BigDecimal totalDecimal = total.add(adminFeeTotal);
    }


    @Test
    public void test2(){
        double tax1 = 11.2000 + 8.0000;
        double tax2 = 0.0900 + 0.0700;
        double total = tax1 + tax2;
    }
}

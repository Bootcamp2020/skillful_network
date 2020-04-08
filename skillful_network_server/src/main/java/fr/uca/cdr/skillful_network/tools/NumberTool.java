package fr.uca.cdr.skillful_network.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberTool {

public static float round(float value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }    
}

package by.it.demologin.jd01_05.math;

import static java.lang.Math.*;

public class Task2 {
    public static double calculate(double a, double b){
        double y;
        y = pow(1.0 / tan(PI / 4 - 1), 4) + cbrt(a + 1.5) - (b / asin(pow(a, 2)));
        return y;
    }
}

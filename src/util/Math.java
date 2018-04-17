package util;

/**
 * Created by sniper825 on 12/3/17.
 */
public class Math {
    public static long map(long x, long in_min, long in_max, long out_min, long out_max) {
        try {
            return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
        } catch(ArithmeticException e) {
            System.out.print("The map method has been given an invalid ratio");
            return 0;
        }
    }
    public static int map(int x, int in_min, int in_max, int out_min, int out_max) {
        try {
            return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
        } catch(ArithmeticException e) {
            System.out.print("The map method has been given an invalid ratio");
            return 0;
        }
    }
}

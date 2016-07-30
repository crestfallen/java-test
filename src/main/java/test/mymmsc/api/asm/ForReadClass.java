/**
 *
 */
package test.mymmsc.api.asm;

/**
 * @author WangFeng
 */
public class ForReadClass {
    public static String commStr = "Common String value";
    public final String stringField = "Public Final Strng Value";
    final int init = 110;
    final double d = 1.1;
    final Double D = 1.2;
    private final Integer intField = 120;
    String str = "Just a string value";

    public ForReadClass() {
    }

    public void methodA() {
        System.out.println(intField);
    }
}

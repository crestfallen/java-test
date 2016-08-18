/**
 *
 */
package test.mymmsc.api.assembly;

import org.mymmsc.api.assembly.Api;

import java.util.HashMap;

/**
 * @author WangFeng
 */
public class TestO3String {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int iCount = 1000000;
        String s = null;
        HashMap<String, String> list = new HashMap<String, String>();
        int k = 0;
        for (int i = 0; i < iCount; i++) {
            s = Api.o3String(16);
            if (list.containsKey(s)) {
                k += 1;
            } else {
                list.put(s, s);
            }

        }
        System.out.println(k + ": " + k);
    }

}

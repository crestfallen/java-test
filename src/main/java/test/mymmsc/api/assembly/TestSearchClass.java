/**
 *
 */
package test.mymmsc.api.assembly;

import org.mymmsc.api.assembly.Api;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author WangFeng
 */
public class TestSearchClass {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Iterator<?> iterator = ServiceLoader.load(Api.class).iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}

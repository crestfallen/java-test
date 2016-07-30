/**
 *
 */
package test.mymmsc.api.j2ee;

import org.mymmsc.api.assembly.Api;

/**
 * @author wangfeng
 */
public class J2eeAuthorize {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String project = "wifi-api";
        String userId = "SH0001";
        String expire = "2014-01-31 00:00:00";

        String tmpKey = Api.md5(userId + "mymmsc" + expire + "j2ee" + project);
        System.out.println("key=[" + tmpKey + "]");
    }

}

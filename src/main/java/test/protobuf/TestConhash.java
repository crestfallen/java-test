/**
 *
 */
package test.protobuf;

import org.mymmsc.api.algorithms.ConsistentHash;

/**
 * @author WangFeng
 * @date 2015年1月3日 下午6:08:53
 */
public class TestConhash {

    /**
     *
     */
    public TestConhash() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ConsistentHash<String> conHash = new ConsistentHash<String>(100);
        for (int i = 0; i < 3; i++) {
            conHash.addNode("10.1.15.1" + i);
        }
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < 10; i++) {
            String user = "cookie:" + i;
            String value = conHash.getShardInfo(user);
            System.out.println("user=[" + user + "]\t" + "node=" + value);
        }
        String rmHost = "10.1.15.10";
        System.out.println("删除: " + rmHost);
        conHash.delNode(rmHost);
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < 10; i++) {
            String user = "cookie:" + i;
            String value = conHash.getShardInfo(user);
            System.out.println("user=[" + user + "]\t" + "node=" + value);
        }
    }

}

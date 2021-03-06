/**
 *
 */
package test.mymmsc.api.http;

import org.mymmsc.api.io.HttpClient;
import org.mymmsc.api.io.HttpResult;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author wangfeng
 */
public class Upload {

    /**
     * @param args
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        String url = "http://180.169.17.89:1080/wifi-api/upload.cgi";
        // url = "http://localhost:16080/wifi-api/upload.cgi";
        url = "http://a.aoqia.com/ajax/set/companyvideo";
        HttpClient hc = new HttpClient(url, 30);
        // HttpClient hc = new
        // HttpClient("http://devp.ifengzi.cn:38090/role/getuserinfo", 30);

        // hc.addField("userId", 100046);
        String filename = "C:/temp/12.jpg";
        filename = "/Users/wangfeng/temp/vip-com.mp4";
        File file = new File(filename);
        byte[] bytenew = null;
        try {
            if (filename == null || filename.equals("")) {
                throw new NullPointerException("无效的文件路径");
            }
            long len = file.length();
            byte[] bytes = new byte[(int) len];

            BufferedInputStream bufferedInputStream = new BufferedInputStream(
                    new FileInputStream(file));
            int r = bufferedInputStream.read(bytes);
            if (r != len)
                throw new IOException("读取文件不正确");
            bufferedInputStream.close();
            bytenew = bytes;

        } catch (Exception e) {
            e.printStackTrace();
        }

        // hc.addFile("content", "test", "application/octet-stream", bytenew);
        hc.addFile("video", filename, "video/mp4", bytenew);
        HttpResult hRet = hc.post(null, null);
        System.out.println("http-status=[" + hRet.getStatus() + "], body=["
                + hRet.getBody() + "], message=" + hRet.getError());
    }

}

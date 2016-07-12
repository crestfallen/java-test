/**
 * 
 */
package test.mymmsc.api.http;

import org.mymmsc.api.assembly.Api;
import org.mymmsc.api.context.JsonAdapter;
import org.mymmsc.api.io.HttpClient;
import org.mymmsc.api.io.HttpResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangfeng
 * 
 */
public class TestHttpJson {
	public static void main(String argv[]) {
		int timeout = 30;
		String host = "100.66.165.86:8080";
		String uri = "http://" + host + "/dsmp";
		uri += "/hideCollectRecords.cgi";

		String userId = "rrc";
		String ts = Api.toString(System.currentTimeMillis());
		Map<String, String> header = new HashMap<>();
		header.put("DSMP-CTRL", uri);
		HttpResult hRet = null;
		HttpClient hc = new HttpClient(uri, timeout);
		hc.addField("ts", ts);
		hc.addField("token", Api.md5(uri + "com.jiedaibao.dsmp"+ts));
		hRet = hc.post(header, null);
		System.out.println("http-status=[" + hRet.getStatus() + "], body=["
				+ hRet.getBody() + "], message=" + hRet.getError());
		// 服务器正常的话, 应该看到http请求的状态码以及json串
		JsonAdapter json = JsonAdapter.parse(hRet.getBody());
		ZDTest info = json.get(ZDTest.class);
		System.out.println(info);
	}
}

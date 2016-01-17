package test.mymmsc.aio.http.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.mymmsc.aio.http.client.AsyncCompletionHandler;
import org.mymmsc.aio.http.client.AsyncHttpClient;
import org.mymmsc.aio.http.client.AsyncHttpClientConfig;
import org.mymmsc.aio.http.client.Request;
import org.mymmsc.aio.http.client.Response;
import org.mymmsc.aio.http.client.AsyncHttpClient.BoundRequestBuilder;
import org.mymmsc.aio.http.client.AsyncHttpClientConfig.Builder;
import org.mymmsc.aio.http.client.providers.jdk.JDKAsyncHttpProvider;

import test.mymmsc.api.aio.EncodingUtil;

public class TestHttpClient {

	private AsyncHttpClient jdkHttpClient = null;
	private AsyncHttpClientConfig aconfig = null;
	private Builder builder = new AsyncHttpClientConfig.Builder();
	public TestHttpClient() {
		aconfig = builder.setConnectionTTL(60000)
				.setRequestTimeout(60000)
				.setMaxRequestRetry(60000).build();
		jdkHttpClient = new AsyncHttpClient(new JDKAsyncHttpProvider(aconfig),
				aconfig);
	}

	public AsyncHttpClient getHttpClient(String type) {
		return jdkHttpClient;
	}

	public void sendRequest() throws IOException {
		// 请求报文信息
		String body = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hel=\"http://www.primeton.com/HelloService\">"
				+
		"<soapenv:Header/>" +
		"<soapenv:Body>" +
		"<hel:getOpers>" +
		"<!--Optional:-->" +
		// "<hel:in0> apple</hel:in0>"+
		"</hel:getOpers>" +
		"</soapenv:Body>" +
		"</soapenv:Envelope>";
		String httpProviderType = "JDKAsyncHttpProvider";
		AsyncHttpClient httpClient;
		httpClient = getHttpClient(httpProviderType);
		BoundRequestBuilder requestBuilder = null;
		byte[] requestBody = createBody(body, "UTF-8",
				"UTF-8");
		requestBuilder = httpClient
				.preparePost("https://www.baidu.com");// 请求地址
		// 报文相关头信息
		//requestBuilder.setHeader("Host", "localhost:9090");
		requestBuilder.setHeader("Accept-Encoding", "gzip,deflate");
		requestBuilder.setHeader("Connection", "Keep-Alive");
		requestBuilder.setHeader("SOAPAction", "");
		requestBuilder.setHeader("Content-Type", "text/xml;charset=UTF-8");
		requestBuilder.setHeader("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");
		requestBuilder.setBody(requestBody);
		Request request = requestBuilder.build();
		httpClient.executeRequest(request,
				new AsyncCompletionHandler<Object>() {
					public Object onCompleted(Response response) throws Exception {
						String[] headerNames = response.getHeaders()
								.keySet().toArray(new String[0]);
						System.out.println(response.getResponseBody());
						System.out.println("---------------------------------------");
						System.out.println(formatXML(
								EncodingUtil.changeCharset(response.getResponseBody(), "iso-8859-1", "UTF-8")));
						return null;
					}
				});
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private byte[] createBody(Object body, String requestEncoding,
			String currentEncoding) throws UnsupportedEncodingException {
		String oldEncoding = currentEncoding != null ? currentEncoding
				: "utf-8";
		try {
			if (body instanceof String) {
				if (requestEncoding.equalsIgnoreCase(oldEncoding)) {
					return ((String) body).getBytes(requestEncoding);
				} else {
					byte[] bytes = EncodingUtil.convertByCharset((String) body,
							oldEncoding, requestEncoding);

					return bytes;
				}
			} else if (body instanceof byte[]) {
				if (requestEncoding.equalsIgnoreCase(oldEncoding)) {
					return (byte[]) body;
				} else {
					byte[] bytes = EncodingUtil.changeCharset((byte[]) body,
							oldEncoding, requestEncoding);
					return bytes;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return (byte[]) body;
	}

	// 格式化XML

	public String formatXML(String inputXML) throws Exception {
		return inputXML;
	}

	/**
	 * 
	 * 测试函数入口
	 * 
	 * @param args
	 * 
	 * @throws IOException
	 * 
	 */

	public static void main(String[] args) throws IOException {
		TestHttpClient client = new TestHttpClient();
		client.sendRequest();
	}
}
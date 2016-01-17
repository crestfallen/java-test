/**
 * 
 */
package test.mymmsc.aio.http.client;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.mymmsc.aio.http.client.*;
import org.mymmsc.aio.http.client.providers.jdk.JDKAsyncHttpProvider;

/**
 * @author wangfeng
 * @date 2016年1月17日 下午7:40:30
 */
public class TestAsynvHttpClient {
	private static AsyncHttpClientConfig aconfig = null;
	private static org.mymmsc.aio.http.client.AsyncHttpClientConfig.Builder builder = new AsyncHttpClientConfig.Builder();
	/**
	 * 
	 */
	public TestAsynvHttpClient() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//AsyncHttpClient c = new AsyncHttpClient();
		aconfig = builder.setConnectTimeout(10000)
	               .setRequestTimeout(10000)
	               .setMaxRequestRetry(10000).build();
		
		AsyncHttpClient c = new AsyncHttpClient(new JDKAsyncHttpProvider(aconfig), aconfig);
		Future<String> f = c.prepareGet("http://www.baidu.com/").execute(new AsyncHandler<String>() {
		    private ByteArrayOutputStream bytes = new ByteArrayOutputStream();

		    @Override
		    public STATE onStatusReceived(HttpResponseStatus status) throws Exception {
		        int statusCode = status.getStatusCode();
		        // The Status have been read
		        // If you don't want to read the headers,body or stop processing the response
		        if (statusCode >= 500) {
		            return STATE.ABORT;
		        }
				return STATE.CONTINUE;
		    }

		    @Override
		    public STATE onHeadersReceived(HttpResponseHeaders h) throws Exception {
		        FluentCaseInsensitiveStringsMap headers = h.getHeaders();
		         // The headers have been read
		         // If you don't want to read the body, or stop processing the response
		         return STATE.CONTINUE;
		    }

		    @Override
		    public STATE onBodyPartReceived(HttpResponseBodyPart bodyPart) throws Exception {
		         bytes.write(bodyPart.getBodyPartBytes());
		         return STATE.CONTINUE;
		    }

		    @Override
		    public String onCompleted() throws Exception {
		         // Will be invoked once the response has been fully read or a ResponseComplete exception
		         // has been thrown.
		         // NOTE: should probably use Content-Encoding from headers
		         return bytes.toString("UTF-8");
		    }

		    @Override
		    public void onThrowable(Throwable t) {
		    	//
		    }
		});

		try {
			String bodyResponse = f.get();
			System.out.println(bodyResponse);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

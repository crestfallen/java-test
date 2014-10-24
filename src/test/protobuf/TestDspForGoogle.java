/**
 * 
 */
package test.protobuf;

import org.mymmsc.api.crypto.Base64;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.sun.xml.internal.ws.util.ByteArrayBuffer;

/**
 * @author wangfeng
 * @date 2014年10月25日 上午9:42:57
 */
public class TestDspForGoogle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "EhBUSk1iAAGlQgq8FpaOABf9IgPaydkyU01vemlsbGEvNS4wIChjb21wYXRpYmxlOyBNU0lFIDkuMDsgV2luZG93cyBOVCA2LjE7IFRyaWRlbnQvNS4wKSxnemlwKGdmZSksZ3ppcChnZmUpWjNodHRwOi8vc3BvcnRzLmlmZW5nLmNvbS9hLzIwMTQxMDI0LzQyMjg2OTc5XzAuc2h0bWxiBXpoX0NOaggItQgVAACAP2oICNMBFQAAQD9y1AIIARB4GNgEIg9GHiAnFg0ODxAREhMUGTQyjwEKHCorMzw/XF5xfoABggGQAZEBkgGcAbMBtgHGAcwB4QHiAeMB5AG0BOYB5wHoAekB7AHtAe4B/wGEAosCnQKvArsCvALFAssCzALOAs8C1gKeA6wDsAO5A70D2APaA9wD3QPgA+ED5QPmA+kD6gPxA/gDiASRBJYEmQSaBJsEngSfBKYEpwSpBLYErASyBDoIExcKAwUYBxJKEBDl9tWnCSjA7m06BDDA7m1SGXBhY2stYnJhbmQt5Yek5Yew572ROjpBbGxSL3BhY2stYnJhbmQt5Yek5Yew572ROjrlm77niYfpobUsIOWkmuS4quS9jee9riAyYAJqJptOqE7lTudO6U7rTplP1VHpUYxSsFOVW7NctFzXaq9O+k7vaP5pcICnq+oLee3ECLag6MDIiAEAkAEAmAEAeACgAQGqARtDQUVTRURFYTFJZ25wVXltczBxY1dwNmVQTTDAAQLIAeAD0gECGij4AYfSBqACHrgCiYA/yALjFtEC/r6dIHgrIis=";
		String dest = null;
		byte[] bytes = null;
		Base64 dcoder = new Base64();
		dest = dcoder.decode(str);
		System.out.println(dest);
		try {
			RtbG.BidRequest.Builder builder = RtbG.BidRequest.newBuilder();
			ByteString bs = null;
			bs = ByteString.copyFromUtf8("1234");
			builder.setId(bs);
			bs = ByteString.copyFromUtf8("127.0.0.1");
			builder.setIp(bs);
			RtbG.BidRequest req = builder.build();
			
			bytes = req.toByteArray();
			req = RtbG.BidRequest.parseFrom(bytes);
			System.out.println("proto buf[" + req + "]");
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}

	}

}

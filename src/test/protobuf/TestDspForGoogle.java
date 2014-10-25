/**
 * 
 */
package test.protobuf;

import java.io.UnsupportedEncodingException;

import org.mymmsc.api.encoding.Base64;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

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
		byte[] dest = null;
		byte[] bytes = null;
		
		//dest = dcoder.decode(str);
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
			bytes = Base64.decode(str);
			req = RtbG.BidRequest.parseFrom(bytes);
			System.out.println("proto buf[" + req + "]");
			bytes = new byte[2];
			bytes[0] = 0x20;
			bytes[1] = 0x01;
			
			RtbG.BidResponse resp = RtbG.BidResponse.parseFrom(bytes);
			System.out.println("proto buf[" + resp + "]");
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}

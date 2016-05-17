package test.mymmsc.api.aio;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;

import java.io.UnsupportedEncodingException;

import java.util.zip.GZIPInputStream;

public class EncodingUtil {

	/**
	 * 
	 * @param str
	 * @param newCharset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String newCharset)
			throws UnsupportedEncodingException {
		if (str != null) {
			// 用默认字符编码解码字符串。
			byte[] bs = str.getBytes();
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	/**
	 * 
	 * @param str
	 * @param oldCharset
	 * @param newCharset
	 * @return
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String oldCharset,
			String newCharset) throws UnsupportedEncodingException {
		if (str != null) {
			// 用旧的字符编码解码字符串。解码可能会出现异常。
			byte[] bs = str.getBytes(oldCharset);
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}

		return null;
	}

	/**
	 * 
	 * @param bytes
	 * @param oldCharset
	 * @param newCharset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] changeCharset(byte[] bytes, String oldCharset,
			String newCharset) throws UnsupportedEncodingException {
		byte[] b = null;
		String s = null;
		if (bytes != null) {// add by zhangyong增加判断返回报文的编码格式为gzip时进行解压缩
			if ("gzip".equals(oldCharset)) {
				b = unGZip(bytes);
				s = new String(b);
			} else {
				s = new String(bytes, oldCharset);
			}
			// 用旧的编码解码字节。解码可能会出现异常。
			// s = new String(bytes, oldCharset);
			// 用新的编码生成字节
			return s.getBytes(newCharset);
		}
		return null;
	}

	/**
	 * @param bytes
	 * @param newCharset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] changeCharset(byte[] bytes,
			String newCharset) throws UnsupportedEncodingException {
		if (bytes != null) {
			// 用缺省的编码解码字节。解码可能会出现异常。
			String s = new String(bytes);
			// 用新的编码生成字节
			return s.getBytes(newCharset);
		}
		return null;
	}

	/**
	 * 
	 * @param str
	 * 
	 * @param stringCharset
	 * @param newCharset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] convertByCharset(String str, String stringCharset,
			String newCharset) throws UnsupportedEncodingException {
		if (str != null) {
			String newString = changeCharset(str, stringCharset, newCharset);
			return newString.getBytes(newCharset);
		}
		return null;
	}

	/**
	 * 
	 * @param bytes
	 * @param bytesCharset
	 * @param newCharset
	 * @return 
	 * @throws UnsupportedEncodingException
	 */
	public static String convertByCharset(byte[] bytes, String bytesCharset,
			String newCharset) throws UnsupportedEncodingException {
		if (bytes != null) {
			byte[] newBytes = changeCharset(bytes, bytesCharset, newCharset);
			return new String(newBytes, newCharset);
		}
		return null;
	}

	/**
	 * 解压缩gzip格式数据
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] unGZip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			GZIPInputStream gzip = new GZIPInputStream(bis);
			byte[] buf = new byte[data.length];
			int num = -1;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((num = gzip.read(buf, 0, buf.length)) != -1) {
				baos.write(buf, 0, num);
			}
			b = baos.toByteArray();
			baos.flush();
			baos.close();
			gzip.close();
			bis.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}
}
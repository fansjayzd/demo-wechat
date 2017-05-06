package cn.wechat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 签名验证工具类
 * 
 * @author USER
 */
public class SignUtil {
	private static String token = "myWeChatToken";

	/**
	 * 校验签名
	 * 
	 * @param signature 微信加密签名
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		// 对token、timestamp和nonce按字典排序
		String[] paramArr = new String[] { token, timestamp, nonce };
		Arrays.sort(paramArr);

		// 将排序后的结果拼接成一个字符串
		String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);

		String ciphertext = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// 对接后的字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			ciphertext = byteToStr(digest);
			//System.out.println(ciphertext+"-------------");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// 将sha1加密后的字符串与signature进行对比
		return ciphertext != null ? ciphertext.equals(signature.toUpperCase()) : false;
	}
	
	public static String DataSecurity(String arg) {
		String ciphertext = "";
		try {
			//创建MessageDigest对象
			MessageDigest md = MessageDigest.getInstance("MD5");//MD5或SHA-1算法
			//对字符串进行加密，返回16位加密字节数组
			byte[] digest = md.digest(arg.getBytes());
			//调用byteToStr方法将字节数组转换为字符串
			ciphertext = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return ciphertext;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		//字节高4位 移4位与运算 得到的值作为下标
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		//将char数组转换为字符串
		String s = new String(tempArr);
		return s;
	}
	
	public static String DataSecurity2(String arg) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer ciphertext = new StringBuffer();
		try {
			//创建MessageDigest对象
			MessageDigest md = MessageDigest.getInstance("SHA-1");//MD5或SHA-1算法
			//对字符串进行加密，返回16位加密字节数组
			byte[] digest = md.digest(arg.getBytes());
			/**
			 * 将加密后的字节数组的每个字符高4位、低4位于0X0F移位运算得到的值作为Digit数组下标
			 * 获取对应位置的字节拼接成一个32为的字符串
			 */
			for (int i = 0; i < digest.length; i++) {
				//字节高4位 移4位与0X0F运算 得到的值作为下标
				ciphertext.append(Digit[(digest[i] >>> 4) & 0X0F]);
				//字节底4位 与0X0F运算 得到的值作为下标
				ciphertext.append(Digit[digest[i] & 0X0F]);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return ciphertext.toString();
	}
	
	public static void main(String[] args) {
//		String aaa = DataSecurity2("123456");
//		System.out.println(aaa);
		char a = 'A';
		String aa = a + "";
//		int num = 1|4;
//		System.out.println(num);
		System.out.println(a);
		System.out.println(aa);
	}
}

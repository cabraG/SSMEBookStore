package Utils;


import java.util.UUID;



/**
 * 小小工具
 * @author qdmmy6
 *
 */
public class CommonUtils {
	/**
	 * 返回一个不重复的字符串
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}



}

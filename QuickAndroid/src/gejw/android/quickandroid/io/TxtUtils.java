package gejw.android.quickandroid.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class TxtUtils {
	/**
	 * 写入文本文件
	 * 
	 * @param path
	 * @param str
	 */
	public static boolean writeFile(String path, String str,String encode) {
		return writeFile(path, str, encode, false);
	}
		/**
		 * 写入文本文件
		 * 
		 * @param path
		 * @param str
		 */
		public static boolean writeFile(String path, String str,String encode,boolean append) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(path),append);
			Writer os = new OutputStreamWriter(fos, encode);
			os.write(str);
			os.flush();
			fos.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 读取文本文件
	 * 
	 * @param path
	 *            文件路径
	 * @return 文本文件内容
	 */
	public static String readFile(String path) {
		File file = new File(path);
		BufferedReader reader = null;
		String laststr = "";
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = "";
			while ((tempString = reader.readLine()) != null) {
				laststr = laststr + tempString;
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}
}

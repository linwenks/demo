package com.hy.demo.common.base.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取文件实际类型工具
 * 
 * @author Tank
 */
@Slf4j
public class FileTypeUtil {

	// 文件类型
	private final static Map<String, String> FILE_TYPE_MAP = new HashMap() {{
		put("ffd8ffe000104a464946", "jpg"); // JPEG (jpg)
		put("89504e470d0a1a0a0000", "png"); // PNG (png)
		put("47494638396126026f01", "gif"); // GIF (gif)
		put("49492a00227105008037", "tif"); // TIFF (tif)
		put("424d228c010000000000", "bmp"); // 16色位图(bmp)
		put("424d8240090000000000", "bmp"); // 24位位图(bmp)
		put("424d8e1b030000000000", "bmp"); // 256色位图(bmp)
		put("41433130313500000000", "dwg"); // CAD (dwg)
		put("3c21444f435459504520", "html"); // HTML (html)
		put("3c21646f637479706520", "htm"); // HTM (htm)
		put("48544d4c207b0d0a0942", "css"); // css
		put("696b2e71623d696b2e71", "js"); // js
		put("7b5c727466315c616e73", "rtf"); // Rich Text Format
		// (rtf)
		put("38425053000100000000", "psd"); // Photoshop (psd)
		put("46726f6d3a203d3f6762", "eml"); // Email [Outlook
		// Express 6] (eml)
//		put("d0cf11e0a1b11ae10000", "doc"); // MS Excel
		// 注意：word、msi 和
		// excel的文件头一样
//		put("d0cf11e0a1b11ae10000", "vsd"); // Visio 绘图
		put("5374616E64617264204A", "mdb"); // MS Access (mdb)
		put("252150532D41646F6265", "ps");
		put("255044462d312e360d25", "pdf"); // Adobe Acrobat (pdf)
		put("2e524d46000000120001", "rmvb"); // rmvb/rm相同
		put("464c5601050000000900", "flv"); // flv与f4v相同
		put("00000020667479706973", "mp4");
		put("49443303000000000f76", "mp3");
		put("000001ba210001000180", "mpg"); //
		put("3026b2758e66cf11a6d9", "wmv"); // wmv与asf相同
		put("524946464694c9015741", "wav"); // Wave (wav)
		put("52494646d07d60074156", "avi");
		put("4d546864000000060001", "mid"); // MIDI (mid)
		put("504b0304140000000800", "zip");
		put("526172211a0700cf9073", "rar");
		put("235468697320636f6e66", "ini");
		put("504b03040a0000000000", "jar");
		put("4d5a9000030000000400", "exe");// 可执行文件
		put("3c25402070616765206c", "jsp");// jsp文件
		put("4d616e69666573742d56", "mf");// MF文件
		put("3c3f786d6c2076657273", "xml");// xml文件
		put("efbbbf2f2a0d0a53514c", "sql");// xml文件
		put("7061636b616765207765", "java");// java文件
		put("406563686f206f66660d", "bat");// bat文件
		put("1f8b0800000000000000", "gz");// gz文件
		put("6c6f67346a2e726f6f74", "properties");// bat文件
		put("cafebabe0000002e0041", "class");// bat文件
		put("49545346030000006000", "chm");// bat文件
		put("04000000010000001300", "mxp");// bat文件
		put("504b0304140006000800", "docx");// docx文件
//		put("d0cf11e0a1b11ae10000", "wps");// WPS文字wps、表格et、演示dps都是一样的
		put("6431303a637265617465", "torrent");
		put("494d4b48010100000200", "264");

		put("6D6F6F76", "mov"); // Quicktime (mov)
		put("FF575043", "wpd"); // WordPerfect (wpd)
		put("CFAD12FEC5FD746F", "dbx"); // Outlook Express (dbx)
		put("2142444E", "pst"); // Outlook (pst)
		put("AC9EBD8F", "qdf"); // Quicken (qdf)
		put("E3828596", "pwl"); // Windows Password (pwl)
		put("2E7261FD", "ram"); // Real Audio (ram)
	}};

	private FileTypeUtil() {
	}

	/**
	 * 得到上传文件的文件头
	 * 
	 * @param src
	 * @return String
	 */
	private static String bytesToHexString(byte[] src) {
		var sb = new StringBuilder();
		if (null == src || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			var hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				sb.append(0);
			}
			sb.append(hv);
		}
		return sb.toString();
	}

	/**
	 * 获取文件类型
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 文件类型
	 */
	public static String getFileType(String filePath) {
		if (StringUtils.isBlank(filePath)) {
			return null;
		}
		var file = new File(filePath);
		if (!file.exists()) {
			log.info("文件[" + filePath + "]不存在！");
			return null;
		}
		return getFileType(file);
	}

	/**
	 * 获取文件类型
	 * 
	 * @param file 文件
	 * @return 文件类型
	 */
	public static String getFileType(File file) {
		try (var fis = new FileInputStream(file)) {
			return getFileType(fis);
		} catch (IOException e) {
			log.error(" error ", e);
		}
		return null;
	}

	/**
	 * 获取文件类型
	 *
	 * @param is 流
	 * @return 文件类型
	 */
	public static String getFileType(InputStream is) {
		try {
			// 获取文件头的前六位
			byte[] b = new byte[3];
			is.read(b, 0, b.length);
			var fileCode = bytesToHexString(b).toLowerCase();

			return FILE_TYPE_MAP.entrySet().stream().filter(e -> {
				// 比较前几位是否相同就可以判断文件格式（相同格式文件文件头后面几位会有所变化）
				var key = e.getKey().toLowerCase();
				return key.startsWith(fileCode) || fileCode.startsWith(key);
			}).map(Map.Entry::getValue).findAny().orElse(null);
		} catch (IOException e) {
			log.error(" error ", e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				log.error(" error ", e);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(getFileType("C:\\Users\\Administrator\\Desktop\\微信图片_20200824175619.png"));
	}
}
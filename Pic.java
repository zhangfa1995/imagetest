/*
 *思路：
 *我想到的两个关键是总解码时间可能大于总播放时间，所以要提前缓存一部分解码，然后可能总缓存等于总播放但是某些帧市场短于帧解码的时间
 *所以我考虑的方法是预缓存10帧，然后再预缓存总播放时间大于总解码时间那部分的帧数*/
package com;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Pic {
	public static void main(String[] args) {
		String strImg = GetImageStr();
		System.out.println(strImg);
		GenerateImage(strImg);
	}
	public static String GetImageStr() {
		String imgFile = "c;\\1.jpg";
		InputStream in =null;
		byte[] data =null;
		try {
			in = new FileInputStream(imgFile);
			data =new byte[in.available()];
			in.read(data);
			in.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Enoder();
		return encoder.encode(data);
	}
	public static boolean GenerateImage(String imgStr) {
		if(imgStr == null)return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(imgStr);
			for(int i=0;i<b.length;i++) {
				if(b[i]<0) {
					b[i]+=256;
				}
			}
			String imgFilePath = "c:\\2.jpg";
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
		}catch(Exception e) {
			return false;
		}
	}
}

package _22T1020329.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Configuration {

	private String LoaiNguon;
	private String ChuoiKetNoi;
	
	public String getLoaiNguon() {
		return LoaiNguon;
	}

	public String getChuoiKetNoi() {
		return ChuoiKetNoi;
	}

	private static Configuration instance = null;

	private Configuration(String loaiNguon, String chuoiKetNoi) {
		LoaiNguon = loaiNguon;
		ChuoiKetNoi = chuoiKetNoi;
	}
	
	public static Configuration getInstance() {
		if(instance == null) {
			try {
				FileReader fr = new FileReader("config/config.txt");
				BufferedReader br = new BufferedReader(fr);
				String LoaiNguon = br.readLine();
				String ChuoiKetNoi = br.readLine();
				instance = new Configuration(LoaiNguon, ChuoiKetNoi);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
}

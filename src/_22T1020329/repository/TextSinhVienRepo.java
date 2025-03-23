package _22T1020329.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import _22T1020329.config.NgaySinhFormat;
import _22T1020329.entity.SinhVien;

public class TextSinhVienRepo implements SinhVienRepo {

	private String path;// vd: .../sinhvien.txt

	public TextSinhVienRepo(String path) {
		this.path = path;
	}

	@Override
	public List<SinhVien> getAllSinhVien() {
		try {
			ArrayList<SinhVien> dssv = new ArrayList<SinhVien>();
			SimpleDateFormat f = NgaySinhFormat.getSimpleDateFormat();
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				if(line.equals("")) continue;
				String[] t_line = line.split(",");
				dssv.add(SinhVien.builder().MaSinhVien(t_line[0]).HoTen(t_line[1])
						.LaGioiTinhNam(Boolean.parseBoolean(t_line[2])).NgaySinh(f.parse(t_line[3])).build());
			}
			br.close();
			fr.close();
			return dssv;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SinhVien addSinhVien(SinhVien sv) {
		try {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			boolean daTonTaiSinhVien = false;
			String line;
			while ((line = br.readLine()) != null) {
				String[] t_line = line.split(",");
				if (sv.getMaSinhVien().equals(t_line[0])) {
					daTonTaiSinhVien = true;
					break;
				}
			}
			br.close();
			fr.close();
			if (!daTonTaiSinhVien) {
				FileWriter fw = new FileWriter(path, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.newLine();
				bw.write(sv.toString());
				bw.close();
				fw.close();
				return sv;
			}
			return null;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deletesinhVien(String maSinhVien) {
		try {
			List<SinhVien> dssv = getAllSinhVien();
			FileWriter fw = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i = 0; i < dssv.size(); i++) {
				SinhVien sv = dssv.get(i);
				if(sv.getMaSinhVien().equals(maSinhVien)) {
					continue;
				}
				bw.write(sv.toString());
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

package _22T1020329.view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import _22T1020329.config.Configuration;
import _22T1020329.config.NgaySinhFormat;
import _22T1020329.entity.SinhVien;
import _22T1020329.factory.DBSinhVienRepoFactory;
import _22T1020329.factory.SinhVienRepoFactory;
import _22T1020329.factory.TextSinhVienRepoFactory;
import _22T1020329.repository.SinhVienRepo;

public class ChuongTrinh {

	public static void main(String[] args) {
		Configuration configuration = Configuration.getInstance();
		SinhVienRepo sinhVienRepo = null;
		SinhVienRepoFactory sinhVienRepoFactory = null;
		if(configuration.getLoaiNguon().equals("TEXT_FILE")) {
			sinhVienRepoFactory = new TextSinhVienRepoFactory(configuration.getChuoiKetNoi());
		} else if(configuration.getLoaiNguon().equals("MS_SQLSERVER")) {
			sinhVienRepoFactory = new DBSinhVienRepoFactory(configuration.getChuoiKetNoi());
		}
		sinhVienRepo = sinhVienRepoFactory.getSinhVienRepo();
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat f = NgaySinhFormat.getSimpleDateFormat();
		while(true) {
			System.out.println("[1] In danh sách sinh viên");
			System.out.println("[2] Thêm sinh viên");
			System.out.println("[3] Xóa sinh viên");
			System.out.println("[4] EXIT");
			System.out.print(">>> Nhập tùy chọn: ");
			
			int tuyChon = Integer.parseInt(sc.nextLine());
			if(tuyChon == 1) {
				List<SinhVien> dssv = sinhVienRepo.getAllSinhVien();
				for(SinhVien sv : dssv) {
					System.out.println(sv.toString());
				}
			}
			else if(tuyChon == 2) {
				try {
					System.out.print("Nhập mã sinh viên: ");
					String MaSinhVien = sc.nextLine();
					System.out.print("Nhập họ và tên: ");
					String HoTen = sc.nextLine();
					System.out.print("Là giới tính nam?(true/false): ");
					boolean LaGioiTinhNam = Boolean.parseBoolean(sc.nextLine());
					System.out.print("Nhập ngày sinh: ");
					Date NgaySinh = f.parse(sc.nextLine());
					SinhVien sv = new SinhVien(MaSinhVien, HoTen, LaGioiTinhNam, NgaySinh);
					
					if(sinhVienRepo.addSinhVien(sv) != null) {
						System.out.println("Đã thêm một sinh viên: " + sv.toString());
					}
					else {
						System.out.println("Thêm thất bại!");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			else if(tuyChon == 3) {
				System.out.print("Nhập mã sinh viên: ");
				String MaSinhVien = sc.nextLine();
				sinhVienRepo.deletesinhVien(MaSinhVien);
				System.out.println("Đã xóa thành công");
			}
			else if(tuyChon == 4) {
				return;
			}
			else {
				System.out.println("Tùy chọn không hợp lệ! Vui lòng nhập lại!");
			}
		}
		
	}
	
}

package _22T1020329.repository;

import java.util.List;

import _22T1020329.entity.SinhVien;

public interface SinhVienRepo {
	public List<SinhVien> getAllSinhVien();
	public SinhVien addSinhVien(SinhVien sv);
	public void deletesinhVien(String maSinhVien);

}

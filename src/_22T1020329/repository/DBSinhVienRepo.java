package _22T1020329.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import _22T1020329.entity.SinhVien;

public class DBSinhVienRepo implements SinhVienRepo {
	
	private String DBUrl;
	
	public DBSinhVienRepo(String DBUrl) {
		this.DBUrl = DBUrl;
	}
	
	private Connection connection;
	
	private Connection getConnection() {
		if(connection == null) {
			try {
				connection = DriverManager.getConnection(DBUrl);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return connection;
	}

	@Override
	public List<SinhVien> getAllSinhVien() {
		List<SinhVien> lstSV = new ArrayList<SinhVien>();
		try {
			String sql = "select * from SinhVien";
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				SinhVien sv = SinhVien.builder()
					.MaSinhVien(resultSet.getString("MaSinhVien"))
					.HoTen(resultSet.getString("HoTen"))
					.LaGioiTinhNam(resultSet.getBoolean("GioiTinhNam"))
					.NgaySinh(new java.util.Date(resultSet.getDate("NgaySinh").getTime()))
					.build();
				lstSV.add(sv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstSV;
	}

	@Override
	public SinhVien addSinhVien(SinhVien sv) {
		try {
			String sql = "insert into SinhVien(MaSinhVien, HoTen, GioiTinhNam, NgaySinh) values(?, ?, ?, ?)";
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setString(1, sv.getMaSinhVien());
			preparedStatement.setString(2, sv.getHoTen());
			preparedStatement.setBoolean(3, sv.isLaGioiTinhNam());
			preparedStatement.setDate(4, new java.sql.Date(sv.getNgaySinh().getTime()));
			if(preparedStatement.executeUpdate() == 0) {
				return null;
			};
			return sv;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deletesinhVien(String maSinhVien) {
		try {
			String sql = "delete from SinhVien where MaSinhVien = ?";
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setString(1, maSinhVien);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

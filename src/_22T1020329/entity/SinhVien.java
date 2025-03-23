package _22T1020329.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import _22T1020329.config.NgaySinhFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinhVien {
	String MaSinhVien;
	String HoTen;
	boolean LaGioiTinhNam;
	Date NgaySinh;
	
	@Override
	public String toString() {
		SimpleDateFormat f = NgaySinhFormat.getSimpleDateFormat();
		return MaSinhVien + "," + HoTen + "," + String.valueOf(LaGioiTinhNam) + "," + f.format(NgaySinh);
	}
}

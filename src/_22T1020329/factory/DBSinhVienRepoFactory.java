package _22T1020329.factory;

import _22T1020329.repository.DBSinhVienRepo;
import _22T1020329.repository.SinhVienRepo;

public class DBSinhVienRepoFactory extends SinhVienRepoFactory {

	private String DBUrl;
	
	public DBSinhVienRepoFactory(String DBUrl) {
		this.DBUrl = DBUrl;
	}

	@Override
	public SinhVienRepo getSinhVienRepo() {
		return new DBSinhVienRepo(DBUrl);
	}

}

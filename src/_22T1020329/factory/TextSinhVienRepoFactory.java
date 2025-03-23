package _22T1020329.factory;

import _22T1020329.repository.SinhVienRepo;
import _22T1020329.repository.TextSinhVienRepo;

public class TextSinhVienRepoFactory extends SinhVienRepoFactory {
	
	private String path;
	
	public TextSinhVienRepoFactory(String path) {
		this.path = path;
	}

	@Override
	public SinhVienRepo getSinhVienRepo() {
		return new TextSinhVienRepo(path);
	}

}

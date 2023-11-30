import java.util.Scanner;

public class DuAnCaNhan extends DuAn {
	public Scanner sc = new Scanner(System.in);
//field
	private NhanVien nv; 
//constructor
	public DuAnCaNhan() {
		super();
		nv = new NhanVien();
	}

	public DuAnCaNhan(int maDuAn,String tenDuAn, double loiNhuan, double nganSach, NhanVien nv) {
		super(maDuAn, tenDuAn, loiNhuan, nganSach);
		this.nv = nv;
	}

//nhap
	public void nhap(NhanVien nv) {
		super.nhap();
		this.nv=nv;
	}
	
//xuat
	@Override public void xuat() {
		super.xuat();
		nv.xuat();
	}

//get-set
	public NhanVien getNhanVien(){
		return nv;
	}

	public void setNhanVien(NhanVien nv){
		this.nv=nv;
	}
}
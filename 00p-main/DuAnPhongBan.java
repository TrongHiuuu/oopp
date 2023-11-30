import java.util.Scanner;

public class DuAnPhongBan extends DuAn {
	public Scanner sc = new Scanner(System.in);

//field
	private int maPb;
	private String tenPb;

//constructor
	public DuAnPhongBan() {
		super();
		maPb=0;
		tenPb="";
	}

	public DuAnPhongBan(int maDuAn,String tenDuAn, double loiNhuan, double nganSach, int maPb, String tenPb) {
		super(maDuAn, tenDuAn, loiNhuan, nganSach);
		this.maPb=maPb;
		this.tenPb=tenPb;
	}

//nhap
	public void nhap(int maPb, String tenPb) {
		super.nhap();
		this.maPb = maPb;
		this.tenPb = tenPb;
	}

//xuat
	@Override public void xuat() {
		super.xuat();
		System.out.println("maPb: "+maPb);
		System.out.println("tenPb: "+tenPb);
	}

//get-set
	public int getMaPb(){
		return maPb;
	}

	public void setMaPb(int maPb){
		this.maPb=maPb;
	}

	public String getTenPb(){
		return tenPb;
	}

	public void setTenPb(String tenPb){
		this.tenPb=tenPb;
	}

//kiem tra dieu kien
	public boolean checkMaPb(){
		if(maPb<1000 || maPb>9999){
			System.out.println("Can nhap ma co 4 chu so!");
			return true;
		}
		return false;
	}
}
	
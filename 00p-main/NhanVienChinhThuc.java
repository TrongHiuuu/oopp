
public class NhanVienChinhThuc extends NhanVien{
	//Các thuộc tính mới
	private double tienBaoHiem;
	
	//Các hàm get và set mới
		//*** Các hàm get ***
	public double getTienBaoHiem() {
		return tienBaoHiem;
	}
	
		//*** Các hàm set ***
	public void setTienBaoHiem(double newTienBaoHiem) {
		if (tienBaoHiemKhongHopLe(newTienBaoHiem)) return;
		tienBaoHiem = newTienBaoHiem;
	}
	
	//Các hàm điều kiện
	public static boolean tienBaoHiemKhongHopLe(double newTienBaoHiem) {
		if(newTienBaoHiem < 0) return true;
		return false;
	}
	
	//Các constructor
		//*** Constructor không tham số ***
	public NhanVienChinhThuc() {
		super();
		tienBaoHiem = 0;
	}
	
		//*** Constructor có tham số ***
	public NhanVienChinhThuc(int newID, String newHo, String newTen, String newNgaySinh, int newMaPhongBan,
			double newTienBaoHiem) {
		super(newID, newHo, newTen, newNgaySinh, newMaPhongBan);
		tienBaoHiem = newTienBaoHiem;
		if(tienBaoHiemKhongHopLe(tienBaoHiem)) tienBaoHiem = 0;
	}
	
	//Các hàm nhập và xuất
			//*** Hàm nhập ***
		
				//***** Các hàm nhập thành phần mới *****
	public void nhapTienBaoHiem() {
		double newTienBaoHiem;
		System.out.print("Nhap tien bao hiem: ");
		newTienBaoHiem = Double.parseDouble(scan.nextLine());
		while(tienBaoHiemKhongHopLe(newTienBaoHiem)) {
			System.out.println("Tien bao hiem khong hop le!! (Phai lon hon 0)");
			System.out.print("Nhap tien bao hiem: ");
			newTienBaoHiem = Double.parseDouble(scan.nextLine());
		}
		tienBaoHiem = newTienBaoHiem;
	}
	
				//***** Hàm nhập tổng hợp *****
	@Override public void nhap() {
		super.nhap();
		nhapTienBaoHiem();
	}
			//*** Hàm xuất ***
	
				//***** Các hàm xuất thành phần mới *****
	public void xuatTienBaoHiem() {
		System.out.println("Tien bao hiem: "+tienBaoHiem);
	}
	
				//*** Hàm xuất tổng hợp ***
	@Override public void xuat() {
		super.xuat();
		xuatTienBaoHiem();
		
	}
}
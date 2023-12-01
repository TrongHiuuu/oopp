import java.util.Scanner; 
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class NhanVien {
	public Scanner scan = new Scanner(System.in);
	
	//Các thuộc tính
	private int id;
	private String ho, ten;
	private LocalDate ngaySinh;
	private static int count = 1000;
	private int maPhongBan;
	
	//Các hàm get và set của class
		//*** Các hàm get ***
	public int getID() {
		return id;
	}
	public String getHo() {
		return ho;
	}
	public String getTen() {
		return ten;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public int getMaPhongBan() {
		return maPhongBan;
	}
	
		//*** Các hàm set ***
	public void setID(int newID) {
		id = newID;
	}
	public void setHo(String newHo) {
		ho = newHo;
	}
	public void setTen(String newTen) {
		ten = newTen;
	}
	public void setHoTen(String newHoTen) {
		newHoTen = newHoTen.trim();
		String[] temp = newHoTen.split(" ");
		ten = temp[temp.length-1];
		ho = "";
		for(int i=0;i<temp.length - 1;i++) {
			ho = ho + temp[i] + " ";
		}
		ho = ho.trim();
	}
	public void setNgaySinh(String newNgaySinh) {
		if((newNgaySinh.intern()).contains("-")) {
			ngaySinh = LocalDate.parse(newNgaySinh);
		}
		else {
			ngaySinh = LocalDate.parse(newNgaySinh, DateTimeFormatter.BASIC_ISO_DATE);
		}
	}
	public void setMaPhongBan(int newMaPhongBan) {
		maPhongBan = newMaPhongBan;
	}
	
	//Hàm tự tăng giá trị của id
	public void setNextID() {
		id = count++;
	} 
	//Hàm set biến đếm
	public static void setCount(int newCount) {
		count = newCount + 1;
	}
	//Các hàm kiểm tra điều kiện
		//*** Kiểm tra điều kiện của tên
	public static boolean chuoiCoChuaSo(String str) {
		for(char i: str.toCharArray()) {
			if(i >= '0' && i<= '9') {
				return true;
			}
		}
		return false;
	}
	public static boolean hoHoacTenKhongHopLe(String hoHoacTen) {
		if(hoHoacTen.intern() == "" || chuoiCoChuaSo(hoHoacTen.intern())) {
			return true;
		}
		return false;
	}
	
		//*** Kiểm tra điều kiện của ngày sinh ***
	public static boolean ngaySinhKhongHopLe(String newNgaySinh) {
		try {
			if ((newNgaySinh.intern()).contains("-")) {
				LocalDate newDate = LocalDate.parse(newNgaySinh);
			} else {
				LocalDate newDate = LocalDate.parse(newNgaySinh, DateTimeFormatter.BASIC_ISO_DATE);
			}
		}
		catch(DateTimeParseException e) { return true; }
		return false;
	}

		//*** Kiểm tra điều kiện của id ***
	public static boolean idKhongHopLe(int newID) {
		if((String.valueOf(newID)).length() != NhanVien_CONST.NUMBEROFCHAR_ID) return true;
		return false;
	}
	
		//*** Kiểm tra điều kiện của maPhongBan ***
	public static boolean maPhongBanKhongHopLe(int newMaPhongBan) {
		String temp = String.valueOf(newMaPhongBan);
		if(temp.length() != NhanVien_CONST.NUMBEROFCHAR_MAPHONGBAN) return true;
		return false;
	}
	
	
	//Các constructor của class
	
		//*** Constructor không tham số ***
	public NhanVien() {
		id = 0;
		ho = "";
		ten = "";
		ngaySinh = LocalDate.parse("0001-01-01");
		maPhongBan = 0;
	}
	
		//*** Constructor có tham số ***
	public NhanVien(int newID, String newHo, String newTen, String newNgaySinh, int newMaPhongBan) {
		id = newID;
		ho = newHo;
		ten = newTen;
		setNgaySinh(newNgaySinh);
		maPhongBan = newMaPhongBan;
	}
	
	
	//Các hàm nhập xuất của class
	
		//*** Hàm nhập
	
			//***** Các hàm nhập thành phần *****
	public void nhapHoTen() {
		String newHoTen;
		System.out.print("Nhap ho va ten: ");
		newHoTen = scan.nextLine();
		while(hoHoacTenKhongHopLe(newHoTen.intern())) {
			System.out.println("Ho ten khong hop le!! (Khong duoc de trong hoac co chua chu so)");
			System.out.print("Nhap ho va ten: ");
			newHoTen = scan.nextLine();
		}
		setHoTen(newHoTen);
	}
	public void nhapNgaySinh() {
		String newNgaySinh;
		System.out.print("Nhap ngay sinh (YYYY-MM-DD): ");
		newNgaySinh = scan.nextLine();
		while(ngaySinhKhongHopLe(newNgaySinh)) {
			System.out.println("Ngay sinh khong hop le!! (Sai dinh dang hoac ngay sinh khong ton tai)");
			System.out.print("Nhap ngay sinh (YYYY-MM-DD): ");
			newNgaySinh = scan.nextLine();
		};
		setNgaySinh(newNgaySinh);
	}
	
			//***** Hàm nhập tổng hợp *****
	public void nhap() {
		nhapHoTen();
		nhapNgaySinh();
		setNextID();
	}
	
		//*** Hàm xuất
	
			//***** Các hàm xuất thành phần *****
	private void xuatID() {
		System.out.println("Ma so nhan vien: "+id);
	}
	private void xuatHoTen() {
		System.out.println("Ho va ten: "+ho+" "+ten);
	}
	private void xuatNgaySinh() {
		System.out.println("Ngay thang nam sinh: "+ngaySinh);
	}
	private void xuatMaPhongBan() {
		System.out.println("Ma phong ban: "+maPhongBan);
	}
			//***** Hàm xuất tổng hợp *****
	public void xuat() {
		xuatID();
		xuatHoTen();
		xuatNgaySinh();
		xuatMaPhongBan();
	}
	//Hàm tính tổng số lượng nhân viên
	/*public static int tongSoLuongNhanVien() {
		return
	}*/
}
import java.util.Scanner;

public class mainQuanLiNhanSu {

	public static void main(String[] args) {
		QuanLyNhanSu menuQuanLiNhanSu = new QuanLyNhanSu();
		menuQuanLiNhanSu.menuQuanLy();
	}
	
	//Này để test mấy phương thức thui chứ hong có gì đâu :3
	/*public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		System.out.println(chuoiCoChuaSo(input));
		System.out.println(hoHoacTenKhongHopLe(input));
	}
	public static boolean chuoiCoChuaSo(String str) {
		for(char i: str.toCharArray()) {
			if(i >= '0' && i<= '9') {
				return true;
			}
		}
		return false;
	}
	public static boolean hoHoacTenKhongHopLe(String hoHoacTen) {
		if(hoHoacTen == "" || chuoiCoChuaSo(hoHoacTen)) {
			return true;
		}
		return false;
	}
	*/
}

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class DanhSachNhanVien implements DanhSach{
	//Khai báo mảng các nhân viên
	private ArrayList<NhanVien> danhSachNhanVien = new ArrayList<NhanVien>();
	public Scanner scan = new Scanner(System.in);
	
	//Các constructor của class
	public DanhSachNhanVien() {
		danhSachNhanVien = new ArrayList<NhanVien>();
	}
	public DanhSachNhanVien(NhanVien nv1) {
		danhSachNhanVien.add(nv1);
	}

	// get&set dsnhanvien
	public ArrayList<NhanVien> getDanhSachNhanVien() {
		return danhSachNhanVien;
	}

	public void setDanhSachNhanVien(ArrayList<NhanVien> newDanhSachNhanVien) {
		danhSachNhanVien = newDanhSachNhanVien;
	}
	
	//Menu của class
	public void menu(DanhSachPhongBan danhSachPhongBan, DanhSachBangLuong danhSachBangLuong,DanhSachDuAn danhSachDuAn) {
		int luaChon;
		System.out.println("=====================================================");
		System.out.println("|1. Xuat danh sach nhan vien");
		System.out.println("|2. Them mot nhan vien vao danh sach");
		System.out.println("|3. Tim kiem nhan vien trong danh sach");
		System.out.println("|4. Xoa nhan vien trong danh sach");
		System.out.println("|5. Sua thong tin nhan vien trong danh sach");
		System.out.println("|0. Thoat");
		System.out.println("=====================================================");
		do {
			System.out.print("Nhap lua chon (0 -> 5): ");
			luaChon = Integer.parseInt(scan.nextLine());
		} while(luaChon < 0 || luaChon > 5);
		switch(luaChon) {
			case 1: 
				xuat(); 
				break;
			case 2: 
				them(); 
				break;
			case 3:
				tim();
				break;
			case 4:
				xoa(danhSachPhongBan, danhSachBangLuong, danhSachDuAn);
				break;
			case 5:
				sua(danhSachPhongBan, danhSachBangLuong, danhSachDuAn);
				break;
			case 0:
				System.out.println("Thoat danh sach nhan vien thanh cong");
				return;
		}
		
	}
	
	//Phương thức xuất
	@Override public void xuat() {
		System.out.println("=====================================================");
		for(NhanVien i: danhSachNhanVien) {
			i.xuat();
		}
	}
	
	//Phương thức thêm
	public void them() {
		int luaChon;
		do {
			System.out.print("Nhap loai nhan vien can them (1.Nhan vien chinh thuc   2. Nhan vien thoi vu): ");
			luaChon = Integer.parseInt(scan.nextLine());
		} while(luaChon < 1 || luaChon > 2);
		if (luaChon == 1) {
			NhanVienChinhThuc newNhanVien = new NhanVienChinhThuc();
			newNhanVien.nhap();
			danhSachNhanVien.add(newNhanVien);
		}
		if (luaChon == 2) {
			NhanVienThoiVu newNhanVien = new NhanVienThoiVu();
			newNhanVien.nhap();
			danhSachNhanVien.add(newNhanVien);
		}
	}
	//Menu tìm kiếm
	@Override public int menuTim() {
		if(danhSachNhanVien.size() == 0) {
			System.out.println("Khong co nhan vien nao trong danh sach!!!");
			return -1;
		}
		int luaChon = -1;
		System.out.println("=====================================================");
		System.out.println("|1. Tim theo ma so nhan vien.");
		System.out.println("|2. Tim theo ten nhan vien.");
		System.out.println("|3. Tim theo ma phong ban nhan vien.");
		System.out.println("|4. Tim theo loai nhan vien.");
		//System.out.println("|5. Tim theo muc luong nhan vien.");
		System.out.println("|0. Huy tim kiem.");
		System.out.println("=====================================================");
		do {
			System.out.println("Nhap lua chon (0 -> 4): ");
			luaChon = Integer.parseInt(scan.nextLine());
		} while (luaChon < 0 || luaChon > 4);
		return luaChon;
	}
	//Phương thức tìm kiếm
		//*** Các phương thức tìm thành phần ***
	
			//***** Tìm theo mã số *****
	private ArrayList<NhanVien> timID() {
		int idCanTim;
		ArrayList<NhanVien> nhanVienCanTim = new ArrayList<NhanVien>();
		System.out.print("Nhap ma so nhan vien can tim: ");
		idCanTim = Integer.parseInt(scan.nextLine());
		while(NhanVien.idKhongHopLe(idCanTim)) {
			System.out.println("Ma so khong hop le!! (Ma so nhan vien phai co "+
					NhanVien_CONST.NUMBEROFCHAR_ID+" ki tu)");
			System.out.print("Nhap ma so nhan vien can tim: ");
			idCanTim = Integer.parseInt(scan.nextLine());
		}
		for(NhanVien i: danhSachNhanVien) {
			int id = i.getID();
			if(id == idCanTim) {
				nhanVienCanTim.add(i);
			}
		}
		return nhanVienCanTim;
	}
	
			//***** Tìm theo tên *****
	private ArrayList<NhanVien> timTen() {
		String tenCanTim;
		ArrayList<NhanVien> danhSachNhanVienCungTen = new ArrayList<NhanVien>();
		System.out.println("Nhap ten nhan vien can tim: ");
		tenCanTim = scan.nextLine();
		for(NhanVien i: danhSachNhanVien) {
			String ten = i.getTen();
			if (ten == tenCanTim.intern()) {
				danhSachNhanVienCungTen.add(i);
			}
		}
		return danhSachNhanVienCungTen;
	}
	
			//***** Tìm theo mã phòng ban *****
	private ArrayList<NhanVien> timMaPhongBan() {
		int maPhongBanCanTim;
		ArrayList<NhanVien> danhSachNhanVienPhongBan = new ArrayList<NhanVien>();
		System.out.print("Nhap ma phong ban can tim: ");
		maPhongBanCanTim = Integer.parseInt(scan.nextLine());
		while(NhanVien.maPhongBanKhongHopLe(maPhongBanCanTim)) {
			System.out.println("Ma phong ban khong hop le (Ma phong ban phai co "+
					NhanVien_CONST.NUMBEROFCHAR_MAPHONGBAN+" ki tu");
			System.out.print("Nhap ma phong ban can tim: ");
			maPhongBanCanTim = Integer.parseInt(scan.nextLine());
		}
		for(NhanVien i: danhSachNhanVien) {
			int maPhongBan = i.getMaPhongBan();
			if(maPhongBan == maPhongBanCanTim) {
				danhSachNhanVienPhongBan.add(i);
			}
		}
		return danhSachNhanVienPhongBan;
	}
	
			//***** Tìm theo loại nhân viên
	private ArrayList<NhanVien> timLoaiNhanVien() {
		int luaChon;
		do {
			System.out.print("Nhap loai nhan vien can tim (1.Nhan vien chinh thuc   2. Nhan vien Chinh thuc): ");
			luaChon = Integer.parseInt(scan.nextLine());
		} while(luaChon < 1 || luaChon > 2);
		switch(luaChon) {
			case 1:
				ArrayList<NhanVien> danhSachNhanVienChinhThuc = new ArrayList<NhanVien>();
				for(NhanVien i: danhSachNhanVien) {
					if(i instanceof NhanVienChinhThuc) {
						danhSachNhanVienChinhThuc.add(i);
					}
				}
				return danhSachNhanVienChinhThuc;
			case 2:
				ArrayList<NhanVien> danhSachNhanVienThoiVu = new ArrayList<NhanVien>();
				for(NhanVien i: danhSachNhanVien) {
					if(i instanceof NhanVienThoiVu) {
						danhSachNhanVienThoiVu.add(i);
					}
				}
				return danhSachNhanVienThoiVu;
		}
		return null;
	}
	
		//*** Phương thức tìm ***
			//***** Tổng hợp các phương thức tìm thành phần *****
	public ArrayList<NhanVien> danhSachCanTim() {
		int luaChon = menuTim();
		ArrayList<NhanVien> danhSachNhanVienCanTim = new ArrayList<NhanVien>();
		switch(luaChon) {
			case 0:
				return null;
			case 1:
				danhSachNhanVienCanTim = timID();
				break;
			case 2:
				danhSachNhanVienCanTim = timTen();
				break;
			case 3:
				danhSachNhanVienCanTim = timMaPhongBan();
				break;
			case 4:
				danhSachNhanVienCanTim = timLoaiNhanVien();
				break;
			/*case 5:
				danhSachNhanVienCanTim = timluongCanBan();
				break;*/
		}
		return danhSachNhanVienCanTim;
	}
	public void tim() {
		ArrayList<NhanVien> danhSachTimDuoc = danhSachCanTim();
		if (danhSachTimDuoc == null) {
			System.out.println("Khong co nhan vien nao trong danh sach can tim...");
			return;
		}
		System.out.println("Danh sach cac nhan vien can tim la: ");
		System.out.println("=====================================================");
		for(NhanVien i: danhSachTimDuoc) {
			i.xuat();
		}
	}
	//Phương thức xóa nhân viên
	public void xoa(DanhSachPhongBan danhSachPhongBan, DanhSachBangLuong danhSachBangLuong,DanhSachDuAn danhSachDuAn) {
		ArrayList<NhanVien> danhSachNhanVienCanXoa = danhSachCanTim();
		if (danhSachNhanVienCanXoa == null) {
			System.out.println("Khong co nhan vien nao trong danh sach can tim...");
			return;
		}
		int luaChon, stt, count = 1;
		while (danhSachNhanVienCanXoa.size() > 0) {
			System.out.println("Danh sach cac nhan vien can xoa: ");
			System.out.println("=====================================================");
			for(NhanVien i: danhSachNhanVienCanXoa) {
				System.out.println(count+". ");i.xuat();
				count++;
			}
			System.out.println("1. Xoa 1 nhan vien trong danh sach");
			System.out.println("0. Thoat");
			do {
				System.out.print("Nhap lua chon (0 -> 1): ");
				luaChon = Integer.parseInt(scan.nextLine());
			} while(luaChon < 0 || luaChon > 1);
		//Danh sách các đối tượng phụ cần tạo
			
			NhanVien nhanVienCanXoa = new NhanVien(); //Tạo đối tượng tạm nhân viên cần xóa
			//Lấy danh sách phòng ban
			ArrayList<PhongBan> dspb = danhSachPhongBan.getDanhSachPhongBan();
			ArrayList<NhanVien> tempDSNV; //mảng danh sách nhân viên tạm
			//Lấy danh sách bảng lương
			ArrayList<BangLuong> dsbl = danhSachBangLuong.getDanhSachBangLuong();
			//Tạo 2 đối tượng bảng lương cần
			BangLuongChinhThuc blctCanXoa = null;
			BangLuongThoiVu bltvCanXoa = null;
			//Lấy danh sách dự án
			ArrayList<DuAn> dsda = danhSachDuAn.getDanhSachDuAn();
			DuAnCaNhan dacnCanXoa = new DuAnCaNhan(); //Tạo đối tượng dự án cần reset nhân viên
			
			if (luaChon == 1) {
				do {
					System.out.println("Nhap STT nhan vien can xoa: ");
					stt = Integer.parseInt(scan.nextLine());
				} while(stt < 0 || stt > danhSachNhanVienCanXoa.size());
			//Lấy id nhân viên cần xóa để so sánh
				int idNhanVienCanXoa = (danhSachNhanVienCanXoa.get(stt-1)).getID();
			//Xóa nhân viên trong danh sách nhân viên
				danhSachNhanVien.remove(danhSachNhanVienCanXoa.get(stt-1));
			//Xóa nhân viên trong danh sách phòng ban:
				danhSachPhongBan.xoaNhanVien(danhSachNhanVienCanXoa.get(stt-1));
			//Xóa bảng lương của nhân viên	
				danhSachBangLuong.xoaNhanVien(danhSachNhanVienCanXoa.get(stt-1));
			//Xóa dự án cá nhân
				danhSachDuAn.xoaNhanVien(danhSachNhanVienCanXoa.get(stt-1));
			/*	
			//Reset Dự án cá nhân
				//	+Duyệt tìm từng dự án cá nhân trong danh sách dự án
				for(DuAn i: dsda) {
					if(i instanceof DuAnCaNhan) {
						NhanVien temp = ((DuAnCaNhan) i).getNhanVien();
						if(temp.getID() == idNhanVienCanXoa) {//Đã tìm thấy dự án cá nhân cần reset
							dacnCanXoa = (DuAnCaNhan)i;
						}
					}
				}
				NhanVienChinhThuc newNV = new NhanVienChinhThuc(); //Khởi tạo 1 nhân viên rỗng
				dacnCanXoa.setNhanVien(newNV); //Reset dự án cá nhân
				*/
				
				
				//Loại bỏ nhân viên được xóa ra khỏi danh sách nhân viên cần xóa
				danhSachNhanVienCanXoa.remove(stt-1);
				System.out.println("Da xoa nhan vien!!");
			} 
			if (luaChon == 0) return;
		}
	}
	
	//Phương thức xóa mã phòng ban
	public void xoaIDPB(int maPhongBanCanXoa) {
		for(NhanVien i: danhSachNhanVien) {
			if(i.getMaPhongBan() == maPhongBanCanXoa) {
				i.setMaPhongBan(0);
			}
		}
	}
	//Phương thức sửa
	public void sua(DanhSachPhongBan danhSachPhongBan, DanhSachBangLuong danhSachBangLuong, DanhSachDuAn danhSachDuAn) {
		ArrayList<NhanVien> danhSachNhanVienCanSua = danhSachCanTim();
		if (danhSachNhanVienCanSua == null) {
			System.out.println("Khong co nhan vien nao trong danh sach can tim...");
			return;
		}
		while(danhSachNhanVienCanSua.size() > 0) {
			int stt, luaChon, count = 1 ;
			System.out.println("Danh sach nhan vien can sua: ");
			System.out.println("=====================================================");
			for(NhanVien i: danhSachNhanVienCanSua) {
				System.out.println(count+". ");i.xuat();
				count++;
			}
			System.out.println("1. Chon 1 nhan vien can sua");
			System.out.println("0. Thoat");
			do {
				System.out.print("Nhap lua chon (0 -> 1): ");
				luaChon = Integer.parseInt(scan.nextLine());
			} while(luaChon < 0 || luaChon > 1);
			if(luaChon == 0) {
				return;
			}
			do {
				System.out.print("Nhap STT nhan vien can sua: ");
				stt = Integer.parseInt(scan.nextLine());
			} while(stt < 0 || stt > danhSachNhanVienCanSua.size());
			int index = danhSachNhanVien.indexOf(danhSachNhanVienCanSua.get(stt-1)); 
			int idNhanVienCanSua = (danhSachNhanVien.get(index)).getID();
			NhanVien nhanVienCanSua = new NhanVien();
			System.out.println("=====================================================");
			System.out.println("Chon thong tin can sua:");
			System.out.println("1. Ho Ten");
			System.out.println("2. Ngay sinh");
			if(danhSachNhanVienCanSua.get(stt-1) instanceof NhanVienChinhThuc) {
				System.out.println("3. Tien bao hiem");
			}
			if(danhSachNhanVienCanSua.get(stt-1) instanceof NhanVienThoiVu) {
				System.out.println("3. Thoi han lam viec");
			}
			System.out.println("=====================================================");
			do {
				System.out.print("Nhap lua chon (1 -> 3): ");
				luaChon = Integer.parseInt(scan.nextLine());
			} while(luaChon < 1 || luaChon > 3);
		//Danh sách các biến phụ cần tạo
			ArrayList<PhongBan> dspb = danhSachPhongBan.getDanhSachPhongBan();
			ArrayList<NhanVien> tempDSNV;
			//Lấy danh sách bảng lương
			ArrayList<BangLuong> dsbl = danhSachBangLuong.getDanhSachBangLuong();
			//Tạo 2 đối tượng bảng lương cần
			BangLuongChinhThuc blctCanSua = null;
			BangLuongThoiVu bltvCanSua = null;
			//Lấy danh sách dự án
			ArrayList<DuAn> dsda = danhSachDuAn.getDanhSachDuAn();
			DuAnCaNhan dacnCanSua = new DuAnCaNhan();
			switch(luaChon) {
				case 1:
					System.out.print("Nhap ho ten moi: ");
					String newHoTen = scan.nextLine();
				//Sửa họ tên nhân viên trong danh sách nhân viên
					(danhSachNhanVien.get(index)).setHoTen(newHoTen);
				//Sửa họ tên nhân viên trong danh sách phòng ban	
					for(PhongBan i: dspb) {
						tempDSNV = i.getDSNhanVien();
						for(NhanVien n: tempDSNV) {
							if(n.getID() == idNhanVienCanSua) {
								nhanVienCanSua = n;
							}
						}
					}
					nhanVienCanSua.setHoTen(newHoTen);
				//Sửa họ tên nhân viên trong danh sách bảng lương
					for(BangLuong i: dsbl) {
						int temp = (i.getNhanVien()).getID();
						if(temp == idNhanVienCanSua) {
							if(i instanceof BangLuongChinhThuc) {
								blctCanSua = (BangLuongChinhThuc)i;
							}
							if(i instanceof BangLuongThoiVu) {
								bltvCanSua = (BangLuongThoiVu)i;
							}
						}
					}
					if(blctCanSua != null) {
						(blctCanSua.getNhanVien()).setHoTen(newHoTen);
					}
					if(bltvCanSua != null) {
						(bltvCanSua.getNhanVien()).setHoTen(newHoTen);
					}
				//Sửa tên nhân viên trong danh sách dự án
					for(DuAn i: dsda) {
						if(i instanceof DuAnCaNhan) {
							NhanVien temp = ((DuAnCaNhan) i).getNhanVien();
							if(temp.getID() == idNhanVienCanSua) {//Đã tìm thấy dự án cá nhân cần reset
								dacnCanSua = (DuAnCaNhan)i;
							}
						}
					}
					(dacnCanSua.getNhanVien()).setHoTen(newHoTen);
					break;
				case 2:
					String newNgaySinh;
					do {
						System.out.print("Nhap ngay sinh moi(YYYY-MM-DD): ");
						newNgaySinh = scan.nextLine();
					} while(NhanVien.ngaySinhKhongHopLe(newNgaySinh));
					(danhSachNhanVien.get(index)).setNgaySinh(newNgaySinh);
					//Sửa họ tên nhân viên trong danh sách phòng ban	
					for(PhongBan i: dspb) {
						tempDSNV = i.getDSNhanVien();
						for(NhanVien n: tempDSNV) {
							if(n.getID() == idNhanVienCanSua) {
								nhanVienCanSua = n;
							}
						}
					}
					nhanVienCanSua.setNgaySinh(newNgaySinh);
				//Sửa họ tên nhân viên trong danh sách bảng lương
					for(BangLuong i: dsbl) {
						int temp = (i.getNhanVien()).getID();
						if(temp == idNhanVienCanSua) {
							if(i instanceof BangLuongChinhThuc) {
								blctCanSua = (BangLuongChinhThuc)i;
							}
							if(i instanceof BangLuongThoiVu) {
								bltvCanSua = (BangLuongThoiVu)i;
							}
						}
					}
					if(blctCanSua != null) {
						(blctCanSua.getNhanVien()).setNgaySinh(newNgaySinh);
					}
					if(bltvCanSua != null) {
						(bltvCanSua.getNhanVien()).setNgaySinh(newNgaySinh);
					}
				//Sửa tên nhân viên trong danh sách dự án
					for(DuAn i: dsda) {
						if(i instanceof DuAnCaNhan) {
							NhanVien temp = ((DuAnCaNhan) i).getNhanVien();
							if(temp.getID() == idNhanVienCanSua) {//Đã tìm thấy dự án cá nhân cần reset
								dacnCanSua = (DuAnCaNhan)i;
							}
						}
					}
					(dacnCanSua.getNhanVien()).setNgaySinh(newNgaySinh);
					break;
				case 3:
					int newThoiHanLamViec = 0;
					double newTienBaoHiem = 0;
					if(danhSachNhanVien.get(index) instanceof NhanVienThoiVu) {
						do {
							System.out.print("Nhap thoi han lam viec moi: ");
							newThoiHanLamViec = Integer.parseInt(scan.nextLine());
						} while(NhanVienThoiVu.thoiHanLamViecKhongHopLe(newThoiHanLamViec));
						((NhanVienThoiVu)(danhSachNhanVien.get(index))).setThoiHanLamViec(newThoiHanLamViec);
					}
					if(danhSachNhanVien.get(index) instanceof NhanVienChinhThuc) {
						do {
							System.out.print("Nhap tien bao hiem moi: ");
							newTienBaoHiem = Double.parseDouble(scan.nextLine());
						} while(NhanVienChinhThuc.tienBaoHiemKhongHopLe(newTienBaoHiem));
						((NhanVienChinhThuc)(danhSachNhanVien.get(index))).setTienBaoHiem(newTienBaoHiem);
					}
					for(PhongBan i: dspb) {
						tempDSNV = i.getDSNhanVien();
						for(NhanVien n: tempDSNV) {
							if(n.getID() == idNhanVienCanSua) {
								nhanVienCanSua = n;
							}
						}
					}
					if (nhanVienCanSua instanceof NhanVienThoiVu) {
						((NhanVienThoiVu)nhanVienCanSua).setThoiHanLamViec(newThoiHanLamViec);
					}
					if (nhanVienCanSua instanceof NhanVienChinhThuc) {
						((NhanVienChinhThuc)nhanVienCanSua).setTienBaoHiem(newTienBaoHiem);
					}
					for(BangLuong i: dsbl) {
						int temp = (i.getNhanVien()).getID();
						if(temp == idNhanVienCanSua) {
							if(i instanceof BangLuongChinhThuc) {
								blctCanSua = (BangLuongChinhThuc)i;
							}
							if(i instanceof BangLuongThoiVu) {
								bltvCanSua = (BangLuongThoiVu)i;
							}
						}
					}
					if(blctCanSua != null) {
						((NhanVienChinhThuc)(blctCanSua.getNhanVien())).setTienBaoHiem(newTienBaoHiem);
					}
					if(bltvCanSua != null) {
						((NhanVienThoiVu)(bltvCanSua.getNhanVien())).setThoiHanLamViec(newThoiHanLamViec);
					}
					for(DuAn i: dsda) {
						if(i instanceof DuAnCaNhan) {
							NhanVien temp = ((DuAnCaNhan) i).getNhanVien();
							if(temp.getID() == idNhanVienCanSua) {//Đã tìm thấy dự án cá nhân cần reset
								dacnCanSua = (DuAnCaNhan)i;
							}
						}
					}
					if(dacnCanSua.getNhanVien() instanceof NhanVienChinhThuc) {
						((NhanVienChinhThuc)(dacnCanSua.getNhanVien())).setTienBaoHiem(newTienBaoHiem);
					}
					if(dacnCanSua.getNhanVien() instanceof NhanVienThoiVu) {
						((NhanVienThoiVu)(dacnCanSua.getNhanVien())).setThoiHanLamViec(newThoiHanLamViec);
					}
					break;
			}
		}
	} 
	
	//Đọc, ghi file
	
		//*** Đọc file ***
	public void docFile() {
		try {
			File inputFile = new File("dataNV.txt");
			Scanner scan = new Scanner(inputFile);
			while(scan.hasNextLine()) {
				String temp = scan.nextLine();
				String arr[] = temp.split(",");	
				if(arr[0].intern() == "1") {
					NhanVienChinhThuc newNV = new NhanVienChinhThuc();
					newNV.setID(Integer.parseInt(arr[1]));
					newNV.setHo(arr[2]);
					newNV.setTen(arr[3]);
					newNV.setNgaySinh(arr[4]);
					newNV.setMaPhongBan(Integer.parseInt(arr[5]));
					newNV.setTienBaoHiem(Double.parseDouble(arr[6]));
					danhSachNhanVien.add(newNV);
				}
				if(arr[0].intern() == "2") {
					NhanVienThoiVu newNV = new NhanVienThoiVu();
					newNV.setID(Integer.parseInt(arr[1]));
					newNV.setHo(arr[2]);
					newNV.setTen(arr[3]);
					newNV.setNgaySinh(arr[4]);
					newNV.setMaPhongBan(Integer.parseInt(arr[5]));
					newNV.setThoiHanLamViec(Integer.parseInt(arr[6]));
					danhSachNhanVien.add(newNV);
				}
				NhanVien.setCount((danhSachNhanVien.get(danhSachNhanVien.size()-1)).getID());
			}
			scan.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
		//*** Ghi file ***
	public void ghiFile() {
		try {
			File outputFile = new File("dataNV.txt");
			FileWriter fw = new FileWriter(outputFile);
			for(NhanVien i: danhSachNhanVien) {
				if (i instanceof NhanVienChinhThuc) {
					fw.write("1,");
				}
				if (i instanceof NhanVienThoiVu) {
					fw.write("2,");
				}
				String ho, ten, ngaySinh;
				int id, maPhongBan;
				id = i.getID();
				ho = i.getHo();
				ten = i.getTen();
				ngaySinh = (i.getNgaySinh()).toString();
				maPhongBan = i.getMaPhongBan();
				fw.write(String.valueOf(id)+","+ho+","+ten+","+ngaySinh+","+maPhongBan+",");
				if (i instanceof NhanVienThoiVu) {
					int temp = ((NhanVienThoiVu)i).getThoiHanLamViec();
					String thoiHanLamViec = String.valueOf(temp);
					fw.write(thoiHanLamViec);
				}
				if (i instanceof NhanVienChinhThuc) {
					double temp = ((NhanVienChinhThuc)i).getTienBaoHiem();
					String tienBaoHiem = String.valueOf(temp);
					fw.write(tienBaoHiem);
				}
				fw.write("\n");
			}
			fw.close();
			System.out.println("Da ghi du lieu vao file theo duong dan: "+outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
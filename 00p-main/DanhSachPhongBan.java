import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class DanhSachPhongBan implements DanhSach {
    public Scanner sc = new Scanner(System.in);
    private ArrayList<PhongBan> danhSachPB = new ArrayList<PhongBan>();
    
    // constructor
    public DanhSachPhongBan (){
        danhSachPB = new ArrayList<PhongBan>();
    }
    public DanhSachPhongBan (ArrayList<PhongBan> danhSachPB) {
        this.danhSachPB = danhSachPB;
    }

    // get & set dsPB
    public ArrayList<PhongBan> getDanhSachPhongBan() {
        return danhSachPB;
    }
    
    public void setDanhSachPhongBan(ArrayList<PhongBan> danhSachPB) {
        this.danhSachPB = danhSachPB;
    }
    // xuat pb
    @Override public void xuat() {
        if (danhSachPB.isEmpty())
            System.out.println("----- DS RONG -----"); 
        else {
            System.out.println("============================");
            System.out.println("----- DS PHONG BAN -----");
            for(PhongBan pb : danhSachPB){
                pb.xuat();
                System.out.println("--------------------");
            }
        }
    }

    // menu 
    public void menu(DanhSachNhanVien dsnv, DanhSachBangLuong dsbl, DanhSachDuAn dsda) {
        int choice;
        do {
            System.out.println("MENU -------------- ");
            System.out.println("1. Them \n2. Sua \n3. Xoa \n4. Tim kiem \n5. Xuat DS \n0. Thoat");
            System.out.print("Vui long chon chuc nang thuc hien: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 0:
                    System.out.println("--- Thoat khoi chuong trinh ---");
                    break;
                case 1:
                    them(dsnv, dsbl, dsda);
                    break;
                case 2:
                    sua(dsnv, dsbl, dsda);
                    break;
                case 3:
                    xoa(dsnv, dsbl, dsda);
                    break;
                case 4:
                    tim();
                    break;
                case 5:
                    xuat();
                    break;
                default:
                    System.out.println("Chuc nang khong hop le, vui long chon lai. . .");
                    break;
            }
        }while(choice!=0);
    }
    
    // menu tim kiem
    @Override public int menuTim(){
        int choice;
        if (danhSachPB.size()==0) {
            System.out.println("=====DS RONG====="); 
            choice=-1;
        }
        else{
            do {
                System.out.println("1. Tim theo ma phong \n2. Tim theo ten phong \n3. Tim theo ten truong phong \n0. Thoat");
                System.out.print("Vui long nhap lua chon: ");
                choice = sc.nextInt();
                sc.nextLine();
                if (choice < 0 && choice > 3) {
                    System.out.println("Lua chon khong hop le, vui long chon lai. . .");
                }
            } while (choice < 0 && choice > 3);
        }
        return choice;
    }

    // them
    public void them (DanhSachNhanVien dsnv, DanhSachBangLuong dsbl, DanhSachDuAn dsda){
        // System.out.println("Nhap so luong phong ban muon them: ");
        // int n = Integer.parseInt(sc.nextLine());
        // for(int i=0; i<n; i++){
        //     System.out.println("=======================");
        //     System.out.println("Nhap vao phong ban thu " + (i+1));
        //     System.out.println("=======================");
        //     PhongBan pb = new PhongBan();
        //     pb.nhap();
        //     danhSachPB.add(pb);
        //     System.out.println("=======================");
        //     pb.xuat();
        // }

        PhongBan pb = new PhongBan();
        // danh sách phòng ban rỗng
        ArrayList<NhanVien> dsNVChuaCoPB = new ArrayList<>();
        for (NhanVien nv : dsnv.getDanhSachNhanVien()) {
            if (nv.getMaPhongBan() == 0)
                dsNVChuaCoPB.add(nv);
        }
        pb.nhap(dsNVChuaCoPB, dsbl, dsda);
        danhSachPB.add(pb);
    }

    // tim kiem
    @Override public void tim(){
        int choice;
        do {
            choice = menuTim();
            ArrayList<PhongBan> ch = new ArrayList<>();
            switch (choice) {
                case 0:
                    System.out.println("Thoat khoi chuong trinh!");
                    break;
                case 1:
                    ch = timMaPhong();
                    break;
                case 2:
                    ch = timTenPhong();
                    break;
                case 3:
                    ch = timTruongPhong(); 
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long thu lai. . .");
                    break;
            }
            if (choice >=1 && choice <=3)
            {
                if(ch.size()==0)
                    System.out.println("Khong tim thay noi dung can tim. . .");
                else {
                    System.out.println("Ket qua tim kiem: ");
                    for(PhongBan pb : ch){
                        pb.xuat();
                    }
                }
            }
        } while(choice!=0);
    }

    // tìm mã phòng
    private ArrayList<PhongBan> timMaPhong(){ 
        ArrayList<PhongBan> ch = new ArrayList<>();
        System.out.print("Nhap ma phong ban can tim: ");
        int maPBCanTim;
        maPBCanTim=sc.nextInt();
        for(PhongBan pb : danhSachPB){
            int mapb = pb.getMaphong();
            if(mapb == maPBCanTim) 
                ch.add(pb);
        }
        return ch;
    }

    // tìm tên phòng
    private ArrayList<PhongBan> timTenPhong(){ 
        ArrayList<PhongBan> ch = new ArrayList<>();
        System.out.print("Nhap ten phong can tim: ");
        String tenPBCanTim=sc.nextLine();
        for(PhongBan pb : danhSachPB){
            String tenpb = pb.getTenphong();
            if(tenpb.equals(tenPBCanTim)) // so sánh 2 chuỗi, trả về true / false
                ch.add(pb);
        }
        return ch;
    }

    // tìm tên trưởng phòng
    private ArrayList<PhongBan> timTruongPhong(){ 
        ArrayList<PhongBan> ch = new ArrayList<>();
        System.out.print("Nhap ma truong phong can tim: ");
        int maTPCanTim= Integer.parseInt(sc.nextLine());
        for(PhongBan pb : danhSachPB){
            int matp = pb.getTrgphong();
            if(matp == maTPCanTim)
                ch.add(pb);
        }
        return ch;
    }

    // xóa
    public void xoa(DanhSachNhanVien dsnv, DanhSachBangLuong dsbl, DanhSachDuAn dsda){
        int choice;
        do{
            System.out.println("================================");
            System.out.println("----- Xoa Khoi Danh Sach -----");
            choice = menuTim();
            ArrayList<PhongBan> ch = new ArrayList<>();
            if (choice == 1) {
                ch = timMaPhong();
            } else if (choice == 2) {
                ch = timTenPhong(); 
            } else if (choice == 3) {
                ch = timTruongPhong(); 
            } else if (choice == 0) {
                System.out.println("=====Thoat=====");
            } else if (choice==-1) {
                break;
            }

            if (choice!=0) {
                if(ch.size()==0)
                    System.out.println("Khong tim thay noi dung can tim . . . ");
                else{
                    System.out.println("Ket qua tim kiem: ");
                    for(PhongBan pb : ch){
                        pb.xuat();
                    }
                    //xoa
                    for(PhongBan pb : ch) {
                        danhSachPB.remove(pb);
                        //Note
                        dsnv.xoaIDPB(pb.getMaphong());
                        dsda.xoaIDPB(pb.getMaphong());
	                    dsbl.xoaIDPB(pb.getMaphong());
                    }
                    System.out.println("----- Da xoa thanh cong -----");
                }
            }
        } while(choice!=0);
    }
    
    // sua
    public void sua(DanhSachNhanVien dsnv, DanhSachBangLuong dsbl, DanhSachDuAn dsda){
        int choice;
    do{
        System.out.println("----- Sua Thong Tin Phong Ban -----");
        choice = menuTim();
        ArrayList<PhongBan> ch = new ArrayList<>(); 
        switch (choice) {
            case 0:
                System.out.println("Thoat khoi chuong trinh!");            
                break;
            case 1:
                ch = timMaPhong();
                break;
            case 2: 
                ch = timTenPhong();
                break;
            case 3:
                ch = timTruongPhong();
                break;
            default:
                System.out.println("Lua chon khong hop le, vui long thu lai. . .");
                break;
        }

        if(choice >= 1 && choice <= 3){
            if(ch.size()==0)
                System.out.println("Khong tim thay noi dung can tim. . . ");
            else{
                System.out.println("Ket qua tim kiem: ");
                System.out.println("----------------------");
                int i = 0;
                for(PhongBan pb : ch){
                    System.out.println((i++)+"."); pb.xuat();
                    System.out.println("=============================");
                }
                
                int stt;
                do {
                    System.out.print("Chon STT cua phong ban can sua: ");
                    stt = sc.nextInt();
                    sc.nextLine();
                }
                while(stt<0 || stt>=ch.size());
                
                //***nhap lua chon thong tin muon sua***
                int sua;
                PhongBan pb = ch.get(stt);
                do {
                    //menu thong tin can sua
                    System.out.println("----- Menu Sua Thong Tin Phong Ban -----");
                    System.out.println("1. Sua ten phong \n2. Sua ma truong phong \n3. Them nhan vien\n4. Xoa nhan vien\n0. Thoat");
                    //nhap lua chon
                    System.out.print("Nhap lua chon: ");
                    sua = sc.nextInt();
                    sc.nextLine();
                    System.out.println("-------------------------");
                    //goi cac ham sua
                    switch (sua) {
                        case 0:
                            System.out.println("Thoat khoi chuong trinh!");
                            break;
                        case 1:
                            String newTenPhong;
                            System.out.print("Nhap ten phong moi: ");
                            newTenPhong = sc.nextLine();
                            pb.setTenphong(newTenPhong);
                            dsda.suaTenPb(pb);
                            System.out.println("---Da sua ten phong ban thanh cong---");
                            break;
                        case 2:
                            int newTruongphong;
                            int j=0;
                            for(NhanVien nv : pb.getDSNhanVien()) {
                                System.out.println((j++) + "."); nv.xuat();
                                System.out.println("=============================");
                            }
                            int stt2;
                            do {
                                System.out.print("Chon STT cua nhan vien muon chon lam truong phong: ");
                                stt2 = Integer.parseInt(sc.nextLine());
                            } while(stt2<0 || stt2>=pb.getDSNhanVien().size());
                            newTruongphong = pb.getDSNhanVien().get(stt2).getID();
                            pb.setTrgphong(newTruongphong);

                            break;
                        case 3:
                            ArrayList<NhanVien> dsNhanVienRong = new ArrayList<>();
                            for (NhanVien nv : dsnv.getDanhSachNhanVien()) {
                                if (nv.getMaPhongBan() == 0)
                                    dsNhanVienRong.add(nv);
                            } 
                            int n = 0;
                            for(NhanVien nv : dsNhanVienRong) {
                                System.out.println((n++) + ".");
                                nv.xuat();
                                System.out.println("=============================");
                            }
                            int stt3;
                            do {
                                System.out.print("Chon STT cua nhan vien muon them vao phong ban: ");
                                stt3 = Integer.parseInt(sc.nextLine());
                            } while(stt3<0 || stt3>=dsNhanVienRong.size());
                            NhanVien nhanvien = dsNhanVienRong.get(stt3);
                            nhanvien.setMaPhongBan(pb.getMaphong());
                            // cap nhat
                            dsda.suaNhanVien(nhanvien);
                            dsbl.suaNhanVien(nhanvien);
                            pb.getDSNhanVien().add(nhanvien);
                            pb.setSLNV(pb.getDSNhanVien().size());
                            System.out.println("----- Da them nhan vien vao phong ban -----");
                            break;
                        case 4:
                            int m = 0;
                            for(NhanVien nv : pb.getDSNhanVien()) {
                                System.out.println((m++) + ".");
                                nv.xuat();
                                System.out.println("=============================");
                            }
                            int stt4;
                            do {
                                System.out.print("Chon STT cua nhan vien muon xoa khoi phong ban: ");
                                stt4 = Integer.parseInt(sc.nextLine());
                            } while(stt4<0 || stt4>=pb.getDSNhanVien().size());
                            NhanVien nvDuocChon = pb.getDSNhanVien().get(stt4);
                            // cap nhat
                            dsda.suaNhanVien(nvDuocChon);
                            dsbl.suaNhanVien(nvDuocChon);
                            pb.getDSNhanVien().remove(nvDuocChon);
                            if (nvDuocChon.getID() == pb.getTrgphong()) {
                                pb.setTrgphong(0);
                                System.out.println("--- Da xoa truong phong ---");
                            }
                            System.out.println("--- Da xoa thanh cong ---");
                            pb.setSLNV(pb.getDSNhanVien().size());
                            break;
                        default:
                            System.out.println("Lua chon khong hop le, vui long thu lai. . .");
                            break;
                    }
                }while(sua != 0);
            }
        }
    }
        while(choice!=0);
    }

    // DOC FILE 
    public void docFile() {
        try {
            BufferedReader input = new BufferedReader(new FileReader("dataPB.txt"));
            String line = input.readLine();
            PhongBan phongban = new PhongBan();
            while(line!=null) {
                String ar[] = line.split(",");
                int maphong = Integer.parseInt(ar[0]);
                String tenphong = ar[1];
                int truongphong = Integer.parseInt(ar[2]);
                int slnv = Integer.parseInt(ar[3]);
                ArrayList<NhanVien> dsnv = new ArrayList<>();
                for (int i = 0; i<slnv; i++) {
                    line = input.readLine();
                    ar = line.split(",");
                    if (ar[0].equalsIgnoreCase("1")) {
                        NhanVienChinhThuc nv = new NhanVienChinhThuc(Integer.parseInt(ar[1]), (ar[2]), (ar[3]), (ar[4]), Integer.parseInt(ar[5]), Double.parseDouble(ar[6]));
                        dsnv.add(nv);
                    }
                    else if (ar[0].equalsIgnoreCase("2")) {
                        NhanVienThoiVu nv = new NhanVienThoiVu(Integer.parseInt(ar[1]), (ar[2]), (ar[3]), (ar[4]), Integer.parseInt(ar[5]), Integer.parseInt(ar[6]));
                        dsnv.add(nv);
                    }
                }

                // int newID, String newHo, String newTen, String newNgaySinh, int newMaPhongBan, double newTienBaoHiem
                //int newID, String newHo, String newTen, String newNgaySinh, int newMaPhongBan, int newThoiHanLamViec 
                // 2 constructor 1 cái có arraylist nhân viên 1 cái ko có
                phongban= new PhongBan(maphong, tenphong, truongphong, slnv, dsnv);
                danhSachPB.add(phongban);
                line = input.readLine();
            }
            input.close();
            PhongBan.init(phongban.getMaphong());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    // GHI FILE
    @Override public void ghiFile() {
        try {
            File f = new File("dataPB.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            String line = "";
            for (PhongBan p : danhSachPB) {
                line = p.getMaphong() + "," + p.getTenphong() + "," + p.getTrgphong() + "," + p.getSLNV() + "\n";
                bw.write(line); 
                NhanVienChinhThuc nv1;
                NhanVienThoiVu nv2;
                ArrayList<NhanVien> dsnv = p.getDSNhanVien();
                for(NhanVien nv : dsnv) {
                    // int newID, String newHo, String newTen, String newNgaySinh, int newMaPhongBan, double newTienBaoHiem
                    //int newID, String newHo, String newTen, String newNgaySinh, int newMaPhongBan, int newThoiHanLamViec 
                    if(nv instanceof NhanVienChinhThuc) {
                        nv1 = (NhanVienChinhThuc)nv;
                        line = 1 + "," + nv1.getID() + "," + nv1.getHo() + "," + nv1.getTen() + "," + nv1.getNgaySinh() + "," + nv1.getMaPhongBan() + "," + nv1.getTienBaoHiem() + "\n";
                    }
                    else if (nv instanceof NhanVienThoiVu) {
                        nv2 = (NhanVienThoiVu)nv;
                        line = 2 + "," + nv2.getID() + "," + nv2.getHo() + "," + nv2.getTen() + "," + nv2.getNgaySinh() + "," + nv2.getMaPhongBan() + "," + nv2.getThoiHanLamViec() + "\n";
                    }
                    bw.write(line);
                }
            }
            bw.close();
            fw.close(); 
        } catch(Exception e) {
        }
    }

    // xoa nhan vien trong ds nhan vien
    public void xoaNhanVien(NhanVien nvCanXoa) {
        int idPhongBan = nvCanXoa.getMaPhongBan();
        int idCanXoa = nvCanXoa.getID();
        ArrayList<NhanVien> ds;
        if(idPhongBan != 0) {
            for (PhongBan pb : danhSachPB) {
                if (idPhongBan == pb.getMaphong()) {
                    ds = pb.getDSNhanVien();
                    for(int i=0; i<ds.size(); i++) {
                        if(idCanXoa == ds.get(i).getID()) {
                            ds.remove(ds.get(i));
                            pb.setSLNV(ds.size());
                        }
                    }
                }
            }
        }
    }
}
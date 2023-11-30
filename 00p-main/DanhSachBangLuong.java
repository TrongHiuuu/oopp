import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class DanhSachBangLuong implements DanhSach{
    public Scanner sc = new Scanner(System.in);

//field
    private ArrayList<BangLuong> dsbl;
//constructor
    public DanhSachBangLuong(){
        dsbl=new ArrayList<BangLuong>();
    }

    public DanhSachBangLuong(ArrayList<BangLuong> dsbl){
        this.dsbl=dsbl;
    }
//get-set
    public void setDanhSachBangLuong(ArrayList<BangLuong> dsbl){
        this.dsbl=dsbl;
    }

    public ArrayList<BangLuong> getDanhSachBangLuong(){
        return dsbl;
    }
// doc file
    public void docFile(){
    try {
        BufferedReader input = new BufferedReader(new FileReader("dataBL.txt"));
        String line = input.readLine();
        while (line != null) {
        //dua du lieu vao mang
            String[] ar = line.split(",");
            BangLuong l;
            if(ar[0].equalsIgnoreCase("1")){
                //1.int newID, 2.String newHo, 3.String newTen, 4.String newNgaySinh, 5.int newMaPhongBan,
			//6.double newTienBaoHiem, 7.int soNgay, 8.double mucLuongTheoNgay, 9.double tienTangCa, 10.double thue, 
            //11. double thucLanh, 12.thanhToan
            //String newHo, String newTen, String newNgaySinh, String newId, String newMaPhongBan, double newTienBaoHiem
                NhanVienChinhThuc nv = new NhanVienChinhThuc(Integer.parseInt(ar[1]), ar[2], ar[3], ar[4], Integer.parseInt(ar[5]), Double.parseDouble(ar[6]));
                l = new BangLuongChinhThuc(nv, Integer.parseInt(ar[7]), Double.parseDouble(ar[8]),
                                            Double.parseDouble(ar[9]), Double.parseDouble(ar[10]), 
                                            Double.parseDouble(ar[11]), Double.parseDouble(ar[12]));
            }
            else{
            //6.int thoiHanLamViec, 7.int soGio, 8.double mucLuongTheoGio, 9.double thuong, 10.double thue, 
            //11. double thucLanh, 12.thanhToan
                NhanVienThoiVu nv = new NhanVienThoiVu(Integer.parseInt(ar[1]), ar[2], ar[3], ar[4], Integer.parseInt(ar[5]), Integer.parseInt(ar[6]));
                l = new BangLuongThoiVu(nv, Integer.parseInt(ar[7]), Double.parseDouble(ar[8]),
                                            Double.parseDouble(ar[9]), Double.parseDouble(ar[10]),
                                            Double.parseDouble(ar[11]), Double.parseDouble(ar[12]));
            }
            dsbl.add(l);
            //doc dong tiep theo
            line = input.readLine();
        }
        input.close();
        } 
    catch (Exception ex) {
        ex.printStackTrace();
    }
}

//ghi file
    public void ghiFile(){
        try {
            FileWriter fw = new FileWriter("dataBL.txt");
            //ghi
            String s;
            for(BangLuong l: dsbl){
                //neu la bang luong chinh thuc
                if(l instanceof BangLuongChinhThuc){
            //1.String newHo, 2.String newTen, 3.String newNgaySinh, 4.int newId, 5.String newMaPhongBan
            //6.double baoHiem, 7.int soNgay, 8.double mucLuongTheoNgay, 9.double tienTangCa, 10.double thanhToan
                    BangLuongChinhThuc l1 = (BangLuongChinhThuc) l; //ep kieu de goi cac pthuc get/set
                    NhanVienChinhThuc nv1 = (NhanVienChinhThuc) l1.getNhanVien();
                    s = 1+","+nv1.getID()+","+nv1.getHo()+","+nv1.getTen()+","+nv1.getNgaySinh()+","+nv1.getMaPhongBan()+","+nv1.getTienBaoHiem()+","+l1.getSoNgayLam()+","+l1.getMucLuongTheoNgay()+","+l1.getTienTangCa()+","+l1.getThue()+","+l1.getThucLanh()+","+l1.getThanhToan()+"\n";
                }
                else{
            //1. newId, 2. newMaPhongBan, 3.newHo, 4.newTen, 5.newNgaySinh, 6.int newThoiHanLamViec
            //7.int soGio, 7.double mucLuongTheoGio, 8.double tienThuong, 9.double thanhToan
                    BangLuongThoiVu l2 = (BangLuongThoiVu) l; //ep kieu de goi cac pthuc get/set
                    NhanVienThoiVu nv2 = (NhanVienThoiVu) l2.getNhanVien();
                    s = 2+","+nv2.getID()+","+nv2.getHo()+","+nv2.getTen()+","+nv2.getNgaySinh()+","+nv2.getMaPhongBan()+","+nv2.getThoiHanLamViec()+","+l2.getSoGioLam()+","+l2.getMucLuongTheoGio()+","+l2.getTienThuong()+","+l2.getThue()+","+l2.getThucLanh()+","+l2.getThanhToan()+"\n";
                }
                fw.write(s);
            }
            fw.close();
            System.out.println("Da ghi du lieu vao file theo duong dan: "+fw);
            } catch (Exception e) {
                System.out.println(e);
            }
    }

//Menu CRUD
    public void menu(DanhSachNhanVien dsnv){
        int luachon;
        do{
        System.out.println("================================");
        System.out.println("---MENU DANH SACH BANG LUONG---");
        System.out.println("|1.Them");
        System.out.println("|2.Tim kiem");
        System.out.println("|3.Xoa");
        System.out.println("|4.Sua");
        System.out.println("|5.Xuat");
        System.out.println("Nhap so khac de thoat !!!");
        System.out.print("Nhap lua chon: ");
        luachon=Integer.parseInt(sc.nextLine());
        switch(luachon){
            case 1: them(dsnv); break;
            case 2: tim(); break;
            case 3: xoa(); break;
            case 4: sua(); break;
            case 5: xuat(); break;
            default: System.out.println("---Thoat danh sach bang luong---"); break;
        }
    }
        while(luachon>=1 && luachon<=5);
    }
//xuat
    @Override public void xuat(){
        if(dsbl.size()==0)
            System.out.println("Danh sach rong!!!");
        else{
            System.out.println("========================");
            System.out.println("------------------------");
            System.out.println("Xuat danh sach bang luong");
            for(BangLuong l : dsbl){
                l.xuat();
                System.out.println("------------------------");
            }
        }
    }

//them
    public void them(DanhSachNhanVien dsNhanVien){
        ArrayList<NhanVien> dsnv = dsNhanVien.getDanhSachNhanVien(); //tao mang nhan vien
        System.out.println("========================");
       System.out.println("Danh sach nhan vien :");
       int i = 0;
       for(NhanVien nv : dsnv){
           System.out.println((i++)+". "); nv.xuat();
           System.out.println("------------------------");
       }
       //chon thu tu cua nhan vien can them banng luong
       int stt;
       do{
           System.out.print("Chon STT cua nhan vien muon them bang luong: ");
           stt = Integer.parseInt(sc.nextLine());
       }
       while(stt<0 || stt>=dsnv.size());

       NhanVien nv = dsnv.get(stt);
       int maNV = nv.getID();
       ArrayList<BangLuong> kq = timMaNhanVien(maNV);
       if(kq.size()==0){
           //them
           if(nv instanceof NhanVienChinhThuc){
               BangLuongChinhThuc l = new BangLuongChinhThuc();
               l.setNhanVien(nv);
               l.nhap();
               dsbl.add(l);
           }
           else{
                BangLuongThoiVu l = new BangLuongThoiVu();
                l.setNhanVien(nv);
                l.nhap();
                dsbl.add(l);
           }
       }
       else{
           System.out.println("Bang luong da ton tai!!!");
       }
    }

//tim kiem
    @Override public int menuTim(){
        int luachon;
        if(dsbl.size()==0){
            System.out.println("Danh sach rong!!!");
            luachon=-1;
        }
        else{
            System.out.println("========================");
            System.out.println("---MENU TIM KIEM---");
            System.out.println("|1.Tim theo ten nhan vien");
            System.out.println("|2.Tim theo loai bang bang luong");
            System.out.println("|3.Tim theo tien thuc lanh");
            System.out.println("|4.Tim theo so tien da thanh toan");
            System.out.println("Nhap so khac de thoat!!!");
            System.out.print("Nhap lua chon: ");
            luachon = Integer.parseInt(sc.nextLine());
        }
        return luachon;
    }

    @Override public void tim(){
        int luachon;
        ArrayList<BangLuong> kq = new ArrayList<>(); //mang de hung ket qua
        do{
            luachon = menuTim();
            //switch tren lua chon
            switch (luachon){
                case 1: kq = timTen(); break;
                case 2: kq = timLoaiBangLuong(); break;
                case 3: kq = timThucLanh(); break;
                case 4: kq = timThanhToan(); break;
            }
            if(luachon <1 || luachon>4){
                System.out.println("---Thoat---");
            }
            //kiem tra mang ket qua co rong khong
            else{
                if(kq.size()==0)
                    System.out.println("Khong tim thay!!!");
                else{
                    //xuat ket qua
                    System.out.println("========================");
                    System.out.println("Ket qua tim kiem :");
                    for(BangLuong l : kq){
                        l.xuat();
                        System.out.println("------------------------");
                    }
                }
            }
        }
        while(luachon>=1 && luachon<=4);
    }
    //cac ham tim kiem
    private ArrayList<BangLuong> timMaNhanVien(int manv){
        //tao mang moi chua ket qua vua tim duoc
        ArrayList<BangLuong> kq = new ArrayList<>();
        for(BangLuong l : dsbl){
            if(l.getNhanVien().getID()==manv)
                kq.add(l);
        }
        return kq;
    }

    private ArrayList<BangLuong> timTen(){
        //tao mang moi chua ket qua vua tim duoc
        ArrayList<BangLuong> kq = new ArrayList<>();
        System.out.print("Nhap ten nhan vien can tim: ");
        String tenInput=sc.nextLine();
        for(BangLuong l : dsbl){
            if(l.getNhanVien().getTen().equalsIgnoreCase(tenInput))
                kq.add(l);
        }
        return kq;
    }

    private ArrayList<BangLuong> timLoaiBangLuong(){
        //tao mang moi chua ket qua vua tim duoc
        ArrayList<BangLuong> kq = new ArrayList<>();
        int luachon;
        do{
        System.out.println("Chon bang luong cua loai nhan vien can tim");
        System.out.println("1.Bang luong cua nhan vien chinh thuc");
        System.out.println("2.Bang luong cua nhan vien thoi vu");
        System.out.print("Nhap lua chon: ");
        luachon = Integer.parseInt(sc.nextLine());
        }
        while(luachon!=1 && luachon!=2);
        if(luachon==1)
            for(BangLuong l : dsbl){
                if(l instanceof BangLuongChinhThuc)
                kq.add(l);
            }
        if(luachon==2)
            for(BangLuong l : dsbl){
                if(l instanceof BangLuongThoiVu)
                kq.add(l);
            }
        return kq;
    }

    private ArrayList<BangLuong> timThanhToan(){
        //tao mang moi chua ket qua vua tim duoc
        ArrayList<BangLuong> kq = new ArrayList<>();
        System.out.print("Nhap so tien da thanh toan can tim: ");
        double tien = Double.parseDouble(sc.nextLine());
        for(BangLuong l : dsbl)
            if(l.getThanhToan()==tien) 
                kq.add(l);
        return kq;
    }

    private ArrayList<BangLuong> timThucLanh(){
        //tao mang moi chua ket qua vua tim duoc
        ArrayList<BangLuong> kq = new ArrayList<>();
        System.out.print("Nhap so tien thuc lanh can tim: ");
        double tien = Double.parseDouble(sc.nextLine());
        for(BangLuong l : dsbl)
            if(l.getThucLanh()==tien) 
                kq.add(l);
        return kq;
    }

//xoa
    public void xoa(){
    int luachon;
    do{
        System.out.println("========================");
        System.out.println("---XOA KHOI DANH SACH---");
        luachon = menuTim();
        ArrayList<BangLuong> kq = new ArrayList<>(); //mang de hung ket qua
        //switch tren lua chon
        switch (luachon){
            case 1: kq = timTen(); break;
            case 2: kq = timLoaiBangLuong(); break;
            case 3: kq = timThucLanh();break;
            case 4: kq = timThanhToan(); break;
        }

        //kiem tra mang ket qua co rong khong
        if(kq.size()==0)
            System.out.println("Khong tim thay noi dung can xoa!!!");
        else{
            System.out.println("========================");
            //xuat ket qua
            System.out.println("Ket qua tim kiem :");
            for(BangLuong l : kq){
                l.xuat();
                System.out.println("------------------------");
            }
            //xoa
            for(BangLuong l : kq)
                dsbl.remove(l);
            System.out.println("Da xoa thanh cong!!!");
        }
    }
    while(luachon>=1 && luachon<=4);
}

//sua
    public void sua(){
        int luachon;
    do{
        System.out.println("========================");
        System.out.println("---SUA THONG TIN---");
        luachon = menuTim();
        ArrayList<BangLuong> kq = new ArrayList<>(); //mang de hung ket qua
        //switch tren lua chon
        switch (luachon){
            case 1: kq = timTen(); break;
            case 2: kq = timLoaiBangLuong(); break;
            case 3: kq = timThucLanh();break;
            case 4: kq = timThanhToan(); break;
        }

        //kiem tra mang ket qua co rong khong
        if(luachon>=1 && luachon<=4){
            if(kq.size()==0)
                System.out.println("Khong tim thay bang luong can sua!!!");
            else{
                //tim thay
                //xuat ket qua
                System.out.println("========================");
                System.out.println("Ket qua tim kiem :");
                int i = 0;
                for(BangLuong l : kq){
                    System.out.println((i++)+". "); l.xuat();
                    System.out.println("------------------------");
                }
                //sua
                //chon thu tu cua nhan vien can sua
                int stt;
                do{
                    System.out.print("Chon STT cua nhan vien muon sua: ");
                    stt = Integer.parseInt(sc.nextLine());
                }
                while(stt<0 || stt>=kq.size());
                
                //***nhap lua chon thong tin muon sua***
                int sua;
                /*sua truc tiep tren dsbangluong*/
                
                BangLuong l = kq.get(stt);
                int index=dsbl.indexOf(l);
                do{
                //menu thong tin can sua
                System.out.println("========================");
                System.out.println("---MENU SUA---");
                if(l instanceof BangLuongChinhThuc){
                    System.out.println("|1.So ngay cong");
                    System.out.println("|2.Muc luong theo ngay");
                    System.out.println("|3.Tien tang ca");
                }
                else if(l instanceof BangLuongThoiVu){
                    System.out.println("|1.So gio lam");
                    System.out.println("|2.Muc luong theo gio");
                    System.out.println("|3.Tien thuong");
                }
                System.out.println("|4.Thanh toan");
                System.out.println("Nhap so khac de thoat !!!");
                //nhap lua chon
                System.out.print("Nhap lua chon: ");
                sua = Integer.parseInt(sc.nextLine());
                //goi cac ham sua
                if(sua>=1 && sua<=4){
                    switch(sua){
                        case 1: 
                            if(l instanceof BangLuongChinhThuc){
                                suaSoNgayCong((BangLuongChinhThuc) l);
                            }
                            else if(l instanceof BangLuongThoiVu)
                                suaSoGio((BangLuongThoiVu) l);
                            break;
                        case 2:
                            if(l instanceof BangLuongChinhThuc)
                                suaMucLuongTheoNgay((BangLuongChinhThuc) l);
                            else if(l instanceof BangLuongThoiVu)
                                suaMucLuongTheoGio((BangLuongThoiVu) l);
                            break;
                        case 3: 
                            if(l instanceof BangLuongChinhThuc)
                                suaTienTangCa((BangLuongChinhThuc) l);
                            else if(l instanceof BangLuongThoiVu){
                                suaTienThuong((BangLuongThoiVu) l);
                            }
                            break;
                        case 4: 
                            do{
                                System.out.print("Nhap so tien da thanh toan: ");
                                l.setThanhToan(Double.parseDouble(sc.nextLine()));
                            } while(l.checkThanhToan());
                            break;
                    }
                    System.out.println("Hien thi sau khi cap nhat:");
                    l.xuat();
                    dsbl.set(index,l);
                }
                else System.out.println("---Thoat---");
                }
                while(sua>=1 && sua<=4);
            }
        }
    }
        while(luachon>=1 && luachon<=4);
    }

    private void suaSoNgayCong(BangLuongChinhThuc l){
        int soNgay;
        do{
            System.out.print("Nhap so ngay lam: ");
            soNgay = Integer.parseInt(sc.nextLine());
        } while(l.checkSoNgayLam());
        l.setSoNgayLam(soNgay); //thue va thuc lanh da duoc set lai trong ham nay
    }

    private void suaMucLuongTheoNgay(BangLuongChinhThuc l){
        double mucLuongTheoNgay;
        do{
            System.out.print("Nhap muc luong theo ngay: ");
            mucLuongTheoNgay=Double.parseDouble(sc.nextLine());
        }while(l.checkMucLuongTheoNgay());
        l.setMucLuongTheoNgay(mucLuongTheoNgay);
    }

    private void suaSoGio(BangLuongThoiVu l){
        int soGio;
        do{
            System.out.print("Nhap so gio lam: ");
            soGio=Integer.parseInt(sc.nextLine());
        }while(l.checkSoGioLam());
        l.setSoGioLam(soGio);
    }

    private void suaMucLuongTheoGio(BangLuongThoiVu l){
        double mucLuongTheoGio;
        do{
            System.out.print("Nhap muc luong theo gio: ");
            mucLuongTheoGio=Double.parseDouble(sc.nextLine());
        }while(l.checkMucLuongTheoGio());
        l.setMucLuongTheoGio(mucLuongTheoGio);
    }

    private void suaTienThuong(BangLuongThoiVu l){
        double tienThuong;
        do{
            System.out.print("Nhap tien thuong: ");
            tienThuong=Double.parseDouble(sc.nextLine());
        }while(l.checktienThuong());
        l.setTienThuong(tienThuong);
    }

    private void suaTienTangCa(BangLuongChinhThuc l){
        double tienTangCa;
        do{
            System.out.print("Nhap tien tang ca: ");
            tienTangCa=Double.parseDouble(sc.nextLine());
        } while(l.checkTienTangCa());
        l.setTienTangCa(tienTangCa);
    }

//ham cap nhat
    public void xoaNhanVien(NhanVien nvCanXoa){
        int idCanXoa = nvCanXoa.getID();
        BangLuong l;
        for(int i =0; i<dsbl.size(); i++){
            l=dsbl.get(i);
            if(l.getNhanVien().getID() == idCanXoa)
                dsbl.remove(l);
        }
    }

    public void suaNhanVien(NhanVien nv){
        int idCanSua = nv.getID();
        for(BangLuong l : dsbl)
            if(l.getNhanVien().getID() == idCanSua)
                l.setNhanVien(nv);
    }

    public void xoaIDPB(int idCanXoa){
        for(BangLuong l : dsbl)
            if(l.getNhanVien().getMaPhongBan() == idCanXoa)
                l.getNhanVien().setMaPhongBan(0);
    }


}
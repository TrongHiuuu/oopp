import java.util.Scanner;
import java.util.ArrayList;

public class QuanLyNhanSu {
    Scanner sc = new Scanner(System.in);
    private DanhSachNhanVien dsnv;
    private DanhSachBangLuong dsbl;
    private DanhSachDuAn dsda;
    private DanhSachPhongBan dspb;

    public QuanLyNhanSu(){
        dsnv=new DanhSachNhanVien();
        dsbl=new DanhSachBangLuong();
        dsda=new DanhSachDuAn();
        dspb=new DanhSachPhongBan();
    }
    
    public QuanLyNhanSu(DanhSachNhanVien dsnv, DanhSachBangLuong dsbl, DanhSachDuAn dsda){
        this.dsnv=dsnv;
        this.dsbl=dsbl;
        this.dsda=dsda;
        this.dspb=dspb;
    }
    
    public void docFileTongHop(){
        dsnv.docFile();
        dsbl.docFile(); 
        dsda.docFile();
        dspb.docFile();
    }

    public void ghiFileTongHop(){
        dsnv.ghiFile();
        dsbl.ghiFile();
        dsda.ghiFile();
        dspb.ghiFile();
    }

    public void menuQuanLy(){
        int luachon;
        docFileTongHop();
        do{
        System.out.println("---QUAN LY NHAN SU---");
        System.out.println("Chon doi tuong can quan ly:");
        System.out.println("1.Nhan vien");
        System.out.println("2.Bang luong cua nhan vien");
        System.out.println("3.Phong ban");
        System.out.println("4.Du an");
        System.out.println("Nhap so khac de thoat");
        System.out.print("Nhap lua chon: ");
        luachon=Integer.parseInt(sc.nextLine());
        switch(luachon){
            case 1: dsnv.menu(dspb,dsbl,dsda); break;
            case 2: dsbl.menu(dsnv); break;
            case 3: dspb.menu(dsnv,dsbl,dsda); break;
            case 4: dsda.menu(dsnv,dspb); break;
            default: 
                ghiFileTongHop();
                System.out.println("***Hen gap lai***"); break;
        }
        }while(luachon>=1 && luachon<=4);
    }
}
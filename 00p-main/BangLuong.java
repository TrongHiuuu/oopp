import java.util.Scanner;

public abstract class BangLuong{
    public Scanner sc = new Scanner(System.in);

//field
    private NhanVien nv;
    private double thue;
    private double thucLanh;
    private double thanhToan;
    
//constructor
    public BangLuong(){
        nv=new NhanVien();
        thue=0;
        thucLanh=0;
        thanhToan=0;
    }

    public BangLuong(NhanVien nv, double thanhToan){ //check before pass parameter
        this.nv=nv;
        thue=tinhThue();
        thucLanh=tongLuong(); 
        this.thanhToan=thanhToan;
    }

    public BangLuong(NhanVien nv, double thue, double thucLanh, double thanhToan){ //dung cho doc file
        this.nv=nv;
        this.thue=thue;
        this.thucLanh=thucLanh;
        this.thanhToan=thanhToan;
    }

//nhap
    public void nhap(){
        thue=tinhThue();
        thucLanh=tongLuong();
        do{
        System.out.print("Nhap so tien da thanh toan: ");
        thanhToan=Double.parseDouble(sc.nextLine());
        } while(checkThanhToan());
    }

//xuat
    public void xuat(){
        System.out.println("thue: "+thue);
        System.out.println("thucLanh: "+thucLanh);
        System.out.println("thanhToan: "+thanhToan);
    }

//cac ham abstract
    public abstract double tongLuong();
    public abstract double tinhThue();
    
//kiem tra dieu kien
    public boolean checkThanhToan(){
        if(thanhToan>=0 && thanhToan<=tongLuong()) return false;
        if(thanhToan<0){
            System.out.println("Can nhap lon hon bang 0 !!!");
        }
        else if(thanhToan>tongLuong()){
            System.out.println("Thanh toan nhieu hon tong bang luong");
        }
        return true;
    }

//get - set
    public double getThue(){
        return thue;
    }

    public void setThue(double thue){
        this.thue=thue;
    }

    public double getThanhToan(){
        return thanhToan;
    }

    public void setThanhToan(double thanhToanInput){
        thanhToan=thanhToanInput;
    }

    public double getThucLanh(){
        return thucLanh;
    }

    public void setThucLanh(double thucLanh){
        this.thucLanh=thucLanh;
    }

    public NhanVien getNhanVien(){
        return nv;
    }

    public void setNhanVien(NhanVien nv){
        this.nv=nv;
    }
}
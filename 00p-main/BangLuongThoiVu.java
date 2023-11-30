public class BangLuongThoiVu extends BangLuong{
    //field
        private double tienThuong;
        private int soGio;
        private double mucLuongTheoGio;
    
    //constructor
        public BangLuongThoiVu(){
            super();
            tienThuong=0;
            soGio=0;
            mucLuongTheoGio=0;
        }
    
        public BangLuongThoiVu(NhanVienThoiVu nv, int soGio, double mucLuongTheoGio,  double tienThuong, double thanhToan){
            super(nv, thanhToan);
            this.tienThuong=tienThuong;
            this.soGio=soGio;
            this.mucLuongTheoGio=soGio;
        }
    
        public BangLuongThoiVu(NhanVienThoiVu nv, int soGio, double mucLuongTheoGio,  double tienThuong,
                            double thue, double thucLanh, double thanhToan){ //kiem tra dieu kien truoc khi pass parameter
            super(nv,thue, thucLanh, thanhToan);
            this.tienThuong=tienThuong;
            this.soGio=soGio;
            this.mucLuongTheoGio=soGio;
        }
    //nhap
        @Override public void nhap(){
            do{
                System.out.print("Nhap tien thuong: ");
                tienThuong=Double.parseDouble(sc.nextLine());
            }while(checktienThuong());
            do{
                System.out.print("Nhap so gio lam: ");
                soGio=Integer.parseInt(sc.nextLine());
            }while(checkSoGioLam());
            do{
                System.out.print("Nhap muc luong theo gio: ");
                mucLuongTheoGio=Double.parseDouble(sc.nextLine());
            }while(checkMucLuongTheoGio());
            super.nhap();
        }
    
    //xuat
        @Override public void xuat(){
            super.getNhanVien().xuat();
            System.out.print("so gio lam: "+soGio);
            System.out.println("luong theo gio: "+mucLuongTheoGio);
            System.out.println("tien thuong: "+tienThuong);
            super.xuat();
        }
    
    //override abstract method
        @Override public double tongLuong(){
            return soGio*mucLuongTheoGio+tienThuong-getThue();
        }
    
        @Override public double tinhThue(){
            return soGio*mucLuongTheoGio*8/100;
        }
    //kiem tra dieu kien
        public boolean checktienThuong(){
            if(tienThuong<0){
                System.out.println("Can nhap lon hon bang 0 !!!");
                return true;
            }
            return false;
        }
    
        public boolean checkSoGioLam(){
            if(soGio<0){
                System.out.println("Can nhap lon hon bang 0 !!!");
                return true;
            }
            return false;
        }
    
        public boolean checkMucLuongTheoGio(){
            if(mucLuongTheoGio<0){
                System.out.println("Can nhap lon hon bang 0 !!!");
                return true;
            }
            return false;
        }
        //get - set
        public double getTienThuong(){
            return tienThuong;
        }
    
        public void setTienThuong(double tienThuong){
            this.tienThuong = tienThuong;
            setThue(tinhThue());
            setThucLanh(tongLuong());
        }
    
        public void setSoGioLam(int soGio){
            this.soGio=soGio;
            setThue(tinhThue());
            setThucLanh(tongLuong());
        }
    
        public int getSoGioLam(){
            return soGio;
        }
    
        public void setMucLuongTheoGio(double tien){
            mucLuongTheoGio=tien;
            setThue(tinhThue());
            setThucLanh(tongLuong());
        }
    
        public double getMucLuongTheoGio(){
            return mucLuongTheoGio;
        }
    }
    
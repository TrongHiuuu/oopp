public class BangLuongChinhThuc extends BangLuong{
    //field
        private int soNgay;
        private double mucLuongTheoNgay;
        private double tienTangCa;
    //constructor
        public BangLuongChinhThuc(){
            super();
            tienTangCa=0;
            soNgay=0;
            mucLuongTheoNgay=0;
        }
    
        public BangLuongChinhThuc(NhanVienChinhThuc nv, int soNgay, double mucLuongTheoNgay, double tienTangCa, double thanhToan){ //kiem tra dieu kien truoc khi pass parameter
            super(nv,thanhToan);
            this.tienTangCa=tienTangCa;
            this.soNgay=soNgay;
            this.mucLuongTheoNgay=mucLuongTheoNgay;
        }

        public BangLuongChinhThuc(NhanVienChinhThuc nv, int soNgay, double mucLuongTheoNgay, double tienTangCa, 
                            double thue, double thucLanh, double thanhToan){ //kiem tra dieu kien truoc khi pass parameter
            super(nv,thue, thucLanh, thanhToan);
            this.tienTangCa=tienTangCa;
            this.soNgay=soNgay;
            this.mucLuongTheoNgay=mucLuongTheoNgay;
        }
        
    //nhap
        @Override public void nhap(){
            do{
            System.out.print("Nhap tien tang ca: ");
            tienTangCa=Double.parseDouble(sc.nextLine());
            } while(checkTienTangCa());
            do{
                System.out.print("Nhap so ngay lam: ");
                soNgay = Integer.parseInt(sc.nextLine());
            } while(checkSoNgayLam());
            do{
                System.out.print("Nhap muc luong theo ngay: ");
                mucLuongTheoNgay=Double.parseDouble(sc.nextLine());
            }while(checkMucLuongTheoNgay());
            super.nhap();
        }
    
    //xuat
        @Override public void xuat(){
            //xuat nhan vien trong class danh sach noi vo cho nay
            super.getNhanVien().xuat();
            System.out.print("so ngay cong: "+soNgay);
            System.out.println("luong theo ngay: "+mucLuongTheoNgay);
            System.out.println("tien tang ca: "+tienTangCa);
            super.xuat();
        }
    
    //override abstract method
        @Override public double tongLuong(){
            return mucLuongTheoNgay*soNgay+tienTangCa-getThue(); //base + bonus - tax
        }
    
        @Override public double tinhThue(){
            return mucLuongTheoNgay*soNgay*8/100;
        }
    
    //kiem tra dieu kien
        public boolean checkTienTangCa(){
            if(tienTangCa<0){
                System.out.println("Can nhap lon hon bang 0");
                return true;
            }
            return false;
        }
    
        public boolean checkSoNgayLam(){
            if(soNgay<0){
                System.out.println("Can nhap lon hon bang 0");
                return true;
            }
            return false;
        }
    
        public boolean checkMucLuongTheoNgay(){
            if(mucLuongTheoNgay<0){
                System.out.println("Can nhap lon hon bang 0");
                return true;
            }
            return false;
        }
    //get - set
    //NOTE
        public void setTienTangCa(double tien){
            tienTangCa=tien;
            setThue(tinhThue());
            setThucLanh(tongLuong());
        }
    
        public double getTienTangCa(){
            return tienTangCa;
        }
    
        public void setSoNgayLam(int soNgay){
            this.soNgay=soNgay;
            setThue(tinhThue());
            setThucLanh(tongLuong());
        }
    
        public int getSoNgayLam(){
            return soNgay;
        }
    
        public void setMucLuongTheoNgay(double tien){
            mucLuongTheoNgay=tien;
            setThue(tinhThue());
            setThucLanh(tongLuong());
        }
    
        public double getMucLuongTheoNgay(){
            return mucLuongTheoNgay;
        }
    }
    

interface DanhSach {
    //static int soluong; //update so luong danh sach // arraylist.length nen co the bo field nay
//xuat
//      trong lop con override xuat():
//      printout("xuat danh sach <ten danh sach>")
//      duyet qua phan tu cua mang
    public void xuat();

//them
//      tao doi tuong can them -> goi ham nhap cua doi tuong
//      add bien moi vao danh sach -->arraylist.add(doituong)
//      update soluong // arraylist.length nen co the bo field nay
    //abstract public void them();    

//timkiem
//      ** lop con override menuTim(): list cac tieu chi tim kiem **
//              cho nguoi dung lua chon tim kiem theo tieu chi nao
//      (VD: DS nhanvien:
//          public int menuTim(){
//              int luachon;
//              nhanvien nv;
//              printout("1. tim theo ten nhan vien")
//              printout("2. tim theo ma nhan vien")
//              luachon=sc.nextInt();
//              return luachon;
//      )
//       ** xay dung method cho tung tieu chi tim kiem **
//          *note: method co kieu tra ve
//      (VD: tim theo tenNV; maNV
//         => public nhanvien timTenNV() 
//         => public nhanvien timMaNV()     )
//         ** override tim(): **    
//              int luachon = menuTim();
//              nhanvien nv;
//              switch (luachon){
//                 case 1: ...method
//                 case 2: ...method
//                      VD: nv = timTenNV();
//              }
//              ** xuat kq tim kiem **:  goi lai method xuat cua doi tuong: nv.xuat();
//          }

    public int menuTim();  //tra ve lua chon cua user
    public void tim();
//xoa
//         override xoa():
//              ** tim doi tuong can xoa **
//               int luachon = menuTim();
//              nhanvien nv;
//              switch (luachon){
//                 case 1: ...method
//                 case 2: ...method
//                      VD: nv = timTenNV(); //gan doi tuong vua tim duoc cho nv
//              }
//              ** xuat thong tin truoc khi xoa **: nv.xuat();
//              ** xoa doi tuong khoi danh sach **:  DanhsachNhanvien.remove(nv)
//          update soluong //arraylist.length nen co the bo field nay
    //public void xoa();

//sua
//         override sua():
//              ** tim doi tuong can sua **
//              int luachon = menuTim();
//              nhanvien nv;
//              switch (luachon){
//                 case 1: ...method
//                 case 2: ...method
//                      VD: nv = timTenNV(); //gan doi tuong vua tim duoc cho nv
//              }
//              ** xuat thong tin truoc khi sua **: nv.xuat();
//              ** menu nd can sua **: tuong tu menuTim
//              switch case ... -> goi cac ham set cua doi tuong 
    //abstract public void sua();
    public void docFile();
    public void ghiFile();
}
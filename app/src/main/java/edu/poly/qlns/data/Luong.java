package edu.poly.qlns.data;
import java.util.ArrayList;
import java.util.List;

import edu.poly.qlns.DatabaseHelper;

public class Luong {

    private DatabaseHelper databaseHelper;
    private String tenNhanVien;
    private String chucVu;
    private double luongCoBan;
    private double luongThucLanh;

    public Luong() {
        // Khởi tạo đối tượng Luong
    }
    public Luong(DatabaseHelper dbHelper) {
        this.databaseHelper = dbHelper;
    }

    // Phương thức tính lương và thực lãnh dựa trên công thức đã cung cấp
    public List<Double> tinhLuongNhanVien() {
        List<Double> thucLanhList = new ArrayList<>();

        // Lấy danh sách các nhân viên từ bảng ChamCong
        List<NhanVien> nhanVienList = databaseHelper.getAllNhanVien();

        // Lặp qua từng nhân viên để tính toán lương và thực lãnh dựa trên công thức đã đề xuất
        for (NhanVien nhanVien : nhanVienList) {
            int maNV = nhanVien.getMaNhanVien();

            // Lấy thông tin chấm công từ bảng ChamCong
            ChamCong chamCong = databaseHelper.getChamCongByMaNV(maNV);

            if (chamCong != null) {
                int ngayCong = chamCong.getNgayCong();
                int ngayPhep = chamCong.getNgayPhep();
                int ngoaiGio = chamCong.getNgoaiGio();

                // Lấy lương cơ bản từ bảng NhanVien
                double luongCB = nhanVien.getLuongCB();

                // Thực hiện tính toán theo công thức để tính lương và thực lãnh
                double luong = luongCB * (ngayCong + ngoaiGio * 2 - ngayPhep) / 26;
                double thucLanh = luong - (luong * 0.1); // Giảm 10% thuế

                // Thêm dữ liệu thực lãnh của nhân viên vào danh sách
                thucLanhList.add(thucLanh);
            }
        }

        return thucLanhList;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public double getLuongThucLanh() {
        return luongThucLanh;
    }

    public void setLuongThucLanh(double luongThucLanh) {
        this.luongThucLanh = luongThucLanh;
    }
}




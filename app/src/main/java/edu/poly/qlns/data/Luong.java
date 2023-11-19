package edu.poly.qlns.data;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.poly.qlns.DatabaseHelper;

public class Luong {

    private DatabaseHelper databaseHelper;
    private String tenNhanVien;
    private String chucVu;
    private double luongCoBan;
    private double luongThucLanh;
    public Luong() {
        // Khởi tạo đối tượng Luong với các giá trị mặc định hoặc logic cần thiết
    }
    public static Luong createFromChamCong(ChamCong chamCong) {
        Luong luongMoi = new Luong();
        luongMoi.setMaNhanVien(chamCong.getMaNhanVien());
        // Thiết lập các thông tin khác cần thiết từ đối tượng ChamCong
        // Ví dụ: luongMoi.setTenNhanVien(chamCong.getTenNhanVien());
        //       luongMoi.setChucVu(chamCong.getChucVu());
        //       luongMoi.setLuongCoBan(chamCong.getLuongCoBan());
        //       luongMoi.setLuongThucLanh(chamCong.getLuongThucLanh());
        return luongMoi;
    }
    public Luong(DatabaseHelper dbHelper) {
        this.databaseHelper = dbHelper;
    }

    // Phương thức tính lương và thực lãnh dựa trên công thức đã cung cấp
    public List<Luong> tinhLuongNhanVien(List<Luong> luongList) {
        List<Luong> result = new ArrayList<>();

        for (Luong luong : luongList) {
            int maNV = luong.getMaNhanVien();
            Log.d("LuongDebug", "MaNV: " + luong.getMaNhanVien());

            ChamCong chamCong = databaseHelper.getChamCongByMaNV(luong.getMaNhanVien());

            if (chamCong != null) {
                int ngayCong = chamCong.getNgayCong();
                int ngayPhep = chamCong.getNgayPhep();
                int ngoaiGio = chamCong.getNgoaiGio();
                double luongCB = luong.getLuongCoBan();

                double tam = luongCB * (ngayCong + ngoaiGio * 2 - ngayPhep) / 26;
                double thucLanh = tam - (tam * 0.1);

                // Tạo một đối tượng Luong mới và cập nhật thông tin thu nhập thực lãnh
                Luong luongMoi = Luong.createFromChamCong(chamCong);
                luongMoi.setTenNhanVien(luong.getTenNhanVien());
                luongMoi.setChucVu(luong.getChucVu());
                luongMoi.setLuongCoBan(luong.getLuongCoBan());
                luongMoi.setLuongThucLanh(thucLanh);

                // Thêm đối tượng Luong mới vào danh sách kết quả
                result.add(luongMoi);
            }
        }

        return result;
    }



    public String getTenNhanVien() {
        return tenNhanVien;
    }
    private int maNV;

    // Phương thức getter cho maNV
    public int getMaNhanVien() {
        return this.maNV; // Trả về giá trị maNV từ đối tượng Luong hiện tại
    }

    // Phương thức setter cho maNV
    public void setMaNhanVien(int maNhanVien) {
        this.maNV = maNhanVien;
    }

    // Khởi tạo maNV
    public Luong(int maNV) {
        this.maNV = maNV;
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




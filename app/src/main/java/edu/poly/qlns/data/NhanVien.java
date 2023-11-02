package edu.poly.qlns.data;

public class NhanVien {
    private int maNhanVien;
    private String tenNhanVien;
    private String ngaySinh;
    private String gioiTinh;
    private String diaChi;
    private String soDienThoai;
    private String coGiaDinh;
    private String trinhDo;
    private double luongCB;
    private String ngayLamViec;
    private String chucVu;
    private String maPhongBan;

    public NhanVien(int maNhanVien, String tenNhanVien, String ngaySinh, String gioiTinh, String diaChi, String soDienThoai, String coGiaDinh, String trinhDo, double luongCB, String ngayLamViec, String chucVu, String maPhongBan) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.coGiaDinh = coGiaDinh;
        this.trinhDo = trinhDo;
        this.luongCB = luongCB;
        this.ngayLamViec = ngayLamViec;
        this.chucVu = chucVu;
        this.maPhongBan = maPhongBan;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getCoGiaDinh() {
        return coGiaDinh;
    }

    public String getTrinhDo() {
        return trinhDo;
    }

    public double getLuongCB() {
        return luongCB;
    }

    public String getNgayLamViec() {
        return ngayLamViec;
    }

    public String getChucVu() {
        return chucVu;
    }

    public String getMaPhongBan() {
        return maPhongBan;
    }
}



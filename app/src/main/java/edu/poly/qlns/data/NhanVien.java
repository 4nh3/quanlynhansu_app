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

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getPhaiTinh() {
        return gioiTinh;
    }

    public void setPhaiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getCoGiaDinh() {
        return coGiaDinh;
    }

    public void setCoGiaDinh(String coGiaDinh) {
        this.coGiaDinh = coGiaDinh;
    }

    public String getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }

    public double getLuongCB() {
        return luongCB;
    }

    public void setLuongCB(double luongCB) {
        this.luongCB = luongCB;
    }

    public String getNgayLamViec() {
        return ngayLamViec;
    }

    public void setNgayLamViec(String ngayLamViec) {
        this.ngayLamViec = ngayLamViec;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getMaPhongBan() {
        return maPhongBan;
    }

    public void setMaPhongBan(String maPhongBan) {
        this.maPhongBan = maPhongBan;
    }
}

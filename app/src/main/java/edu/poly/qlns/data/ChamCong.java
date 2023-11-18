package edu.poly.qlns.data;

public class ChamCong {
    private int maNhanVien;
    private int thang;
    private int ngayCong;
    private int ngayPhep;
    private int ngoaiGio;

    public ChamCong(int maNhanVien, int thang, int ngayCong, int ngayPhep, int ngoaiGio) {
        this.maNhanVien = maNhanVien;
        this.thang = thang;
        this.ngayCong = ngayCong;
        this.ngayPhep = ngayPhep;
        this.ngoaiGio = ngoaiGio;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNgayCong() {
        return ngayCong;
    }

    public void setNgayCong(int ngayCong) {
        this.ngayCong = ngayCong;
    }

    public int getNgayPhep() {
        return ngayPhep;
    }

    public void setNgayPhep(int ngayPhep) {
        this.ngayPhep = ngayPhep;
    }

    public int getNgoaiGio() {
        return ngoaiGio;
    }

    public void setNgoaiGio(int ngoaiGio) {
        this.ngoaiGio = ngoaiGio;
    }
}

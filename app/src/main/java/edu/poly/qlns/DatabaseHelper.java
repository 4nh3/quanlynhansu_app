package edu.poly.qlns;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.poly.qlns.data.NhanVien;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Tên cơ sở dữ liệu và phiên bản
    public static final String DATABASE_NAME = "QuanLyNhanSu.db";
    public static final int DATABASE_VERSION = 4;
    private SQLiteDatabase db;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo các bảng cơ sở dữ liệu ở đây
        createTables(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Cập nhật cơ sở dữ liệu (nếu cần) ở đây
        // Chẳng hạn, nếu bạn muốn thêm cột mới hoặc làm thay đổi cấu trúc bảng.
    }

    public void createTables(SQLiteDatabase db) {
        // Tạo bảng Nhân viên
        String createNhanVienTable = "CREATE TABLE NhanVien ("
                + "manv INTEGER PRIMARY KEY,"
                + "tennv TEXT,"
                + "ngaysinh TEXT,"
                + "phaitinh TEXT,"
                + "cogiadinh TEXT,"
                + "diachi TEXT,"
                + "sodienthoai TEXT,"
                + "trinhdo TEXT,"
                + "luongcb INTEGER,"
                + "ngaylamviec DATE,"
                + "chucvu TEXT,"
                + "maphongban TEXT,"
                + "FOREIGN KEY (maphongban) REFERENCES PhongBan(mapb)"
                + ");";
        db.execSQL(createNhanVienTable);

        // Tạo bảng Chấm công
        String createChamCongTable = "CREATE TABLE ChamCong ("
                + "manv INTEGER,"
                + "thang INTEGER,"
                + "ngaycong DATE,"
                + "ngayphep INTEGER,"
                + "ngoaigio INTEGER,"
                + "FOREIGN KEY (manv) REFERENCES NhanVien(manv)"
                + ");";
        db.execSQL(createChamCongTable);

        // Tạo bảng Phòng ban
        String createPhongBanTable = "CREATE TABLE PhongBan ("
                + "mapb TEXT PRIMARY KEY ,"
                + "tenpb TEXT,"
                + "sdt INTEGER"
                + ");";
        db.execSQL(createPhongBanTable);

        // Tạo bảng Người dùng
        String createUsersTable = "CREATE TABLE Nguoidung ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "tentk TEXT,"
                + "matkhau TEXT"
                + ");";
        db.execSQL(createUsersTable);
    }


    public long updatePassword(SQLiteDatabase db, String username, String newPassword) {
        ContentValues values = new ContentValues();
        values.put("matkhau", newPassword);

        // Làm việc với cơ sở dữ liệu để cập nhật mật khẩu
        return db.update("Nguoidung", values, "tentk = ?", new String[]{username});
    }
//    public long addUser(SQLiteDatabase db, String username, String hashedPassword) {
//        ContentValues values = new ContentValues();
//        values.put("tentk", username);
//        values.put("matkhau", hashedPassword);
//
//        long newRowId = db.insert("Nguoidung", null, values);
//        return newRowId;
//    }
    public void insertSampleUserData(String tentk, String matkhau) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tentk", tentk);
        values.put("matkhau", matkhau);
        db.insert("Nguoidung", null, values);
        db.close();
    }
    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> nhanVienList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM NhanVien", null);

        if (cursor.moveToFirst()) {
            do {
                int maNhanVien = cursor.getInt(cursor.getColumnIndex("manv"));
                String tenNhanVien = cursor.getString(cursor.getColumnIndex("tennv"));
                String ngaySinh = cursor.getString(cursor.getColumnIndex("ngaysinh"));
                String gioiTinh = cursor.getString(cursor.getColumnIndex("phaitinh"));
                String diaChi = cursor.getString(cursor.getColumnIndex("diachi"));
                String soDienThoai = cursor.getString(cursor.getColumnIndex("sodienthoai"));
                String coGiaDinh = cursor.getString(cursor.getColumnIndex("cogiadinh"));
                String trinhDo = cursor.getString(cursor.getColumnIndex("trinhdo"));
                double luongCB = cursor.getDouble(cursor.getColumnIndex("luongcb"));
                String ngayLamViec = cursor.getString(cursor.getColumnIndex("ngaylamviec"));
                String chucVu = cursor.getString(cursor.getColumnIndex("chucvu"));
                String maPhongBan = cursor.getString(cursor.getColumnIndex("maphongban"));

                NhanVien nhanVien = new NhanVien(maNhanVien, tenNhanVien, ngaySinh, gioiTinh, diaChi, soDienThoai, coGiaDinh, trinhDo, luongCB, ngayLamViec, chucVu, maPhongBan);
                nhanVienList.add(nhanVien);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return nhanVienList;
    }
    public void deleteNhanVien(NhanVien nhanVien) {
        SQLiteDatabase db = this.getWritableDatabase();
        int maNV = nhanVien.getMaNhanVien();
        db.delete("NhanVien", "manv = ?", new String[]{String.valueOf(maNV)});
        db.close();
    }
    public long updateNhanVien(NhanVien nhanVien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tennv", nhanVien.getTenNhanVien());
        values.put("ngaysinh", nhanVien.getNgaySinh());
        values.put("phaitinh", nhanVien.getPhaiTinh());
        values.put("diachi", nhanVien.getDiaChi());
        values.put("sodienthoai", nhanVien.getSoDienThoai());
        values.put("cogiadinh", nhanVien.getCoGiaDinh());
        values.put("trinhdo", nhanVien.getTrinhDo());
        values.put("luongcb", String.valueOf(nhanVien.getLuongCB()));
        values.put("ngaylamviec", nhanVien.getNgayLamViec());
        values.put("chucvu", nhanVien.getChucVu());
        values.put("maphongban", nhanVien.getMaPhongBan());

        long result = db.update("NhanVien", values, "manv = ?", new String[]{String.valueOf(nhanVien.getMaNhanVien())});
        db.close();
        return result;
    }
    public NhanVien getNhanVienByMaNV(int maNhanVien) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                "manv",
                "tennv",
                "ngaysinh",
                "phaitinh",
                "diachi",
                "sodienthoai",
                "cogiadinh",
                "trinhdo",
                "luongcb",
                "ngaylamviec",
                "chucvu",
                "maphongban"
        };
        String selection = "manv = ?";
        String[] selectionArgs = {String.valueOf(maNhanVien)};
        Cursor cursor = db.query("NhanVien", columns, selection, selectionArgs, null, null, null);

        NhanVien nhanVien = null;
        if (cursor != null && cursor.moveToFirst()) {
            int maNV = cursor.getInt(cursor.getColumnIndex("manv"));
            String tenNV = cursor.getString(cursor.getColumnIndex("tennv"));
            String ngaySinh = cursor.getString(cursor.getColumnIndex("ngaysinh"));
            String phaiTinh = cursor.getString(cursor.getColumnIndex("phaitinh"));
            String diaChi = cursor.getString(cursor.getColumnIndex("diachi"));
            String soDienThoai = cursor.getString(cursor.getColumnIndex("sodienthoai"));
            String coGiaDinh = cursor.getString(cursor.getColumnIndex("cogiadinh"));
            String trinhDo = cursor.getString(cursor.getColumnIndex("trinhdo"));
            double luongCB = cursor.getDouble(cursor.getColumnIndex("luongcb"));
            String ngayLamViec = cursor.getString(cursor.getColumnIndex("ngaylamviec"));
            String chucVu = cursor.getString(cursor.getColumnIndex("chucvu"));
            String maPhongBan = cursor.getString(cursor.getColumnIndex("maphongban"));

            nhanVien = new NhanVien(maNV, tenNV, ngaySinh, phaiTinh, diaChi, soDienThoai, coGiaDinh, trinhDo, luongCB, ngayLamViec, chucVu, maPhongBan);
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return nhanVien;
    }
    public void insertSampleData() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Đưa dữ liệu mẫu vào bảng Nhân viên
        values.put("manv", "001");
        values.put("tennv", "Nguyen Van A");
        values.put("ngaysinh", "1990-01-01");
        values.put("phaitinh", "Nam");
        values.put("cogiadinh", "Có gia đình");
        values.put("diachi", "123 Đường ABC");
        values.put("sodienthoai", "0123456789");
        values.put("trinhdo", "Cử nhân");
        values.put("luongcb", "10000000");
        values.put("ngaylamviec", "2023-10-01");
        values.put("chucvu", "Nhân viên");
        values.put("maphongban", "BGD");
        db.insert("NhanVien", null, values);

//         Đưa dữ liệu mẫu vào bảng Chấm công
        values.clear();
        values.put("manv", 1);
        values.put("thang", 10);
        values.put("ngaycong", "2023-10-01");
        values.put("ngayphep", 2);
        values.put("ngoaigio", 0);
        db.insert("ChamCong", null, values);

//         Đưa dữ liệu mẫu vào bảng Phòng ban

//        values.put("mapb", "HCH");
//        values.put("tenpb", "Phong Hanh Chinh");
//        values.put("sdt", "0987954321");
//        db.insert("PhongBan", null, values);
//        db.close();
//
//        values.put("mapb", "BGD");
//        values.put("tenpb", "Ban Giam Doc");
//        values.put("sdt", "0983954321");
//        db.insert("PhongBan", null, values);
//        db.close();
//
//        values.put("mapb", "KDO");
//        values.put("tenpb", "Phong Kinh Doanh");
//        values.put("sdt", "0997954321");
//        db.insert("PhongBan", null, values);
//        db.close();

        values.put("mapb", "KTH");
        values.put("tenpb", "Phong Ki Thuat");
        values.put("sdt", "0987944321");
        db.insert("PhongBan", null, values);
        db.close();

//        values.put("mapb", "KTO");
//        values.put("tenpb", "Phong Ke Toan");
//        values.put("sdt", "0987954321");
//        db.insert("PhongBan", null, values);
//        db.close();
    }
    public class Employee {
        private int maNhanVien;
        private String tenNhanVien;
        private String maPhongBan;
        private String soDienThoai;

        public Employee(int maNhanVien, String tenNhanVien, String maPhongBan) {
            this.maNhanVien = maNhanVien;
            this.tenNhanVien = tenNhanVien;
            this.maPhongBan = maPhongBan;
            // Khởi tạo các trường dữ liệu khác nếu cần thiết
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

        public String getMaPhongBan() {
            return maPhongBan;
        }

        public void setMaPhongBan(String maPhongBan) {
            this.maPhongBan = maPhongBan;
        }

        public String getSoDienThoai() {
            return soDienThoai;
        }

        public void setSoDienThoai(String soDienThoai) {
            this.soDienThoai = soDienThoai;
        }
    }

    public List<Employee> getNhanVienByPhongBan(String phongBan) {
        List<Employee> nhanVienList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {"manv", "tennv", "maphongban"};
        String selection = "maphongban = ?";
        String[] selectionArgs = {phongBan};
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor cursor = db.query("NhanVien", columns, selection, selectionArgs, groupBy, having, orderBy);

        while (cursor.moveToNext()) {
            int maNhanVien = cursor.getInt(cursor.getColumnIndex("manv"));
            String tenNhanVien = cursor.getString(cursor.getColumnIndex("tennv"));
            String maPhongBan = cursor.getString(cursor.getColumnIndex("maphongban"));

            Employee nhanVienItem = new Employee(maNhanVien, tenNhanVien, maPhongBan);
            nhanVienList.add(nhanVienItem);
        }

        cursor.close();
        return nhanVienList;
    }

}
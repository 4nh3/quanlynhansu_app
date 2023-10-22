package edu.poly.qlns;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Tên cơ sở dữ liệu và phiên bản
    public static final String DATABASE_NAME = "QuanLyNhanSu.db";
    public static final int DATABASE_VERSION = 1;

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
                + "manv INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "tennv TEXT,"
                + "ngaysinh TEXT,"
                + "phaitinh TEXT,"
                + "cogiadinh TEXT,"
                + "diachi TEXT,"
                + "sodienthoai TEXT,"
                + "trinhdo TEXT,"
                + "luongcb INTERGER,"
                + "ngaylamviec DATE,"
                + "chucvu TEXT,"
                + "maphongban TEXT"
                + ");";
        db.execSQL(createNhanVienTable);

        // Tạo bảng Chấm công
        String createChamCongTable = "CREATE TABLE ChamCong ("
                + "manv INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "thang INTEGER PRIMARY KEY,"
                + "ngaycong Date,"
                + "ngayphep INTEGER,"
                + "ngoaigio INTEGER"
                + ");";
        db.execSQL(createChamCongTable);

        // Tạo bảng Phòng ban
        String createPhongBanTable = "CREATE TABLE PhongBan ("
                + "mapb INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "tenpb TEXT,"
                + "sdt INTEGER"
                + ");";
        db.execSQL(createPhongBanTable);
    }
}

package edu.poly.qlns;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ChamCongAdapter extends CursorAdapter {
    public ChamCongAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.item_hienthiluong2, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Lấy dữ liệu từ Cursor
        int manv = cursor.getInt(cursor.getColumnIndex("_id"));
        String tennv = cursor.getString(cursor.getColumnIndex("tennv"));
        int ngaycong = cursor.getInt(cursor.getColumnIndex("ngaycong"));
        int ngayphep = cursor.getInt(cursor.getColumnIndex("ngayphep"));
        int ngoaigio = cursor.getInt(cursor.getColumnIndex("ngoaigio"));
        double tongluong = cursor.getDouble(cursor.getColumnIndex("tongluong"));

        // Gán dữ liệu vào các View trong layout
        TextView maNVTextView = view.findViewById(R.id.textViewMaNV_HienThi);
        TextView ngayCongTextView = view.findViewById(R.id.textViewNgayCong_HienThi);
        TextView ngayPhepTextView = view.findViewById(R.id.textViewNgayPhep_HienThi);
        TextView ngoaiGioTextView = view.findViewById(R.id.textViewNgoaiGio_HienThi);
        TextView tongLuongTextView = view.findViewById(R.id.textViewTongLuong_HienThi);

        maNVTextView.setText(String.valueOf(manv));
        ngayCongTextView.setText(String.valueOf(ngaycong));
        ngayPhepTextView.setText(String.valueOf(ngayphep));
        ngoaiGioTextView.setText(String.valueOf(ngoaigio));
        tongLuongTextView.setText(String.valueOf(tongluong));
    }
}


package edu.poly.qlns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.poly.qlns.data.Luong;

public class LuongAdapter extends ArrayAdapter<Luong> {
    private Context context;
    private int resource;
    private List<Luong> luongList;

    public LuongAdapter(Context context, int resource, List<Luong> luongList) {
        super(context, resource, luongList);
        this.context = context;
        this.resource = resource;
        this.luongList = luongList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_luong, null);
        }

        Luong luong = luongList.get(position);

        TextView textViewSTT = view.findViewById(R.id.textStt);
        TextView textViewHoTen = view.findViewById(R.id.tennv);
        TextView textViewChucVu = view.findViewById(R.id.chucvu);
        TextView textViewLuongCoBan = view.findViewById(R.id.luongcb);
        TextView textViewThucLanh = view.findViewById(R.id.thuclanh);

        // Set dữ liệu cho các TextView từ đối tượng Luong tương ứng
        textViewSTT.setText(Integer.toString(position + 1)); // STT
        textViewHoTen.setText(luong.getTenNhanVien()); // Họ tên
        textViewChucVu.setText(luong.getChucVu()); // Chức vụ
        textViewLuongCoBan.setText(String.valueOf(luong.getLuongCoBan())); // Lương cơ bản
        //textViewThucLanh.setText(String.valueOf(luong.getLuongThucLanh())); // Thực lãnh
        double thucLanh = luong.getLuongThucLanh();
        textViewThucLanh.setText(String.valueOf(thucLanh)); // Thực lãnh
        return view;
    }
}

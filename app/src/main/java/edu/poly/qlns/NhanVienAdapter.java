package edu.poly.qlns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import edu.poly.qlns.data.NhanVien;

public class NhanVienAdapter extends BaseAdapter {
    private Context context;
    private List<NhanVien> nhanVienList;

    public NhanVienAdapter(Context context, List<NhanVien> nhanVienList) {
        this.context = context;
        this.nhanVienList = nhanVienList;
    }

    @Override
    public int getCount() {
        return nhanVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return nhanVienList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_nhanvien, parent, false);
            holder = new ViewHolder();
            holder.tvTenNV = convertView.findViewById(R.id.tvTenNV);
            holder.tvMaNV = convertView.findViewById(R.id.tvMaNV); // Đã thêm TextView mới cho mã nhân viên
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NhanVien nhanVien = nhanVienList.get(position);
        holder.tvTenNV.setText(nhanVien.getTenNhanVien());
        holder.tvMaNV.setText(String.valueOf(nhanVien.getMaNhanVien())); // Đặt mã nhân viên

        return convertView;
    }

    // Phương thức này dùng để cập nhật danh sách nhân viên và cập nhật giao diện
    public void updateData(List<NhanVien> updatedNhanVienList) {
//        nhanVienList.clear(); // Xóa danh sách hiện tại
        nhanVienList.addAll(updatedNhanVienList); // Thêm danh sách mới
        notifyDataSetChanged(); // Cập nhật giao diện
    }

    static class ViewHolder {
        TextView tvTenNV;
        TextView tvMaNV;
    }
}

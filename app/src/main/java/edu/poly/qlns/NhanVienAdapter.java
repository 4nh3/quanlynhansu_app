package edu.poly.qlns;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import edu.poly.qlns.data.NhanVien;

public class NhanVienAdapter extends BaseAdapter {
    private Context context;
    private List<NhanVien> nhanVienList;
    private DatabaseHelper dbHelper; // Thêm biến dbHelper

    public NhanVienAdapter(Context context, List<NhanVien> nhanVienList, DatabaseHelper dbHelper) {
        this.context = context;
        this.nhanVienList = nhanVienList;
        this.dbHelper = dbHelper; // Gán giá trị dbHelper
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
            holder.tvMaNV = convertView.findViewById(R.id.tvMaNV);
            holder.btnXoa = convertView.findViewById(R.id.btnXoa);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NhanVien nhanVien = nhanVienList.get(position);
        holder.tvTenNV.setText(nhanVien.getTenNhanVien());
        holder.tvMaNV.setText(String.valueOf(nhanVien.getMaNhanVien()));

        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Hiển thị hộp thoại cảnh báo xác nhận xóa
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa nhân viên này?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Thực hiện xóa nhân viên từ cơ sở dữ liệu
                        dbHelper.deleteNhanVien(nhanVien);

                        // Sau khi xóa, cập nhật danh sách và giao diện
                        nhanVienList.remove(nhanVien);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        return convertView;
    }

    // Phương thức này dùng để cập nhật danh sách nhân viên và cập nhật giao diện
    public void updateData(List<NhanVien> updatedNhanVienList) {
        nhanVienList.clear(); // Xóa danh sách hiện tại
        nhanVienList.addAll(updatedNhanVienList); // Thêm danh sách mới
        notifyDataSetChanged(); // Cập nhật giao diện
    }

    static class ViewHolder {
        TextView tvTenNV;
        TextView tvMaNV;
        ImageView btnXoa;
    }
}

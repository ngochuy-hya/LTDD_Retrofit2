package com.example.baitapretrofit2.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import com.example.baitapretrofit2.R;
import com.example.baitapretrofit2.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private Context context;
    private List<Category> array;

    public CategoryAdapter(Context context, List<Category> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = array.get(position);
        holder.tenSp.setText(category.getName());

        // Load ảnh với Glide
        Glide.with(context)
                .load(category.getImages())
                .into(holder.images);
    }

    @Override
    public int getItemCount() {
        return array == null ? 0 : array.size();
    }

    // Cập nhật dữ liệu mới
    public void updateData(List<Category> newList) {
        array.clear(); // Xóa danh sách cũ
        array.addAll(newList); // Thêm dữ liệu mới
        notifyDataSetChanged(); // Cập nhật RecyclerView
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView images;
        TextView tenSp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.image_cate);
            tenSp = itemView.findViewById(R.id.tvNameCategory);

            // Bắt sự kiện click vào item
            itemView.setOnClickListener(v ->
                    Toast.makeText(context, "Bạn đã chọn category: " + tenSp.getText().toString(), Toast.LENGTH_SHORT).show()
            );
        }
    }
}

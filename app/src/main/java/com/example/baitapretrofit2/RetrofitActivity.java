package com.example.baitapretrofit2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.baitapretrofit2.model.Category;
import com.example.baitapretrofit2.ui.CategoryAdapter;
import com.example.baitapretrofit2.network.RetrofitClient;
import com.example.baitapretrofit2.network.APIService;

public class RetrofitActivity extends AppCompatActivity {
    private RecyclerView rcCate;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        rcCate = findViewById(R.id.rc_category);
        getCategory();
    }

    private void getCategory() {
        // Gọi API từ APIService
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getCategoryAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoryList = response.body();

                    if (categoryAdapter == null) {
                        // Khởi tạo adapter nếu chưa có
                        categoryAdapter = new CategoryAdapter(RetrofitActivity.this, categoryList);
                        rcCate.setHasFixedSize(true);

                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(getApplicationContext(),
                                        LinearLayoutManager.HORIZONTAL, false);

                        rcCate.setLayoutManager(layoutManager);
                        rcCate.setAdapter(categoryAdapter);
                    } else {
                        // Cập nhật dữ liệu nếu adapter đã tồn tại
                        categoryAdapter.updateData(categoryList);
                    }
                } else {
                    Toast.makeText(RetrofitActivity.this, "Lỗi lấy dữ liệu!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("logg", t.getMessage());
                Toast.makeText(RetrofitActivity.this, "Lỗi kết nối!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

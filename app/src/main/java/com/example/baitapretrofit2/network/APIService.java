package com.example.baitapretrofit2.network;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import com.example.baitapretrofit2.model.Category;

public interface APIService {
    @GET("categories.php")  // Đường dẫn API lấy danh sách categories
    Call<List<Category>> getCategoryAll();
}

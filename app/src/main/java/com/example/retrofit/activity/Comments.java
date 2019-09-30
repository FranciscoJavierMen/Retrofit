package com.example.retrofit.activity;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit.R;
import com.example.retrofit.adapter.CommentsAdapter;
import com.example.retrofit.data.model.Owner;
import com.example.retrofit.data.model.remote.APIService;
import com.example.retrofit.data.model.remote.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class Comments extends AppCompatActivity {

    private RecyclerView recyclerComments;
    private APIService service;
    private CommentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        service = ApiUtils.getApiServiceComments();
        recyclerComments = findViewById(R.id.recyclerComments);
        adapter = new CommentsAdapter(new ArrayList<Owner>(), this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerComments.setLayoutManager(layoutManager);
        recyclerComments.setAdapter(adapter);
        recyclerComments.hasFixedSize();

        loadComments();
    }

    private void loadComments() {
        service.getComments().enqueue(new Callback<com.example.retrofit.data.model.Comments>() {
            @Override
            public void onResponse(Call<com.example.retrofit.data.model.Comments> call, Response<com.example.retrofit.data.model.Comments> response) {
                //adapter.updateComments(response.body().);
            }

            @Override
            public void onFailure(Call<com.example.retrofit.data.model.Comments> call, Throwable t) {
                Toast.makeText(Comments.this, "Probelas al cargar los resultados", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

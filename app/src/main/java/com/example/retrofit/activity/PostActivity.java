package com.example.retrofit.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.retrofit.R;
import com.example.retrofit.data.model.Post;
import com.example.retrofit.data.model.remote.APIService;
import com.example.retrofit.data.model.remote.ApiUtils;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private TextInputLayout edtTitle, edtBody;
    private TextView txtResponse;
    private APIService apiService;
    private ExtendedFloatingActionButton fabSend, fabUpdate, fabDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        edtTitle = findViewById(R.id.edtTitle);
        edtBody = findViewById(R.id.edtBody);
        fabSend = findViewById(R.id.fabSend);
        fabUpdate = findViewById(R.id.fabUpdate);
        fabDelete = findViewById(R.id.fabDelete);
        txtResponse = findViewById(R.id.txtResponse);

        apiService = ApiUtils.getApiService();

        fabSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtTitle.getEditText().getText().toString();
                String body = edtBody.getEditText().getText().toString();
                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)){
                    sendPost(title, body);
                } else {
                    Toast.makeText(PostActivity.this, "Campos requeridos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fabUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtTitle.getEditText().getText().toString();
                String body = edtBody.getEditText().getText().toString();
                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)){
                    updatePost(title, body);
                } else {
                    Toast.makeText(PostActivity.this, "Campos requeridos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePost(1);
            }
        });
    }

    private void deletePost(long id) {
        apiService.deletePost(id).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()){
                    Toast.makeText(PostActivity.this, "Post eliminado correctamente", Toast.LENGTH_SHORT).show();
                }
                if (response.code() == 200){
                    Toast.makeText(PostActivity.this, "Done", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(PostActivity.this, "Algo salió mal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updatePost(String title, String body) {
        apiService.updatePost(2, title, body, 1).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()){
                    showResponse(response.body().toString());
                    Toast.makeText(PostActivity.this, "Post actualizado correctamente", Toast.LENGTH_SHORT).show();
                }
                if (response.code() == 200){
                    Toast.makeText(PostActivity.this, "Done", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(PostActivity.this, "Algo salió mal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendPost(String title, String body) {
        apiService.sendPost(title, body, 1).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()){
                    showResponse(response.body().toString());
                    Toast.makeText(PostActivity.this, "Post enviado correctamente", Toast.LENGTH_SHORT).show();
                }
                if (response.code() == 200){
                    Toast.makeText(PostActivity.this, "Done", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(PostActivity.this, "Algo salió mal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showResponse(String response) {
        if (txtResponse.getVisibility() == View.GONE){
            txtResponse.setVisibility(View.VISIBLE);
        }
        txtResponse.setText(response);
    }
}

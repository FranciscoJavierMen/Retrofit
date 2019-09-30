package com.example.retrofit.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit.R;
import com.example.retrofit.adapter.EmployeeAdapter;
import com.example.retrofit.model.Employee;
import com.example.retrofit.model.EmployeeList;
import com.example.retrofit.network.GetEmployeeDataService;
import com.example.retrofit.network.RetrofitInstance;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EmployeeAdapter adapter;
    private RecyclerView recyclerEmployee;
    private ExtendedFloatingActionButton fabIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabIntent = findViewById(R.id.fabGoToPost);
        fabIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Comments.class));
            }
        });

        //Retrofit instance
        GetEmployeeDataService service = RetrofitInstance.getRetrofitInstance().create(GetEmployeeDataService.class);
        //CAll the method with parameter
        Call<EmployeeList> call = service.getEmployeeData(100);

        //Log the URL called
        Log.wtf("URL Called: ",  call.request().url()+"");

        call.enqueue(new Callback<EmployeeList>() {
            @Override
            public void onResponse(Call<EmployeeList> call, Response<EmployeeList> response) {
                generateEmployeeList(response.body().getEmployeeList());
            }

            @Override
            public void onFailure(Call<EmployeeList> call, Throwable t) {
                CoordinatorLayout coordinatorLayout = findViewById(R.id.layoutMain);
                Snackbar.make(coordinatorLayout, "Algo salió mal, por favor intente más tarde", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void generateEmployeeList(ArrayList<Employee> employeeList) {
        recyclerEmployee = findViewById(R.id.recyclerEmployee);
        adapter = new EmployeeAdapter(employeeList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerEmployee.setLayoutManager(layoutManager);
        recyclerEmployee.setAdapter(adapter);
    }
}

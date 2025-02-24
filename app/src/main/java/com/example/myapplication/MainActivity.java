package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageModel> modelClassList;
    private RecyclerView recyclerView;
    Adapter adapter;

    CardView mnature,msports,manimal,mabstract,mspace,mbook;
    EditText editText;
    ImageButton searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        recyclerView=findViewById(R.id.recycler_view);
        mnature=findViewById(R.id.nature);
        msports=findViewById(R.id.sports);
        manimal=findViewById(R.id.animal);
        mabstract=findViewById(R.id.abstracts);
        mspace=findViewById(R.id.space);
        mbook=findViewById(R.id.book);

        editText=findViewById(R.id.search_bar);
        searchButton=findViewById(R.id.search_button);

        modelClassList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(getApplicationContext(),modelClassList);
        recyclerView.setAdapter(adapter);

        findPhotos();
        mnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="nature";
                getSearchImage(query);
            }
        });
        msports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="sports";
                getSearchImage(query);
            }

        });

        mabstract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="Abstract";
                getSearchImage(query);
            }
        });

        manimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="animal";
                getSearchImage(query);
            }

        });
        mspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="Galaxy";
                getSearchImage(query);
            }

        });
        mbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="book";
                getSearchImage(query);
            }

        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query=editText.getText().toString().trim().toLowerCase();
                if(query.isEmpty()){
                    editText.setError("Enter Something");
                    Toast.makeText(MainActivity.this, "Enter Something", Toast.LENGTH_SHORT).show();
                }else{
                    getSearchImage(query);
                }

            }

        });
    }

    private void getSearchImage(String query) {
        ApiUtilities.getApiInterface().getSearchImage(query,1,40).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClassList.clear();
                if(response.isSuccessful()){
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }

    private void findPhotos() {
        ApiUtilities.getApiInterface().getImage(1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if(response.isSuccessful()){
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }
}
package com.example.avalicacaodevmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListProductsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private AppDatabase db;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);

        db = AppDatabase.getDatabase(this);

        recyclerView = findViewById(R.id.recycler_view_products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Fecha a tela atual e volta para a anterior
            }
        });

        loadProducts();
    }

    private void loadProducts() {
        // Busca a lista no banco Room
        List<Product> products = db.productDao().getAllProducts();
        
        // Configura o adapter
        adapter = new ProductAdapter(products);
        recyclerView.setAdapter(adapter);
    }
}

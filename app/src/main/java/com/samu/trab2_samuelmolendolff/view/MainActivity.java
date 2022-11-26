package com.samu.trab2_samuelmolendolff.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.samu.trab2_samuelmolendolff.R;
import com.samu.trab2_samuelmolendolff.adapter.Adapter;
import com.samu.trab2_samuelmolendolff.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Product> listProducts = new ArrayList<>();
    private ArrayList<String> allBrands = new ArrayList<String>();
    private ActivityResultLauncher<Intent> activityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // About recycler view
        recyclerView = findViewById(R.id.recyclerView);

        // Set adapter
        Adapter adapter = new Adapter(this.listProducts);

        // Set Recycleview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        // Config activityLauncher
        this.activityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == 1) {
                            Intent intent = result.getData();

                            allBrands= intent.getStringArrayListExtra("AllBrands");

                            if(intent.getStringArrayListExtra("Products").isEmpty())
                                return;

                            List<String> newProduct = intent.getStringArrayListExtra("Products");
                            List<String> newBrands = intent.getStringArrayListExtra("Brands");

                            createProduct(newProduct, newBrands);
                        }
                    }
                }
        );

    }

    public void getNewProduct(View view) {
        Intent intent = new Intent(getApplicationContext(), ProductPage.class);
        intent.putStringArrayListExtra("AllBrands", this.allBrands);
        activityLauncher.launch(intent);
    }

    public void createProduct(List<String> product, List<String> brand) {
        for (int i = 0; i < product.size(); i++) {
            listProducts.add(new Product(product.get(i), brand.get(i)));
        }

        recyclerView.setAdapter(new Adapter(this.listProducts));
    }

}
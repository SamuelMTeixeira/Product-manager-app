package com.samu.trab2_samuelmolendolff.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.samu.trab2_samuelmolendolff.R;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends AppCompatActivity {

    private String brand;
    private EditText edtProduct;
    private Spinner spinner;
    private ArrayList<String> listProducts = new ArrayList<>();
    private ArrayList<String> listBrands = new ArrayList<>();
    private ArrayList<String> spinnerOpc = new ArrayList<>();
    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        // About the product input
        this.edtProduct = findViewById(R.id.edtBrand);

        // About the spinner
        this.spinner = findViewById(R.id.spinner);
        this.spinnerOpc = getIntent().getExtras().getStringArrayList("AllBrands");
        this.brand = "";

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, this.spinnerOpc);
        this.spinner.setAdapter(adapter);
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                brand = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // Result launcher configs
        this.activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == 2) {
                            Intent intent = result.getData();

                            assert intent != null;
                            if (intent.getExtras().getBoolean("OnBack", false) == true) {
                                setBackOpc();
                                return;
                            }

                            createNewBrand(intent.getExtras().getString("newBrand"));
                        }
                    }
                }
        );


    }

    public void onBackPressed() {
        // super.onBackPressed();
    }

    public void setBackOpc() {
        Intent intent = new Intent();
        intent.putStringArrayListExtra("Products", this.listProducts);
        intent.putStringArrayListExtra("Brands", this.listBrands);
        intent.putStringArrayListExtra("AllBrands", this.spinnerOpc);
        setResult(1, intent);
        super.onBackPressed();
    }

    public void showProduct(View v) {
        Intent intent = new Intent();
        intent.putStringArrayListExtra("Products", this.listProducts);
        intent.putStringArrayListExtra("Brands", this.listBrands);
        intent.putStringArrayListExtra("AllBrands", this.spinnerOpc);
        setResult(1, intent);
        super.onBackPressed();
    }

    public void addProduct(View v) {
        String productSelected = this.edtProduct.getText().toString();
        String brandSelected = this.brand;

        // About the alert dialog
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Erro ao tentar cadastrar o produto");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        // Validations
        if (productSelected.isEmpty()) {
            alertDialog.setMessage("O produto não pode ser um valor vazio");
            alertDialog.create().show();
            return;
        } else if (brandSelected.isEmpty()) {
            alertDialog.setMessage("A marca não pode ser um valor vazio");
            alertDialog.create().show();
            return;
        }

        // Add product
        this.listProducts.add(productSelected);
        this.listBrands.add(brandSelected);
        Toast.makeText(this, "Produto adicionado com sucesso", Toast.LENGTH_SHORT).show();
        this.edtProduct.setText("");
        this.edtProduct.requestFocus();
    }

    public void addBrand(View view) {
        Intent intent = new Intent(this, BrandPage.class);
        this.activityResultLauncher.launch(intent);
    }

    public void createNewBrand(String newBrand) {
        this.spinnerOpc.add(newBrand);
        this.spinner.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, this.spinnerOpc));
    }
}
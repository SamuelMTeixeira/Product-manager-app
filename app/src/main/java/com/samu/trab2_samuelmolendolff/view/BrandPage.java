package com.samu.trab2_samuelmolendolff.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.samu.trab2_samuelmolendolff.R;

import java.util.ArrayList;

public class BrandPage extends AppCompatActivity {

    private EditText edtBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_page);
        this.edtBrand = findViewById(R.id.edtBrand);
    }

    public void setBrand(View v) {
        String brandTyped = edtBrand.getText().toString();

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Erro ao tentar cadastrar o produto");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        if (brandTyped.isEmpty()) {
            alertDialog.setMessage("A marca não pode ser um valor vazio");
            alertDialog.create().show();
            return;
        }

        Toast.makeText(this, "Marca adicionada com sucesso", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("newBrand", brandTyped);
        setResult(2, intent);
        super.onBackPressed();
    }

    public void showProducts(View v) {
        Intent intent = new Intent();
        intent.putExtra("OnBack", true);
        setResult(2, intent);
        super.onBackPressed();
    }

    public void onBackPressed() {
        // super.onBackPressed();
    }

    public void backToLastPage(View v) {
        super.onBackPressed();
    }
}
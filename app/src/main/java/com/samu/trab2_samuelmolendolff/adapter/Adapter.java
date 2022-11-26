package com.samu.trab2_samuelmolendolff.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samu.trab2_samuelmolendolff.R;
import com.samu.trab2_samuelmolendolff.model.Product;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Product> listProduct;

    public Adapter(List list) {
        this.listProduct = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter, parent, false);

        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Product itemTerm = listProduct.get(position);

        holder.product.setText(itemTerm.getName());
        holder.brand.setText(itemTerm.getBrand());
        holder.cod.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return this.listProduct.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView product, brand, cod;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cod = itemView.findViewById(R.id.txtCod);
            product = itemView.findViewById(R.id.txtProduct);
            brand = itemView.findViewById(R.id.txtBrand);
        }
    }


}

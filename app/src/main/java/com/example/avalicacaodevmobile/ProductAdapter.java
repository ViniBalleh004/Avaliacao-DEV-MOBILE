package com.example.avalicacaodevmobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter responsável por vincular os dados da lista de produtos ao RecyclerView.
 * Critério: Listagem de Produtos e Boas Práticas.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        Context context = holder.itemView.getContext();

        holder.textName.setText(product.getName());

        // Usando strings.xml com parâmetros para evitar concatenação de texto (Boa Prática)
        holder.textCode.setText(context.getString(R.string.label_code, product.getCode()));
        holder.textPrice.setText(context.getString(R.string.label_price, product.getPrice()));
        holder.textQuantity.setText(context.getString(R.string.label_stock, product.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textCode, textPrice, textQuantity;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_item_name);
            textCode = itemView.findViewById(R.id.text_item_code);
            textPrice = itemView.findViewById(R.id.text_item_price);
            textQuantity = itemView.findViewById(R.id.text_item_quantity);
        }
    }
}

package com.example.promanage;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> productList;
    private DatabaseHelper dbHelper;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.dbHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_card, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvProductName.setText(product.getName());
        holder.tvQuantity.setText(String.valueOf(product.getQuantity()));

        if (product.getImageByteArray() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImageByteArray(), 0, product.getImageByteArray().length);
            holder.ivProductImage.setImageBitmap(bitmap);
        }

        holder.btnDelete.setOnClickListener(v -> deleteProduct(product, position));
        holder.btnUpdate.setOnClickListener(v -> showUpdateDialog(product, position));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    private void deleteProduct(Product product, int position) {
        dbHelper.deleteProduct(product.getId());
        productList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, productList.size());
    }

    private void showUpdateDialog(Product product, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update Quantity");

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setText(String.valueOf(product.getQuantity()));
        builder.setView(input);

        builder.setPositiveButton("Update", (dialog, which) -> {
            String quantityStr = input.getText().toString();
            if (!quantityStr.isEmpty()) {
                int newQuantity = Integer.parseInt(quantityStr);
                updateProductQuantity(product, newQuantity, position);
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void updateProductQuantity(Product product, int newQuantity, int position) {
        dbHelper.updateProductQuantity(product.getId(), newQuantity);
        product.setQuantity(newQuantity);
        notifyItemChanged(position);
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProductImage;
        TextView tvProductName;
        TextView tvQuantity;
        Button btnUpdate;
        Button btnDelete;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProductImage = itemView.findViewById(R.id.iv_show_item_image);
            tvProductName = itemView.findViewById(R.id.tv_show_item_name);
            tvQuantity = itemView.findViewById(R.id.tv_show_quantity);
            btnUpdate = itemView.findViewById(R.id.btn_update);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
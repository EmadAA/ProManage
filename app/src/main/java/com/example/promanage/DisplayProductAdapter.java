package com.example.promanage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DisplayProductAdapter extends RecyclerView.Adapter<DisplayProductAdapter.ViewHolder> {

    private Context context;
    private List<Product> productList;

    public DisplayProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvProductName.setText(product.getName());
        holder.tvQuantity.setText("Quantity: " + product.getQuantity());

        if (product.getImageByteArray() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImageByteArray(), 0, product.getImageByteArray().length);
            holder.ivProductImage.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProductImage;
        TextView tvProductName;
        TextView tvQuantity;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProductImage = itemView.findViewById(R.id.iv_show_item_image);
            tvProductName = itemView.findViewById(R.id.tv_show_item_name);
            tvQuantity = itemView.findViewById(R.id.tv_show_quantity);
        }
    }
}

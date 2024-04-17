package com.example.empresaclientes.ProductosAdmin;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.empresaclientes.DBHelper;
import com.example.empresaclientes.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable {

    private List<Item> itemList;
    private List<Item> itemListFull;
    private boolean isSingleColumn;
    private Context context;
    DBHelper DB;

    public MyAdapter(List<Item> itemList, boolean isSingleColumn, Context context) {
        this.itemList = itemList;
        this.isSingleColumn = isSingleColumn;
        this.itemListFull = new ArrayList<>(itemList);
        this.context = context;
        this.DB = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView;

        if (isSingleColumn) {
            itemView = inflater.inflate(R.layout.item_layout_single, parent, false);
        } else {
            itemView = inflater.inflate(R.layout.item_layout_double, parent, false);
        }

        return new ViewHolder(itemView, context);
    }
    public void setProductList(List<Item> productList) {
        this.itemList = new ArrayList<>(productList);
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);

        Glide.with(context)
                .load(item.getFoto())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("GlideError", "Error loading image for item: " + item.getNombre(), e);
                        Log.e("GlideError", "Error message: " + e.getMessage());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.imageView);

        holder.nombreTextView.setText(item.getNombre());
        holder.precioTextView.setText(item.getPrecio());
        holder.cantidadTextView.setText(item.getCantidad());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void toggleColumnCount(boolean isSingleColumn) {
        this.isSingleColumn = isSingleColumn;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return itemFilter;
    }

    private Filter itemFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Item> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(itemListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Item item : itemListFull) {
                    if (item.getNombre().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            itemList.clear();
            itemList.addAll((List<Item>) results.values);
            notifyDataSetChanged();
        }
    };


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView nombreTextView;
        public TextView precioTextView;
        public TextView cantidadTextView;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            nombreTextView = itemView.findViewById(R.id.NombreProducto);
            precioTextView = itemView.findViewById(R.id.PrecioProducto);
            cantidadTextView = itemView.findViewById(R.id.CantidadProducto);
        }
    }
}

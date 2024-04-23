package com.example.empresaclientes.VentasAdmin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
import com.example.empresaclientes.ProductosAdmin.Item;
import com.example.empresaclientes.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterVentas extends RecyclerView.Adapter<MyAdapterVentas.ViewHolder> implements Filterable {

    private List<Item> itemList;
    private List<Item> itemListFull;
    private List<Item> selectedItems = new ArrayList<>();
    private boolean isSingleColumn;
    private Context context;
    DBHelper DB;

    public MyAdapterVentas(List<Item> itemList, boolean isSingleColumn, Context context) {
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
            itemView = inflater.inflate(R.layout.item_layout_single_ventas, parent, false);
        } else {
            itemView = inflater.inflate(R.layout.item_layout_double_ventas, parent, false);
        }

        return new ViewHolder(itemView, context);
    }
    public void toggleSelection(int position) {
        Item item = itemList.get(position);
        if (selectedItems.contains(item)) {
            selectedItems.remove(item);
        } else {
            selectedItems.add(item);
        }
        notifyDataSetChanged();
    }
    public List<Item> getSelectedItems() {
        return selectedItems;
    }
    public void setProductList(List<Item> productList) {
        this.itemList = new ArrayList<>(productList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,    @SuppressLint("RecyclerView") int position) {
        Item item = itemList.get(position);
        Item currentItem = itemList.get(position);

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
        holder.checkBox.setChecked(selectedItems.contains(currentItem));

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSelection(position);
            }
        });
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
    public void removeItem(int position) {
        Item item = itemList.remove(position);
        itemListFull.remove(item);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView nombreTextView;
        public TextView precioTextView;
        public TextView cantidadTextView;
        public CheckBox checkBox;


        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            checkBox=itemView.findViewById(R.id.checkBox);
            imageView = itemView.findViewById(R.id.imageView);
            nombreTextView = itemView.findViewById(R.id.NombreProducto);
            precioTextView = itemView.findViewById(R.id.PrecioProducto);
            cantidadTextView = itemView.findViewById(R.id.CantidadProducto);
        }
    }

}

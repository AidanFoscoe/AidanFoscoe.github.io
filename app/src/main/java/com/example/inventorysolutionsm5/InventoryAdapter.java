package com.example.inventorysolutionsm5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {

    private Context context;
    private List<InventoryItem> itemList;

    public InventoryAdapter(Context context, List<InventoryItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_inventory, parent, false);
        return new InventoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        InventoryItem item = itemList.get(position);

        holder.itemName.setText(item.getName());
        holder.itemQuantity.setText("Qty: " + item.getQuantity());
        holder.itemCategory.setText("Category: " + item.getCategory());

        // DELETE BUTTON
        holder.deleteButton.setOnClickListener(v -> {
            DatabaseHelper db = new DatabaseHelper(context);
            db.deleteItem(item.getId());
            itemList.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // VIEW HOLDER
    static class InventoryViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemQuantity, itemCategory;
        ImageButton editButton, deleteButton;

        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.textItemName);
            itemQuantity = itemView.findViewById(R.id.textItemQuantity);
            itemCategory = itemView.findViewById(R.id.textItemCategory);

            editButton = itemView.findViewById(R.id.buttonEdit);
            deleteButton = itemView.findViewById(R.id.buttonDelete);
        }
    }
}

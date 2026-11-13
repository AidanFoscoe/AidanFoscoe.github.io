package com.example.inventorysolutionsm5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {

    private RecyclerView inventoryRecyclerView;
    private InventoryAdapter adapter;
    private ArrayList<InventoryItem> inventoryList;
    private Button addItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        // Bind views
        inventoryRecyclerView = findViewById(R.id.recyclerView);
        addItemButton = findViewById(R.id.buttonAddItem);

        // Initialize list and adapter
        inventoryList = new ArrayList<>();
        adapter = new InventoryAdapter(this, inventoryList);

        // RecyclerView setup (2-column grid)
        inventoryRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        inventoryRecyclerView.setAdapter(adapter);

        // Add Item button
        addItemButton.setOnClickListener(v -> {
            Intent intent = new Intent(InventoryActivity.this, AddItem.class);
            startActivity(intent);
        });
    }

    /**
     * This runs every time the activity becomes visible again.
     * REQUIRED so the UI updates after returning from AddItem.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadInventory();
    }

    /**
     * Loads inventory items from SQLite and refreshes the RecyclerView
     */
    private void loadInventory() {
        inventoryList.clear();

        DatabaseHelper db = new DatabaseHelper(this);
        inventoryList.addAll(db.getAllItems());

        adapter.notifyDataSetChanged();
    }
}

package com.example.inventorysolutionsm5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity used for both adding a new inventory item
 * and editing an existing inventory item.
 *
 * If item data is passed through the Intent, the activity
 * switches to UPDATE mode. Otherwise, it stays in CREATE mode.
 */
public class AddItem extends AppCompatActivity {

    private EditText itemNameInput;
    private EditText itemQuantityInput;
    private EditText itemCategoryInput;
    private Button saveItemButton;

    private DatabaseHelper db;
    private int itemId = -1; // -1 means create mode

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        // Initialize UI components
        itemNameInput = findViewById(R.id.itemNameInput);
        itemQuantityInput = findViewById(R.id.itemQuantityInput);
        itemCategoryInput = findViewById(R.id.itemCategoryInput);
        saveItemButton = findViewById(R.id.saveItemButton);

        // Initialize database helper
        db = new DatabaseHelper(this);

        // Check if this activity was launched for editing an item
        if (getIntent().hasExtra("ITEM_ID")) {
            itemId = getIntent().getIntExtra("ITEM_ID", -1);

            itemNameInput.setText(getIntent().getStringExtra("ITEM_NAME"));
            itemQuantityInput.setText(
                    String.valueOf(getIntent().getIntExtra("ITEM_QTY", 0))
            );
            itemCategoryInput.setText(
                    getIntent().getStringExtra("ITEM_CATEGORY")
            );

            // Update button text for clarity
            saveItemButton.setText("Update Item");
        }

        // Handle save/update button click
        saveItemButton.setOnClickListener(v -> {
            String name = itemNameInput.getText().toString().trim();
            String qtyText = itemQuantityInput.getText().toString().trim();
            String category = itemCategoryInput.getText().toString().trim();

            // Basic input validation
            if (name.isEmpty() || qtyText.isEmpty()) {
                return;
            }

            int quantity = Integer.parseInt(qtyText);

            if (itemId == -1) {
                // CREATE new item
                db.addItem(name, quantity, category);
            } else {
                // UPDATE existing item
                db.updateItem(itemId, name, quantity, category);
            }

            // Return to InventoryActivity
            finish();
        });
    }
}

package com.example.avalicacaodevmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Tela de Cadastro de Produtos.
 * Implementa validações de entrada e persistência local com Room.
 */
public class MainActivity extends AppCompatActivity {

    private TextInputEditText editName, editCode, editPrice, editQuantity;
    private TextInputLayout layoutName, layoutCode, layoutPrice, layoutQuantity;
    private MaterialButton btnSave, btnList;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instancia o banco de dados via padrão Singleton
        db = AppDatabase.getDatabase(this);

        initViews();

        btnSave.setOnClickListener(v -> saveProduct());

        btnList.setOnClickListener(v -> {
            // Intent para navegação explícita entre Activities
            Intent intent = new Intent(MainActivity.this, ListProductsActivity.class);
            startActivity(intent);
        });
    }

    private void initViews() {
        editName = findViewById(R.id.edit_product_name);
        editCode = findViewById(R.id.edit_product_code);
        editPrice = findViewById(R.id.edit_product_price);
        editQuantity = findViewById(R.id.edit_product_quantity);

        layoutName = findViewById(R.id.layout_product_name);
        layoutCode = findViewById(R.id.layout_product_code);
        layoutPrice = findViewById(R.id.layout_product_price);
        layoutQuantity = findViewById(R.id.layout_product_quantity);

        btnSave = findViewById(R.id.btn_save);
        btnList = findViewById(R.id.btn_list_products);
    }

    private void saveProduct() {
        clearErrors();

        // Captura e limpeza de espaços em branco
        String name = editName.getText().toString().trim();
        String code = editCode.getText().toString().trim();
        String priceStr = editPrice.getText().toString().trim();
        String quantityStr = editQuantity.getText().toString().trim();

        boolean isValid = true;

        // VALIDAÇÃO 1: Campos em branco (Obrigatório por contrato)
        if (name.isEmpty()) {
            layoutName.setError(getString(R.string.error_empty));
            isValid = false;
        }
        if (code.isEmpty()) {
            layoutCode.setError(getString(R.string.error_empty));
            isValid = false;
        }

        // VALIDAÇÃO 2: Preço (Deve ser positivo e numérico)
        double price = 0;
        try {
            if (priceStr.isEmpty()) {
                layoutPrice.setError(getString(R.string.error_empty));
                isValid = false;
            } else {
                price = Double.parseDouble(priceStr);
                if (price <= 0) {
                    layoutPrice.setError(getString(R.string.error_invalid_value));
                    isValid = false;
                }
            }
        } catch (NumberFormatException e) {
            layoutPrice.setError("Formato de preço inválido");
            isValid = false;
        }

        // VALIDAÇÃO 3: Quantidade (Deve ser inteiro positivo)
        int quantity = 0;
        try {
            if (quantityStr.isEmpty()) {
                layoutQuantity.setError(getString(R.string.error_empty));
                isValid = false;
            } else {
                quantity = Integer.parseInt(quantityStr);
                if (quantity <= 0) {
                    layoutQuantity.setError(getString(R.string.error_invalid_value));
                    isValid = false;
                }
            }
        } catch (NumberFormatException e) {
            layoutQuantity.setError("Apenas números inteiros permitidos");
            isValid = false;
        }

        // PERSISTÊNCIA: Se tudo estiver correto, salva no Room
        if (isValid) {
            Product product = new Product(name, code, price, quantity);
            db.productDao().insert(product);

            Toast.makeText(this, R.string.success_save, Toast.LENGTH_SHORT).show();
            clearFields();
        }
    }

    private void clearErrors() {
        layoutName.setError(null);
        layoutCode.setError(null);
        layoutPrice.setError(null);
        layoutQuantity.setError(null);
    }

    private void clearFields() {
        editName.setText("");
        editCode.setText("");
        editPrice.setText("");
        editQuantity.setText("");
        editName.requestFocus();
    }
}

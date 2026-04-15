package com.example.avalicacaodevmobile;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/**
 * Interface DAO para operações no banco de dados.
 * Segue o padrão utilizado no repositório mobile-cadastro-de-usu-rio.
 */
@Dao
public interface ProductDao {
    @Insert
    void insert(Product product);

    @Query("SELECT * FROM products")
    List<Product> getAllProducts();
}

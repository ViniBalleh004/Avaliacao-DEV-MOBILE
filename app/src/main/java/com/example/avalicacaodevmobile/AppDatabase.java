package com.example.avalicacaodevmobile;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Classe que representa o Banco de Dados Room.
 * @Database define as entidades e a versão do banco.
 */
@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    // Método abstrato para acessar as operações do DAO
    public abstract ProductDao productDao();

    // Instância única (Singleton) para evitar múltiplas conexões abertas ao mesmo tempo
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Criação do banco de dados persistente
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "product_database")
                            // .allowMainThreadQueries() permite rodar o banco na thread principal (UI).
                            // IMPORTANTE: Em produção, o ideal é usar Threads separadas para não travar a tela.
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

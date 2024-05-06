package database;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Formula.class}, version = 2)
public abstract class FormulaDatabase extends RoomDatabase
{
    public abstract FormulaDao formulaDao();
}

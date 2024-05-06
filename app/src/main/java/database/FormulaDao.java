package database;

import androidx.room.*;

import java.util.List;

/**
 *  Data Access Object
 *  for user's history of calculation
 */
@Dao
public interface FormulaDao
{
    @Insert
    void insertFormula(Formula formula);

    @Update
    void updateFormula(Formula formula);

    @Delete
    void deleteFormula(Formula formula);

    @Query("SELECT * FROM FORMULAS ORDER BY formula_id DESC LIMIT 1")
    long getLastAddedID();

    @Query("SELECT * FROM FORMULAS")
    List<Formula> getAllFormula();
}

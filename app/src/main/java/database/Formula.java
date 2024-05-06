package database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "FORMULAS")
public class Formula
{
    @ColumnInfo(name = "formula_id")
    @PrimaryKey(autoGenerate = true)
    private long id = 0;

    @ColumnInfo(name = "formula")
    private String formula;

    @ColumnInfo(name = "answer")
    private String answer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return formula + " = " + answer;
    }
}

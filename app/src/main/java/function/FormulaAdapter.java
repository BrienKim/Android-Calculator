package function;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;
import com.example.androidcalculator.R;
import database.Formula;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FormulaAdapter extends RecyclerView.Adapter<FormulaAdapter.ViewHolder>
{
    private List<Formula> formulaList;

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView formulaView;
        private TextView answerView;

        public ViewHolder(@NonNull @NotNull View itemView)
        {
            super(itemView);
            formulaView = itemView.findViewById(R.id.formula_view);
            answerView = itemView.findViewById(R.id.answer_view);
        }
    }

    public FormulaAdapter(List<Formula> formulaList)
    {
        this.formulaList = formulaList;
    }

    @NonNull
    @NotNull
    @Override
    public FormulaAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FormulaAdapter.ViewHolder holder, int position)
    {
        holder.formulaView.setText(formulaList.get(position).getFormula());
        holder.answerView.setText(formulaList.get(position).getAnswer());
    }

    @Override
    public int getItemCount()
    {
        return formulaList.size();
    }
}

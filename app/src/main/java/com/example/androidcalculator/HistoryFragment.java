package com.example.androidcalculator;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import database.Formula;
import function.FormulaAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HistoryFragment extends Fragment
{
    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private static List<Formula> formulaList;

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        formulaList = MainActivity.formulaDao.getAllFormula();

        recyclerView = view.findViewById(R.id.history_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new FormulaAdapter(formulaList);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.calculation_history, container, false);
    }

    public void addHistory(Formula formula)
    {
        int position = formulaList.size();

        MainActivity.formulaDao.insertFormula(formula);

        /*
            Since initial id for the Formula object is always 0
            Thus, implement alternative SQL-based method: getLastAddedID
            Which, return actual id (primary key value), corresponding to the Formula object
         */
        formula.setId(MainActivity.formulaDao.getLastAddedID());
        formulaList.add(position, formula);

        adapter.notifyItemInserted(position);
    }

    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT)
    {
        @Override
        public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction)
        {
            int position = viewHolder.getAdapterPosition();

            Formula deleted = formulaList.remove(position);
            MainActivity.formulaDao.deleteFormula(deleted);
            adapter.notifyItemRemoved(position);

            Snackbar.make(recyclerView, deleted.toString(), Snackbar.LENGTH_LONG)
                    .setAction("Undo", view -> {
                        formulaList.add(position, deleted);
                        MainActivity.formulaDao.insertFormula(formulaList.get(position));
                        adapter.notifyItemInserted(position);
                    }).show();
        }

        @Override
        public void onChildDraw(@NonNull @NotNull Canvas c, @NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive)
        {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE && isCurrentlyActive)
            {
                View view = viewHolder.itemView;

                GradientDrawable background = new GradientDrawable();
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.baseline_delete_24);

                int height = view.getBottom() - view.getTop();
                int margin = (height - icon.getIntrinsicHeight()) / 2;
                int iconLeft = view.getRight() - margin - icon.getIntrinsicWidth();
                int iconTop = view.getTop() + (height - icon.getIntrinsicHeight()) / 2;
                int iconRight = view.getRight() - margin;
                int iconBottom = iconTop + icon.getIntrinsicHeight();

                background.setColor(getContext().getColor(R.color.yellow_orange));
                background.setBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                background.setCornerRadius(70);

                icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

                background.draw(c);
                icon.draw(c);
            }
        }
    };
}
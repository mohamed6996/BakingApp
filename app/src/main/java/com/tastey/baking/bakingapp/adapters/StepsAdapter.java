package com.tastey.baking.bakingapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tastey.baking.bakingapp.ListItemClickListener;
import com.tastey.baking.bakingapp.R;
import com.tastey.baking.bakingapp.model.StepModel;

import java.util.List;

/**
 * Created by lenovo on 3/9/2018.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    List<StepModel> steps;
    ListItemClickListener listener;

    public StepsAdapter(ListItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public StepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_item, parent, false);
        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (steps != null) return steps.size();
        else return 0;
    }

    public void setList(List<StepModel> list) {
        this.steps = list;
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView shortDescription, stepNumber;

        public StepsViewHolder(View itemView) {
            super(itemView);
            shortDescription = itemView.findViewById(R.id.step_short_desc);
            stepNumber = itemView.findViewById(R.id.step_number);
            itemView.setOnClickListener(this);
        }

        private void bind(int position) {
            shortDescription.setText(steps.get(position).getShortDescription());
            int step_num = position + 1;
            stepNumber.setText("" + step_num);
        }

        @Override
        public void onClick(View view) {
            listener.onListITemClicked(getAdapterPosition());
        }
    }
}

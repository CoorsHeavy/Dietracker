package com.hart.samplehartapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.hart.samplehartapplication.R;
import com.hart.samplehartapplication.models.CheckListItem;
import com.hart.samplehartapplication.models.PhotoListItem;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by hudsonhughes on 7/1/16.
 */
public class FoodDialogAdapter extends RecyclerView.Adapter<PastRecycleAdapter.ViewHolder>{
    ArrayList<CheckListItem> mCheckList;

    @Override
    public PastRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderCheck(LayoutInflater.from(parent.getContext()).inflate(R.layout.check_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PastRecycleAdapter.ViewHolder holder, int position) {
        ViewHolderCheck vh0 = (ViewHolderCheck) holder;
        View view = vh0.getView();
        {
            TextView tv = ButterKnife.findById(view, R.id.textView);
            CheckBox cb = ButterKnife.findById(view, R.id.checkBox);
            cb.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public ViewHolder(View v) {
            super(v);
            view = v;
        }
        public View getView(){
            return view;
        }
    }

    public static class ViewHolderCheck extends PastRecycleAdapter.ViewHolder {
        public ViewHolderCheck(View v) {
            super(v);
        }
    }
}

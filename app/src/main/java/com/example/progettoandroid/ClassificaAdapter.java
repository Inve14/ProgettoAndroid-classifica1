package com.example.progettoandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClassificaAdapter extends RecyclerView.Adapter<ClassificaViewHolder> {

    private ClassificaViewModel viewModel;
    private LayoutInflater mInflater;
    private ClassificaRecyclerViewClickListener rvcl;

    public ClassificaAdapter(Context context, ClassificaViewModel viewModel, ClassificaRecyclerViewClickListener rvcl) {
        this.viewModel = viewModel;
        this.mInflater = LayoutInflater.from(context);
        this.rvcl = rvcl;
    }

    @NonNull
    @Override
    public ClassificaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.single_row_classifica, parent, false);
        return new ClassificaViewHolder(view, rvcl);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassificaViewHolder holder, int position) {
        holder.bind(viewModel.getContact(position));
    }

    @Override
    public int getItemCount() {
        return viewModel.getContactsCount();
    }
}


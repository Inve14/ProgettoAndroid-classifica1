/*
package com.example.progettoandroid;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;


public class ClassificaViewHolder extends RecyclerView.ViewHolder {

    private Button nameButton;
    private String previousText = "";

    private ClassificaRecyclerViewClickListener rvcl;

    public ClassificaViewHolder(@NonNull View itemView, ClassificaRecyclerViewClickListener rvcl) {
        super(itemView);
        this.rvcl = rvcl;
        nameButton = itemView.findViewById(R.id.nameButton);

        itemView.setOnClickListener(v -> {
            rvcl.onItemClicked(getAdapterPosition());
        });

        nameButton.setOnClickListener(v -> {
            rvcl.onItemClicked(getAdapterPosition());
        });
    }

    public void bind(String contact) {
        previousText = contact;
        nameButton.setText(contact);
    }
}

*/

package com.example.progettoandroid;

import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClassificaViewHolder extends RecyclerView.ViewHolder {

    private Button nameButton;
    private ClassificaRecyclerViewClickListener rvcl;

    public ClassificaViewHolder(@NonNull View itemView, ClassificaRecyclerViewClickListener rvcl) {
        super(itemView);
        this.rvcl = rvcl;
        nameButton = itemView.findViewById(R.id.nameButton);

        itemView.setOnClickListener(v -> {
            rvcl.onItemClicked(getAdapterPosition());
        });

        nameButton.setOnClickListener(v -> {
            rvcl.onItemClicked(getAdapterPosition());
        });
    }

    public void bind(String contact) {
        nameButton.setText(contact);
    }
}


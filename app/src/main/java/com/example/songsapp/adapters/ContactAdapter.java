package com.example.songsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.songsapp.R;
import com.example.songsapp.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactHolder>{
    Context context;
    ArrayList user_fname, user_lname;
    ContactHolder contactHolder;

    public ContactAdapter(Context context, ArrayList user_fname, ArrayList user_lname) {
        this.context = context;
        this.user_fname = user_fname;
        this.user_lname = user_lname;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        //Contact contact = contactList.get(position);
        holder.name.setText(String.valueOf(user_fname.get(position)));
        holder.phone.setText(String.valueOf(user_lname.get(position)));
        //holder = contactHolder;
    }

    @Override
    public int getItemCount() {
        return user_lname.size();
    }
}

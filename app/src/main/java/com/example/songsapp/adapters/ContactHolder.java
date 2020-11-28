package com.example.songsapp.adapters;

import android.view.View;
import android.widget.TextView;

import com.example.songsapp.R;
import com.example.songsapp.model.Contact;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactHolder extends RecyclerView.ViewHolder {
    TextView name, phone;
    public ContactHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.name);
        phone = (TextView)itemView.findViewById(R.id.phone);
    }

        /*public void bind(Contact contact){
            name.setText(contact.getName());
            phone.setText(contact.getPhone());
        }*/
}

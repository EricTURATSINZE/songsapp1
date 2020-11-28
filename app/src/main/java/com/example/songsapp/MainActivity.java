package com.example.songsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.songsapp.adapters.ContactAdapter;
import com.example.songsapp.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.songsapp.model.Contact;
import com.example.songsapp.DatabaseManager;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> user_fname, user_lname;
    ContactAdapter contactAdapter;
    DatabaseManager dbManager;
    User user1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.enableDefaults();
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);

        dbManager = new DatabaseManager(MainActivity.this);
        /*user1.setFirstName("Eric");
        user1.setLastName("Turatsinze");
        user1.setEmail("gahamaced@gmail.com");
        user1.setPassword("test1234");
        dbManager.addUser(user1);
        User user2 = new User(2, "Cedric", "Gahamanyi", "gahamaced@gmail.com", "test1234");
        dbManager.addUser(user1);
        dbManager.addUser(user2);*/

        user_fname = new ArrayList<>();
        user_lname = new ArrayList<>();
        makeContacts();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
    }

    public void makeContacts() {
        Cursor cursor = dbManager.getAllUsers();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()) {
                user_fname.add(cursor.getString(1));
                user_lname.add(cursor.getString(2));
            }
        }
        /*RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest request=new StringRequest(Request.Method.GET, "http://127.0.0.1:8000/api/v1/users", response -> {
            //sucessfull
            System.out.println(response);
            Log.d("Data",response);

            try {
                JSONArray jsonArray=new JSONArray(response);
                System.out.println("============jsonArray=============");
                System.out.println(jsonArray);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject json=jsonArray.getJSONObject(i);
                    //String name=json.getString("name");
                    String name = "Eric";
                    //String phone=json.getString("phone");
                    String phone="078888888";
                    contacts.add(new Contact(name,phone));

                }
                //contactAdapter=new ContactAdapter(contacts);
                //recyclerView.setAdapter(contactAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Data",error.toString());
            }
        });
        requestQueue.add(request);*/
        contactAdapter=new ContactAdapter(MainActivity.this, user_fname, user_lname);
        recyclerView.setAdapter(contactAdapter);
    }
}
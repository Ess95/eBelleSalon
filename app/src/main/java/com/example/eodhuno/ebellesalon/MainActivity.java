package com.example.eodhuno.ebellesalon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText cFirstName;
    private EditText cLastName;
    private EditText cPhnoneNo;
    private EditText cEmail;
    private EditText cPassword;
    private ScrollView cScrollView;
    private EditText cColumn;
    private EditText cValue;
    private Button cAdd;
    private Button cUpdate;
    private Button cDelete;
    private Button cSearch;
    private Spinner cColumnSearch;


    private FirebaseDatabase cFirebaseDatabase;
    private DatabaseReference cDatabaseReference;
    private ChildEventListener cChildEventListener;

    private ValueEventListener cProfileValueEventListener;

    ListView clistViewProfiles;
    List<NewCustomer> customerProfileList;

    //Add Listenser for retrieving data


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize and connecting to Database
        cFirebaseDatabase = FirebaseDatabase.getInstance();
        cDatabaseReference = cFirebaseDatabase.getReference().child("customers");

        //Initialize References to Views
        cFirstName = (EditText) findViewById(R.id.FirstName_et);
        cLastName = (EditText) findViewById(R.id.LastName_et);
        cPhnoneNo = (EditText) findViewById(R.id.PhoneNo_et);
        cEmail = (EditText) findViewById(R.id.Email_et);
        cPassword = (EditText) findViewById(R.id.Password_et);
        cValue = (EditText) findViewById(R.id.Value_et);
        cAdd = (Button) findViewById(R.id.button_Add);
        cUpdate = (Button) findViewById(R.id.button_Update);
        cDelete = (Button) findViewById(R.id.button_Delete);
        cSearch = (Button) findViewById(R.id.button_Search);
        cColumnSearch = (Spinner) findViewById(R.id.Column_sp);

        clistViewProfiles = (ListView) findViewById(R.id.listView_cProfiles);
        customerProfileList = new ArrayList<>();

        //Enable Add button for new customer
        cAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewCustomer newcustomer = new NewCustomer(cFirstName.getText().toString(),cLastName.getText().toString(),
                                        cPhnoneNo.getText().toString(), cEmail.getText().toString(), cPassword.getText().toString());
                cDatabaseReference.push().setValue(newcustomer);

                Log.d("here","HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" );

                cFirstName.setText("");
                cLastName.setText("");
                cPhnoneNo.setText("");
                cEmail.setText("");
                cPassword.setText("");
            }

        });

        cDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            //Retrieves the data as is from the database and whatever changes are made.
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot cProfileSnapshot : dataSnapshot.getChildren()){
                    NewCustomer cProfile = cProfileSnapshot.getValue(NewCustomer.class);
                    boolean add = customerProfileList.add(cProfile);
                }
                CustomerProfiles adapter = new CustomerProfiles(MainActivity.this, customerProfileList);
                clistViewProfiles.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        cChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                NewCustomer newCustomer = dataSnapshot.getValue(NewCustomer.class);
                }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        };


        cDatabaseReference.addChildEventListener(cChildEventListener);
    }


}





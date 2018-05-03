package com.example.eodhuno.ebellesalon;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class CustomerProfiles extends ArrayAdapter<NewCustomer> {

    private Activity context;
    private List<NewCustomer> customerProfileList;

    public CustomerProfiles(Activity context, List<NewCustomer> customerProfileList){
        super(context, R.layout.customer_profiles, customerProfileList);
        this.context = context;
        this.customerProfileList = customerProfileList;
    }

    @NonNull
    @Override
    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public List<NewCustomer> getCustomerProfileList() {
        return customerProfileList;
    }

    public void setCustomerProfileList(List<NewCustomer> customerProfileList) {
        this.customerProfileList = customerProfileList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutinflater = context.getLayoutInflater();
        View listViewItem = layoutinflater.inflate(R.layout.customer_profiles, null, true);

        //Initialize the TextViews
        TextView tvFname = (TextView) listViewItem.findViewById(R.id.textView_Fname);
        TextView tvLname = (TextView) listViewItem.findViewById(R.id.textView_Lname);
        TextView tvEmail = (TextView) listViewItem.findViewById(R.id.textView_Email);
        TextView tvPhoneNo = (TextView) listViewItem.findViewById(R.id.textView_Phone);

        //Get Customer profile at specific position and add their details with the set values
        NewCustomer pCustomer = customerProfileList.get(position);

        tvFname.setText(String.valueOf(pCustomer.getFname()));
        tvLname.setText(String.valueOf(pCustomer.getLName()));
        tvEmail.setText(String.valueOf(pCustomer.getEmail()));
        tvPhoneNo.setText(String.valueOf(pCustomer.getPhoneNo()));


        return listViewItem;
    }
}



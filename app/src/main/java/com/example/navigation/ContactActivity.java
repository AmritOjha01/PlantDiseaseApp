package com.example.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.navigation.utils.Utils;

public class ContactActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity);

        toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        toolbar.setTitle("Contact");
        Utils.toolbarConfiguration(toolbar, this);
    }

}

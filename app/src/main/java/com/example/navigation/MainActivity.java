package com.example.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String selection;
    AutoCompleteTextView stageDropDown, partTextField, leafTextField, rateTextField;
    ImageView imageStageDrpdown, imgPartDropDown, imgLeafDropDown, imgRateDropDown;
    LinearLayout layoutStage, layoutPart, layoutLeaf, layoutRate;
    ImageView mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        init();
        showDropDown();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * Inflate the menu; this adds items to the action bar if it is present.
         *         getMenuInflater().inflate(R.menu.main, menu);
         */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /**
         * Handle action bar item clicks here. The action bar will
         * automatically handle clicks on the Home/Up button, so long
         * as you specify a parent activity in AndroidManifest.xml.
         */
        int id = item.getItemId();

        /**
         * noinspection SimplifiableIfStatement
         */
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        /**
         * Handle navigation view item clicks here.
         */
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void init() {
        stageDropDown = findViewById(R.id.dpStage);
        partTextField = findViewById(R.id.dpPart);
        leafTextField = findViewById(R.id.dpLeaf);
        rateTextField = findViewById(R.id.dpRate);

        imageStageDrpdown = findViewById(R.id.stageDrpdown);
        imgPartDropDown = findViewById(R.id.partDropdown);
        imgLeafDropDown = findViewById(R.id.leafDropDown);
        imgRateDropDown = findViewById(R.id.rateDropdown);

        layoutStage = findViewById(R.id.layoutStage);
        layoutPart = findViewById(R.id.editPart);
        layoutLeaf = findViewById(R.id.layoutLeaf);
        layoutRate = findViewById(R.id.editRate);

        mCamera = findViewById(R.id.imageCamera);

    }

    private void showDropDown() {
        final ArrayAdapter<String> arrayAdapterStage = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, getResources()
                .getStringArray(R.array.Stage));

        final ArrayAdapter<String> arrayAdapterPart = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, getResources()
                .getStringArray(R.array.Part));


        final ArrayAdapter<String> arrayAdapterLeaf = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, getResources()
                .getStringArray(R.array.Leaf));

        final ArrayAdapter<String> arrayAdapterRate = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, getResources()
                .getStringArray(R.array.Rate));

        stageDropDown.setAdapter(arrayAdapterStage);
        partTextField.setAdapter(arrayAdapterPart);
        leafTextField.setAdapter(arrayAdapterLeaf);
        rateTextField.setAdapter(arrayAdapterRate);


        stageDropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                stageDropDown.showDropDown();
                selection = (String) parent.getItemAtPosition(position);

            }
        });

        partTextField.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                partTextField.showDropDown();
                selection = (String) parent.getItemAtPosition(position);

            }
        });

        leafTextField.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                leafTextField.showDropDown();
                selection = (String) parent.getItemAtPosition(position);

            }
        });

        mCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Camera Functionality", Toast.LENGTH_SHORT).show();
            }
        });

       /* rateTextField.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                rateTextField.showDropDown();
                selection = (String) parent.getItemAtPosition(position);

            }
        });*/

        onClickDropDown();


    }

    private void onClickDropDown() {

        /**
         * STAGE
         */
        layoutStage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                //    HoursAgo.hideSoftKeyboard(MainActivity.this);
                stageDropDown.showDropDown();

                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            }
        });


        /**
         * PART
         */
        layoutPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                //    HoursAgo.hideSoftKeyboard(MainActivity.this);
                partTextField.showDropDown();

                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            }
        });

        /**
         * LEAF
         */
        layoutLeaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                //    HoursAgo.hideSoftKeyboard(MainActivity.this);
                leafTextField.showDropDown();

                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            }
        });

        /**
         * RATE
         */
        layoutRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                //HoursAgo.hideSoftKeyboard(MainActivity.this);
                rateTextField.showDropDown();

                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            }
        });
    }
}


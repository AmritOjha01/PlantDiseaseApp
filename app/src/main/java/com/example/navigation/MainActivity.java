package com.example.navigation;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.navigation.model.SaveInfo;
import com.example.navigation.model.SaveResponse;
import com.example.navigation.resrhelper.RestClient;
import com.example.navigation.resrhelper.RetroInterfaceAPI;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int GALLERY_REQUEST_CODE = 101;
    private static final int CAMERA_REQUEST_CODE = 102;
    private String pathToFile;

    private String selectionStage, selectionPart, selectionLeaf, selectionDate, seletionRate;
    private AutoCompleteTextView mStage, mPart, mLeaf, mRate;
    private ImageView imageStageDrpdown, imgPartDropDown, imgLeafDropDown, imgRateDropDown;
    private LinearLayout layoutStage, layoutPart, layoutLeaf, layoutRate;
    private ImageView mCamera, imageTomato, datePicker;
    private Button mSaveBtn;
    private EditText mTemp, mDate, mHumdity, mLabel, mFarm, mLocation, mComment;
    private int mYear, mMonth, mDay, mHour, mMinute;
    ProgressBar progressbarEdit;
    String label, farm, location, image, comment, temp, humidity, date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_REQUEST_CODE);
        }

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void init() {


        mTemp = findViewById(R.id.editTemp);
        mHumdity = findViewById(R.id.editHumidity);
        mLabel = findViewById(R.id.editLabel);
        mDate = findViewById(R.id.editDate);
        mFarm = findViewById(R.id.editFarm);
        mLocation = findViewById(R.id.editLocation);
        mComment = findViewById(R.id.editComment);


        mStage = findViewById(R.id.dpStage);
        mPart = findViewById(R.id.dpPart);
        mLeaf = findViewById(R.id.dpLeaf);
        mRate = findViewById(R.id.dpRate);

        imageStageDrpdown = findViewById(R.id.stageDrpdown);
        imgPartDropDown = findViewById(R.id.partDropdown);
        imgLeafDropDown = findViewById(R.id.leafDropDown);
        imgRateDropDown = findViewById(R.id.rateDropdown);

        layoutStage = findViewById(R.id.layoutStage);
        layoutPart = findViewById(R.id.editPart);
        layoutLeaf = findViewById(R.id.layoutLeaf);
        layoutRate = findViewById(R.id.editRate);

        mCamera = findViewById(R.id.imageCamera);
        mSaveBtn = findViewById(R.id.buttonSave);
        imageTomato = findViewById(R.id.imageTomato);
        datePicker = findViewById(R.id.datePicker);
        progressbarEdit = findViewById(R.id.progressbarEdit);
        progressbarEdit.bringToFront();


        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateTemp() |
                        !validateHumidity() |
                        !validateLabel() |
                        !validateStage() |
                        !validatePart() |
                        !validateLeaf() |
                        !validateDate() |
                        !validateFarm() |
                        !validatLocation() |
                        !validatRate()) {
                    return;

                } else {
                    try {
                        callRetrofitSaveInfo();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormat = "yyyy-MM-dd"; //Change as you need
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                        mDate.setText(sdf.format(myCalendar.getTime()));

                        mDay = selectedday;
                        mMonth = selectedmonth;
                        mYear = selectedyear;
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.show();
            }
        });


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

        mStage.setAdapter(arrayAdapterStage);
        mPart.setAdapter(arrayAdapterPart);
        mLeaf.setAdapter(arrayAdapterLeaf);
        mRate.setAdapter(arrayAdapterRate);


        mStage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                mStage.showDropDown();
                selectionStage = (String) parent.getItemAtPosition(position);

            }
        });

        mPart.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                mPart.showDropDown();
                selectionPart = (String) parent.getItemAtPosition(position);
                Log.d("Part", selectionPart);

            }
        });

        mLeaf.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                mLeaf.showDropDown();
                selectionLeaf = (String) parent.getItemAtPosition(position);
                Log.d("Leaf", selectionLeaf);

            }
        });

        mRate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                mRate.showDropDown();
                seletionRate = (String) parent.getItemAtPosition(i);
                Log.d("Rate", seletionRate);

            }
        });

        mCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraOptionDialogue(v);
            }
        });

        onClickDropDown();
    }


    private void cameraOptionDialogue(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Choose Photo Options");
        alertDialogBuilder.setPositiveButton("CAMERA",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        captureFromCamera();
                    }
                });

        alertDialogBuilder.setNegativeButton("GALLERY",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        pickFromGallery();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void pickFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    private void captureFromCamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            photoFile = createPhotoFile();

            if (photoFile != null) {
                pathToFile = photoFile.getAbsolutePath();
                Uri photoURI = FileProvider.getUriForFile(MainActivity.this, "com.example.navigation.fileprovider", photoFile);
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePicture, CAMERA_REQUEST_CODE);
            }

        }
    }

    private File createPhotoFile() {
        String name = new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date());
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(name, ".jpg", storageDir);
        } catch (IOException e) {
            Log.d("mylog", "Excep : " + e.toString());
        }
        return image;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GALLERY_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    imageTomato.setImageURI(selectedImage);
                }
                break;

            case CAMERA_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
                    imageTomato.setImageBitmap(bitmap);
                }
                break;
        }
    }


    private void onClickDropDown() {

        /**
         * STAGE
         */
        layoutStage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                mStage.showDropDown();

                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            }
        });


        /**
         * PART
         */
        layoutPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                mPart.showDropDown();

                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            }
        });

        /**
         * LEAF
         */
        layoutLeaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                mLeaf.showDropDown();

                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            }
        });

        /**
         * RATE
         */
        layoutRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                mRate.showDropDown();

                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            }
        });

        mStage.requestFocus();
        mRate.requestFocus();
        mPart.requestFocus();
        mLeaf.requestFocus();
    }


    private void callRetrofitSaveInfo() {


        label = mLabel.getText().toString().trim();
        farm = mFarm.getText().toString();
        location = mLocation.getText().toString();
        image = null;
        comment = mComment.getText().toString();
        temp = mTemp.getText().toString();
        humidity = mHumdity.getText().toString();
        date = mDate.getText().toString();
        final SaveResponse saveResponse = new SaveResponse(label, selectionStage, selectionPart, selectionLeaf, farm, location, null, comment, temp, humidity, seletionRate, date);

        RetroInterfaceAPI mInterface = RestClient.getClient();

        Call<SaveInfo> call = mInterface.getSaveInfo(saveResponse);
        call.enqueue(new Callback<SaveInfo>() {
            @Override
            public void onResponse(Call<SaveInfo> call, Response<SaveInfo> response) {
                if (response.body() != null) {
                    if (response.code() == 200) {
                        Toast.makeText(MainActivity.this, "Record Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearData();
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));

                    }
                }
            }

            @Override
            public void onFailure(Call<SaveInfo> call, Throwable t) {

            }
        });

    }

    private void clearData() {
        mLabel.getText().clear();
        mFarm.getText().clear();
        mLocation.getText().clear();
        mLeaf.getText().clear();
        mPart.getText().clear();
        mComment.getText().clear();
        mRate.getText().clear();
        mDate.getText().clear();
        mStage.getText().clear();
        mHumdity.getText().clear();
        mTemp.getText().clear();


    }

    private boolean validateTemp() {
        String userInput = mTemp.getText().toString().trim();
        if (userInput.isEmpty()) {
            mTemp.setError("Field must not be empty");
            return false;
        } else {
            mTemp.setError(null);
            return true;

        }
    }

    private boolean validateHumidity() {
        String userInput = mHumdity.getText().toString().trim();
        if (userInput.isEmpty()) {
            mHumdity.setError(" Field must not be empty");
            return false;
        } else {
            mHumdity.setError(null);
            return true;

        }
    }

    private boolean validateLabel() {
        String userInput = mLabel.getText().toString().trim();
        if (userInput.isEmpty()) {
            mLabel.setError(" Field must not be empty");
            return false;
        } else {
            mLabel.setError(null);
            return true;

        }
    }


    private boolean validateStage() {
        String userInput = mStage.getText().toString().trim();
        if (userInput.isEmpty()) {
            mStage.setError("Field must not be empty");
            return false;
        } else {
            mStage.setError(null);
            return true;

        }
    }

    private boolean validatePart() {
        String userInput = mPart.getText().toString().trim();
        if (userInput.isEmpty()) {
            mPart.setError("Field must not be empty");
            return false;
        } else {
            mPart.setError(null);
            return true;

        }
    }

    private boolean validateLeaf() {
        String userInput = mLeaf.getText().toString().trim();
        if (userInput.isEmpty()) {
            mLeaf.setError("Field must not be empty");
            return false;
        } else {
            mLeaf.setError(null);
            return true;

        }
    }


    private boolean validateDate() {
        String userInput = mDate.getText().toString().trim();
        if (userInput.isEmpty()) {
            mDate.setError("Field must not be empty");
            return false;
        } else {
            mDate.setError(null);
            return true;

        }
    }

    private boolean validateFarm() {
        String userInput = mFarm.getText().toString().trim();
        if (userInput.isEmpty()) {
            mFarm.setError("Field must not be empty");
            return false;
        } else {
            mFarm.setError(null);
            return true;

        }
    }

    private boolean validatLocation() {
        String userInput = mLocation.getText().toString().trim();
        if (userInput.isEmpty()) {
            mLocation.setError("Field must not be empty");
            return false;
        } else {
            mLocation.setError(null);
            return true;

        }
    }


    private boolean validatRate() {
        String userInput = mRate.getText().toString().trim();
        if (userInput.isEmpty()) {
            mRate.setError("Field must not be empty");
            return false;
        } else {
            mRate.setError(null);
            return true;

        }
    }
}
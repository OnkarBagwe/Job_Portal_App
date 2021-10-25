package com.example.jobportalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.example.jobportalapp.Model.ProfileData;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RegistrationActivity extends AppCompatActivity {

    private EditText emailReg;
    private EditText passReg;
    private EditText full_name;
    private EditText phone_no;
    private EditText designation;

    private Button btnReg;
    private Button btnLogin;

    FusedLocationProviderClient fusedLocationProviderClient;

    //Firebase authentication
    private FirebaseAuth mAuth;

    //Firebase
    private DatabaseReference Userdata;

    //Progress dialog..

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        Userdata = FirebaseDatabase.getInstance().getReference().child("User Data");

        mDialog = new ProgressDialog(this);

        Registration();
    }

//    private String hereLocation(double lat, double lon){
//        String cityName = "";
//
//        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
//        List<Address> addresses;
//        try{
//            addresses = geocoder.getFromLocation(lat, lon, 10);
//            if(addresses.size()>0){
//                for (Address adr:addresses){
//                    if(adr.getLocality()!=null && adr.getLocality().length()>0){
//                        cityName = adr.getLocality();
//                        break;
//                    }
//                }
//            }
//        } catch ( IOException e){
//            e.printStackTrace();
//        }
//        return cityName;
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode){
//            case 1000:{
//                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                    try{
//                        String city = hereLocation(location.getLatitude(), location.getLatitude());
//                        System.out.println("HHHHHHHHHHHHHHHHOOOOOOOOOOOLLLLLLLLLLLLLAAAAAAAAAAAAAAAAAAAAAA"+city);
//                    } catch (Exception e){
//                        e.printStackTrace();
//                        Toast.makeText(RegistrationActivity.this, "Not found!", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            }
//        }
//    }

    private void Registration(){

        emailReg = findViewById(R.id.email_registration);
        passReg = findViewById(R.id.registration_password);
        full_name = findViewById(R.id.full_name);
        phone_no = findViewById(R.id.phone_no);
        designation = findViewById(R.id.designation);

        btnReg = findViewById(R.id.btn_reg);
        btnLogin = findViewById(R.id.btn_login);

        //Initialize fusedLocationProviderClient

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailReg.getText().toString().trim();
                String pass = passReg.getText().toString().trim();
                String fullname = full_name.getText().toString().trim();
                String phoneno = phone_no.getText().toString().trim();
                String desig = designation.getText().toString().trim();
                String city = "";

                if(TextUtils.isEmpty(email)){
                    emailReg.setError("Required Field...");
                    return;
                }

                if(TextUtils.isEmpty(pass)){
                    passReg.setError("Required Field...");
                    return;
                }

                if(TextUtils.isEmpty(fullname)){
                    full_name.setError("Required Field...");
                    return;
                }

                if(TextUtils.isEmpty(phoneno)){
                    phone_no.setError("Required Field...");
                    return;
                }

                if(TextUtils.isEmpty(desig)){
                    designation.setError("Required Field...");
                    return;
                }

                if (ActivityCompat.checkSelfPermission(RegistrationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    //When permission granted
                    fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            //Initialize Location
                            Location location = task.getResult();
                            if (location != null){
                                // Initialize geoCoder
                                Geocoder geocoder = new Geocoder(RegistrationActivity.this, Locale.getDefault());
                                //Initialize address list
                                List<Address> addresses;
                                try{
                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    String city = addresses.get(0).getLocality() + ", " + addresses.get(0).getCountryName();
                                } catch ( IOException e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } else {
                    //When permission denied
                    ActivityCompat.requestPermissions(RegistrationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }

//                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
//                } else {
//                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                    try{
//                        city = hereLocation(location.getLatitude(), location.getLatitude());
//                    } catch (Exception e){
//                        e.printStackTrace();
//                        Toast.makeText(RegistrationActivity.this, "Not found!", Toast.LENGTH_SHORT).show();
//                    }
//                }

                mDialog.setMessage("Processing...");
                mDialog.show();

//                String finalCity = city;
                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            String user_id = mAuth.getCurrentUser().getUid();
                            String join_date = DateFormat.getDateInstance().format(new Date());
                            ProfileData data = new ProfileData (fullname, phoneno, desig, email, pass, city, user_id, join_date);
                            Userdata.child(user_id).setValue(data);
                            Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            mDialog.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "Registration Failed...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btnLogin.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        }));
    }

}

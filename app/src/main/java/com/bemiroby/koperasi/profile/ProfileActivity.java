package com.bemiroby.koperasi.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bemiroby.koperasi.R;

public class ProfileActivity extends AppCompatActivity {

    private ImageView imgProfile1;
    private ImageView imgProfile2;
    private TextView tvUserName1;
    private TextView tvUserInfo1;
    private TextView tvUserName2;
    private TextView tvUserInfo2;
    private ImageButton btnBack;  // Deklarasi tombol back

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize views
        imgProfile1 = findViewById(R.id.imgProfile1);
        imgProfile2 = findViewById(R.id.imgProfile2);
        tvUserName1 = findViewById(R.id.tvUserName1);
        tvUserInfo1 = findViewById(R.id.tvUserInfo1);
        tvUserName2 = findViewById(R.id.tvUserName2);
        tvUserInfo2 = findViewById(R.id.tvUserInfo2);
        btnBack = findViewById(R.id.btnBack);  // Inisialisasi tombol back

        // Set up user profiles
        setupUserProfile();

        // Tambahkan logika untuk tombol back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Kembali ke MainActivity
            }
        });
    }

    private void setupUserProfile() {
        // Hardcoded user information for both profiles
        tvUserName1.setText("Bemi Raihan Rawadi");
        tvUserInfo1.setText("Broadband Multimedia Student");

        tvUserName2.setText("Muhammad Roby Meidiansyah");
        tvUserInfo2.setText("Broadband Multimedia Student");

        // Set placeholder images for both profiles
        imgProfile1.setImageResource(R.drawable.bemi);
        imgProfile2.setImageResource(R.drawable.roby);

        // Optional: listeners for changing profile images
        imgProfile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to open image picker or camera for imgProfile1
            }
        });

        imgProfile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to open image picker or camera for imgProfile2
            }
        });
    }
}

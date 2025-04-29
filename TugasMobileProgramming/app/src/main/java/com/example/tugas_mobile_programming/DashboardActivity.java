package com.example.tugas_mobile_programming;

import android.view.View;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.cardview.widget.CardView;

public class DashboardActivity extends AppCompatActivity {

    CardView btnMaps;
    CardView cardGeneral, cardCredential;
    String email, password, name, birthdate, gender, address;
    TextView txtEmail, txtPassword, txtName, txtBirthdate, txtGender, txtAddress;
    LinearLayout btnConstructor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        btnMaps = findViewById(R.id.btn_maps);
        cardGeneral = findViewById(R.id.card_general);

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addressMaps = getIntent().getStringExtra("address");

                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(addressMaps));

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        name = getIntent().getStringExtra("name");
        birthdate = getIntent().getStringExtra("birthdate");
        gender = getIntent().getStringExtra("gender");
        address = getIntent().getStringExtra("address");

        txtName = findViewById(R.id.txt_name);
        txtBirthdate = findViewById(R.id.txt_birthdate);
        txtGender = findViewById(R.id.txt_gender);
        txtAddress = findViewById(R.id.txt_address);

        txtName.setText(name);
        txtBirthdate.setText(birthdate);
        txtGender.setText(gender);
        txtAddress.setText(address);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dashboard), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}
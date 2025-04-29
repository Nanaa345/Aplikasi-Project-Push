package com.example.tugas_mobile_programming;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    EditText txtEmail, txtPassword, txtName, txtBirthdate, txtAddress;
    Button btnSubmit;
    RadioButton radioButtonMale, radioButtonFemale;
    RadioGroup radioGroupGender;
    private void initUI(){
        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        txtName = findViewById(R.id.txt_name);
        txtBirthdate = findViewById(R.id.txt_birthdate);
        txtAddress = findViewById(R.id.txt_address);
        radioButtonMale = findViewById(R.id.radio_male);
        btnSubmit =findViewById(R.id.btn_submit);
        radioGroupGender = findViewById(R.id.radio_group_gender);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        initUI();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password, name, birthdate, address, gender;
                Intent intent;

                email = txtEmail.getText().toString();
                password = txtPassword.getText().toString();
                name = txtName.getText().toString();
                birthdate = txtBirthdate.getText().toString();
                gender = radioButtonMale.isChecked() ? "Laki Laki" : "Perempuan";
                address = txtAddress.getText().toString();

                if (email.isEmpty() || password.isEmpty() || name.isEmpty() || birthdate.isEmpty() || address.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Semua field harus diisi!", Toast.LENGTH_LONG).show();
                }else{
                    if(radioGroupGender.getCheckedRadioButtonId() == -1){
                        Toast.makeText(getApplicationContext(), "Silahkan pilih jenis kelamin!", Toast.LENGTH_LONG).show();
                    }else{
                        intent = new Intent(RegisterActivity.this, DashboardActivity.class);
                        intent.putExtra("email", email);
                        intent.putExtra("password", password);
                        intent.putExtra("name", name);
                        intent.putExtra("birthdate", birthdate);
                        intent.putExtra("gender", gender);
                        intent.putExtra("address", address);
                        startActivity(intent);
                    }

                }


            }
        });


        txtBirthdate = findViewById(R.id.txt_birthdate);
        txtBirthdate.setFocusable(false);
        txtBirthdate.setFocusableInTouchMode(false);
        txtBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                txtBirthdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

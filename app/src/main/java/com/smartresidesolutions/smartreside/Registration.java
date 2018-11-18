package com.smartresidesolutions.smartreside;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.smartresidesolutions.api.RegisterApiInterface;
import com.smartresidesolutions.common.RetrofitClient;
import com.smartresidesolutions.model.Register;

import java.util.ArrayList;

import retrofit2.Call;

public class Registration extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

private EditText registerName;
private EditText registerEmail;
private EditText registerMobile;
private EditText registerDob;
private EditText registerKycNumber;
private Spinner registerKycType;
private Spinner registerGenderType;
    DatePickerDialog datePickerDialogdob;
Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Name selection code//
        registerName = (EditText) findViewById(R.id.register_name_edittext);
        //Name selection code//

        //Date of birth selection code//
        datePickerDialogdob = new DatePickerDialog(
                this, this,2018,1, 1);
        registerDob=findViewById(R.id.register_dob_edittext);
        registerDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialogdob.show();
            }
        });
        //Date of birth selection code//

        //Email selection code//
        registerEmail = (EditText) findViewById(R.id.register_email_edittext);
        //Email selection code//

        //Mobile selection code//
        registerMobile = (EditText) findViewById(R.id.register_mobile_edittext);
        //Mobile selection code//


        //Gender type selection code//
        registerGenderType = (Spinner)findViewById(R.id.register_gender_spinner);
        ArrayList priorityLevel=new ArrayList();
        priorityLevel.add("Male");
        priorityLevel.add("Female");
        ArrayAdapter<String> adapterPriority = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,priorityLevel);
        adapterPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        registerGenderType.setAdapter(adapterPriority);
        //Gender type selection code//


        //KYC type selection code//
        registerKycType = (Spinner)findViewById(R.id.register_kyc_type_spinner);
        ArrayList priorityLevel1=new ArrayList();
        priorityLevel1.add("PAN Card");
        priorityLevel1.add("Passport");
        priorityLevel1.add("Aadhar Card");
        ArrayAdapter<String> adapterPriority1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,priorityLevel1);
        adapterPriority1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        registerKycType.setAdapter(adapterPriority1);
        //KYC type selection code//



        //Kyc Number selection code//
        registerKycNumber = (EditText) findViewById(R.id.register_kyc_details_edittext);
        //Kyc Number selection code//


        // Submit button code//
        submitButton = (Button) findViewById(R.id.register_submit_button);
        submitButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                RegisterApiInterface apiService=
                        RetrofitClient.getClient().create(RegisterApiInterface.class);

                Register register = new Register(registerName.getText().toString(),registerEmail.getText().toString(),registerMobile.getText().toString(),registerDob.getText().toString(),registerKycNumber.getText().toString(),registerKycType.getSelectedItem().toString(),registerGenderType.getSelectedItem().toString());
                Call<Register> call = apiService.getUser(register);


               // Intent activityChangeIntent = new Intent(view.getContext(),LoginActivity.class);
                //activityChangeIntent.putExtra("email",mEmailView.getText().toString());
               // startActivity(activityChangeIntent);
            }
        });
        //Submit button code//

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        registerDob.setText(year+"/"+(month+1)+"/"+dayOfMonth);
    }


    private void sendUserDetails(Register register) {

        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
        loginIntent.putExtra("userRegistrationData",register);
        startActivity(loginIntent);
    }
}

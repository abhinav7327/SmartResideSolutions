package com.smartresidesolutions.smartreside;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.smartresidesolutions.api.LoginApiInterface;
import com.smartresidesolutions.common.RetrofitClient;
import com.smartresidesolutions.model.Register;
import com.smartresidesolutions.model.User;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

private EditText registerFirstName;
private EditText registerLastName;
private EditText registerEmail;
private EditText registerMobile;
private EditText registerDob;
private EditText registerKycNumber;
private EditText registerSetPassword;
private EditText registerConfirmPassword;
private Spinner registerKycType;
private Spinner registerGenderType;

    DatePickerDialog datePickerDialogdob;
Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //First Name selection code//
        registerFirstName = (EditText) findViewById(R.id.register_firstname_edittext);
        //First Name selection code//

        //First Name selection code//
        registerLastName = (EditText) findViewById(R.id.register_lastname_edittext);
        //First Name selection code//

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

        //Set password code//
        registerSetPassword = (EditText) findViewById(R.id.register_set_password_edittext);
        //Set password code//

        //Confirm password code//
        registerConfirmPassword = (EditText) findViewById(R.id.register_confirm_password_edittext);
        //Confirm password code//

        // Submit button code//
        submitButton = (Button) findViewById(R.id.register_submit_button);
        submitButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (validationRegistration() == true) {
                    LoginApiInterface apiService =
                            RetrofitClient.getClient().create(LoginApiInterface.class);

                    Register register = new Register(registerFirstName.getText().toString(),registerLastName.getText().toString(), registerEmail.getText().toString(), registerMobile.getText().toString(), registerDob.getText().toString(), registerKycNumber.getText().toString(), registerKycType.getSelectedItem().toString(), registerGenderType.getSelectedItem().toString());
                    Call<Register> call = apiService.registerUser(register);


                    call.enqueue(new Callback<Register>() {
                        @Override
                        public void onResponse(Call<Register> call, Response<Register> response) {
                            Register register = response.body();
                            sendUserDetails(register);
                        }

                        @Override
                        public void onFailure(Call<Register> call, Throwable t) {

                        }
                    });

                    // Intent activityChangeIntent = new Intent(view.getContext(),LoginActivity.class);
                    //activityChangeIntent.putExtra("email",mEmailView.getText().toString());
                    // startActivity(activityChangeIntent);
                }
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


    //=========================================================================================
    //=================Validations start from here=============================================
    //=========================================================================================
    private boolean validationRegistration() {
        boolean validationResponse = false;
        //First Name vaildation
        if (isEmpty(registerFirstName)) {
            //Toast missingFirstName = Toast.makeText(this,"Please enter First Name to register!",Toast.LENGTH_SHORT);
            //missingFirstName.show();
            registerFirstName.setError("Please enter First Name to register!");
        }
        //First Name vaildation

        //Last Name vaildation
        if (isEmpty(registerLastName)) {
           // Toast missingLastName = Toast.makeText(this,"Please enter Last Name to register!",Toast.LENGTH_LONG);
           // missingLastName.show();
            registerLastName.setError("Please enter Last Name to register!");
        }
        //Last Name vaildation

        //Date of Birth vaildation
        if(isEmpty(registerDob)){
            //Toast missingDob = Toast.makeText(this,"Chose date of birth to register!",Toast.LENGTH_LONG);
            //missingDob.show();
            registerDob.setError("Choose date of birth");
        }
        //Date of Birth vaildation

        //Email vaildation
         if(isEmail(registerEmail)==false){
            registerEmail.setError("Enter valid email");
         }
        //Email vaildation


        //Mobile Number vaildation
        if(isValidMobile(registerMobile)==false){
            registerMobile.setError("Enter valid mobile number");
        }
        //Mobile Number vaildation

        //KYC Number vaildation
        if(isValidKyc(registerKycNumber)==false){
            registerKycNumber.setError("Enter Valid Kyc for the Kyc Type chosen!");
        }
        //KYC Number vaildation

        //Code to validate first password
        if(registerSetPassword.getText().toString().length()<8 || registerSetPassword.getText().toString().length()>25){
            Toast incorrectLengthPassword = Toast.makeText(this, "Password must have minimum 8 characters!", Toast.LENGTH_LONG);
            registerSetPassword.setError("Password must have minimum 8 characters");
        }else if(!isValidPassword(registerSetPassword)) {
            System.out.println("Not Valid");
            Toast invalidPassword = Toast.makeText(this, "Password must contain Number, Symbol & alphabet!", Toast.LENGTH_LONG);
            registerSetPassword.setError("Password must contain Number, Symbol & alphabet!");
        }
        else{
            System.out.println("Valid");
            Toast validPassword = Toast.makeText(this, "Re-enter password to confirm", Toast.LENGTH_LONG);
        }
        //Code to validate first password

        //Code to validate second password
        if(registerConfirmPassword.getText().toString().length()<8 || registerConfirmPassword.getText().toString().length()>25){
            Toast incorrectLengthPassword = Toast.makeText(this, "Password must have minimum 8 characters!", Toast.LENGTH_LONG);
            registerConfirmPassword.setError("Password must have minimum 8 characters");
        }else if(!isValidPassword(registerConfirmPassword)) {
            System.out.println("Not Valid");
            Toast invalidPassword = Toast.makeText(this, "Password must contain Number, Symbol & alphabet!", Toast.LENGTH_LONG);
            registerConfirmPassword.setError("Password must contain Number, Symbol & alphabet!");
        }
        else{
            System.out.println("Valid");
           // Toast validPassword = Toast.makeText(this, "Re-enter password to confirm", Toast.LENGTH_LONG);
        }
        //Code to validate second password


        //to validate if passwords are matching
        if(!passwordValidator()== true){
            registerConfirmPassword.setError("Password do not match re-enter");
        }
        //to validate if passwords are matching


        //code to check if all fields are entered
         if(isEmpty(registerFirstName)==false && isEmail(registerEmail)==true  && isValidMobile(registerMobile)==true  && isValidKyc(registerKycNumber)==true  && isValidPassword(registerSetPassword)==true && isValidPassword(registerConfirmPassword)==true && passwordValidator()== true) {
              validationResponse = true;
         }
        //code to check if all fields are entered

         return validationResponse;
        // returns true if all validations are correct from validationRegistration methos
    }


    //Method to check empty field
    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    //Method to check empty field

    //Method to check email validation
    boolean isEmail(EditText text){
        CharSequence email = text.getText().toString();
        return(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    //Method to check email validation

    //Method to check mobile validation
     boolean isValidMobile(EditText text) {
         CharSequence phone = text.getText().toString();
        return (!TextUtils.isEmpty((phone)) && Patterns.PHONE.matcher(phone).matches()  && phone.length()==10);
    }
    //Method to check mobile validation

    //Method to check kyc validation
    boolean isValidKyc(EditText text){
        boolean kycValidation = false;
        CharSequence kyc = text.getText().toString();
        if(registerKycType.getSelectedItem()=="PAN Card"){
            kycValidation = (!TextUtils.isEmpty((kyc)) && kyc.length()==10);
        }else if(registerKycType.getSelectedItem()=="Passport"){
            kycValidation = (!TextUtils.isEmpty((kyc)) && kyc.length()==8);
        }else if(registerKycType.getSelectedItem()=="Aadhar Card"){
            kycValidation = (!TextUtils.isEmpty((kyc)) && Patterns.PHONE.matcher(kyc).matches() && kyc.length()==12);
        }
        return kycValidation;
    }
    //Method to check kyc validation

    //Method to check password validation - must have numeric, alphabet and symbol
     boolean isValidPassword(EditText text) {
        CharSequence setPassword = text.getText().toString();
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(setPassword);
        return matcher.matches();
    }
    //Method to check password validation - must have numeric, alphabet and symbol

    //Method to check password and confirm password
    boolean passwordValidator(){
        boolean passwordMatch = false;
        String passwordSet=registerSetPassword.getText().toString();
        String passwordConfirm=registerConfirmPassword.getText().toString();
        if(!passwordSet.equals(passwordConfirm)){
            //Toast.makeText(this,"Password do not match re-enter",Toast.LENGTH_SHORT).show();
            passwordMatch=false;
        }else{
            passwordMatch=true;
        }
        return passwordMatch;
    }
    //Method to check password and confirm password
}

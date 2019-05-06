package lintend.pabson.bhaktapur.login_signupActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lintend.pabson.bhaktapur.CustomToast.CustomToastErr;
import lintend.pabson.bhaktapur.CustomToast.CustomToastSuccess;
import lintend.pabson.bhaktapur.MainActivity;
import lintend.pabson.bhaktapur.R;
import lintend.pabson.bhaktapur.SessionManager;

public class loginActivity extends AppCompatActivity {

    SessionManager sessionManager;
    AlertDialog dialogs;
    // login ativity
    EditText username, password;
    TextView forgetPassword, spinnererror;
    Button loginButton, signupButton;


//signup actvity
    EditText siUname, siPwd, nameOfScl, AddOfScl, phofSl, emailOfScl, estdDate;  // School Details
    EditText pName, pEmail,pMobno;

    Spinner spinner;
    String selectedCategory ;
    Button signup;
    ImageView close;
    RequestQueue requestQueues;

    ProgressDialog progressDialog;
    String
    rejex="^[A-Za-z][A-Za-z0-9]*([._-]?[A-Za-z0-9]+)@[A-Za-z].[A-Za-z]{0,3}?.[A-Za-z]{0,2}$";

    String emailCheck = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


   String loginURL = "http://xdroid051.000webhostapp.com/pabson/pabson/login/login.php";
  //  String loginURL = "http://192.168.1.174:8080/pabson/login/login.php";
    String signUpURL = "http://xdroid051.000webhostapp.com/pabson/pabson/login/signup.php";
   // String signUpURL="http://192.168.1.174:8080/pabson/login/signup.php";
    //String municipalityURL = "http://192.168.1.174:8080/pabson/spinner.php";
    String municipalityURL = "http://xdroid051.000webhostapp.com/pabson/pabson/spinner.php";
    //String municipalityURL = "http://popularkoju.com.np/id1277129_lintendforum/category.php";
    List<String> categories = new ArrayList<String>();
    //ArrayList<String> categories;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        username = findViewById(R.id.loginusername);
        password = findViewById(R.id.loginpassword);

        loginButton = findViewById(R.id.loginbtn);
        signupButton = findViewById(R.id.signupbtn);

        forgetPassword = findViewById(R.id.forgetpassword);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        //Session Mannager
        sessionManager = new SessionManager(loginActivity.this);
        if(sessionManager.isLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));
        }


//        final String target =username.getText().toString().trim();
//        //final boolean result = android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
//        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                 if (target.matches(emailPattern)){
//                    username.setError("Enter Valid email");
//
//                }
//               else
//
                if (username.length() == 0) {
                    username.setError("Username cannot be empty");
                    username.requestFocus();
                } else if (password.length() == 0) {
                    password.setError("Password Cannot be empty");
                    password.requestFocus();
                } else {
                    progressDialog.show();
                    loginToServer();

                }
            }
        });


        /*************************************************LOGIN  button ENDS************************************************/

        /*************************************************SIGN UP************************************************/

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupalert();
            }
        });

    }

    public void signupalert() {


        View v = LayoutInflater.from(this).inflate(R.layout.signup_layout, null);
        close = v.findViewById(R.id.cross);
        spinner = (Spinner) v.findViewById(R.id.spinner);


        //School details

        siUname=v.findViewById(R.id.signupusername);
        siPwd=v.findViewById(R.id.signuppassword);
        nameOfScl=v.findViewById(R.id.NameOFSchool);
        phofSl=v.findViewById(R.id.contactOfSchool);
        emailOfScl=v.findViewById(R.id.emailOfSchool);
        estdDate=v.findViewById(R.id.EstdOfSchool);
        AddOfScl=v.findViewById(R.id.locationOfSchool);

        //Principal Details

        pName=v.findViewById(R.id.nameofPrincipal);
        pEmail=v.findViewById(R.id.emailOfPrinciipal);
        pMobno=v.findViewById(R.id.contactofPrincipal);


        signup = v.findViewById(R.id.registerbtn);

        categories = new ArrayList<>();
        //SPINNER
        loadSpinnerData();
        spinnererror = v.findViewById(R.id.spinnerErr);

        AlertDialog.Builder builders = new AlertDialog.Builder(loginActivity.this, R.style.DialogTheme);
        builders.setView(v);
        dialogs = builders.create();
        dialogs.show();


        close.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         dialogs.dismiss();
                                     }
                                 }
        );

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedMunicipality();

               issignupValid();
                //Toast.makeText(loginActivity.this, "haha", Toast.LENGTH_SHORT).show();






            }
        });
    }

    public void selectedMunicipality() {


        try {
            selectedCategory = spinner.getSelectedItem().toString();
            Toast.makeText(loginActivity.this, selectedCategory, Toast.LENGTH_SHORT).show();
        } catch ( Exception e){
            Toast.makeText(loginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

        }








    }

    private void loadSpinnerData() {                                                         // spinner method
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, municipalityURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array1 = new JSONArray(response);

                    for (int i = 0; i < array1.length(); i++) {


                        JSONObject obj1 = array1.getJSONObject(i);
                        String cate = obj1.getString("name");
                        categories.add(cate);

                    }

                    // Creating adapter for spinner
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_layout, categories);

                    // Drop down layout style
                    dataAdapter.setDropDownViewResource(R.layout.spinner_list);

                    // attaching data adapter to spinner
                    spinner.setAdapter(dataAdapter);
//                    spinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,categories));
//                    spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                } catch (Exception e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                new CustomToastErr(loginActivity.this, "No Internet Connection");


            }
        });
        requestQueue.add(stringRequest);
    }


    /*************************************************SIGN UP ENDs************************************************/

    /*************************************************Login to server************************************************/

    public void loginToServer() {
        requestQueues = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("error", response);
                try {
                    JSONObject obj1 = new JSONObject(response);

                      String emailFromShredpref = username.getText().toString();
                    if (obj1.names().get(0).equals("success")) {
                        String id = obj1.getString("id");
                        String role = obj1.getString("role");
                        //Toast.makeText(loginActivity.this, "user id = " +id+ "role = " + role, Toast.LENGTH_SHORT).show();
                        sessionManager.getQid(id);
                        sessionManager.getRole(role);
                        progressDialog.dismiss();
                       // Toast.makeText(loginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        new CustomToastSuccess(loginActivity.this, "Login Successful");
                        //created session

                          sessionManager.createLoginSession(emailFromShredpref);
//                                new
                        Intent i = new Intent(loginActivity.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        loginActivity.this.finish();
                    } else {
                        progressDialog.dismiss();
                       // Toast.makeText(loginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        new CustomToastErr(loginActivity.this, "Invalid Credentials");
                    }

                } catch (Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(loginActivity.this, "Exception Caught", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                //Toast.makeText(loginActivity.this, "No Internet Connectivity", Toast.LENGTH_SHORT).show();
                new CustomToastErr(loginActivity.this, "No Internet Connection");

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> mymap = new HashMap<>();

                mymap.put("username", username.getText().toString().trim());
                mymap.put("password", password.getText().toString().trim());
                return mymap;
            }
        };

        requestQueues.add(stringRequest);
    }
    /*********************LOGINTO SERVER ENDS*******************************************************/



public void issignupValid() {
    if(siUname.getText().length()==0){
        siUname.setError("Username cannot be empty");
        siUname.requestFocus();
    }
       else if(siPwd.length() == 0){
        siPwd.setError("Password cannot be empty");
       siPwd.requestFocus();
       }
        else if( nameOfScl.length() == 0 ){
        nameOfScl.setError("Field cannot be empty");
        nameOfScl.requestFocus();
        }
         else if(AddOfScl.length() == 0 ){
        AddOfScl.setError("Field cannot be empty");
         AddOfScl.requestFocus();
         }
        else if(phofSl.length() == 0 ){
        phofSl.setError("Field cannot be empty");
        phofSl.requestFocus();
        }
        else if(emailOfScl.length() == 0){
        emailOfScl.setError("Field cannot be empty");
        emailOfScl.requestFocus();
        }
         else if (estdDate.length() == 0){
        estdDate.setError("Field cannot be empty");
        estdDate.requestFocus();}
         else if (pName.length() == 0 ){
        pName.setError("Name cannot be empty");
         pName.requestFocus();
         }
//   else if( pEmail.length() == 0 ){
//        pEmail.setError("Email cannot be empty");
//        pEmail.requestFocus();}
   else if( pMobno.length() == 0) {
        pMobno.setError("Field cannot be empty");
        pMobno.requestFocus();

    }

    else if(!emailOfScl.getText().toString().trim().matches(emailCheck)){
        emailOfScl.setError("Enter valid Email");}

    else if (pMobno.getText().toString().trim().length()!= 10){
        pMobno.setError("Number Must Be 10 digits");}

//    else if( pEmail.getText().toString().trim().matches(rejex)){
//               pEmail.setError("Enter valid Email");}

   else if(estdDate.getText().toString().trim().length()!=4){

        estdDate.setError("Year must be 4 digits");}

   else{
          //  Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
        sendDataSignup();

        }
}


public void sendDataSignup(){

    //login details
    final String sentUsername= siUname.getText().toString().trim();
    final String sentPassword= siPwd.getText().toString().trim();

    //school details
    final String sentNameOfSchool= nameOfScl.getText().toString().trim();
    final String sentScholEmail= emailOfScl.getText().toString().trim();
    final String sentPhoneOfSchool= phofSl.getText().toString().trim();
    final String sentEstdDate= estdDate.getText().toString().trim();
    final String sentAddress= AddOfScl.getText().toString().trim();

    //principal Details
    final String sentPrincipalName= pName.getText().toString().trim();
    //final String sentPrincipalEmail= pEmail.getText().toString().trim();
    final String sentPricipalContactNum= pMobno.getText().toString().trim();


    progressDialog.show();


    requestQueues = Volley.newRequestQueue(getApplicationContext());
            final StringRequest stringRequest = new StringRequest(Request.Method.POST, signUpURL, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.i("error", response);
            try {
                JSONObject obj1 = new JSONObject(response);

                //  String emailFromShredpref = username.getText().toString();
                if (obj1.names().get(0).equals("success")) {
                    progressDialog.dismiss();
                   // Toast.makeText(loginActivity.this, "Sign up Successful", Toast.LENGTH_SHORT).show();
                    new CustomToastSuccess(loginActivity.this, "Signup Successfull");
                    //created session
                    progressDialog.dismiss();
                    dialogs.dismiss();


                } else {
                    progressDialog.dismiss();
                  //  Toast.makeText(loginActivity.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                   new CustomToastErr(loginActivity.this, "Sign Up Failed");


                }

            } catch (Exception e) {
                progressDialog.dismiss();
                Toast.makeText(loginActivity.this, "Exception Caught", Toast.LENGTH_SHORT).show();
            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            progressDialog.dismiss();
            //Toast.makeText(loginActivity.this, "No Internet Connectivity", Toast.LENGTH_SHORT).show();
            new CustomToastErr(loginActivity.this, "No Internet Connection");

        }
    }) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> mymap = new HashMap<>();

            mymap.put("username", sentUsername);
            mymap.put("password", sentPassword);

            mymap.put("name", sentNameOfSchool);
            mymap.put("address", sentAddress);
            mymap.put("email", sentScholEmail);
            mymap.put("contact", sentPhoneOfSchool);
            mymap.put("estDate", sentEstdDate);

            mymap.put("principal_name", sentPrincipalName);
         //   mymap.put("password", sentPrincipalEmail);
            mymap.put("principal_contact", sentPricipalContactNum);

            mymap.put("municipality", selectedCategory);

            return mymap;
        }
    };

    requestQueues.add(stringRequest);
}

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    //   loginActivity.this.finish();
    }
}



















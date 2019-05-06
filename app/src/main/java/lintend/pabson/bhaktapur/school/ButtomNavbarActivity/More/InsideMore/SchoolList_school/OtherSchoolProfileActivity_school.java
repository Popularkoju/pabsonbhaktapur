package lintend.pabson.bhaktapur.school.ButtomNavbarActivity.More.InsideMore.SchoolList_school;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import lintend.pabson.bhaktapur.R;
import lintend.pabson.bhaktapur.SessionManager;
import lintend.pabson.bhaktapur.school.ButtomNavbarActivity.More.InsideMore.Profile_school.ProfileActivitySchool;

public class OtherSchoolProfileActivity_school extends AppCompatActivity {

    String schoolProfileURL = "http://xdroid051.000webhostapp.com/pabson/pabson/schoolCat/viewSchoolProfile.php";
    //school details

    TextView schoolName, estdDate, address, schoolEmail, municipality, schoolPhone;
   // String ss_name;
    //principal Details
    TextView principalNam, principalPhoneNum;
    ImageView dialNumberPrincipal;
    Menu menu;
    Toolbar mToolbar;

    String dialSchoolnumber, dialPrincipalNum,  schoolID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_school_profile_school_layout);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        //school
        schoolName = findViewById(R.id.schoolname);
        estdDate = findViewById(R.id.estdDate);
        address = findViewById(R.id.schoolocation);
        schoolEmail = findViewById(R.id.emailSch);
        municipality = findViewById(R.id.municipality);
        schoolPhone = findViewById(R.id.contactnumSchool);

        //principal

        principalNam = findViewById(R.id.principalname);
        principalPhoneNum = findViewById(R.id.principalPhone);

        dialNumberPrincipal = findViewById(R.id.dialPrincipalNum);

        schoolID= getIntent().getStringExtra("school_id");

        profileDataFromServer();

        //schoolName.setText(schoolID);

        dialNumberPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent next = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + dialPrincipalNum));
                    startActivity(next);
                } catch (Exception e) {
                    Toast.makeText(OtherSchoolProfileActivity_school.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                }

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                try {
                    Intent next = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + dialSchoolnumber));
                    startActivity(next);
                } catch (Exception e) {
                    Toast.makeText(OtherSchoolProfileActivity_school.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    showOption(R.id.action_info);
                } else if (isShow) {
                    isShow = false;
                    hideOption(R.id.action_info);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.content_scrolling, menu);
        hideOption(R.id.action_info);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {

            Toast.makeText(this, "call school", Toast.LENGTH_SHORT).show();
            try {
                Intent next = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + dialSchoolnumber));
                startActivity(next);
            } catch (Exception e) {
                Toast.makeText(OtherSchoolProfileActivity_school.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }


    public void profileDataFromServer() {

//        SessionManager sm = new SessionManager(getApplicationContext());
//        HashMap<String, String> mp = sm.getUserDetails();
//        final String id = mp.get(SessionManager.KEY_USER_ID);
//       final String username = mp.get(SessionManager.KEY_EMAIL);


        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getApplication()));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, schoolProfileURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array1 = new JSONArray(response);

                    for (int i = 0; i < array1.length(); i++) {
                        JSONObject obj1 = array1.getJSONObject(i);

                        //School details data
                        String s_name = obj1.getString("schoolName");
                        String estd = obj1.getString("estDate");
                        String addressofSchool = obj1.getString("schoolLocation");
                        String schoolEmailid = obj1.getString("schoolEmail");
                        String phoneNumberSchool = obj1.getString("schoolContact");
                        String municpalityname = obj1.getString("municipality");

                        schoolName.setText(s_name);
                        estdDate.setText(estd);
                        address.setText(addressofSchool);
                        schoolEmail.setText(schoolEmailid);
                        schoolPhone.setText(phoneNumberSchool);
                        municipality.setText(municpalityname);

                        //principal details Data
                        String principalname =obj1.getString("principal_name");
                        String principalContact =obj1.getString("principal_contact");

                        principalNam.setText(principalname);
                        principalPhoneNum.setText(principalContact);

                        dialSchoolnumber = schoolPhone.getText().toString().trim();
                        dialPrincipalNum  = principalPhoneNum.getText().toString().trim();



                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Error_Dude" , e.getMessage());
                    Toast.makeText(getApplicationContext(), "Exception Caught" + e, Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(c, "No internet", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
               // map.put("username", username);
                map.put("user_id", schoolID);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}


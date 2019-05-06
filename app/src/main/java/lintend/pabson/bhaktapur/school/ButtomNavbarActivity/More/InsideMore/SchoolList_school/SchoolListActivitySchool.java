package lintend.pabson.bhaktapur.school.ButtomNavbarActivity.More.InsideMore.SchoolList_school;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lintend.pabson.bhaktapur.CustomToast.CustomToastErr;
import lintend.pabson.bhaktapur.DataModule;
import lintend.pabson.bhaktapur.R;
import lintend.pabson.bhaktapur.school.ButtomNavbarActivity.Home.HomeActivitySchoolRecylerAdapter;

public class SchoolListActivitySchool extends AppCompatActivity {

    //String schoolListURl="http://192.168.1.174:8080/pabson/schoolCat/viewSchoolList.php";
    String schoolListURl="http://xdroid051.000webhostapp.com/pabson/pabson/schoolCat/viewSchoolList.php";
    RecyclerView recyclerView;
    ImageView backButton;

    List<DataModule> schoolListData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_list_school_layout_recylerview);
        backButton=findViewById(R.id.backButton);

        recyclerView=findViewById(R.id.school_list_recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        dataSchoolListFromServer();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SchoolListActivitySchool.this.finish();
            }
        });

    }

    public void dataSchoolListFromServer(){

      RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, schoolListURl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("data", response);
                try {
                    JSONArray array1 = new JSONArray(response);

                    for (int i = 0; i < array1.length(); i++) {


                        JSONObject obj1 = array1.getJSONObject(i);


                        DataModule m = new DataModule();
                        m.setSenderName(obj1.getString("schoolName"));
                        m.setSchoolNumber(obj1.getString("schoolContact"));
                        m.setSchoolId(obj1.getString("id"));
                        schoolListData.add(m);

                    }
                    // Toast.makeText(getContext(), "Refreshed ", Toast.LENGTH_LONG).show();


                    recyclerView.setAdapter(new School_list_Adapter(getApplicationContext(), schoolListData));


                } catch (Exception e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error" , error.toString());
                new CustomToastErr(getApplicationContext(),"No Internet Connection");



            }
        });
        requestQueue.add(stringRequest);
    }
    }

package lintend.pabson.bhaktapur.school.ButtomNavbarActivity.Home;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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
import lintend.pabson.bhaktapur.CustomToast.CustomToastSuccess;
import lintend.pabson.bhaktapur.DataModule;
import lintend.pabson.bhaktapur.R;

public class HomeActivitySchool extends Fragment {

    SwipeRefreshLayout refresh;
    RecyclerView recyclerView;
List<DataModule> datas = new ArrayList<>();
RequestQueue requestQueue;
    String url = "http://popularkoju.com.np/id1277129_lintendforum/question_display_image.php";





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_recyleview, container, false);
        refresh = v.findViewById(R.id.swipeRefresh);


        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(true);
                datas.clear();
                dataLoading();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new CustomToastSuccess(getContext(),"Refreshed");
                        refresh.setRefreshing(false);
                    }
                }, 2000);

            }
        });
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView =view.findViewById(R.id.home_tab_recycleview);
          recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




        dataLoading();
      //  recyclerView.setAdapter(new HomeActivitySchoolRecylerAdapter(getActivity(),datas));
    }


    public void dataLoading() {
        requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array1 = new JSONArray(response);

                    for (int i = 0; i < array1.length(); i++) {


                        JSONObject obj1 = array1.getJSONObject(i);
                        DataModule m = new DataModule();
                        m.setSenderName(obj1.getString("name"));
                        m.setNotice(obj1.getString("question"));
                        m.setTime(obj1.getString("date_time"));




                        datas.add(m);

                    }
                    // Toast.makeText(getContext(), "Refreshed ", Toast.LENGTH_LONG).show();


                    recyclerView.setAdapter(new HomeActivitySchoolRecylerAdapter(getContext(), datas));


                } catch (Exception e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               new CustomToastErr(getContext(),"No Internet Connection");



            }
        });
        requestQueue.add(stringRequest);
    }
}

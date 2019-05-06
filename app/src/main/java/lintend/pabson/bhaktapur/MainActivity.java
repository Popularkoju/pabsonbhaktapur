package lintend.pabson.bhaktapur;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import lintend.pabson.bhaktapur.school.ButtomNavbarActivity.Home.HomeActivitySchool;
import lintend.pabson.bhaktapur.school.ButtomNavbarActivity.Message.MessageActivitySchool;
import lintend.pabson.bhaktapur.school.ButtomNavbarActivity.More.MoreActivitySchool;

public class MainActivity extends AppCompatActivity {
    public BottomNavigationView bottomNavigationView;
  //  Toolbar toolbar;

    FragmentTransaction ft;

    //public static Context contextOfAppliction;
//    public static  Context getContextOfAppliction(){
//        return  contextOfAppliction;

    //Context context = MainActivity.this;
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.layout_view_mid);

        bottomNavigationView = findViewById(R.id.buttom_nav_bar);
        //bottomNavigationView.setSelectedItemId(R.id.ic_home);
        bottomNavigationView.getMenu().getItem(1).setChecked(true);




        final SessionManager sm = new SessionManager(this);
        sm.isLoggedIn();

        ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.layoutmid,new HomeActivitySchool());
        ft.commit();

      //  contextOfAppliction = getApplicationContext();








        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.ic_home: {
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.layoutmid, new HomeActivitySchool());
                        ft.commit();
                        break;
                    }

                    case R.id.ic_messages: {
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.layoutmid, new MessageActivitySchool());
                        ft.commit();
                        break;
                    }

                    case R.id.ic_more: {
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.layoutmid, new MoreActivitySchool());
                        ft.commit();
                        break;
                    }

                }
                return true;
            }
        });
    }



    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to leave?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Intent.ACTION_MAIN);
                i.addCategory(Intent.CATEGORY_HOME);
                startActivity(i);
                finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("No", null);
        builder.create().show();

    }

}

package lintend.pabson.bhaktapur.school.ButtomNavbarActivity.More.InsideMore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import lintend.pabson.bhaktapur.CustomToast.CustomToastSuccess;
import lintend.pabson.bhaktapur.R;
import lintend.pabson.bhaktapur.SessionManager;
import lintend.pabson.bhaktapur.school.ButtomNavbarActivity.More.MoreActivitySchool;

public class SettingActivitySchool extends AppCompatActivity {
    CardView editProfile, changePassword, logout;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_school_layout);
        editProfile =findViewById(R.id.editcard);
        changePassword=findViewById(R.id.changePasswordcard);
        logout =findViewById(R.id.logoutcard);

        backButton=findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SettingActivitySchool.this.finish();
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CustomToastSuccess(SettingActivitySchool.this,"edit profile");
            }

        });


        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CustomToastSuccess(SettingActivitySchool.this,"Change password");
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager s = new SessionManager(getApplicationContext());
                s.logoutUser();
            }
        });


    }
}

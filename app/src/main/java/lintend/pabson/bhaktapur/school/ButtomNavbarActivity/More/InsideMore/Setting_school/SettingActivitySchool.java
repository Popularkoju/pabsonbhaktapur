package lintend.pabson.bhaktapur.school.ButtomNavbarActivity.More.InsideMore.Setting_school;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import lintend.pabson.bhaktapur.CustomToast.CustomToastSuccess;
import lintend.pabson.bhaktapur.R;
import lintend.pabson.bhaktapur.SessionManager;

public class SettingActivitySchool extends AppCompatActivity {
    CardView editProfile, changePassword, logout;
    ImageView backButton;
    SessionManager s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_school_layout);
        editProfile =findViewById(R.id.editcard);
        changePassword=findViewById(R.id.changePasswordcard);
        logout =findViewById(R.id.logoutcard);
        s = new SessionManager(this);
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

                s.logoutUser();
             //   SettingActivitySchool.this.finish();
            }
        });


    }
}

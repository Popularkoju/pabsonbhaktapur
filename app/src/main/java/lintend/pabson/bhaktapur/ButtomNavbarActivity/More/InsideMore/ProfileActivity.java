package lintend.pabson.bhaktapur.ButtomNavbarActivity.More.InsideMore;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import lintend.pabson.bhaktapur.R;

public class ProfileActivity extends AppCompatActivity {
    //school details

    TextView schoolName, estdDate, address, schoolEmail, municipality, schoolPhone;

    //principal Details
    TextView principalName, principalPhone;
    ImageView dialNumber;
    Menu menu;

    String dialSchoolnumber ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("School Name");
        setSupportActionBar(mToolbar);

        //school

        schoolName = findViewById(R.id.schoolname);
        estdDate = findViewById(R.id.estdDate);
        address = findViewById(R.id.locationOfSchool);
        schoolEmail = findViewById(R.id.emailSch);
        municipality=findViewById(R.id.municipality);
        schoolPhone = findViewById(R.id.contactnumSchool);

        //principal

        principalName = findViewById(R.id.principalname);
        principalPhone=findViewById(R.id.principalPhone);

        dialNumber = findViewById(R.id.dialPrincipalNum);

        dialSchoolnumber=  schoolPhone.getText().toString().trim();






        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                try {
                    Intent next = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+dialSchoolnumber));
                    startActivity(next);
                }catch (Exception e){
                    Toast.makeText(ProfileActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
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
                 Intent next = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+dialSchoolnumber));
                 startActivity(next);
             }catch (Exception e){
                 Toast.makeText(ProfileActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
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
}











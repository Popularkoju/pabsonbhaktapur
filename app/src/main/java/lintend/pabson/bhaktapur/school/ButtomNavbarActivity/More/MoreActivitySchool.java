package lintend.pabson.bhaktapur.school.ButtomNavbarActivity.More;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import lintend.pabson.bhaktapur.school.ButtomNavbarActivity.More.InsideMore.Profile_school.ProfileActivitySchool;
import lintend.pabson.bhaktapur.R;
import lintend.pabson.bhaktapur.school.ButtomNavbarActivity.More.InsideMore.SchoolList_school.SchoolListActivitySchool;
import lintend.pabson.bhaktapur.school.ButtomNavbarActivity.More.InsideMore.Setting_school.SettingActivitySchool;

public class MoreActivitySchool extends Fragment {
    GridView mGridView;
    String[] data = {"Municipality", "Schools List" ,"Profile" , "Setting"};
    int[] images = {R.drawable.municipality , R.drawable.school, R.drawable.pabb , R.drawable.ic_settings_black_24dp };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.more_layout_school, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGridView = view.findViewById(R.id.gridView);
        MyAdapter adapter =  new MyAdapter(getActivity(),data,images);
        mGridView.setAdapter(adapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              //  Toast.makeText(getActivity(),String.valueOf(position), Toast.LENGTH_SHORT).show();


                switch(position){
                    //Municipality grid
                    case 0:{

                        break;}

                    //School list
                    case 1:{ Intent i = new Intent(getContext(), SchoolListActivitySchool.class);
                        startActivity(i);

                        break;
                    }
                    //profile
                    case 2:{
                        Intent i = new Intent(getContext(), ProfileActivitySchool.class);
                        startActivity(i);
                        break;

                    }
                    //Setting
                    case 3:{
                        Intent i = new Intent(getContext(), SettingActivitySchool.class);
                        startActivity(i);

                        break;
                    }






                }

            }
        });
    }

    class MyAdapter extends ArrayAdapter{
        int[] imageArray;
        String[] titleArray;
        Context context;

        public MyAdapter(Context context, String[] titleArray, int[] imageArray) {
            super(context,R.layout.custom_grid_view_more, titleArray);
            this.titleArray = titleArray;
            this.imageArray = imageArray;
            this.context = context;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row =  inflater.inflate(R.layout.custom_grid_view_more,parent, false);
            ImageView myImage = (ImageView)row.findViewById(R.id.gridIconLogo);
            TextView myText = (TextView) row.findViewById(R.id.gridIconTitle);

            myImage.setImageResource(imageArray[position]);
            myText.setText(titleArray[position]);
            return row;
        }
    }
}

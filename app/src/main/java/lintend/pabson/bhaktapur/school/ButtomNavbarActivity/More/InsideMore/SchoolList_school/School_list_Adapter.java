package lintend.pabson.bhaktapur.school.ButtomNavbarActivity.More.InsideMore.SchoolList_school;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import lintend.pabson.bhaktapur.DataModule;
import lintend.pabson.bhaktapur.R;
import lintend.pabson.bhaktapur.school.ButtomNavbarActivity.Home.HomeActivitySchoolRecylerAdapter;

public class School_list_Adapter extends RecyclerView.Adapter<School_list_Adapter.MyViewHolder> {

    String schoolId;
    Context c;
    List<DataModule> mydata;


    public School_list_Adapter(Context c, List<DataModule> mydata) {
        this.c = c;
        this.mydata = mydata;

    }


    @NonNull
    @Override
    public School_list_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(c);
        View v = inflater.inflate(R.layout.school_list_school_layout, null);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView schoolList;
        TextView schoolName, schoolPhoneNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            schoolList = itemView.findViewById(R.id.School_name_card);
            schoolName = itemView.findViewById(R.id.txtSchooname);
            schoolPhoneNumber = itemView.findViewById(R.id.txtPhoneNUmber);


        }
    }

    @Override
    public void onBindViewHolder(@NonNull final School_list_Adapter.MyViewHolder myViewHolder, final int i) {

        myViewHolder.schoolName.setText(mydata.get(i).getSenderName());
        myViewHolder.schoolPhoneNumber.setText(mydata.get(i).getSchoolNumber());

        schoolId = mydata.get(i).getSchoolId();



        myViewHolder.schoolList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schoolId = mydata.get(i).getSchoolId();
                Intent i = new Intent(c, OtherSchoolProfileActivity_school.class);
                i.putExtra("school_id",schoolId);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(i);


              //  Toast.makeText(c, "Toast " + String.valueOf(myViewHolder.getAdapterPosition()+schoolId), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mydata.size();
    }


}

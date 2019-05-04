package lintend.pabson.bhaktapur.school.ButtomNavbarActivity.Home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lintend.pabson.bhaktapur.CustomToast.CustomToastSuccess;
import lintend.pabson.bhaktapur.DataModule;
import lintend.pabson.bhaktapur.R;
import lintend.pabson.bhaktapur.school.ButtomNavbarActivity.More.InsideMore.ProfileActivitySchool;

public class HomeActivitySchoolRecylerAdapter extends RecyclerView.Adapter<HomeActivitySchoolRecylerAdapter.MyViewHolder> {


    Context c;
    List<DataModule> mydata;


    public HomeActivitySchoolRecylerAdapter(Context c, List<DataModule> mydata){
        this.c =c;
        this.mydata = mydata;

    }

    @NonNull
    @Override
    public HomeActivitySchoolRecylerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(c);
        View v = inflater.inflate(R.layout.home_post_layout, null);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sendername, datetime, notice;
        CardView cardnameDate, cardNotice;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sendername=itemView.findViewById(R.id.senderName);
            datetime=itemView.findViewById(R.id.dateTIme);
            notice=itemView.findViewById(R.id.notice);
            cardnameDate=itemView.findViewById(R.id.carddetails);
            cardNotice=itemView.findViewById(R.id.cardnotice);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeActivitySchoolRecylerAdapter.MyViewHolder myViewHolder, int i) {

        myViewHolder.notice.setText(mydata.get(i).getNotice());
        myViewHolder.datetime.setText(mydata.get(i).getTime());
        myViewHolder.sendername.setText(mydata.get(i).getNotice());
        myViewHolder.sendername.setText(mydata.get(i).getSenderName());



        myViewHolder.cardnameDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c, ProfileActivitySchool.class);
                c.startActivity(i);
            }
        });
        
        myViewHolder.cardNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String notice = mydata.get(myViewHolder.getAdapterPosition()).getNotice();
               // Toast.makeText(c, notice, Toast.LENGTH_SHORT).show();
                 new CustomToastSuccess(c, notice);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mydata.size();
    }


}

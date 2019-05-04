package lintend.pabson.bhaktapur.CustomToast;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import lintend.pabson.bhaktapur.R;

public class CustomToastSuccess extends  Toast{

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     *
     *
     */
 TextView toasti;
 LinearLayout linearLayout;

 //String cusToast = "this is toast";


    public CustomToastSuccess(Context context , String customToast) {
        super(context);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, -150);
        toast.setDuration(Toast.LENGTH_SHORT);
        //LayoutInflater inflater = getLayoutInflater();
        View converview = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
        linearLayout=converview.findViewById(R.id.linearLayout);
        linearLayout.setBackground(context.getResources().getDrawable(R.drawable.custom_toast_green));
        toasti = converview.findViewById(R.id.customToast);
        toast.setView(converview);
        toasti.setText(customToast);
        toast.show();

    }


    }









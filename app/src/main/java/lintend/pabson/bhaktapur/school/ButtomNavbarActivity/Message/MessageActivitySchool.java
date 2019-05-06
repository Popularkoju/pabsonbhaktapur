package lintend.pabson.bhaktapur.school.ButtomNavbarActivity.Message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

import lintend.pabson.bhaktapur.R;
import lintend.pabson.bhaktapur.SessionManager;

public class MessageActivitySchool extends Fragment {
    SessionManager sm;
    TextView sendername;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_post_layout, container, false);

   sendername=v.findViewById(R.id.senderName);

        sm = new SessionManager(getActivity());
        HashMap<String, String> map = sm.getUserDetails();
        final String userEmail = map.get(SessionManager.KEY_EMAIL);
        final String user_id = map.get(SessionManager.KEY_USER_ID);

        sendername.setText(userEmail + user_id);
        return v;
    }
}
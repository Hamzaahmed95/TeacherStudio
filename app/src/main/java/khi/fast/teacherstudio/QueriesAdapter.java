package khi.fast.teacherstudio;

/**
 * Created by Hamza Ahmed on 23-Dec-17.
 */


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

import android.widget.ProgressBar;
import java.util.ArrayList;
/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class QueriesAdapter extends ArrayAdapter<QueryClass> {

    ArrayList al = new ArrayList();
    ProgressBar mprogressBar;
    public QueriesAdapter(Context context, int resource, List<QueryClass> objects) {
        super(context, resource, objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.list_item_queries, parent, false);
        }
        TextView StudentName=(TextView)convertView.findViewById(R.id.studentName);
        TextView TeacherName=(TextView)convertView.findViewById(R.id.teacherName);
        TextView Questions=(TextView)convertView.findViewById(R.id.questions);
        TextView Answer=(TextView)convertView.findViewById(R.id.answers);

        LinearLayout linearLayout=(LinearLayout)convertView.findViewById(R.id.hide);

        QueryClass message = getItem(position);
        Questions.setText(message.getQuestion());
        Answer.setText(message.getAnswer());
        StudentName.setText("Posted By: Anousha");
        TeacherName.setText("Answered By: Sir Hamza");
        if(message.getStatus().equals("0")){
            linearLayout.setVisibility(View.GONE);
        }
        else linearLayout.setVisibility(View.VISIBLE);




        return convertView;
    }

}
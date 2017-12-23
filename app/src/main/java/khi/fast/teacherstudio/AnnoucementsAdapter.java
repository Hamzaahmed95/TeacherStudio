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
import android.widget.TextView;
import java.util.List;

import android.widget.ProgressBar;
import java.util.ArrayList;
/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class AnnoucementsAdapter extends ArrayAdapter<PostAnnoucmentClass> {

    ArrayList al = new ArrayList();
    ProgressBar mprogressBar;
    public AnnoucementsAdapter(Context context, int resource, List<PostAnnoucmentClass> objects) {
        super(context, resource, objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_annoucement, parent, false);
        }
        TextView Annoucements=(TextView)convertView.findViewById(R.id.Annoucements);
        TextView TeacherName=(TextView)convertView.findViewById(R.id.TeacherName);
        TextView CourseName=(TextView)convertView.findViewById(R.id.CourseName);


         PostAnnoucmentClass message = getItem(position);

        System.out.println("hereadapter:"+message.getAnnoucements());
            Annoucements.setText(message.getAnnoucements());
            TeacherName.setText("Teacher Name: "+message.getTeacherName());
            CourseName.setText("Course Name : "+message.getCourseName());



        return convertView;
    }

}
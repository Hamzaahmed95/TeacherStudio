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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class RescheduleDemoAdapter extends ArrayAdapter<DemoSlotsClass>{

    ArrayList al = new ArrayList();
    Dialog dialog;
    private Context context;
    int pos1;
    private FirebaseDatabase mFirebaseDatabase;
    ProgressBar mprogressBar;
    DemoSlotsClass message;
    private int count=0;
    DemoSlotsClass message2;
    public RescheduleDemoAdapter(Context context, int resource, List<DemoSlotsClass> objects) {
        super(context, resource, objects);
        this.context=context;
        mFirebaseDatabase = FirebaseDatabase.getInstance();

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_reschedule_demo, parent, false);
        }
        final TextView Status=(TextView)convertView.findViewById(R.id.DemoSLots);
        final TextView StatusImage = (TextView)convertView.findViewById(R.id.Course);
        final Button select=(Button)convertView.findViewById(R.id.select);


        System.out.println("here??");
        DemoSlotsClass demoSlotsClass = getItem(position);

        Status.setText("Slot: "+demoSlotsClass.getTimings());
        StatusImage.setText("Course: "+demoSlotsClass.getCourse());

        select.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    showDialog();
            }
        });

        return convertView;
    }

    private void showDialog() {
        // custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.demo_slots_details);

        // set the custom dialog components - text, image and button

        // Close Button

        // Buy Button
        //  System.out.println(leader1+" hahaha");
        Button Close  =(Button) dialog.findViewById(R.id.close2);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}


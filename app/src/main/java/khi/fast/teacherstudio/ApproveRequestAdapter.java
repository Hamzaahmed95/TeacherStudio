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

public class ApproveRequestAdapter extends ArrayAdapter<RegisterTeamClass>{

    ArrayList al = new ArrayList();
    Dialog dialog;
    private Context context;
    int pos1;
    ProgressBar mprogressBar;
    RegisterTeamClass message;
    private FirebaseDatabase mFirebaseDatabase;

    public ApproveRequestAdapter(Context context, int resource, List<RegisterTeamClass> objects) {
        super(context, resource, objects);
        this.context=context;
        mFirebaseDatabase = FirebaseDatabase.getInstance();


    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_approve_request, parent, false);
        }
        TextView name=(TextView)convertView.findViewById(R.id.RequestFrom);
        final Button Approve=(Button)convertView.findViewById(R.id.approve);
        final RegisterTeamClass message=getItem(position);
        name.setText(message.getLeaderName());

        final Button Cancel=(Button)convertView.findViewById(R.id.delete);
        Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("registerTeams");

        mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"


                        if(issue.child("leaderName").getValue().equals(message.getLeaderName())) {

                            if(issue.child("status").getValue().equals("1")) {
                                Approve.setEnabled(false);
                                Approve.setBackgroundDrawable(getContext().getResources().getDrawable(R.color.green));
                                Approve.setText("Approved");
                                Cancel.setVisibility(View.GONE);
                            }
                            else if(issue.child("status").getValue().equals("-1")){
                                Approve.setEnabled(false);
                                Approve.setBackgroundDrawable(getContext().getResources().getDrawable(R.color.green));
                                Approve.setText("Cancelled");
                                Cancel.setVisibility(View.GONE);
                            }
                            else{

                            }
                        }


                    }


                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Approve"+position);
                Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("registerTeams");

                mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                // do something with the individual "issues"


                                if(issue.child("leaderName").getValue().equals(message.getLeaderName())) {
                                    System.out.println("Hi+leaderName" + issue.child("leaderName").getValue().toString());
                                    issue.getRef().child("status").setValue("1");
                                    Approve.setEnabled(false);
                                    Approve.setBackground( getContext().getResources().getDrawable(R.color.green));
                                    Approve.setText("Approved");
                                    Cancel.setVisibility(View.GONE);
                                }


                            }


                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Not Approve"+position);
                Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("registerTeams");

                mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                // do something with the individual "issues"


                                if(issue.child("leaderName").getValue().equals(message.getLeaderName())) {
                                    System.out.println("Hi+leaderName" + issue.child("leaderName").getValue().toString());
                                    issue.getRef().child("status").setValue("-1");
                                    Approve.setEnabled(false);
                                    Approve.setBackgroundDrawable( getContext().getResources().getDrawable(R.color.red) );
                                    Approve.setText("Cancelled");
                                    Cancel.setVisibility(View.GONE);
                                }


                            }


                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });



        return convertView;
    }

    private void showDialog(String leader1,String member1,String member2,String status) {
        // custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.registeration_item_detail);

        // set the custom dialog components - text, image and button

        // Close Button

        // Buy Button
        System.out.println(leader1+" hahaha");
        TextView L=(TextView)dialog.findViewById(R.id.m2);
        TextView Member1=(TextView)dialog.findViewById(R.id.member1);
        TextView Member2=(TextView)dialog.findViewById(R.id.member2);
        TextView Status=(TextView)dialog.findViewById(R.id.Pending);

        L.setText(leader1);
        Member1.setText(member1);
        Member2.setText(member2);
        if(status.equals("0"))
            Status.setText("Pending!");
        else if(status.equals("1")){
            Status.setText("Accepted!");
        }
        else{
            Status.setText("Deleted");
        }
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


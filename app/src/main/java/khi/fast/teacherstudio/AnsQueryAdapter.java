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
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class AnsQueryAdapter extends ArrayAdapter<QueryClass> {

    ArrayList al = new ArrayList();
    ProgressBar mprogressBar;
    QueryClass message;
    private FirebaseDatabase mFirebaseDatabase;


    public AnsQueryAdapter(Context context, int resource, List<QueryClass> objects) {
        super(context, resource, objects);
        mFirebaseDatabase = FirebaseDatabase.getInstance();


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_answer_queries, parent, false);
        }
        TextView StudentName=(TextView)convertView.findViewById(R.id.studentName);
        TextView TeacherName=(TextView)convertView.findViewById(R.id.teacherName);
        TextView Questions=(TextView)convertView.findViewById(R.id.questions);
        TextView Answer=(TextView)convertView.findViewById(R.id.answers);
        final EditText editText=(EditText)convertView.findViewById(R.id.editAns);
        Button question=(Button)convertView.findViewById(R.id.AskQuestion);


        LinearLayout linearLayout=(LinearLayout)convertView.findViewById(R.id.hide);

        message = getItem(position);
        Questions.setText(message.getQuestion());
        Answer.setText(message.getAnswer());
        StudentName.setText("Posted by: Anousha");
        TeacherName.setText("Answered by Sir Hamza");
        if(message.getStatus().equals("0")){
            linearLayout.setVisibility(View.GONE);
        }
        else linearLayout.setVisibility(View.VISIBLE);

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Approve"+position);
                Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("askQueries");

                mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                // do something with the individual "issues"


                                if(issue.child("studentName").getValue().equals(message.getStudentName())) {
                                  //  System.out.println("Hi+leaderName" + issue.child("leaderName").getValue().toString());
                                    issue.getRef().child("status").setValue("1");
                                    issue.getRef().child("answer").setValue(editText.getText().toString());

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

}
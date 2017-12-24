package khi.fast.teacherstudio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Hamza Ahmed on 23-Dec-17.
 */

public class TeacherDashboard  extends AppCompatActivity {
    Button postAnnoucements;
    Button uploadDemo;
    Button approveRequest;
    Button answerQueries;
    Button viewStudentDetails;
    ImageView closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_dashboard);
        closeButton = (ImageView) findViewById(R.id.logout);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TeacherDashboard.this,TeacherLoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        postAnnoucements=(Button)findViewById(R.id.postAnnoucements);
        postAnnoucements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TeacherDashboard.this,PostAnnoucementsActivity.class);
                startActivity(i);

            }
        });
        uploadDemo=(Button)findViewById(R.id.uploadDemo);
        uploadDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TeacherDashboard.this,UploadDemoActivity.class);
                startActivity(i);

            }
        });
        approveRequest=(Button)findViewById(R.id.approveRequest);
        approveRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TeacherDashboard.this,ApproveRequestActivity.class);
                startActivity(i);

            }
        });
        answerQueries=(Button)findViewById(R.id.answerQueries);
        answerQueries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TeacherDashboard.this,AnswerQueriesActivity.class);
                startActivity(i);

            }
        });
        viewStudentDetails=(Button)findViewById(R.id.viewStudentDetails);
        viewStudentDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TeacherDashboard.this,ViewStudentDetails.class);
                startActivity(i);

            }
        });

    }
    }

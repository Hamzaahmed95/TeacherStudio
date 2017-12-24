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

public class StudentDashboard  extends AppCompatActivity {
    Button viewAnnoucements;
    Button rescheduleDemo;
    Button registerTeams;
    Button postQueries;
    Button DemoSlots;
    ImageView closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_dashboard);
        closeButton = (ImageView) findViewById(R.id.logout);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentDashboard.this,StudentLoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        viewAnnoucements=(Button)findViewById(R.id.viewAnnoucements);
        viewAnnoucements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentDashboard.this,ViewAnnoucementsActivity.class);
                startActivity(i);

            }
        });
        rescheduleDemo=(Button)findViewById(R.id.rescheduleDemo);
        rescheduleDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentDashboard.this,RescheduleDemo.class);
                startActivity(i);

            }
        });
        registerTeams=(Button)findViewById(R.id.registerTeams);
        registerTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentDashboard.this,RegisterTeamsActivity.class);
                startActivity(i);

            }
        });
        postQueries=(Button)findViewById(R.id.postQueries);
        postQueries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentDashboard.this,PostQueriesActivity.class);
                startActivity(i);

            }
        });
        DemoSlots=(Button)findViewById(R.id.demoSlots);
        DemoSlots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentDashboard.this,DemoSlotsActivity.class);
                startActivity(i);

            }
        });
    }
    }

package khi.fast.teacherstudio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Hamza Ahmed on 23-Dec-17.
 */

public class StudentLoginActivity extends AppCompatActivity {

    EditText id;
    EditText pwd;
    Button send;

    TextView signInasStudent;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login);
        id=(EditText)findViewById(R.id.studentId);
        pwd=(EditText)findViewById(R.id.studentPwd);
        send=(Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id.getText().toString().equals("Student") && pwd.getText().toString().equals("student")){
                    Intent i = new Intent(StudentLoginActivity.this,StudentDashboard.class);
                    startActivity(i);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                }
                else {
                    Toast.makeText(StudentLoginActivity.this,"Enter Correct ID or Password!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        signInasStudent=(TextView)findViewById(R.id.signInasTeacher);
        signInasStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentLoginActivity  .this,TeacherLoginActivity.class);
                startActivity(i);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });



    }
}

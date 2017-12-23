package khi.fast.teacherstudio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Hamza Ahmed on 23-Dec-17.
 */

public class AnswerQueriesActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_queries);
        Toast.makeText(AnswerQueriesActivity.this,"AnswerQueriesActivity Opens! ",Toast.LENGTH_SHORT).show();

    }
}

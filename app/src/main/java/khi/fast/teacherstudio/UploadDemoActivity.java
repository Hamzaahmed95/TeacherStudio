package khi.fast.teacherstudio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Hamza Ahmed on 23-Dec-17.
 */

public class UploadDemoActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_demo);
        Toast.makeText(UploadDemoActivity.this,"UploadDemoActivity Opens! ",Toast.LENGTH_SHORT).show();

    }
}

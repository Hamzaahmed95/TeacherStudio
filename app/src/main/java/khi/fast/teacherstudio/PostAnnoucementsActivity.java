package khi.fast.teacherstudio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Hamza Ahmed on 23-Dec-17.
 */

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamza Ahmed on 19-Jul-17.
 */

public class PostAnnoucementsActivity extends AppCompatActivity {

    private static final String TAG = "MOM";

    public static final String ANONYMOUS = "anonymous";

    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    public static final int RC_SIGN_IN =1;

    private ListView mmessageListViewMOM;


    private ImageView closeButton;

    private AnnoucementsAdapter mAnnoucementsAdapter;

    private ProgressBar mProgressBar;


    private EditText mmessageEditTextMOM;

    private android.widget.Button mSendButton;
    public String NAME;
    public String x="";
    private static final int RC_PHOTO_PICKER =  2;

    private String mUsername;
    private ImageView Button;
    private List<String> notes;
    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference mMessageDatabaseReference;

    private ChildEventListener mChildEventListener;

    private FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListner;


    @Override
    protected void onCreate( final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_annoucements);

        NAME=ANONYMOUS;
        notes = new ArrayList<String>();
        mUsername = ANONYMOUS;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();

        closeButton = (ImageView) findViewById(R.id.backButtonMOM);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PostAnnoucementsActivity.this,TeacherDashboard.class);
                startActivity(i);
                finish();
            }
        });
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("postAnnoucements");
        Log.d("oncreate ",mMessageDatabaseReference.getDatabase().toString());



        // Initialize references to views
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mmessageListViewMOM = (ListView) findViewById(R.id.messageListViewMOM);
        mmessageEditTextMOM = (EditText) findViewById(R.id.messageEditTextMOM);
        mSendButton = (Button) findViewById(R.id.sendButtonMOM);

        // Initialize message ListView and its adapter

        // Initialize progress bar


        // ImagePickerButton shows an image picker to upload a image for a message


        // Enable Send button when there's text to send
        if(mmessageEditTextMOM!=null) {
            mmessageEditTextMOM.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().trim().length() > 0) {
                        mSendButton.setEnabled(true);
                        mSendButton.setBackgroundDrawable( getResources().getDrawable(R.color.green) );
                    } else {
                        mSendButton.setEnabled(false);
                        mSendButton.setBackgroundDrawable( getResources().getDrawable(R.color.red) );
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }

            });
            mmessageEditTextMOM.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        }
        // Send button sends a message and clears the EditText
        if(mSendButton!=null)
            mSendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String Annoucement= mmessageEditTextMOM.getText().toString();

                    // TODO: Send messages on click
                    PostAnnoucmentClass PostAnnoucmentClass = new PostAnnoucmentClass(Annoucement,"Anousha Ali","Mathematics");

                    // Clear input box
                    mMessageDatabaseReference.push().setValue(PostAnnoucmentClass);
                    mmessageEditTextMOM.setText("");


                }
            });


        Bundle extra =getIntent().getExtras();
        if(extra!=null) {
            String url2 = extra.getString("username");
            Log.d("hamza: ",url2);
            if(!url2.equals("K142805 Hamza Ahmed")){
                Log.d(TAG, ""+url2);
                mSendButton.setVisibility(View.GONE);
                mmessageEditTextMOM.setVisibility(View.GONE);
            }
        }


        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                onSignedInInitialize("");
                final List<PostAnnoucmentClass> momclasses = new ArrayList<>();
                mAnnoucementsAdapter = new AnnoucementsAdapter(PostAnnoucementsActivity.this, R.layout.item_annoucement, momclasses);
                System.out.println("here => "+mAnnoucementsAdapter);
                if(mmessageListViewMOM!=null)
                    mmessageListViewMOM.setAdapter(mAnnoucementsAdapter);

           /*     if(user!=null){
                    //user is signed in



                }else{
                    //user is signed out
                  //  onSignedOutInitialize();

                    /*startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setTheme(R.style.FirebaseLoginTheme)
                                    .setLogo(R.drawable.wb5)
                                    .setProviders(
                                            AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER
                                    ).build(),
                            RC_SIGN_IN);




                }
                */
            };
        };


    }


 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    @Override
    protected void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
        mAnnoucementsAdapter.clear();
    }


    @Override
    protected void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

    private void  onSignedInInitialize(String username){
        mUsername = username;
        attachDatabaseReadListener();

    }
    private void  onSignedOutInitialize(){
        mUsername = ANONYMOUS;
        mAnnoucementsAdapter.clear();

        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    PostAnnoucmentClass momclass = dataSnapshot.getValue(PostAnnoucmentClass.class);
                    if(momclass!=null)
                        mAnnoucementsAdapter.add(momclass);
                    mProgressBar.setVisibility(View.GONE);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    PostAnnoucmentClass f =dataSnapshot.getValue(PostAnnoucmentClass.class);

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            mMessageDatabaseReference.addChildEventListener(mChildEventListener);
            mMessageDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                        PostAnnoucmentClass mom =noteDataSnapshot.getValue(PostAnnoucmentClass.class);
                        String pictures[] = new String[100];
                        //  Log.d("mom ",""+mom.getPICTURE());

                    }
                    for (String s:notes){
                        Log.d("hamza1234",s);

                    }





                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null)
            mMessageDatabaseReference.removeEventListener(mChildEventListener);
        mChildEventListener=null;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
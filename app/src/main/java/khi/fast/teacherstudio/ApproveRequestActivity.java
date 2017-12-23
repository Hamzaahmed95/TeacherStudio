package khi.fast.teacherstudio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamza Ahmed on 23-Dec-17.
 */

public class ApproveRequestActivity  extends AppCompatActivity {

    private ImageView closeButton;

        private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference mMessageDatabaseReference;
    private ListView mmessageListViewMOM;

    private ChildEventListener mChildEventListener;
    private ApproveRequestAdapter ApproveRequestAdapter;
    private FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approve_request);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mmessageListViewMOM = (ListView) findViewById(R.id.messageListViewMOM);

        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("registerTeams");
        closeButton = (ImageView) findViewById(R.id.backButtonMOM);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ApproveRequestActivity.this,TeacherDashboard.class);

                startActivity(i);
                finish();
            }
        });
        Toast.makeText(ApproveRequestActivity.this,"ApproveRequestActivity Opens! ",Toast.LENGTH_SHORT).show();

            mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                onSignedInInitialize("");
                final List<RegisterTeamClass> momclasses = new ArrayList<>();
                ApproveRequestAdapter = new ApproveRequestAdapter(ApproveRequestActivity.this, R.layout.item_annoucement, momclasses);
                System.out.println("here => "+ApproveRequestAdapter);
                if(mmessageListViewMOM!=null)
                    mmessageListViewMOM.setAdapter(ApproveRequestAdapter);


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
        mmessageListViewMOM.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                System.out.println("hamza is this click?");
                Toast.makeText(ApproveRequestActivity.this, position, Toast.LENGTH_SHORT).show();
            }
        });

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
        ApproveRequestAdapter.clear();
    }


    @Override
    protected void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

    private void  onSignedInInitialize(String username){
        attachDatabaseReadListener();

    }
    private void  onSignedOutInitialize(){
        ApproveRequestAdapter.clear();

        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    RegisterTeamClass momclass = dataSnapshot.getValue(RegisterTeamClass.class);
                    if(momclass!=null)

                       // textHide.setVisibility(View.GONE);
                    ApproveRequestAdapter.add(momclass);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    RegisterTeamClass f =dataSnapshot.getValue(RegisterTeamClass.class);

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

                        //  Log.d("mom ",""+mom.getPICTURE());

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
}

package khi.fast.teacherstudio;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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

public class UploadDemoActivity  extends AppCompatActivity {

    ImageView closeButton;
    ImageButton Add;
    Dialog dialog;
    private FirebaseDatabase mFirebaseDatabase;

    private UploadDemoAdapter UploadDemoAdapter;
    private DatabaseReference mMessageDatabaseReference;

    private ChildEventListener mChildEventListener;

    private ListView mmessageListViewMOM;
    private FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_demo);
        Toast.makeText(UploadDemoActivity.this,"UploadDemoActivity Opens! ",Toast.LENGTH_SHORT).show();
        closeButton = (ImageView) findViewById(R.id.backButtonMOM);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UploadDemoActivity.this,TeacherDashboard.class);

                startActivity(i);
                finish();
            }
        });
        mmessageListViewMOM = (ListView) findViewById(R.id.messageListViewMOM);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("uploadDemoSlots");




        Add=(ImageButton) findViewById(R.id.uploadDemo);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                onSignedInInitialize("");
                final List<DemoSlotsClass> momclasses = new ArrayList<>();
                UploadDemoAdapter = new UploadDemoAdapter(UploadDemoActivity.this, R.layout.item_upload_demo, momclasses);
                System.out.println("here => "+UploadDemoAdapter);
                if(mmessageListViewMOM!=null)
                    mmessageListViewMOM.setAdapter(UploadDemoAdapter);


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
        UploadDemoAdapter.clear();
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
        UploadDemoAdapter.clear();

        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    DemoSlotsClass momclass = dataSnapshot.getValue(DemoSlotsClass.class);
                    if(momclass!=null)

                       UploadDemoAdapter.add(momclass);

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

    private void showDialog() {
        // custom dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_demo_teacher);

        // set the custom dialog components - text, image and button

        // Close Button

        // Buy Button

        final EditText slotTimings =(EditText) dialog.findViewById(R.id.slotTimings);
        final EditText demoSlots =(EditText) dialog.findViewById(R.id.Course);


        dialog.setCanceledOnTouchOutside (false);
        Button Close = (Button) dialog.findViewById(R.id.close1);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String slotTimings2= slotTimings.getText().toString();
                String demoSlots2= demoSlots.getText().toString();


                // TODO: Send messages on click
                DemoSlotsClass DemoSlotsClass = new DemoSlotsClass(slotTimings2,demoSlots2,"","0");
                // Clear input box
                mMessageDatabaseReference.push().setValue(DemoSlotsClass);
                slotTimings.setText("");
                demoSlots.setText("");

               /* Intent i = new Intent(PlatinumPlayers.this,SelectedTeams.class);
                i.putExtra("NAME",NAME);
                startActivity(i);
                mTeamDatabaseReference.push().setValue(usersFantacyTeam);
                */

                dialog.dismiss();

            }
        });
        Button Close1 = (Button) dialog.findViewById(R.id.close2);
        Close1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                //Intent i = new Intent(Sil,GoldPlayers.class);
                //getContext().startActivity(i);

            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

}

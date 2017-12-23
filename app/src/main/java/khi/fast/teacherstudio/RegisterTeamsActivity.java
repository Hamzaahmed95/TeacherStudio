package khi.fast.teacherstudio;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class RegisterTeamsActivity extends AppCompatActivity {
    private Dialog dialog;
    ImageButton Add;
    private FirebaseDatabase mFirebaseDatabase;

    private RegisterTeamAdapter registerTeamAdapter;
    private DatabaseReference mMessageDatabaseReference;

    private ChildEventListener mChildEventListener;

    private ListView mmessageListViewMOM;
    private FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private TextView textHide;

    private ImageView closeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_teams);

        mmessageListViewMOM = (ListView) findViewById(R.id.messageListViewMOM);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        textHide=(TextView)findViewById(R.id.textHide);
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("registerTeams");
        closeButton = (ImageView) findViewById(R.id.backButtonMOM);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterTeamsActivity.this,StudentDashboard.class);
                startActivity(i);
                finish();
            }
        });
        Add=(ImageButton) findViewById(R.id.UserTeam);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                onSignedInInitialize("");
                final List<RegisterTeamClass> momclasses = new ArrayList<>();
                registerTeamAdapter = new RegisterTeamAdapter(RegisterTeamsActivity.this, R.layout.item_annoucement, momclasses);
                System.out.println("here => "+registerTeamAdapter);
                if(mmessageListViewMOM!=null)
                    mmessageListViewMOM.setAdapter(registerTeamAdapter);


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
                Toast.makeText(RegisterTeamsActivity.this, position, Toast.LENGTH_SHORT).show();
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
        registerTeamAdapter.clear();
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
        registerTeamAdapter.clear();

        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    RegisterTeamClass momclass = dataSnapshot.getValue(RegisterTeamClass.class);
                    if(momclass!=null)

                        textHide.setVisibility(View.GONE);
                        registerTeamAdapter.add(momclass);
            
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
        dialog.setContentView(R.layout.pop_up_teams);

        // set the custom dialog components - text, image and button

        // Close Button

        // Buy Button

        final EditText leaderName =(EditText) dialog.findViewById(R.id.leaderName);
        final EditText member1 =(EditText) dialog.findViewById(R.id.member1);
        final EditText member2 =(EditText) dialog.findViewById(R.id.member2);


        dialog.setCanceledOnTouchOutside (false);
        Button Close = (Button) dialog.findViewById(R.id.close1);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String LeaderName= leaderName.getText().toString();
                String Member1= member1.getText().toString();
                String Member2= member2.getText().toString();

                // TODO: Send messages on click
                RegisterTeamClass registerTeamClass = new RegisterTeamClass(LeaderName,Member1,Member2,"0");
                // Clear input box
                mMessageDatabaseReference.push().setValue(registerTeamClass);
                leaderName.setText("");
                member1.setText("");
                member2.setText("");
                textHide.setVisibility(view.GONE);

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

package com.example.daredevil07.ItsShowTime;




import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;






    public class Cust_Sign_In extends AppCompatActivity {

//    SQLiteDatabase db;

        private EditText u;
        private EditText p;
        private static final int RC_SIGN_IN = 234;
        private static final String TAG = "ItsShowTime";
        GoogleSignInClient mGoogleSignInClient;
        FirebaseAuth mAuth;

        private ProgressBar progressBar;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


//        if (auth.getCurrentUser() != null) {
//            startActivity(new Intent(Cust_Sign_In.this, Cust_Sign_In.class));
//            finish();
//        }

            setContentView(R.layout.cust__sign__in);

            mAuth = FirebaseAuth.getInstance();

            u = findViewById(R.id.uninp);
            p = findViewById(R.id.pwdinp);

            // Creating database and table  
//        db = openOrCreateDatabase("MOVIES", Context.MODE_PRIVATE, null);
// db.execSQL("CREATE TABLE IF NOT EXISTS USERS(name VARCHAR,email VARCHAR,mobile VARCHAR,uname VARCHAR,pwd VARCHAR);");
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signIn();
                }
            });
            progressBar  = findViewById(R.id.progressBar);


            Button si = findViewById(R.id.login);
            si.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressBar.setVisibility(View.VISIBLE);
                    Login_user(u.getText().toString(),p.getText().toString());
                }

            });

            TextView f = findViewById(R.id.forgot);
            f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    forgot();
                }
            });

            Button s = findViewById(R.id.signup);
            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signup();
                }
            });

        }


        public void Login_user(String username,String password)
        {
            try
            {
                if(username.equals("") || password.equals("")){
                    Toast.makeText(this,"Please Enter all Details",Toast.LENGTH_SHORT).show();
                }
                else{

                    login(username,password);
//                    int i = 0;
//                    Cursor c = null;
//                    c = db.rawQuery("select * from USERS where uname =" + "\""+ username.trim() + "\""+" and pwd="+ "\""+ password.trim() + "\"", null);
//                    c.moveToFirst();
//                    i = c.getCount();
//                    c.close();
//
//                    if(i >= 1 ){
//                        Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show();
//                        Intent qwe = new Intent(this, Home_Activity.class);
//                        startActivity(qwe);
//                    }
//                    else{
//                        Toast.makeText(this,"Invalid Login Details",Toast.LENGTH_SHORT).show();
//                    }
                }

//            return 0;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
//        return 1;
        }

        private void login(String un,String pd) {
//        String un = u.getText().toString();
//        final String pd = p.getText().toString();

            progressBar.setVisibility(View.VISIBLE);

            //authenticate user
            mAuth.signInWithEmailAndPassword(un, pd)
                    .addOnCompleteListener(Cust_Sign_In.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
//                        progressBar.setVisibility(View.GONE);
                            if (!task.isSuccessful()) {
                                // there was an error
//                            if (pd.length() < 6) {
//                                p.setError(getString(R.string.pwd));
//                            } else {
                                FirebaseUser user = mAuth.getCurrentUser();

                                Toast.makeText(Cust_Sign_In.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
//                            }
                            } else {
                                Intent intent = new Intent(Cust_Sign_In.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });




        }
        protected void onStart() {
            super.onStart();

            //if the user is already signed in
            //we will close this activity
            //and take the user to profile activity
            if (mAuth.getCurrentUser() != null) {
                finish();
                startActivity(new Intent(this, MainActivity.class));
            }
        }


        public void signup() {
            Intent qwe = new Intent(this, Sign_Up_Cust.class);
            startActivity(qwe);
        }

        public void forgot() {
            Intent qwe = new Intent(this, MainActivity.class);
            startActivity(qwe);
        }
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            //if the requestCode is the Google Sign In code that we defined at starting
            if (requestCode == RC_SIGN_IN) {

                //Getting the GoogleSignIn Task
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    //Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = task.getResult(ApiException.class);

                    //authenticating with firebase
                    firebaseAuthWithGoogle(account);
                } catch (ApiException e) {
                    Toast.makeText(Cust_Sign_In.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
            Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

            //getting the auth credential
            AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

            //Now using firebase we are signing in the user here
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithCredential:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                Toast.makeText(Cust_Sign_In.this, "User Signed In", Toast.LENGTH_SHORT).show();
                                Intent signin= new Intent(Cust_Sign_In.this,MainActivity.class);
                                startActivity(signin);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithCredential:failure", task.getException());
                                Toast.makeText(Cust_Sign_In.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });
        }


        //this method is called on click
        private void signIn() {
            //getting the google signin intent
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();

            //starting the activity for result
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    }


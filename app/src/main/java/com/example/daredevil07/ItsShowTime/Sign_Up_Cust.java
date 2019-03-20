package com.example.daredevil07.ItsShowTime;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Sign_Up_Cust extends AppCompatActivity implements View.OnClickListener{

    public EditText name, email, pwd, mob;
    Button login,clear;
//    SQLiteDatabase db;

    public static final String TAG = "";
    private FirebaseAuth mAuth;

//    private final FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign__up__cust);

        name = findViewById(R.id.ninp);
        email = findViewById(R.id.emailinp);
        pwd = findViewById(R.id.pwdinp);
        mob = findViewById(R.id.mobinp);
        login = findViewById(R.id.login);
        clear = findViewById(R.id.clear);

        login.setOnClickListener(this);
        clear.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
// Get a reference to our posts
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = database.getReference("users/dam/Name");
//
//// Attach a listener to read the data at our posts reference
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.e("HELLOWORLD10", dataSnapshot.getValue().toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//        });


        // Creating database and table  
//        db = openOrCreateDatabase("MOVIES", Context.MODE_PRIVATE, null);
//        db.execSQL("CREATE TABLE IF NOT EXISTS USERS(name VARCHAR,email VARCHAR,mobile VARCHAR,uname VARCHAR,pwd VARCHAR);");
    }

    public void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View view) {

        if (view == login) {
            // Checking empty fields
            String q = name.getText().toString();

            String w = email.getText().toString();

            String e = pwd.getText().toString();

            String t = mob.getText().toString();


            if (q.length() == 0) {
                name.requestFocus();
                name.setError("Field Can't be Empty");
            } else if (w.length() == 0) {
                email.requestFocus();
                email.setError("Field Can't be Empty");
            } else if (e.length() == 0) {
                pwd.requestFocus();
                pwd.setError("Field Can't be Empty");
            } else if (t.length() == 0) {
                mob.requestFocus();
                mob.setError("Field Can't be Empty");
            } else if (!w.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                email.requestFocus();
                email.setError("Please enter in Alphabetical Order");
            } else if (!e.matches("[a-zA-Z0-9\\!\\@\\#\\$]{8,24}")) {
                pwd.requestFocus();
                pwd.setError("Pass word must have alphabets, numbers and some special characters");
            } else {
                Toast.makeText(this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                name.requestFocus();

                insert_firebase();
//                insert_firestore();

//                db.execSQL("INSERT INTO USERS VALUES('" + q+ "','"  +w + "','" + t+ "','"+ r +  "','" + e+"');");

                name.setText("");
                email.setText("");
                pwd.setText("");
                mob.setText("");
            }

        }
        else if(view == clear){

            name.setText("");
            email.setText("");
            pwd.setText("");
            mob.setText("");

            Toast.makeText(this,"Cleared",Toast.LENGTH_SHORT).show();
            name.requestFocus();

        }


    }

    public void insert_firebase(){
        String u = email.getText().toString().trim();
        String p = pwd.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(u, p)
                .addOnCompleteListener(Sign_Up_Cust.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Sign_Up_Cust.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
//                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(Sign_Up_Cust.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(Sign_Up_Cust.this, Cust_Sign_In.class));
                            finish();
                        }
                    }
                });


        String q = name.getText().toString();

        String w = email.getText().toString();

        String e = pwd.getText().toString();

        String t = mob.getText().toString();

        String mail= w.substring(0, w.indexOf("@"));

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
        Log.e("HELLOWORLD",myRef.toString());

        Log.e("HELLOWORLD1",myRef.toString());


        String userId = myRef.push().getKey();
        User us = new User(q, w,t,e);

        //myRef.child(userId).child(w).setValue("");
        myRef.child(mail).child("Name").setValue(q); //name
        myRef.child(mail).child("Mobile").setValue(t); //mobile
    }

    public class User {

        String name,email,pwd,mobile;
        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String name, String email,String mob, String pwd) {
            this.name = name;
            this.email = email;
            this.mobile = mob;
            this.pwd = pwd;
        }

    }


//    private void insert_firestore() {
//
//        // Access a Cloud Firestore instance from your Activity
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        // Create a new user with a first and last name
//        Map<String, Object> user = new HashMap<>();
//        user.put("name", name);
//        user.put("email", email);
//        user.put("pwd", pwd);
//        user.put("mobile", mob);
//
//// Add a new document with a generated ID
//        db.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
////                        Toast.makeText(this,"DocumentSnapshot added with ID: " + documentReference.getId(),Toast.LENGTH_SHORT).show();
////                        Log.e(Tag,"DocumentSnapshot added with ID: " + documentReference.getId());
////                        Log.e(Tag,"DocumentSnapshot added with ID: " + documentReference.getId());
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });
//    }

}


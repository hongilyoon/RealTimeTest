package com.example.hiyoon.realtimetest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.hiyoon.realtimetest.model.Client;
import com.example.hiyoon.realtimetest.model.Manager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // insertManager
        insertManager(new Manager("KAKAO", "가나1", "01000000000", "M12345"));

        // insertClient
        insertClient(new Client("01011111111", "C12345"), "3");
    }

    public void insertManager(final Manager manager) {
        FirebaseDatabase.getInstance().getReference("manager").orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    FirebaseDatabase.getInstance().getReference("manager").child(String.valueOf(Integer.valueOf(snapshot.getKey()) + 1)).setValue(manager);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("MainActivity", databaseError.toString());
            }
        });
    }

    public void insertClient(final Client client, final String managerKey) {
        FirebaseDatabase.getInstance().getReference("client").orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    FirebaseDatabase.getInstance().getReference("client").child(String.valueOf(Integer.valueOf(snapshot.getKey()) + 1)).setValue(client)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Log.d("MainActivity", task.toString());
                                }
                            });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("MainActivity", databaseError.toString());
            }
        });
    }

    public void readData() {
        Toast.makeText(this.getApplicationContext(), "############ insert data. started!!", Toast.LENGTH_LONG);
        FirebaseDatabase.getInstance().getReference("client/1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.d("MainActivity", "Single ValueEventListener : " + snapshot.getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("MainActivity", databaseError.toString());
            }
        });
    }
}

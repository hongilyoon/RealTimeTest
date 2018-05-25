package com.example.hiyoon.realtimetest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.hiyoon.realtimetest.model.Client;
import com.example.hiyoon.realtimetest.model.Manager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // insertManager
//        this.insertManager(new Manager("KAKAO", "가나1", "01000000000", "M12345", "K04"));

//        // insertClient
//        this.insertClient(new Client("010444444", "C12345"), "4");

//        // getManagerData
//        this.getManagerDataByLoginTypeAndId("KAKAO_K04");

        // mappingManagerClient
//        this.mappingManagerClient("3", "4", true);
    }

    /**
     * 관리자를 등록합니다.
     * 등록하기 전에 기존 값이 존재하는지 확인필요?
     * @param manager
     */
    public void insertManager(final Manager manager) {

        // TODO 같은 정보의 관리자가 존재하는지 검색합니다.

        // 관리자 max seq를 검색 후에 seq + 1로 등록합니다.
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

    /**
     * 고객을 등록합니다.
     * 등록하기 전에 기존 값이 존재하는지 확인필요?
     * @param client
     * @param managerKey
     */
    public void insertClient(final Client client, final String managerKey) {

        // TODO 같은 정보의 고객이 존재하는지 검색합니다.

        // 고객 max seq를 검색 후에 seq + 1로 등록합니다.
        FirebaseDatabase.getInstance().getReference("client").orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String clientKey = String.valueOf(Integer.valueOf(snapshot.getKey()) + 1);
                    FirebaseDatabase.getInstance().getReference("client").child(clientKey).setValue(client)
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

    /**
     * 로그인 타입과 아이디로 검색합니다.
     * @param loginTypeAndId
     */
    public void getManagerDataByLoginTypeAndId(String loginTypeAndId) {

        // 로그인 타입과 아이디는 중첩 검색을 지원하지 않기 때문에 등록 시 생성한 loginTypeAndId의 값을 조회합니다.
        FirebaseDatabase.getInstance().getReference("manager")
                .orderByChild("loginTypeAndId")
                .equalTo(loginTypeAndId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // TODO 해당 아이디에 해당하는 값이 여러명 검색된다면??
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            Manager manager = snapshot.getValue(Manager.class);
                            Log.d("MainActivity", manager.toString());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("MainActivity", databaseError.toString());
                    }
                });
    }

    /**
     * 관리자와 고객을 매핑합니다.
     * @param managerKey
     * @param clientKey
     */
    public void mappingManagerClient(String managerKey, String clientKey, Boolean isMapping) {
        FirebaseDatabase.getInstance().getReference("mapping_manager_client").child(managerKey).child(clientKey).setValue(isMapping);
    }
}

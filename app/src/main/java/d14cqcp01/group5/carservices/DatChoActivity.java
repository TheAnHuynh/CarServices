package d14cqcp01.group5.carservices;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DatChoActivity extends AppCompatActivity {

    private final static String TAG = DatChoActivity.class.getSimpleName();
    private Button btnDatCho;
    private String currentCarID;
    private String currentCarType;
    private DatabaseReference coachTicketListRef;
    private DatabaseReference ticketListRef;
    private DatabaseReference currentTicketHistory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datcho);
        btnDatCho = findViewById(R.id.btnDatVe);
       // makeDummyData();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        final FragmentXuLyDatCho fragmentXuLyDatCho = new FragmentXuLyDatCho();
        transaction.add(R.id.fragmetnContainer, fragmentXuLyDatCho);
        transaction.commit();

        coachTicketListRef = FirebaseDatabase.getInstance()
                .getReference("CoachList/" + currentCarID + "/ticketList");
        ticketListRef = FirebaseDatabase.getInstance().getReference(getString(R.string.NODE_TICKET));

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        currentTicketHistory = FirebaseDatabase.getInstance().getReference("UserList");

        coachTicketListRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(DatChoActivity.this,"Đặt vé thành công",
                        Toast.LENGTH_LONG).show();
                String ticketId = dataSnapshot.getValue(String.class);
                Log.d(TAG,"Lưu " + ticketId + "vào lịch sử đặt vé");
                currentTicketHistory.child(user.getUid()).push().setValue(ticketId)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "Lưu lịch sử thành công");
                                Toast.makeText(DatChoActivity.this,"Lưu lịch sử thành công",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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
        });
        ticketListRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String ticketId = dataSnapshot.getKey();
                Log.d(TAG,"New ticket id: " + ticketId);
                VeXe vexe = dataSnapshot.getValue(VeXe.class);
                vexe.setId(ticketId);
                ticketListRef.child(ticketId).setValue(vexe);
                coachTicketListRef.push().setValue(ticketId);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

        });
        btnDatCho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> seats = fragmentXuLyDatCho.getSelectedSeatNumbers();
                final int successed = 0;
                for(String seatNumber: seats){
                    VeXe ve = new VeXe();
                    ve.setIdCoach(currentCarID);
                    ve.setSeatNumber(Integer.parseInt(seatNumber));
                    ve.setOrderTime(System.currentTimeMillis());
                    ve.setStatus("Chờ thanh toán");
                    ve.setId("Waiting");
                    ticketListRef.push().setValue(ve).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(TAG,e.toString());
                            Toast.makeText(DatChoActivity.this,"Đặt vé thất bại",Toast.LENGTH_LONG);
                            Log.e(TAG,e.toString());
                        }
                    });
                }
            }
        });

    }

    private void makeDummyData() {
        Intent intent = getIntent();
        currentCarID = intent.getStringExtra(getString(R.string.currentCarID));
        currentCarType = intent.getStringExtra(getString(R.string.currentCarType));
        if(currentCarID.isEmpty() || currentCarType.isEmpty()){
            Log.e(TAG, "Can't find currentCar");
            return;
        }
        SharedPreferences myCar = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = myCar.edit();
        edt.putString(getString(R.string.currentCarID),"TP HCM-Tiền Giang-05-05-2018 08:00-Hoàng Long");
        edt.putString(getString(R.string.currentCarType),XeKhach.XE_GIUONG_NAM);
        edt.commit();
    }
}

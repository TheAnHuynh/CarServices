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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DatChoActivity extends AppCompatActivity {

    private final static String TAG = DatChoActivity.class.getSimpleName();
    private Button btnDatCho;
    private String currentCarID;
    private String currentCarType;
    private XeKhach currentCar;

    private DatabaseReference ticketListRef; // Tham chiếu đến danh sách chi tiết vé .
    private DatabaseReference coachRef; // Tham chiếu đến danh sách xe khách.
    private DatabaseReference ticketHistoryOfUser; // Tham chiếu đến danh sách vé của người dùng.

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datcho);
        btnDatCho = findViewById(R.id.btnDatVe);

        Intent intent = getIntent();
        currentCarID = intent.getStringExtra(getString(R.string.currentCarID));
        currentCarType = intent.getStringExtra(getString(R.string.currentCarType));
        if(currentCarID.isEmpty() || currentCarType.isEmpty()){
            Log.e(TAG, "Can't find currentCar");
            return;
        }
        Log.d(TAG, "Xe: " + currentCarID + "-------" + currentCarType);

        SharedPreferences myCar = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = myCar.edit();
        edt.putString(getString(R.string.currentCarID),currentCarID);
        edt.putString(getString(R.string.currentCarType),currentCarType);
        edt.commit();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmetnContainer, new FragmentXuLyDatCho());
        transaction.commit();

        coachRef = FirebaseDatabase.getInstance().getReference(getString(R.string.NODE_COACH));
        coachRef.child(currentCarID)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        currentCar = dataSnapshot.getValue(XeKhach.class);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        ticketListRef = FirebaseDatabase.getInstance().getReference(getString(R.string.NODE_TICKET));

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        ticketHistoryOfUser = FirebaseDatabase.getInstance().getReference("UserList/" + user.getUid());

        btnDatCho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> seats = FragmentXuLyDatCho.getSelectedSeatNumbers();
                if(seats.size()>0){
                    for(String seatNumber: seats){
                        VeXe ve = new VeXe();
                        ve.setIdCoach(currentCarID);
                        ve.setSeatNumber(Integer.parseInt(seatNumber));
                        ve.setOrderTime(System.currentTimeMillis());
                        ve.setStatus("Chờ thanh toán");
                        ve.setId("Waiting");
                        String idVe = ticketListRef.push().getKey();
                        ticketListRef.child(idVe).setValue(ve);
                        Log.d(TAG,"Tạo vé thành công: " + idVe);
                        if(currentCar.getTicketList() == null){
                            currentCar.setTicketList(new ArrayList<String>());
                        }
                        currentCar.addTicket(idVe);
                        coachRef.child(currentCarID).setValue(currentCar);
                        ticketHistoryOfUser.child("ticketList").push().setValue(idVe);
                    }
                    Toast.makeText(DatChoActivity.this, "Đặt vé thành công.",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(DatChoActivity.this,SearchActivity.class);
                    startActivity(intent1);
                    finish();
                }else{
                    Toast.makeText(DatChoActivity.this, "Bạn chưa chọn ghế",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1 = new Intent(DatChoActivity.this,SearchActivity.class);
        startActivity(intent1);
        finish();
    }
}
/**
 * String ticketId = dataSnapshot.getKey();
 Log.d(TAG,"New ticket id: " + ticketId);
 VeXe vexe = dataSnapshot.getValue(VeXe.class);
 vexe.setId(ticketId);
 ticketListRef.child(ticketId).child("id").setValue(ticketId);
 coachRef.push().setValue(ticketId);
 Log.d(TAG,"Lưu " + ticketId + "vào lịch sử đặt vé");
 ticketHistoryOfUser.child(user.getUid()).push().setValue(ticketId)
 .addOnSuccessListener(new OnSuccessListener<Void>() {
@Override
public void onSuccess(Void aVoid) {
Log.d(TAG, "Lưu lịch sử thành công");
Toast.makeText(DatChoActivity.this,"Lưu lịch sử thành công",
Toast.LENGTH_LONG).show();
}
});
 *
 */
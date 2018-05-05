package d14cqcp01.group5.carservices;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentXuLyDatCho extends Fragment implements View.OnClickListener{
    private final static String TAG = "FragmentXuLyDatCho";
    private String carId;
    private String carType;
    private DatabaseReference myRef;
//    private XeKhach car;
    private int numOfTicket;

    private ArrayList<Button> arrButton;
    private HashMap<String,Button> currentSelectedSeat;
    private TicketIDMap ticketIdMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SharedPreferences preferences = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        carId = preferences.getString(getString(R.string.currentCarID), "");
        Log.d(TAG,"Car ID:" + carId);
        carType = preferences.getString(getString(R.string.currentCarType), "");
        Log.d(TAG,"Car Type:" + carType);
        View viewSoDoGheXe;
        if(!carType.isEmpty() && !carId.isEmpty()){
            numOfTicket = 0;
            currentSelectedSeat = new HashMap<>();
            DatabaseReference ticketListRef = FirebaseDatabase.getInstance().getReference(getString(R.string.NODE_TICKET));
            arrButton = new ArrayList<>();
            ticketIdMap = new TicketIDMap(new HashMap<String, String>(),arrButton,ticketListRef);
            switch(carType){
                case XeKhach.XE_16_CHO: {
                    viewSoDoGheXe = inflater.inflate(R.layout.layout_ghe_xe_16_cho,container,false);
                    addControlsXe16Cho(viewSoDoGheXe);
                    addEvents();
                    return viewSoDoGheXe;
                }
                case XeKhach.XE_25_CHO:{
                    viewSoDoGheXe = inflater.inflate(R.layout.layout_ghe_xe_25_cho,container,false);
                    addControlsXe24Cho(viewSoDoGheXe);
                    addEvents();
                    return viewSoDoGheXe;
                }
                case XeKhach.XE_GIUONG_NAM:{
                    viewSoDoGheXe = inflater.inflate(R.layout.layout_ghe_xe_giuong_nam,container,false);
                    addControlsXeGiuongNam(viewSoDoGheXe);
                    addEvents();
                    return viewSoDoGheXe;
                }
            }
            myRef = FirebaseDatabase.getInstance().getReference();
            myRef.child(getString(R.string.NODE_COACH)+"/"+ carId+ "/" + "ticketList")
                    .addChildEventListener(new ChildEventListener() {
                @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    for(DataSnapshot ve : dataSnapshot.getChildren()){
                        String maVeXe = ve.getValue(String.class);
                        ticketIdMap.put(ve.getKey(),maVeXe);
                        Log.d(TAG,"Ticket list on child added: " + maVeXe);
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    for(DataSnapshot ve : dataSnapshot.getChildren()){
                        String maVeXe = ve.getValue(String.class);
                        ticketIdMap.put(ve.getKey(),maVeXe);
                        Log.d(TAG,"Ticket list on child changed: " + ve.getKey() + ":" + maVeXe);
                    }
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    for(DataSnapshot ve : dataSnapshot.getChildren()){
                        String maVeXe = ve.getValue(String.class);
                        ticketIdMap.remove(ve.getKey());
                        Log.d(TAG,"Ticket list on child removed: " + ve.getKey() + ":" + maVeXe);
                    }
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        return null;
    }
    private void addControlsXe16Cho(View viewSoDoGheXe) {
        Button ghe01 = viewSoDoGheXe.findViewById(R.id.btnGhe01);
        Button ghe02 = viewSoDoGheXe.findViewById(R.id.btnGhe02);
        Button ghe03 = viewSoDoGheXe.findViewById(R.id.btnGhe03);
        Button ghe04 = viewSoDoGheXe.findViewById(R.id.btnGhe04);
        Button ghe05 = viewSoDoGheXe.findViewById(R.id.btnGhe05);
        Button ghe06 = viewSoDoGheXe.findViewById(R.id.btnGhe06);
        Button ghe07 = viewSoDoGheXe.findViewById(R.id.btnGhe07);
        Button ghe08 = viewSoDoGheXe.findViewById(R.id.btnGhe08);
        Button ghe09 = viewSoDoGheXe.findViewById(R.id.btnGhe09);
        Button ghe10 = viewSoDoGheXe.findViewById(R.id.btnGhe10);
        Button ghe11 = viewSoDoGheXe.findViewById(R.id.btnGhe11);
        Button ghe12 = viewSoDoGheXe.findViewById(R.id.btnGhe12);
        Button ghe13 = viewSoDoGheXe.findViewById(R.id.btnGhe13);
        Button ghe14 = viewSoDoGheXe.findViewById(R.id.btnGhe14);
        Button ghe15 = viewSoDoGheXe.findViewById(R.id.btnGhe15);
        Button ghe16 = viewSoDoGheXe.findViewById(R.id.btnGhe16);
        arrButton.add(ghe01);
        arrButton.add(ghe02);
        arrButton.add(ghe03);
        arrButton.add(ghe04);
        arrButton.add(ghe05);
        arrButton.add(ghe06);
        arrButton.add(ghe07);
        arrButton.add(ghe08);
        arrButton.add(ghe09);
        arrButton.add(ghe10);
        arrButton.add(ghe11);
        arrButton.add(ghe12);
        arrButton.add(ghe13);
        arrButton.add(ghe14);
        arrButton.add(ghe15);
        arrButton.add(ghe16);
    }

    private void addControlsXeGiuongNam(View viewSoDoGheXe) {
        Button ghe01 = viewSoDoGheXe.findViewById(R.id.btnGhe01);
        Button ghe02 = viewSoDoGheXe.findViewById(R.id.btnGhe02);
        Button ghe03 = viewSoDoGheXe.findViewById(R.id.btnGhe03);
        Button ghe04 = viewSoDoGheXe.findViewById(R.id.btnGhe04);
        Button ghe05 = viewSoDoGheXe.findViewById(R.id.btnGhe05);
        Button ghe06 = viewSoDoGheXe.findViewById(R.id.btnGhe06);
        Button ghe07 = viewSoDoGheXe.findViewById(R.id.btnGhe07);
        Button ghe08 = viewSoDoGheXe.findViewById(R.id.btnGhe08);
        Button ghe09 = viewSoDoGheXe.findViewById(R.id.btnGhe09);
        Button ghe10 = viewSoDoGheXe.findViewById(R.id.btnGhe10);
        Button ghe11 = viewSoDoGheXe.findViewById(R.id.btnGhe11);
        Button ghe12 = viewSoDoGheXe.findViewById(R.id.btnGhe12);
        Button ghe13 = viewSoDoGheXe.findViewById(R.id.btnGhe13);
        Button ghe14 = viewSoDoGheXe.findViewById(R.id.btnGhe14);
        Button ghe15 = viewSoDoGheXe.findViewById(R.id.btnGhe15);
        Button ghe16 = viewSoDoGheXe.findViewById(R.id.btnGhe16);
        Button ghe17 = viewSoDoGheXe.findViewById(R.id.btnGhe17);
        Button ghe18 = viewSoDoGheXe.findViewById(R.id.btnGhe18);
        Button ghe19 = viewSoDoGheXe.findViewById(R.id.btnGhe19);
        Button ghe20 = viewSoDoGheXe.findViewById(R.id.btnGhe20);
        Button ghe21 = viewSoDoGheXe.findViewById(R.id.btnGhe21);
        Button ghe22 = viewSoDoGheXe.findViewById(R.id.btnGhe22);
        Button ghe23 = viewSoDoGheXe.findViewById(R.id.btnGhe23);
        Button ghe24 = viewSoDoGheXe.findViewById(R.id.btnGhe24);
        Button ghe25 = viewSoDoGheXe.findViewById(R.id.btnGhe25);
        Button ghe26 = viewSoDoGheXe.findViewById(R.id.btnGhe26);
        Button ghe27 = viewSoDoGheXe.findViewById(R.id.btnGhe27);
        Button ghe28 = viewSoDoGheXe.findViewById(R.id.btnGhe28);
        Button ghe29 = viewSoDoGheXe.findViewById(R.id.btnGhe29);
        Button ghe30 = viewSoDoGheXe.findViewById(R.id.btnGhe30);
        Button ghe31 = viewSoDoGheXe.findViewById(R.id.btnGhe31);
        Button ghe32 = viewSoDoGheXe.findViewById(R.id.btnGhe32);
        Button ghe33 = viewSoDoGheXe.findViewById(R.id.btnGhe33);
        Button ghe34 = viewSoDoGheXe.findViewById(R.id.btnGhe34);
        Button ghe35 = viewSoDoGheXe.findViewById(R.id.btnGhe35);
        Button ghe36 = viewSoDoGheXe.findViewById(R.id.btnGhe36);
        Button ghe37 = viewSoDoGheXe.findViewById(R.id.btnGhe37);
        Button ghe38 = viewSoDoGheXe.findViewById(R.id.btnGhe38);
        Button ghe39 = viewSoDoGheXe.findViewById(R.id.btnGhe39);
        arrButton.add(ghe01);
        arrButton.add(ghe02);
        arrButton.add(ghe03);
        arrButton.add(ghe04);
        arrButton.add(ghe05);
        arrButton.add(ghe06);
        arrButton.add(ghe07);
        arrButton.add(ghe08);
        arrButton.add(ghe09);
        arrButton.add(ghe10);
        arrButton.add(ghe11);
        arrButton.add(ghe12);
        arrButton.add(ghe13);
        arrButton.add(ghe14);
        arrButton.add(ghe15);
        arrButton.add(ghe16);
        arrButton.add(ghe17);
        arrButton.add(ghe18);
        arrButton.add(ghe19);
        arrButton.add(ghe20);
        arrButton.add(ghe21);
        arrButton.add(ghe22);
        arrButton.add(ghe23);
        arrButton.add(ghe24);
        arrButton.add(ghe25);
        arrButton.add(ghe26);
        arrButton.add(ghe27);
        arrButton.add(ghe28);
        arrButton.add(ghe29);
        arrButton.add(ghe30);
        arrButton.add(ghe31);
        arrButton.add(ghe32);
        arrButton.add(ghe33);
        arrButton.add(ghe34);
        arrButton.add(ghe35);
        arrButton.add(ghe36);
        arrButton.add(ghe37);
        arrButton.add(ghe38);
        arrButton.add(ghe39);
    }

    private void addControlsXe24Cho(View viewSoDoGheXe) {
        Button ghe01 = viewSoDoGheXe.findViewById(R.id.btnGhe01);
        Button ghe02 = viewSoDoGheXe.findViewById(R.id.btnGhe02);
        Button ghe03 = viewSoDoGheXe.findViewById(R.id.btnGhe03);
        Button ghe04 = viewSoDoGheXe.findViewById(R.id.btnGhe04);
        Button ghe05 = viewSoDoGheXe.findViewById(R.id.btnGhe05);
        Button ghe06 = viewSoDoGheXe.findViewById(R.id.btnGhe06);
        Button ghe07 = viewSoDoGheXe.findViewById(R.id.btnGhe07);
        Button ghe08 = viewSoDoGheXe.findViewById(R.id.btnGhe08);
        Button ghe09 = viewSoDoGheXe.findViewById(R.id.btnGhe09);
        Button ghe10 = viewSoDoGheXe.findViewById(R.id.btnGhe10);
        Button ghe11 = viewSoDoGheXe.findViewById(R.id.btnGhe11);
        Button ghe12 = viewSoDoGheXe.findViewById(R.id.btnGhe12);
        Button ghe13 = viewSoDoGheXe.findViewById(R.id.btnGhe13);
        Button ghe14 = viewSoDoGheXe.findViewById(R.id.btnGhe14);
        Button ghe15 = viewSoDoGheXe.findViewById(R.id.btnGhe15);
        Button ghe16 = viewSoDoGheXe.findViewById(R.id.btnGhe16);
        Button ghe17 = viewSoDoGheXe.findViewById(R.id.btnGhe17);
        Button ghe18 = viewSoDoGheXe.findViewById(R.id.btnGhe18);
        Button ghe19 = viewSoDoGheXe.findViewById(R.id.btnGhe19);
        Button ghe20 = viewSoDoGheXe.findViewById(R.id.btnGhe20);
        Button ghe21 = viewSoDoGheXe.findViewById(R.id.btnGhe21);
        Button ghe22 = viewSoDoGheXe.findViewById(R.id.btnGhe22);
        Button ghe23 = viewSoDoGheXe.findViewById(R.id.btnGhe23);
        Button ghe24 = viewSoDoGheXe.findViewById(R.id.btnGhe24);
        Button ghe25 = viewSoDoGheXe.findViewById(R.id.btnGhe25);
        arrButton.add(ghe01);
        arrButton.add(ghe02);
        arrButton.add(ghe03);
        arrButton.add(ghe04);
        arrButton.add(ghe05);
        arrButton.add(ghe06);
        arrButton.add(ghe07);
        arrButton.add(ghe08);
        arrButton.add(ghe09);
        arrButton.add(ghe10);
        arrButton.add(ghe11);
        arrButton.add(ghe12);
        arrButton.add(ghe13);
        arrButton.add(ghe14);
        arrButton.add(ghe15);
        arrButton.add(ghe16);
        arrButton.add(ghe17);
        arrButton.add(ghe18);
        arrButton.add(ghe19);
        arrButton.add(ghe20);
        arrButton.add(ghe21);
        arrButton.add(ghe22);
        arrButton.add(ghe23);
        arrButton.add(ghe24);
        arrButton.add(ghe25);
    }

    private void addEvents() {
        for(Button btn : arrButton){
            btn.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Button seat = (Button) view;
        String seatNummber = (String) seat.getText();
//        Toast.makeText(getContext(),seat.getText(), Toast.LENGTH_SHORT).show();
        if(currentSelectedSeat.size() < 7){
            if(currentSelectedSeat.containsKey(seatNummber)){
                currentSelectedSeat.remove(seatNummber);
                seat.setBackground(getActivity().getResources().getDrawable(R.drawable.button_selector_2));
            }else{
                currentSelectedSeat.put(seatNummber,seat);
                seat.setBackground(getActivity().getResources().getDrawable(R.drawable.seat_is_choosed));
            }
        }else{
            Toast.makeText(getContext(),"Bạn chỉ đươc chọn tối đa 6 ghế.", Toast.LENGTH_SHORT).show();
        }
    }
//
//    public List<String> getCurrentSelectedSeat() {
//
//    }
}

package d14cqcp01.group5.carservices;

import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.function.BiFunction;

public class TicketIDMap extends HashMap<String, String> {

    private DatabaseReference ticketListRef;
    private HashMap<String, String> myTicketIDMap;
    private HashMap<String,ValueEventListener> ticketEventMap;
    private HashMap<String, Integer> seatNumberMap;
    private ArrayList<Button> seatList;

    public TicketIDMap(HashMap<String,String> myTicketIDMap, ArrayList<Button> seatList, DatabaseReference ref){
        this.myTicketIDMap = myTicketIDMap;
        this.seatList = seatList;
        this.ticketListRef = ref;
        this.ticketEventMap = new HashMap<>();
        seatNumberMap = new HashMap<>();
    }

    @Override
    public String put(final String key, final String value) {
        ValueEventListener  valueEventListener= new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                VeXe veXe = dataSnapshot.getValue(VeXe.class);
                if (veXe == null) throw new AssertionError();
                seatNumberMap.put(key,veXe.getSeatNumber());
                switch (veXe.getStatus()){
                    case "Đã đặt":
                    case "Đã thanh toán":{
                        seatList.get(seatNumberMap.get(key)).setEnabled(false);
                        break;
                    }
                    default:{
                        seatList.get(veXe.getSeatNumber()-1).setEnabled(true);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        ticketEventMap.put(key,valueEventListener);
        ticketListRef.child(value).addValueEventListener(valueEventListener);
        return myTicketIDMap.put(key,value);
    }

    @Override
    public String remove(Object key) {
        seatList.get(seatNumberMap.get(key)).setEnabled(true);
        ticketListRef.removeEventListener(ticketEventMap.get(key));
        ticketEventMap.remove(key);
        return myTicketIDMap.remove(key);
    }

    @Override
    public String get(Object key) {
        return myTicketIDMap.get(key);
    }

    @Override
    public int size() {
        return myTicketIDMap.size();
    }

    @Override
    public Collection<String> values() {
        return myTicketIDMap.values();
    }

    @Override
    public boolean isEmpty() {
        return myTicketIDMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return myTicketIDMap.containsKey(key);
    }

}

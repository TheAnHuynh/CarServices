package d14cqcp01.group5.carservices;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 05/05/2018.
 */

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ratingbar);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final String company = "Hoàng Long";    //du lieu ao
        final String currentUser = user.getDisplayName();

        final ArrayList<PhanHoi> phanHoiList = new ArrayList<PhanHoi>();
        final DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        final Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        final EditText danhGiaText = (EditText) findViewById(R.id.etFeedback);
        final TextView companyText = (TextView) findViewById(R.id.textView2);
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        companyText.setText(company);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String danhGia =  danhGiaText.getText().toString();
                PhanHoi phanHoi = new PhanHoi(currentUser, danhGia);
                DatabaseReference ref = db.child("CommentList").push();
                ref.setValue(phanHoi);
                String key = ref.getKey();
                db.child("Company").child(company).child("commentList").push().setValue(key);
                danhGiaText.setText("");
//                Toast.makeText(getApplicationContext(),danhGia,
//                        Toast.LENGTH_LONG).show();
            }
        });

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                phanHoiList.clear();
                DataSnapshot cmtList = dataSnapshot.child("Company").child(company).child("commentList");
                for (DataSnapshot postSnapshot: cmtList.getChildren()) {
                    String key = postSnapshot.getValue(String.class);
                    PhanHoi p = dataSnapshot.child("CommentList").child(key).getValue(PhanHoi.class);
                    phanHoiList.add(p);
                }
                Collections.reverse(phanHoiList);
                ListView list = (ListView) findViewById(R.id.listviewPhanHoi);
                DanhGiaApdapter apdapter = new DanhGiaApdapter(CommentActivity.this, R.layout.layout_comment, phanHoiList);
                list.setAdapter(apdapter);
                Toast.makeText(getApplicationContext(),"",
                        Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

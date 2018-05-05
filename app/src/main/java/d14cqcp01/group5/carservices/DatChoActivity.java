package d14cqcp01.group5.carservices;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class DatChoActivity extends AppCompatActivity {

    private final static String TAG = DatChoActivity.class.getSimpleName();
    private Button btnDatCho;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datcho);
        btnDatCho = findViewById(R.id.btnDatVe);
        makeDummyData();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        final FragmentXuLyDatCho fragmentXuLyDatCho = new FragmentXuLyDatCho();
        transaction.add(R.id.fragmetnContainer, fragmentXuLyDatCho);
        transaction.commit();
        btnDatCho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                HashMap selectedSeat = fragmentXuLyDatCho.getCurrentSelectedSeat();

            }
        });

    }

    private void makeDummyData() {
        Intent intent = getIntent();
        String currentCarID = intent.getStringExtra(getString(R.string.currentCarID));
        String currentCarType = intent.getStringExtra(getString(R.string.currentCarType));
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

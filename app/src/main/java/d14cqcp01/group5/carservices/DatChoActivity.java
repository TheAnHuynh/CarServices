package d14cqcp01.group5.carservices;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class DatChoActivity extends AppCompatActivity {

    private Button btnDatCho;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datcho);

        makeDummyData();
    }

    private void makeDummyData() {
        SharedPreferences myCar = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = myCar.edit();
        edt.putString(getString(R.string.currentCarID),"TP HCM-Tiền Giang-02-05-2018 08:00-Hoàng Long");
        edt.putString(getString(R.string.currentCarType),XeKhach.XE_GIUONG_NAM);
        edt.commit();
    }
}

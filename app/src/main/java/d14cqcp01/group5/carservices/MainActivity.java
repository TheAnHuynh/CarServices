package d14cqcp01.group5.carservices;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by An on 3/14/2018.
 */

public class MainActivity extends AppCompatActivity {
    private Button btnBus, btnPassengerCar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lua_chon_phuong_tien);
        addControls();
        addEvents();
    }

    private void addControls() {
        btnBus = findViewById(R.id.btnBus);
        btnPassengerCar = findViewById(R.id.btnPassengerCar);
    }

    private void addEvents() {
        btnBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnPassengerCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent passengerCarManagerIntent = new Intent(MainActivity.this, QuanLiXeKhachActivity.class);
                startActivity(passengerCarManagerIntent);
                finish();
            }
        });
    }


}

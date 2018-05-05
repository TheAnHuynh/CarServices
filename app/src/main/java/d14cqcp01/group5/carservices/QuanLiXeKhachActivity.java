package d14cqcp01.group5.carservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class QuanLiXeKhachActivity extends AppCompatActivity {

    private Button btnbtnSearhPassengerCar;
    private Button btnBookingHistory;
    private Button btnUserInfo;
    private Button btnDangXuat;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quan_li_xe_khach);
        addControls();
        addEvents();

    }

    private void addControls() {
        btnbtnSearhPassengerCar = findViewById(R.id.btnSearhPassengerCar);
        btnBookingHistory = findViewById(R.id.btnBookingHistory);
        btnUserInfo = findViewById(R.id.btnUserInfo);
        btnDangXuat = findViewById(R.id.btnDangXuat);
    }

    private void addEvents() {
        btnbtnSearhPassengerCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển đến tìm kiếm xe khách
                Intent intent = new Intent(QuanLiXeKhachActivity.this, SearchActivity.class);
                startActivity(intent);

            }
        });

        btnBookingHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Chuyển đến xem lịch sử đặt vé ( nếu chưa đăng nhập thì chuyển đến trang đăng nhập.)

            }
        });

        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if(currentUser != null){
                    //TODO:Hiện thông tin người dùng.
                }
                else{
                    //TODO: Chuyển hướng đến giao diện đăng nhập

                }
            }
        });

        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(QuanLiXeKhachActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

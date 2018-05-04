package d14cqcp01.group5.carservices;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
public class GuiLaiPassThongQuaEmail extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private final static String TAG = "GuiLaiPassThongQuaEmail";
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    Button btnDangki;
    EditText email;
    // NguoiDung Nuser = new NguoiDung();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dat_lai_mat_khau);
        mAuth = FirebaseAuth.getInstance();
        AnhXa();
        btnDangki.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ReEmail();
            }
        });
    }
/*
    private void Dangki()
    {
        String Email = email.getText().toString();
        String Pass = pass.getText().toString();
        mAuth.createUserWithEmailAndPassword(Email, Pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });


    }

    private void AnhXa()
    {
        btnDangki = (Button)findViewById(R.id.btnDk);
        hoten = (EditText)findViewById(R.id.txtHovaten);
        email = (EditText)findViewById(R.id.txtEmail);
        pass = (EditText)findViewById(R.id.txtMatkhau);
        repass = (EditText)findViewById(R.id.txtRePass);
        sdt = (EditText)findViewById(R.id.txtSDT);
    }
*/

    private void ReEmail()
    {
        //FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = email.getText().toString().trim();
        if(Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches() == false){
            Log.d(TAG,"Email không đúng định dạng");
            Toast.makeText(GuiLaiPassThongQuaEmail.this,"Email không đúng định dạng.",Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "Email sent.");
                            Toast.makeText(GuiLaiPassThongQuaEmail.this, "Đã Gửi thành công", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(GuiLaiPassThongQuaEmail.this, "THất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void AnhXa()
    {
        btnDangki = (Button)findViewById(R.id.btnSend);
        email = (EditText)findViewById(R.id.txtEmail1);
    }

}

package mulawan.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername,etPassword;
    Button btnlogin;
    TextView createAccount;
    String username, password;
    int formsuccess, userid;

    STIdb db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new STIdb(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnlogin = findViewById(R.id.btnlogin);
        createAccount = findViewById(R.id.createAccount);

        btnlogin.setOnClickListener(this);
        createAccount.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnlogin:
                formsuccess = 2;

                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                if (username.equals("")){
                    etUsername.setError("This Field is required");
                    formsuccess--;
                }

                if (password.equals("")){
                    etPassword.setError("This Field is required");
                    formsuccess--;
                }

                if (formsuccess == 2){
                    userid = db.checkUser(username, password);

                   if(userid >= 1){
                       startActivity(new Intent(this, HomeActivity.class));
                   }
                   else{
                       etUsername.setError("Invalid Login Credentials");
                       etPassword.setError(" ");
                   }
                }
                break;
            case R.id.createAccount:
                Intent i = new Intent(MainActivity.this, Registration.class);
                startActivity(i);
                break;
        }

    }
}

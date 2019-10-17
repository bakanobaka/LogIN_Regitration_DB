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

    EditText lnUsername, lnPassword;
    Button btnlogin;
    TextView createAccount;
    String username, password;
    int formsuccess;

    STIdb db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new STIdb(this);

        lnUsername = findViewById(R.id.lnUsername);
        lnPassword = findViewById(R.id.lnPassword);
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

                username = lnUsername.getText().toString();
                password = lnPassword.getText().toString();

                if (username.equals("")){
                    lnUsername.setError("This Field is required");
                    formsuccess--;
                }

                if (password.equals("")){
                    lnPassword.setError("This Field is required");
                    formsuccess--;
                }

                if (formsuccess == 2){
                   /* HashMap<String, String> map_user = new HashMap();
                    map_user.put(db.TBL_USER_USERNAME, username);
                    map_user.put(db.TBL_USER_PASSWORD, password);

                    if (db.checkUser(map_user)>0){
                        Toast.makeText(this, "User Log-In Existed", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        lnUsername.setError("Invalid Log-in Credentials");
                    }*/
                    Toast.makeText(this, "LOGIN SUCCESSFULL!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.createAccount:
                Intent i = new Intent(MainActivity.this, Registration.class);
                startActivity(i);
                break;
        }

    }
}

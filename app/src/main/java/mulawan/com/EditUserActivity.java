package mulawan.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class EditUserActivity extends AppCompatActivity {

    EditText etUsername, etPassword, etFullname;
    String username, password, fullname;
    int formsuccess, userid;

    STIdb db;

    ArrayList<HashMap<String, String>> selected_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        db = new STIdb(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etFullname = findViewById(R.id.etFullname);

        Intent intent = getIntent();
        userid = intent.getIntExtra(db.TBL_USER_ID, 0);

        selected_user = db.getSelectedUserData(userid);

        etUsername.setText(selected_user.get(0).get(db.TBL_USER_USERNAME));
        etPassword.setText(selected_user.get(0).get(db.TBL_USER_PASSWORD));
        etFullname.setText(selected_user.get(0).get(db.TBL_USER_FULLNAME));

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnSave:
                formsuccess = 3;
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                fullname = etFullname.getText().toString();

                // validate username
                if (username.equals("")) {
                    etUsername.setError("This field is required");
                    formsuccess--;
                }

                // validate password
                if (password.equals("")) {
                    etPassword.setError("This field is required");
                    formsuccess--;
                }

                // validate fullname
                if (fullname.equals("")) {
                    etFullname.setError("This field is required");
                    formsuccess--;
                }

                // validate success
                if (formsuccess == 3) {
                    HashMap<String, String> map_user = new HashMap();
                    map_user.put(db.TBL_USER_USERNAME, username);
                    map_user.put(db.TBL_USER_PASSWORD, password);
                    map_user.put(db.TBL_USER_FULLNAME, fullname);
                    userid = db.createUser(map_user);

                    db.updateUser(map_user);
                    Toast.makeText(this, "USER SUCCESSFULLY UPDATED", Toast.LENGTH_SHORT).show();
                    this.finish();
                }

                break;
            case R.id.btnCancel:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}

package bgv.fit.bstu.lab3stpms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Next(View view)
    {
        EditText name = findViewById(R.id.nameet);
        EditText sname = findViewById(R.id.snameet);
        EditText surname = findViewById(R.id.surnameet);
        User user = new User();
        user.name = name.getText().toString();
        user.sname = sname.getText().toString();
        user.surname = surname.getText().toString();
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }
}
package bgv.fit.bstu.lab3stpms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    List<User> users;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        File myFile = new File(getFilesDir().toString() + "/" + "4Lab.json");
        if(!myFile.exists()) {
            try {
                myFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        users = Read();
        Bundle arguments = getIntent().getExtras();
        if(arguments!=null)
        {
            user = (User) arguments.get("User");
            TextView textView = findViewById(R.id.info);
            textView.setText(user.name+" "+user.sname+" "+user.surname);
        }
    }

    public void Save(View view)
    {
        EditText mail = findViewById(R.id.emailet);
        EditText phone = findViewById(R.id.phoneet);
        EditText link = findViewById(R.id.linket);
        user.mail = mail.getText().toString();
        user.phone = phone.getText().toString();
        user.link = link.getText().toString();
        users.add(user);
        File myFile = new File(getFilesDir().toString() + "/" + "4Lab.json");
        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setUsers(users);
        String jsonString = gson.toJson(dataItems);

        try {
            FileOutputStream outputStream = new FileOutputStream(myFile);
            /*
             * Буфферезируем данные из выходного потока файла
             */
            outputStream.write(jsonString.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }

    public List<User> Read()
    {
        List<User> users = new ArrayList<User>();
        File myFile = new File(getFilesDir().toString() + "/" + "4Lab.json");
        try {
            FileInputStream inputStream = new FileInputStream(myFile);
            /*
             * Буфферезируем данные из выходного потока файла
             */
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            /*
             * Класс для создания строк из последовательностей символов
             */
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            try {
                /*
                 * Производим построчное считывание данных из файла в конструктор строки,
                 * Псоле того, как данные закончились, производим вывод текста в TextView
                 */
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                }
                Gson gson = new Gson();
                DataItems dataItems = gson.fromJson(stringBuilder.toString(), DataItems.class);
                try {
                    if (dataItems.getUsers() != null) {
                        for (User user :
                                dataItems.getUsers()) {
                            users.add(user);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }


}
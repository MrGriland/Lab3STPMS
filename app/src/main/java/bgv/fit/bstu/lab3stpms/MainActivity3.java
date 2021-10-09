package bgv.fit.bstu.lab3stpms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    ArrayList<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = new Intent(this, MainActivity4.class);
        setInitialData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        ViewAdapter.OnStateClickListener stateClickListener = new ViewAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(User user, int position) {

                Toast.makeText(getApplicationContext(), "Был выбран пункт " + user.name,
                        Toast.LENGTH_SHORT).show();
                intent.putExtra("User", user);
                startActivity(intent);
            }
            // создаем адаптер
        };
        ViewAdapter adapter = new ViewAdapter(this, users, stateClickListener);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }

    private void setInitialData() {
        users = Read();

    }

    public ArrayList<User> Read()
    {
        ArrayList<User> users = new ArrayList<User>();
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
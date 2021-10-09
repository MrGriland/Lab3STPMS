package bgv.fit.bstu.lab3stpms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity4 extends AppCompatActivity {
    static final int GALLERY_REQUEST = 1;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Bundle arguments = getIntent().getExtras();
        if(arguments!=null)
        {
            user = (User) arguments.get("User");
            TextView names = findViewById(R.id.vnametv);
            TextView phonet = findViewById(R.id.cphonetv);
            TextView mailt = findViewById(R.id.cmailtv);
            TextView linkt = findViewById(R.id.clinktv);
            names.setText(user.name+" "+user.sname+" "+user.surname);
            phonet.setText(user.phone);
            mailt.setText(user.mail);
            linkt.setText(user.link);
        }
    }

    public void GoPhone(View view)
    {
        Intent phonePickerIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + user.phone));
        startActivity(phonePickerIntent);
    }

    public void GoMail(View view)
    {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",user.mail, null));
        startActivity(emailIntent);
    }

    public void GoLink(View view)
    {
        Intent linkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+user.link));
        startActivity(linkIntent);
    }

    public void GetPic(View view)
    {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;
        ImageView imageView = (ImageView) findViewById(R.id.piciv);

        switch(requestCode) {
            case GALLERY_REQUEST:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageBitmap(bitmap);
                }
        }
    }
}
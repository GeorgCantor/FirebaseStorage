package com.georgcantor.firebasestorage;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    String LOG_TAG = MainActivity.class.getSimpleName();

    Button buttonUpload, buttonDownload;
    RadioGroup radioGroup;
    ImageView imageviewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonUpload = findViewById(R.id.upload_button);
        buttonDownload = findViewById(R.id.download_button);
        radioGroup = findViewById(R.id.radio_group);
        imageviewResult = findViewById(R.id.resultant_imageview);

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void uploadImage() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference rootRef = storage.getReference();
        StorageReference bearRef = rootRef.child("images/tv_banner.jpg");

        ImageView bearImage = getSelectedBearImage();
        bearImage.setDrawingCacheEnabled(true);
        bearImage.buildDrawingCache();
        Bitmap bitmap = bearImage.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

    }

    private ImageView getSelectedBearImage() {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radio1:
                return findViewById(R.id.image_bear1);
            case R.id.radio2:
                return findViewById(R.id.image_bear2);
            case R.id.radio3:
                return findViewById(R.id.image_bear3);
            case R.id.radio4:
                return findViewById(R.id.image_bear4);
            default:
                return findViewById(R.id.image_bear1);
        }
    }
}

package com.example.classwork02;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClickClear(View view) {
        // The original code for getting a view by ID is:
        // ImageView v = findViewById(R.id.iv);

        // We need to use the correct class for the custom view
        MyDrawingArea v = findViewById(R.id.drawingarea);

        // Reset the path of the canvas
        v.resetPath();
    }


    public void onClickSave(View view) {
        // We need to use the correct class for the custom view
        MyDrawingArea v = findViewById(R.id.drawingarea);

        // Save the bitmap of the current image
        Bitmap b = v.getBitmap(); //we wrote this function inside custom view
        try {
            File f = new File(getFilesDir().getAbsolutePath() + "/mysketch.png");
            FileOutputStream fos = new FileOutputStream(f);
            b.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
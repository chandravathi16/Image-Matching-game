package com.example.imagematching;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    ImageView presentView = null;
    int imagePair = 0;
    int[] images = new int[] {
            R.drawable.greenary_switzerland1a,
            R.drawable.teddy_bear1a,
            R.drawable.diarymilk1a,
            R.drawable.grapes1a,
            R.drawable.ice_cream_1a,
            R.drawable.oranges1a,
            R.drawable.ice_cream_2a,
            R.drawable.flower_buke1a
    };
    int[] index = {1,4,0,6,2,3,5,7,1,4,2,0,6,3,5,7};
    int presentPos = -1;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = findViewById(R.id.relativeLayout);

        PictureAdapter pictureAdapter = new PictureAdapter(this);
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(pictureAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (presentPos < 0 ) {
                    presentPos = position;
                    presentView = (ImageView) view;
                    ((ImageView) view).setImageResource(images[index[position]]);
                }
                else {
                    if (presentPos == position) {
                        ((ImageView) view).setImageResource(R.drawable.hidden_img);
                    } else if (index[presentPos] != index[position]) {
                        presentView.setImageResource(R.drawable.hidden_img);
                        Toast.makeText(MainActivity.this, "Not Match!", Toast.LENGTH_LONG).show();
                    } else {
                        ((ImageView) view).setImageResource(images[index[position]]);
                        imagePair++;
                        if (imagePair == 8) {
                            Toast.makeText(MainActivity.this, "You Win!", Toast.LENGTH_LONG).show();
                        }
                    }
                    presentPos = -1;
                }
            }
        });

    }
}
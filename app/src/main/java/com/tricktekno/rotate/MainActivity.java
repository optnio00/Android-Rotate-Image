package com.tricktekno.rotate;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button left10,rooriginal,right10;
    private ImageView logo;
    private int rotateAngle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        left10= (Button) findViewById(R.id.left10);
        rooriginal= (Button) findViewById(R.id.rooriginal);
        right10= (Button) findViewById(R.id.right10);
        logo= (ImageView) findViewById(R.id.logo);

        left10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateAngle -=10;
                rotateAngle %= 360;

                logo.setDrawingCacheEnabled(true);
                Bitmap bitmap = logo.getDrawingCache();
                logo.setImageBitmap(rotate(bitmap));
            }
        });

        rooriginal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateAngle =0;
                rotateAngle %= 360;

                logo.setDrawingCacheEnabled(true);
                Bitmap bitmap = logo.getDrawingCache();
                logo.setImageBitmap(rotate(bitmap));
            }
        });

        right10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateAngle +=10;
                rotateAngle %= 360;
                logo.setDrawingCacheEnabled(true);
                Bitmap bitmap = logo.getDrawingCache();
                logo.setImageBitmap(rotate(bitmap));
            }
        });

    }


    public Bitmap rotate(Bitmap paramBitmap)
    {
        if (rotateAngle% 360 == 0) {
            return paramBitmap;
        }
        Matrix localMatrix = new Matrix();
        float f1 = paramBitmap.getWidth() / 2;
        float f2 = paramBitmap.getHeight() / 2;
        localMatrix.postTranslate(-paramBitmap.getWidth() / 2, -paramBitmap.getHeight() / 2);
        localMatrix.postRotate(rotateAngle);
        localMatrix.postTranslate(f1, f2);
        paramBitmap = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
        new Canvas(paramBitmap).drawBitmap(paramBitmap, 0.0F, 0.0F, null);
        return paramBitmap;
    }

}
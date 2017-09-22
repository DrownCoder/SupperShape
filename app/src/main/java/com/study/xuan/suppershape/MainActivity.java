package com.study.xuan.suppershape;

import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.study.xuan.shapebuilder.shape.ShapeBuilder;
import com.study.xuan.shapebuilder.shape.ShapeListBuilder;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShapeBuilder builder1 = ShapeBuilder.create()
                .Type(RECTANGLE)
                .Soild(Color.BLACK);
        ShapeBuilder builder2 = ShapeBuilder.create()
                .Type(RECTANGLE)
                .Soild(Color.RED);
        Log.i("builder", builder1 == builder2 ? "true" : "false");
        Log.i("drawable", builder1.Soild(Color.BLACK).build() == builder2.Soild(Color.RED).build() ? "true" : "false");
        ShapeListBuilder.create(builder1.build())
                .addShape(builder2.Soild(Color.BLUE).build(), android.R.attr.state_selected)
                .build(findViewById(R.id.tv1));
        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.tv1).setSelected(!findViewById(R.id.tv1).isSelected());
            }
        });

    }
}

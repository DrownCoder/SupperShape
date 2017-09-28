package com.study.xuan.suppershape;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.study.xuan.shapebuilder.shape.LayerBuilder;
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
                .Soild(Color.RED);
        ShapeBuilder builder2 = ShapeBuilder.create()
                .Type(RECTANGLE)
                .Soild(Color.RED);
        ShapeListBuilder.create(builder1.build())
                .addShape(builder2.Soild(Color.BLUE).build(), android.R.attr.state_selected)
                .build(findViewById(R.id.tv1));
        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.tv1).setSelected(!findViewById(R.id.tv1).isSelected());
            }
        });

        LayerBuilder.create(builder1.build(), builder2.build()).Bottom(1, 15).build(findViewById(R
                .id.tv3));

    }
}

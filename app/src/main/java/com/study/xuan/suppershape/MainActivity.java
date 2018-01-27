package com.study.xuan.suppershape;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.study.xuan.shapebuilder.annotation.Shape;
import com.study.xuan.shapebuilder.shape.LayerBuilder;
import com.study.xuan.shapebuilder.shape.ShapeBuilder;
import com.study.xuan.shapebuilder.shape.ShapeListBuilder;

import static android.graphics.drawable.GradientDrawable.OVAL;
import static android.graphics.drawable.GradientDrawable.RECTANGLE;
import static android.graphics.drawable.GradientDrawable.RING;

public class MainActivity extends AppCompatActivity{
    @Shape(RECTANGLE)
    private TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShapeBuilder.create()
                .Type(RECTANGLE)
                .Radius(35)
                .Stroke(15,Color.BLACK)
                .Solid(Color.RED)
                .build(findViewById(R.id.tv1));

        ShapeBuilder builder1 = ShapeBuilder.create()
                .Type(OVAL)
                .Solid(Color.RED);
        ShapeBuilder builder2 = ShapeBuilder.create()
                .Type(RECTANGLE)
                .Solid(Color.RED);
        ShapeListBuilder.create(builder1.build())
                .addShape(builder2.Solid(Color.BLUE).build(), android.R.attr.state_selected)
                .build(findViewById(R.id.tv2));
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.tv2).setSelected(!findViewById(R.id.tv2).isSelected());
            }
        });

        LayerBuilder.create(
                ShapeBuilder.create()
                .Type(RECTANGLE)
                .Solid(Color.BLACK).build(),
                ShapeBuilder.create()
                .Type(RECTANGLE)
                .Solid(Color.RED).build())
                .Bottom(1, 15)
                .build(findViewById(R.id.tv3));
    }
}

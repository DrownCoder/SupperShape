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
        Log.i("drawable", builder1.Soild(Color.BLACK).getDrawable() == builder2.Soild(Color.RED).getDrawable() ? "true" : "false");
       /* ShapeListBuilder.create()
                .addShape(builder1.Soild(Color.YELLOW).getDrawable(), -android.R.attr.state_selected)
                .addShape(builder2.Soild(Color.BLUE).getDrawable(), android.R.attr.state_selected)
                .addShape(builder1.getDrawable(), android.R.attr.state_enabled)
                .build(findViewById(R.id.tv1));*/

        StateListDrawable stateListDrawable = new StateListDrawable();
        // 明确指定状态，当没有选中的时候
        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, builder1.getDrawable());
        stateListDrawable.addState(new int[]{-android.R.attr.state_selected},builder2.getDrawable());


        final TextView tv = (TextView) findViewById(R.id.tv2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            tv.setBackground(stateListDrawable);
            //tv.setBackgroundResource(R.drawable.aa);
        }
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setSelected(!tv.isSelected());
            }
        });
    }
}

package com.study.xuan.suppershape;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.study.xuan.easywidget.EasyTextView;
import com.study.xuan.shapebuilder.annotation.Shape;
import com.study.xuan.shapebuilder.shape.LayerBuilder;
import com.study.xuan.shapebuilder.shape.ShapeBuilder;
import com.study.xuan.shapebuilder.shape.ShapeListBuilder;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

public class MainActivity extends AppCompatActivity{
    @Shape(RECTANGLE)
    private TextView mTv;
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
        /*TextView ic = (TextView) findViewById(R.id.easyText);
        Log.i("TAG", "---" + ic.getText().toString() + "----");
        ic.append("300");

        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor
                ("#11FFFC"));
        SpannableString spannableString = new SpannableString("+++"+ic.getText());
        spannableString.setSpan(foregroundColorSpan, 0, 1, 1);
        ic.setText(spannableString);*/
        final EasyTextView ic = (EasyTextView) findViewById(R.id.easyText);
        ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ic.setIconColor(Color.parseColor("#2E7D19"));
            }
        });

    }
}

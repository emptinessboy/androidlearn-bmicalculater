package com.emptinessboy.bmisimple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Cal = findViewById(R.id.buttonCal);
        Button Reset = findViewById(R.id.buttonReset);
        Cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCalBmi(v);
            }
        });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inHeight = findViewById(R.id.inputHeight);
                EditText inWeight = findViewById(R.id.inputWeight);
                inHeight.setText("");
                inWeight.setText("");
            }
        });
    }

    public void btnCalBmi(View v){
        TextView textBmi = findViewById(R.id.textBmi);
        EditText inHeight = findViewById(R.id.inputHeight);
        EditText inWeight = findViewById(R.id.inputWeight);
        //引用控件（定义两个变量）
        if(TextUtils.isEmpty(inHeight.getText().toString())){
            textBmi.setText("请输入身高！");
            Toast.makeText(MainActivity.this,"请输入身高",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(inWeight.getText())){
            textBmi.setText("请输入体重！");
            Toast.makeText(MainActivity.this,"请输入体重",Toast.LENGTH_SHORT).show();
        }
        else{
            Double height = Double.parseDouble(inHeight.getText().toString());
            Double weight = Double.parseDouble(inWeight.getText().toString());
            if(height<50||height>250){
                textBmi.setText("身高异常（这是人的身高麽 ？）");
                Toast.makeText(MainActivity.this,"请重新输入身高",Toast.LENGTH_SHORT).show();
            }
            else if(weight<3||weight>250){
                textBmi.setText("体重异常（这是人的体重麽 ？");
                Toast.makeText(MainActivity.this,"请重新输入体重",Toast.LENGTH_SHORT).show();
            }
            else{
                calBmi(height,weight,textBmi);
            }
        }
    }

    public void calBmi(double height,double weight,TextView textBmi){
        RadioButton asia = findViewById(R.id.stdAsia);
        RadioButton who = findViewById(R.id.stdWho);
        RadioButton cn = findViewById(R.id.stdCn);
        //计算
        Double bmi = weight/Math.pow(height/100,2);
        //判断选中
        if(who.isChecked()){
            if(bmi<18.5){
                textBmi.setText("您的 MBI 为："+bmi+" 您的体型为有一丢丢瘦（WHO标准）");
            }
            else if(bmi>=18.5&&bmi<25){
                textBmi.setText("您的 MBI 为："+bmi+" 您的体型为正常体型（WHO标准）");
            }
            else if(bmi>=25&&bmi<30){
                textBmi.setText("您的 MBI 为："+bmi+" 您的体型为微微发胖（WHO标准）");
            }
            else if(bmi>=30&&bmi<35){
                textBmi.setText("您的 MBI 为："+bmi+" 您的体型为肥胖！！（WHO标准）");
                ToastZj();
            }
            else{
                textBmi.setText("您的 MBI 为："+bmi+" 您简直是一个超级大胖次！（WHO标准）");
                ToastZj();
            }
        }
        else if(asia.isChecked()){
            if(bmi<18.5){
                textBmi.setText("您的 MBI 为："+bmi+" 您的体型为有一丢丢瘦（亚洲标准）");
            }
            else if(bmi>=18.5&&bmi<23){
                textBmi.setText("您的 MBI 为："+bmi+" 您的体型为正常体型（亚洲标准）");
            }
            else if(bmi>=23&&bmi<25){
                textBmi.setText("您的 MBI 为："+bmi+" 您的体型为微微发胖（亚洲标准）");
            }
            else if(bmi>=25&&bmi<30){
                textBmi.setText("您的 MBI 为："+bmi+" 您的体型为肥胖！！（亚洲标准）");
                ToastZj();
            }
            else{
                textBmi.setText("您的 MBI 为："+bmi+" 您简直是一个超级大胖次！（亚洲标准）");
                ToastZj();
            }
        }
        else if(cn.isChecked()){
            if(bmi<18.5){
                textBmi.setText("您的 MBI 为："+bmi+" 您的体型为有一丢丢瘦（中国标准）");
            }
            else if(bmi>=18.5&&bmi<24){
                textBmi.setText("您的 MBI 为："+bmi+" 您的体型为正常体型（中国标准）");
            }
            else if(bmi>=24&&bmi<28){
                textBmi.setText("您的 MBI 为："+bmi+" 您的体型为微微发胖（中国标准）");
            }
            else if(bmi>=28&&bmi<32){
                textBmi.setText("您的 MBI 为："+bmi+" 您的体型为很肥胖！！（中国标准）");
                ToastZj();
            }
            else{
                textBmi.setText("您的 MBI 为："+bmi+" 您简直是一个超级大胖次！（中国标准）");
                ToastZj();
            }
        }
        else{
            textBmi.setText("请先选择一个标准！");
        }
    }

    public void ToastZj(){
        //新建 Toast 控件 zj
        Toast zj = Toast.makeText(MainActivity.this,"震惊 ！！",Toast.LENGTH_SHORT);
        //设置控件位置（底部向上偏移160dp）
        zj.setGravity(Gravity.BOTTOM,0,160);

        //使用 getView() 方法得到 Toast 的 View
        LinearLayout zjView = (LinearLayout)zj.getView();

        //创建一个 ImageView
        ImageView zjImg = new ImageView(getApplicationContext());
        //设置图像
        zjImg.setImageResource(R.drawable.zhenjing);

        //使用 LinearLayout.LayoutParams 方法设置图片大小和图片在 LinerLayout 里的位置
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150,150);
        params.setMargins(0,30,0,-20);
        zjImg.setLayoutParams(params);

        //调整 Toast 的 View 的 LinerLayout 的内部布局
        zjView.setGravity(Gravity.CENTER);
        zjView.setOrientation(LinearLayout.VERTICAL);

        //图像添加到 zjView 里面，第二个参数决定了显示顺序（图片和文字的先后）
        zjView.addView(zjImg,0);

        //显示 Toast
        zj.show();

    }

}

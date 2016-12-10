package com.example.kingwen.test1.Activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kingwen.test1.R;
import com.example.kingwen.test1.Views.CircleImageView;
import com.example.kingwen.test1.Views.MDialog;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 头像
     */
    private CircleImageView touxiang;


    /**
     * 出生年月日
     */
    private LinearLayout birthLayout;


    private int year,month,day;


    /**
     * 显示出生年月
     */
    private TextView tv_birthday;


    /**
     * 显示年龄
     */
    private TextView tv_age;

    /**
     *年龄
     */
    private LinearLayout ly_age;






    /**
     * 姓名
     */
    private EditText et_name;

    /**
     * age seekbar
     */
    private SeekBar ageBar;

    /**
     * 性别
     */
    private RadioGroup rg_sex;
    private RadioButton rb_man;
    private RadioButton rb_woman;

    /**
     * 出生年月
     * */
    private String birthday;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initListeners();

    }

    private void initListeners() {
        touxiang.setOnClickListener(this);
        birthLayout.setOnClickListener(this);
        ly_age.setOnClickListener(this);

    }

    private void initViews() {

        touxiang= (CircleImageView) findViewById(R.id.touxiang);
        birthLayout= (LinearLayout) findViewById(R.id.layout_birthday);
        tv_birthday= (TextView) findViewById(R.id.tv_showbirthday);
        tv_age= (TextView) findViewById(R.id.tv_showage);
        ly_age= (LinearLayout) findViewById(R.id.layout_age);


        rg_sex= (RadioGroup) findViewById(R.id.rg_sex);
        rb_man= (RadioButton) findViewById(R.id.rb_man);
        rb_woman= (RadioButton) findViewById(R.id.rb_woman);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.touxiang:
                //打开相册选择
                Intent intent = new Intent();
//                开启pictures画面的type设定为image
                intent.setType("image/*");
//                使用intent.action_get_content这个action
                intent.setAction(Intent.ACTION_GET_CONTENT);
//                取得相片之后返回本界面
                startActivityForResult(intent, 1);
                break;
            case R.id.layout_birthday:

                MDialog dialog=new MDialog(MainActivity.this,"选择生日", new MDialog.OnMyDialogListener() {
                    @Override
                    public void back(int year, int month, int day) {
                    String  birthday=year+"-"+month+"-"+day;
                        MainActivity.this.tv_birthday.setText(birthday);

                        Calendar calendar=Calendar.getInstance();
                        int thisyear=calendar.get(Calendar.YEAR);
                        int age=thisyear-year;

                        Log.e("MainActivity",age+"");
                        MainActivity.this.tv_age.setText(age+"");
                    }
                });

                dialog.show();
                break;
            case R.id.layout_age:
                Toast.makeText(getApplicationContext(),"选择出生日期自动生成",Toast.LENGTH_LONG).show();
                Log.e("layout_age","layout-age");
                break;
        }
    }


    /*
    * 选择照片并回传这个照片
    * */
    protected void  onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode==RESULT_OK){
            Uri uri=data.getData();
            Log.e("uri", uri.toString());
            ContentResolver cr=this.getContentResolver();
            try{
//                通过路径得到图片。然后显示到我们的imageButton中
                Bitmap bitMap= BitmapFactory.decodeStream(cr.openInputStream(uri));
                if(bitMap==null){
                    Log.e("bitMap is wrong","bitMap 为空");
                }
                Log.e("showImage","yunxing dao le show Image");
                touxiang.setImageBitmap(bitMap);
            }catch (FileNotFoundException e) {
                Log.e("Exception",e.getMessage());
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

}

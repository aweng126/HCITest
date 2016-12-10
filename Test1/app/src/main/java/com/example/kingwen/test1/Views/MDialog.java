package com.example.kingwen.test1.Views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.kingwen.test1.R;

import java.util.Calendar;

/**
 * Created by kingwen on 2016/11/28.
 */
public class MDialog extends Dialog {
    private DatePicker mPicker;

    private Button btn_confirm;
    private Button btn_cancel;

    private int year,month,day;

    private String dialogname;

    private OnMyDialogListener onMyDialogListener;

    //自定义的dialog的构造方法
    public MDialog(Context context, String name, OnMyDialogListener onMyDialogListener) {
        super(context);
        this.dialogname=name;
        this.onMyDialogListener=onMyDialogListener;
    }


    //定义回调事件
    public interface  OnMyDialogListener{
        public void back(int year,int month,int day);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        setTitle("请选择你的出生日期");

        initDatas();
        initViews();
        initListener();
    }

    private void initDatas() {

        Calendar calendar=Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);

    }

    private void initListener() {


        mPicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                MDialog.this.year=year;
                MDialog.this.month=monthOfYear;
                MDialog.this.day=dayOfMonth;
            }
        });


        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onMyDialogListener.back(MDialog.this.year, MDialog.this.month,MDialog.this.day);
                MDialog.this.dismiss();

            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MDialog.this.dismiss();
            }
        });
    }
    private void initViews() {
         mPicker= (DatePicker) findViewById(R.id.datapicker);
        btn_confirm= (Button) findViewById(R.id.btn_confirm_dialog);
        btn_cancel= (Button) findViewById(R.id.btn_cancel_dialog);
    }


}

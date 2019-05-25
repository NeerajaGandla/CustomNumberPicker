package com.apptmyz.counterview.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apptmyz.counterview.R;


public class CounterView extends LinearLayout implements View.OnClickListener {

    private TextView valueTv;
    private Button incrementBtn, decrementBtn;
    private int mValue = 1, mMinLimit = 1, mMaxLimit = (int) Double.POSITIVE_INFINITY;
    private CounterListener listener;

    public CounterView(Context context) {
        super(context);
        init(context, null);
    }

    public CounterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CounterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.counter_view, this);
        //initialize attributes here
        TypedArray ta = getContext().getResources().obtainAttributes(attrs,R.styleable.CounterView);
        Log.d("test", "init: " + ta.toString());
        mMinLimit = ta.getInteger(R.styleable.CounterView_minlimit,1);
        if (mMinLimit != 1) {
            mValue = mMinLimit;
        }
        mMaxLimit = ta.getInteger(R.styleable.CounterView_maxlimit,(int)Double.POSITIVE_INFINITY);
        invalidate();
        ta.recycle();

        valueTv = (TextView) findViewById(R.id.tv_value);
        valueTv.setText(mValue+"");
        incrementBtn = (Button) findViewById(R.id.btn_increment);
        decrementBtn = (Button) findViewById(R.id.btn_decrement);
        incrementBtn.setOnClickListener(this);
        decrementBtn.setOnClickListener(this);
    }

    //    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_increment) {
            if (mValue < mMaxLimit) {
                mValue++;
                valueTv.setText(mValue + "");
                invalidate();
            }
            if (mValue == mMaxLimit) {
                incrementBtn.setEnabled(false);
                incrementBtn.setClickable(false);
                invalidate();
            }
            if (mValue > mMinLimit) {
                decrementBtn.setEnabled(true);
                decrementBtn.setClickable(true);
                invalidate();
            }
            //pass value to the activity
            if (this.listener != null)
                this.listener.onIncrementClick(this.valueTv.getText().toString());

        } else if (i == R.id.btn_decrement) {
            if (mValue > mMinLimit) {
                mValue--;
                valueTv.setText(mValue + "");
                invalidate();
            }
            if (mValue == mMinLimit) {
                decrementBtn.setEnabled(false);
                decrementBtn.setClickable(false);
                invalidate();
            }
            if (mValue < mMaxLimit) {
                incrementBtn.setEnabled(true);
                incrementBtn.setClickable(true);
                invalidate();
            }
            //pass value to the activity
            if (this.listener != null)
                this.listener.onDecrementClick(this.valueTv.getText().toString());

        } else {
        }
    }

    public void setCounterListener(CounterListener listener) {
        this.listener = listener;
    }

    public void setValue(int value) {
        mValue = value;
        valueTv.setText(value + "");
    }

    public int getValue() {
        String value = valueTv.getText().toString().trim();
        if (value != null && value.length() != 0)
            return Integer.parseInt(value);
        else
            return 0;
    }
}

package com.techno.fabcount;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;
    private FABCount mFABCount;
    private Button mBtnIncTextSize;
    private Button mBtnDecTextSize;
    private Button mBtnChangeDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        mBtnIncTextSize = findViewById(R.id.btnIncTextSize);
        mBtnDecTextSize = findViewById(R.id.btnDecTextSize);
        mBtnChangeDir = findViewById(R.id.btnChangeDir);

        mRadioGroup = findViewById(R.id.counter_mode);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.increase_button) {
                    mFABCount.setImageResource(R.drawable.ic_add_white_24dp);
                } else {
                    mFABCount.setImageResource(R.drawable.ic_remove_white_24dp);
                }
            }
        });

        mFABCount = findViewById(R.id.fab);
        mFABCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRadioGroup.getCheckedRadioButtonId() == R.id.increase_button) {
                    mFABCount.increase();
                } else {
                    mFABCount.decrease();
                }
            }
        });
        mBtnIncTextSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFABCount.changeTextSize(mFABCount.getCurrentSize() + 1);
                mFABCount.increase();
                mFABCount.decrease();
            }
        });
        mBtnDecTextSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFABCount.changeTextSize(mFABCount.getCurrentSize() - 1);
                mFABCount.increase();
                mFABCount.decrease();
            }
        });
        mBtnChangeDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = (mFABCount.getDirection() + 1)%5;
                if(a == 0) a = 1;
                mFABCount.setDirection(a);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

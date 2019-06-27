package net.lzzy.algorithm;

import android.annotation.SuppressLint;
import android.os.BadParcelableException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.lzzy.algorithm.algorlib.BaseSearch;
import net.lzzy.algorithm.algorlib.BaseSort;
import net.lzzy.algorithm.algorlib.SearchFactory;
import net.lzzy.algorithm.algorlib.sortFactory;

import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import static java.util.Collections.swap;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Integer[] items;
    private EditText edtItems;
    private TextView tvResult;
    private Spinner spinner;
    private LinearLayout container;
    private Spinner spSearch;
    private Button btnSort;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtItems = findViewById(R.id.activity_main_edt_items);
        initViews();
        initSpinner();




        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseSearch<Integer> search= SearchFactory.getInstance(spSearch.getSelectedItemPosition(),items);
                if (search!=null){
                    int pos= (int) search.search(v.getId());
                    tvResult.setText("该元素位于数组的第".concat(String.valueOf(pos+1))+"位");

                }
            }
        };

        container.removeAllViews();
        generateItems();
        btnSort.callOnClick();
        for (Integer i:items){
            Button btn=new Button(this);
            btn.setText(String.format(i.toString(), Locale.CHINA));
            btn.setId(i);
            btn.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1));
            btn.setOnClickListener(listener);
            container.addView(btn);
        }

    }

    private void resetSearch() {
    }

    private void initSpinner(){
         spinner=findViewById(R.id.activity_main_sp);
        String[]names={"直接选择排序","直接插入排序","希尔排序"};
        spinner.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,names));
    }

    private void initViews() {
        edtItems=findViewById(R.id.activity_main_edt_items);
        findViewById(R.id.activity_main_btn_generate).setOnClickListener(this);
        findViewById(R.id.activity_main_btn_sort).setOnClickListener(this);
        tvResult = findViewById(R.id.activity_main_tv_result);
    }
    private void resetSearc(){
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_btn_generate:
                generateItems();
                displayItems(edtItems);
                break;
            case R.id.activity_main_btn_sort:
                BaseSort<Integer> sort= sortFactory.getInstance(spinner.getSelectedItemPosition(),items);
                BaseSort<Integer>sortNotNull= Objects.requireNonNull(sort);
                sortNotNull.sortWithTime();
                String result=sort.getResult();
                tvResult.setText(result);

                displayItems(tvResult);
                Toast.makeText(this, "总时长:"+sort.getDuration(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
    public void initSearch(){
        spinner=findViewById(R.id.activity_main_sp);
        spSearch.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,sortFactory.getSortNames()));
        container=findViewById(R.id.linear);
        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetSearch();
            }
        });
        resetSearch();
    }
    private void displayItems(TextView tv) {
        String display = "";
        for (Integer i : items) {
            display = display.concat(i + ",");
        }
        display = display.substring(0, display.length() - 1);
        tv.setText(display);
    }

    private void swap(int m, int n) {
        int tmp = items[m];
        items[m] = items[n];
        items[n] = tmp;

    }
    private void generateItems() {
        items = new Integer[10];
        Random generator = new Random();
        for (int i = 0; i < items.length; i++) {
            items[i] = generator.nextInt(99);
        }
    }
}

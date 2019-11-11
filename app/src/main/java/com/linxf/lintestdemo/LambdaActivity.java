package com.linxf.lintestdemo;

import android.view.View;
import android.widget.TextView;

import com.linxf.base.BaseActivity;
import com.linxf.base.utils.LogUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/5/24.
 */

public class LambdaActivity extends BaseActivity {

    private TextView text1TxV;

    @Override
    public int getLayoutId() {
        return R.layout.activity_lambda;
    }

    @Override
    public void initView() {
        text1TxV = findViewById(R.id.text1TxV);
    }

    @Override
    public void initData() {
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players =  Arrays.asList(atp);

// 以前的循环方式
        for (String player : players) {
            LogUtil.error(player + "; ");
        }

// 使用 lambda 表达式以及函数操作(functional operation)
        players.forEach((player) ->{LogUtil.error("mmm:" + player);LogUtil.error("tmmm:" + player);});

// 在 Java 8 中使用双冒号操作符(double colon operator)
        players.forEach(System.out::println);


        text1TxV.setOnClickListener(v -> LogUtil.error("viewId:" +v.getId()));


        // 1.1使用匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        }).start();

    // 1.2使用 lambda expression
        new Thread(() -> System.out.println("Hello world !")).start();

    // 2.1使用匿名内部类
        Runnable race1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        };

    // 2.2使用 lambda expression
        Runnable race2 = () -> System.out.println("Hello world !");

    // 直接调用 run 方法(没开新线程哦!)
        race1.run();
        race2.run();

        //排序
        Arrays.sort(atp, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.compareTo(s2));
            }
        });
        // 1.2 使用 lambda expression 排序 players
        Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));
        Arrays.sort(atp, sortByName);

        // 1.3 也可以采用如下形式:
        Arrays.sort(atp, (String s1, String s2) -> (s1.compareTo(s2)));


    }
    

}

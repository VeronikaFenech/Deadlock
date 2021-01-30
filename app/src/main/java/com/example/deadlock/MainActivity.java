package com.example.deadlock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.CyclicBarrier;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deadlock();


    }

    Object a = new Object(), b = new Object();
    CyclicBarrier barrier = new CyclicBarrier(2);


    private void deadlock() {

        new Thread(() -> {
            synchronized (a) {
                try {
                    barrier.await();
                } catch (Exception ignored) {
                }
                synchronized (b) {
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (b) {
                try {
                    barrier.await();
                } catch (Exception ignored) {
                }
                synchronized (a) {
                }
            }
        }).start();
    }
}

package xpxpxp.vdiodemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.runing.utilslib.debug.log.L;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "sayay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        L.t("sayay").e();
//        L.print("onCreate: ");
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        L.print("onStart: ");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        L.print("onResume: ");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        L.print("onPause: ");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        L.print("onStop: ");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        L.print("onDestroy: ");
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        L.print("onRestart: ");
//    }
//
        Log.e(TAG, "旧Activity的onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "旧Activity的onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "旧Activity的onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "旧Activity的onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "旧Activity的onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "旧Activity的onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "旧Activity的onRestart: ");
    }

    public void nextto(View view) {
        startActivity(new Intent(MainActivity.this, NextActivity.class));
    }
}

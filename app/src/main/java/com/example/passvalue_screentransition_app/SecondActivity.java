package com.example.passvalue_screentransition_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        /// 計算結果の表示
        showResult();

        /// アクションバー
        configureActionBar();
    }

    /// アクションバーの構築
    private void configureActionBar() {
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(null);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.show();
        }
    }

    /// 計算結果を表示
    private void showResult() {
        /// インテントからデータを受け取る
        Intent intent = getIntent();
        String result = intent.getStringExtra("result");

        /// データを表示するためのビューにセットするなどの処理を行う
        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText(result);
    }

    /*
    onSupportNavigateUp
    メソッドの戻り値が boolean 型である理由は、Androidアクティビティの規約に従ったもの
    このメソッドは、アクションバーの戻るボタンが押されたときに、
    アクティビティを終了して前の画面に戻る処理を行う際に、
    成功したかどうかを示すために戻り値として boolean を返す
    */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
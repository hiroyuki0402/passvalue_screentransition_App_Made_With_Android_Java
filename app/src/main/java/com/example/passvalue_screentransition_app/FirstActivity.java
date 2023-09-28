package com.example.passvalue_screentransition_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class FirstActivity extends AppCompatActivity {
    /// 画面上の数値入力フィールド
    private EditText numberTextFiled;
    private EditText numberTextFiled2;

    /// 四則演算ボタン
    private ToggleButton additionButton;
    private ToggleButton subtractionButton;
    private ToggleButton multiplicationButton;
    private ToggleButton divisionButton;

    /// 計算結果
    private Integer result; // 計算結果

    /// アクションバー
    ActionBar actionBar;

    /// 四則演算
    Operator operator = Operator.Addition;

    /// アクティビティの作成時に呼び出されるメソッド
    /// - Parameter savedInstanceState: 以前の状態を保持するためのデータ
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /// レイアウトをセットアップ
        setContentView(R.layout.activity_main);

        /// 数値入力フィールドを構成
        configureNumber();

        /// ボタンを構成
        configureButtons();

        /// アクションバー
        configureActionBar();
    }

    /// 数値入力フィールドを構成するメソッド
    private void configureNumber() {
        numberTextFiled = findViewById(R.id.editTextNumber);
        numberTextFiled2 = findViewById(R.id.editTextNumber2);
    }

    /// 四則演算ボタンを構成するメソッド
    private void configureButtons() {
        /// ボタンビューを取得
        additionButton = findViewById(R.id.toggleButton);
        subtractionButton = findViewById(R.id.toggleButton3);
        multiplicationButton = findViewById(R.id.toggleButton4);
        divisionButton = findViewById(R.id.toggleButton5);

        int color = ContextCompat.getColor(this, R.color.bg);

        /// 加算ボタンのチェック状態が変更されたときに呼び出されるリスナーを設定
        additionButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            /// チェック状態が変更された時に呼び出されるメソッド
            /// - Parameters:
            ///   - buttonView: ボタンビュー
            ///   - isChecked: チェック状態
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    operator = Operator.Addition;
                    additionButton.setBackgroundColor(Color.CYAN);
                    clearOtherButtons(additionButton);
                } else {
                    additionButton.setBackgroundColor(color);
                }
            }
        });

        /// 減算ボタンのチェック状態が変更されたときに呼び出されるリスナーを設定
        subtractionButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /// チェック状態が変更された時に呼び出されるメソッド
            /// - Parameters:
            ///   - buttonView: ボタンビュー
            ///   - isChecked: チェック状態
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    operator = Operator.Multiplication;
                    subtractionButton.setBackgroundColor(Color.CYAN);
                    clearOtherButtons(subtractionButton);
                } else {
                    subtractionButton.setBackgroundColor(color);
                }
            }
        });

        /// 乗算ボタンのチェック状態が変更されたときに呼び出されるリスナーを設定
        multiplicationButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /// チェック状態が変更された時に呼び出されるメソッド
            /// - Parameters:
            ///   - buttonView: ボタンビュー
            ///   - isChecked: チェック状態
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    operator = Operator.Multiplication;
                    multiplicationButton.setBackgroundColor(Color.CYAN);
                    clearOtherButtons(multiplicationButton);
                } else {
                    multiplicationButton.setBackgroundColor(color);
                }
            }
        });

        /// 割り算ボタンのチェック状態が変更されたときに呼び出されるリスナーを設定
        divisionButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /// チェック状態が変更された時に呼び出されるメソッド
            /// - Parameters:
            ///   - buttonView: ボタンビュー
            ///   - isChecked: チェック状態
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    operator = Operator.Division;
                    divisionButton.setBackgroundColor(Color.CYAN);
                    clearOtherButtons(divisionButton);
                } else {
                    divisionButton.setBackgroundColor(color);
                }
            }
        });
    }

    /// 他のボタンをクリアするメソッド
    /// - Parameter selectedButton: 選択されたボタン
    private void clearOtherButtons(ToggleButton selectedButton) {
        /// 選択されたボタンが加算ボタンでない場合
        if (selectedButton != additionButton) {
            /// 加算ボタンのチェックを解除し、背景色を白に設定
            additionButton.setChecked(false);
        }

        /// 選択されたボタンが減算ボタンでない場合
        if (selectedButton != subtractionButton) {
            /// 減算ボタンのチェックを解除し、背景色を白に設定
            subtractionButton.setChecked(false);
        }

        /// 選択されたボタンが乗算ボタンでない場合
        if (selectedButton != multiplicationButton) {
            /// 乗算ボタンのチェックを解除し、背景色を白に設定
            multiplicationButton.setChecked(false);
        }

        /// 選択されたボタンが除算ボタンでない場合
        if (selectedButton != divisionButton) {
            /// 除算ボタンのチェックを解除し、背景色を白に設定
            divisionButton.setChecked(false);
        }
    }

    /// 計算結果を表示するメソッド(画面遷移)
    /// - Parameter view: ビュー
    public void didTapResult(View view) {
        /// null check
        if (result == null) { return; }

        /// 計算
        performOperation(this.operator);

        /// 画面遷移用のインテントを作成
        Intent intent = new Intent(this, SecondActivity.class);

        /// 計算結果をインテントに追加
        intent.putExtra("result", result.toString());

        /// 画面遷移を実行
        startActivity(intent);
    }


    /// 指定された演算を実行するメソッド
    /// - Parameter operator: 演算子
    private void performOperation( Operator operator) {
        int num1 = Integer.parseInt(numberTextFiled.getText().toString());
        int num2 = Integer.parseInt(numberTextFiled2.getText().toString());
        switch (operator) {
            case Addition:
                this.result = num1 + num2;
                break;

            case Subtraction:
                this.result = num1 - num2;
                break;

            case Multiplication:
                this.result = num1 * num2;
                break;

            case Division:
                this.result = num1 / num2;
                break;
        }
    }

    /// アクションバーの構築
    private void configureActionBar() {
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    /// 四則演算
    enum Operator {
        Addition,
        Subtraction,
        Multiplication,
        Division
    }
}


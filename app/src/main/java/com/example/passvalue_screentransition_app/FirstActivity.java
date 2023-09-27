package com.example.passvalue_screentransition_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
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

    /// アクティビティの作成時に呼び出されるメソッド
    /// - Parameter savedInstanceState: 以前の状態を保持するためのデータ
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /// レイアウトをセットアップ
        setContentView(R.layout.activity_main);

        /// 数値入力フィールドを構成
        configureNumber();

        /// ボタンを構成
        configureButtons();
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

        /// ボタンの背景色を初期化
        additionButton.setBackgroundColor(Color.WHITE);
        subtractionButton.setBackgroundColor(Color.WHITE);
        multiplicationButton.setBackgroundColor(Color.WHITE);
        divisionButton.setBackgroundColor(Color.WHITE);

        /// 加算ボタンのチェック状態が変更されたときに呼び出されるリスナーを設定
        additionButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            /// チェック状態が変更された時に呼び出されるメソッド
            /// - Parameters:
            ///   - buttonView: ボタンビュー
            ///   - isChecked: チェック状態
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    performOperation("+");
                    additionButton.setBackgroundColor(Color.CYAN);
                    clearOtherButtons(additionButton);
                } else {
                    additionButton.setBackgroundColor(Color.WHITE);
                }
            }
        });

        /// 減算ボタンのチェック状態が変更されたときに呼び出されるリスナーを設定
        subtractionButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /// チェック状態が変更された時に呼び出されるメソッド
            /// - Parameters:
            ///   - buttonView: ボタンビュー
            ///   - isChecked: チェック状態
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    performOperation("-");
                    subtractionButton.setBackgroundColor(Color.CYAN);
                    clearOtherButtons(subtractionButton);
                } else {
                    subtractionButton.setBackgroundColor(Color.WHITE);
                }
            }
        });

        /// 乗算ボタンのチェック状態が変更されたときに呼び出されるリスナーを設定
        multiplicationButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /// チェック状態が変更された時に呼び出されるメソッド
            /// - Parameters:
            ///   - buttonView: ボタンビュー
            ///   - isChecked: チェック状態
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    performOperation("*");
                    multiplicationButton.setBackgroundColor(Color.CYAN);
                    clearOtherButtons(multiplicationButton);
                } else {
                    multiplicationButton.setBackgroundColor(Color.WHITE);
                }
            }
        });

        /// 割り算ボタンのチェック状態が変更されたときに呼び出されるリスナーを設定
        divisionButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /// チェック状態が変更された時に呼び出されるメソッド
            /// - Parameters:
            ///   - buttonView: ボタンビュー
            ///   - isChecked: チェック状態
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    performOperation("/");
                    divisionButton.setBackgroundColor(Color.CYAN);
                    clearOtherButtons(divisionButton);
                } else {
                    divisionButton.setBackgroundColor(Color.WHITE);
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
            additionButton.setBackgroundColor(Color.WHITE);
        }

        /// 選択されたボタンが減算ボタンでない場合
        if (selectedButton != subtractionButton) {
            /// 減算ボタンのチェックを解除し、背景色を白に設定
            subtractionButton.setChecked(false);
            subtractionButton.setBackgroundColor(Color.WHITE);
        }

        /// 選択されたボタンが乗算ボタンでない場合
        if (selectedButton != multiplicationButton) {
            /// 乗算ボタンのチェックを解除し、背景色を白に設定
            multiplicationButton.setChecked(false);
            multiplicationButton.setBackgroundColor(Color.WHITE);
        }

        /// 選択されたボタンが除算ボタンでない場合
        if (selectedButton != divisionButton) {
            /// 除算ボタンのチェックを解除し、背景色を白に設定
            divisionButton.setChecked(false);
            divisionButton.setBackgroundColor(Color.WHITE);
        }
    }

    /// 計算結果を表示するメソッド
    /// - Parameter view: ビュー
    public void didTapResult(View view) {
        Log.d("白石", "Result: " + result);
    }

    /// 指定された演算を実行するメソッド
    /// - Parameter operator: 演算子
    private void performOperation(String operator) {
        int num1 = Integer.parseInt(numberTextFiled.getText().toString());
        int num2 = Integer.parseInt(numberTextFiled2.getText().toString());
        switch (operator) {
            case "+":
                this.result = num1 + num2;
                break;
            case "-":
                this.result = num1 - num2;
                break;
            case "*":
                this.result = num1 * num2;
                break;
            case "/":
                this.result = num1 / num2;
                break;
        }
    }
}
package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button mTrueButton;
    private Button mFalseButton;
    ArrayList<Question> mQuestionAL= new ArrayList <>();
    int mCurrentIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        /* True and False buttons with an OnClickListener  */

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QuizActivity.this,R.string.correct_toast, Toast.LENGTH_SHORT).show();
            }
        });
        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show();


    }
}

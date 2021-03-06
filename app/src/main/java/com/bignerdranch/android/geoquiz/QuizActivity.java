package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.icu.lang.UProperty;
import android.nfc.Tag;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private Button mCheatButton;


    private TextView mQuestionTextView;

    private ArrayList<Question> mQuestionAL= new ArrayList <>();

    private int mCurrentIndex;
    private boolean mIsCheater;

    private boolean answerIsTrue;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;


    /** Method declarations
     */

    private void updateQuestion() {
        int question = mQuestionAL.get(mCurrentIndex).getTextResId();
        Log.d(TAG, "updateQuestion called,  int question: " + question);
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        int messageResId = 0;
        answerIsTrue = mQuestionAL.get(mCurrentIndex).isAnswerTrue();
        if(mQuestionAL.get(mCurrentIndex).isAnswerCheated()) messageResId = R.string.jugment_toast;
        else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }




    /** OnCreate Override
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG,"onCreate(Bundle) called");
        super.onCreate(savedInstanceState);
        Log.d(TAG,"SAVED INSTANCE CALL");
        setContentView(R.layout.activity_quiz);
        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        // Array with Model Classes and initiation
        mQuestionAL.add(new Question(R.string.question_americas, true,false) );
        mQuestionAL.add(new Question(R.string.question_mideast, false,false) );
        mQuestionAL.add(new Question(R.string.question_africa, true,false) );
        mQuestionAL.add(new Question(R.string.question_asia, false, false) );
        mQuestionAL.add(new Question(R.string.question_oceans, true, false) );



        mQuestionTextView= (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex==mQuestionAL.size()-1) mCurrentIndex=0;
                else mCurrentIndex++;
                updateQuestion();
            }
        });

        updateQuestion();

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);

            }
        });

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAnswer(false);
            }
        });

        mPrevButton = findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex != 0) {
                    mCurrentIndex--;
                    updateQuestion();
                }
            }
        });

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex==mQuestionAL.size()-1) mCurrentIndex=0;
                else mCurrentIndex++;
                mIsCheater = false;
                updateQuestion();
            }
        });

        mCheatButton = findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean answerIsTrue = mQuestionAL.get(mCurrentIndex).isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(i,REQUEST_CODE_CHEAT);
            }
        });

    }

    /** Other Overrided Methods and Log prints
     *
     */

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_CODE_CHEAT){
            if (data == null){
                return;
            }
            mQuestionAL.get(mCurrentIndex).setAnswerCheated(CheatActivity.wasAnswerShown(data));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }


}

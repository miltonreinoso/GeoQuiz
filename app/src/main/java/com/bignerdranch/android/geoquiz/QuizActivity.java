package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;
    private ArrayList<Question> mQuestionAL= new ArrayList <>();
    static int mCurrentIndex;

    /** Method declaration
     */

    private void updateQuestion() {
        int question = mQuestionAL.get(mCurrentIndex).getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        int messageResId;
        boolean answerIsTrue = userPressedTrue == mQuestionAL.get(mCurrentIndex).isAnswerTrue();

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Array with Model Classes and initiation
        mQuestionAL.add(new Question(R.string.question_americas, true) );
        mQuestionAL.add(new Question(R.string.question_mideast, false) );
        mQuestionAL.add(new Question(R.string.question_africa, true) );
        mQuestionAL.add(new Question(R.string.question_asia, false) );
        mQuestionAL.add(new Question(R.string.question_oceans, true) );



        mQuestionTextView= (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex==mQuestionAL.size()-1) mCurrentIndex=0;
                else mCurrentIndex++;
                updateQuestion();
            }
        });


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
                updateQuestion();
            }
        });

    }


}

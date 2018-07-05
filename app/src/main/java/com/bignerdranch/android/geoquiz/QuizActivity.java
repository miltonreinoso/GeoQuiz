package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.bignerdranch.android.geoquiz.Question;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private ArrayList<Question> mQuestionAL= new ArrayList <>();
    static int mCurrentIndex;

    private void updateQuestion() {
        int question = mQuestionAL.get(mCurrentIndex).getTextResId();
        mQuestionTextView.setText(question);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionAL.add(new Question(R.string.question_americas, true) );
        mQuestionAL.add(new Question(R.string.question_mideast, false) );
        mQuestionAL.add(new Question(R.string.question_africa, true) );
        mQuestionAL.add(new Question(R.string.question_asia, false) );
        mQuestionAL.add(new Question(R.string.question_oceans, true) );


        mQuestionTextView= (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        /* True and False buttons with an OnClickListener  */

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mQuestionAL.get(mCurrentIndex).isAnswerTrue() == true)
                    Toast.makeText(QuizActivity.this,R.string.correct_toast, Toast.LENGTH_SHORT).show();
                else  Toast.makeText(QuizActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
            }
        });
        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mQuestionAL.get(mCurrentIndex).isAnswerTrue() == false)
                    Toast.makeText(QuizActivity.this,R.string.correct_toast, Toast.LENGTH_SHORT).show();
                else  Toast.makeText(QuizActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show();

            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
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

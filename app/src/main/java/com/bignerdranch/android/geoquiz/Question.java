package com.bignerdranch.android.geoquiz;

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mIsAnswerCheated;

    public int getTextResId() {
        return mTextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean isAnswerCheated() {
        return mIsAnswerCheated;
    }

    public void setAnswerCheated(boolean answerCheated) {
        mIsAnswerCheated = answerCheated;
    }


    public Question(int textResId, boolean answerTrue, boolean isAnswerCheated) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mIsAnswerCheated = isAnswerCheated;
    }
}



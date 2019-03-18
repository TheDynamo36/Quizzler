package pro.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {



    // TODO: Declare member variables here:
    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionTextView;
    TextView mScoreTetView;
    ProgressBar mProgressBar;
    int mIndex;
    int mQuestion;
    int mScore = 0;


    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    // TODO: Declare constants here
    final int PROGRESS_BAR_INCREMENT = (int)Math.ceil (100.0 /mQuestionBank.length) ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            mTrueButton = findViewById(R.id.true_button);
            mFalseButton = findViewById(R.id.false_button);
            mQuestionTextView = findViewById(R.id.question_text_view);
            mScoreTetView = findViewById(R.id.score);
            mProgressBar = findViewById(R.id.progress_bar);

            mTrueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(true);
                    updateQuestion();
                }
            });

            mFalseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(false);
                    updateQuestion();
                }
            });

    }

    private void updateQuestion (){
        mIndex = (mIndex + 1) % mQuestionBank.length;
        if(mIndex == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over!");
            alert.setCancelable(false);
            alert.setMessage("Final Score is "+mScore + " Points!");
            alert.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                   finish();
                }
            });

            alert.show();
        }

        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);

    }

    private void checkAnswer(boolean userSelection){

        if(userSelection == mQuestionBank[mIndex].isAnswer()){
            Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_SHORT).show();
            mScore = mScore + 1;
            mScoreTetView.setText("Score" + mScore + "/" + mQuestionBank.length);
        }else {
            Toast.makeText(this, R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
        }

    }
}

package com.example.android.rogerquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * Initializing the number of correct answers
     */
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide the keyboard
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the result button is clicked.
     */
    public void endTest(View view) {

        //Called question checking methods
        question_1();
        question_2();
        question_3();
        question_4();
        question_5();

        // Hide the keyboard
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main);

        // Displays the test result on the screen
        String quiz_result = createQuizResult(score);
        Toast.makeText(getApplicationContext(), quiz_result, Toast.LENGTH_LONG).show();
        if (score==5) {
            Toast.makeText(getApplicationContext(), getString(R.string.bravo), Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), getString(R.string.play), Toast.LENGTH_LONG).show();
        }

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.helloscore) + getString(R.string.helloquiz) + score);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.share)));

        //Resets the score
        score = 0;
    }

    /**
     * Create summary of the test result.
     *
     * @param score of the counting of right answers
     * @return text of the test result
     */
    private String createQuizResult(int score) {
        String quiz_result = getString(R.string.hello) + " " + getString(R.string.thanks);
        quiz_result += "\n" + getString(R.string.score) + " " + score + " " + getString(R.string.five);
        return quiz_result;
    }

    /**
     * This method counts correct answers in question 1
     * "Which big slam was won 5 following times by Roger Federer ?"(Answers are Wimbledon and Us Open)
     */
    public void question_1() {
        // Figure out if the user chose "Wimbledon" answer
        CheckBox WimbledonCheckBox = (CheckBox) findViewById(R.id.answer_q_1_cb_1);
        boolean isWimbledon = WimbledonCheckBox.isChecked();

        // Figure out if the user chose "Roland Garros" answer
        CheckBox RolandCheckBox = (CheckBox) findViewById(R.id.answer_q_1_cb_2);
        boolean isRoland = RolandCheckBox.isChecked();

        // Figure out if the user chose "US Open" answer
        CheckBox UsopenCheckBox = (CheckBox) findViewById(R.id.answer_q_1_cb_3);
        boolean isUsopen = UsopenCheckBox.isChecked();

        // Figure out if the user chose "Australian Open" answer
        CheckBox AustraliaCheckBox = (CheckBox) findViewById(R.id.answer_q_1_cb_4);
        boolean isAustralia = AustraliaCheckBox.isChecked();

        // Score is incremented of 1 point if user answered both Wimbledon and Us Open
        if (isWimbledon && isUsopen &&! isAustralia &&! isRoland) {
            incrementScore();
        }
    }

    /**
     * This method counts the correct answer in question 2
     * "How old was Roger Federer when he won a grand slam for the first time ? " (Answer is 21 years old)
     */
    public void question_2() {
        RadioGroup answers = (RadioGroup) findViewById(R.id.answer_2);
        if (answers.getCheckedRadioButtonId() == R.id.answer_q_2_rb_2) {
            incrementScore();
        }
    }

    /**
     * This method counts the correct answer in question 3
     * "How many grand slams did Roger Federer win ? " (Answer is 20)
     */
    public void question_3() {
        // Figure out if the user chose "20" as answer
        EditText slamField = (EditText) findViewById(R.id.slam_field);
        String slam = slamField.getText().toString();
        // Score is incremented of 1 point if user answered 20
        if (slam.equals("20")) {
            incrementScore();
        }
    }

    /**
     * This method counts the correct answer in question 4
     * "During how many weeks has Federer been world N°1 in his career ? "  (Answer is 302)
     */
    public void question_4() {
        RadioGroup answers = (RadioGroup) findViewById(R.id.answer_4);
        if (answers.getCheckedRadioButtonId() == R.id.answer_q_4_rb_1) {
            incrementScore();
        }
    }

    /**
     * This method counts the correct answer in question 5
     * "On which surface has Federer been the best tennis player ever ?"  (Answer is Grass)
     */
    public void question_5() {
        RadioGroup answers = (RadioGroup) findViewById(R.id.answer_5);
        if (answers.getCheckedRadioButtonId() == R.id.answer_q_5_rb_2) {
            incrementScore();
        }
    }

    /**
     * This method is called when user selected a correct answer.
     * Adds +1 to score for each correct answer
     */
    private int incrementScore() {
        score = score + 1;
        return score;
    }
}

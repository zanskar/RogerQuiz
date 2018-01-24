package com.example.android.rogerquiz;

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
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        //Called question checking methods
        question_1();
        question_2();
        question_3();
        question_4();
        question_5();

        // Displays the test result on the screen
        String quiz_result = createQuizResult(name, score);
        Toast.makeText(getApplicationContext(), quiz_result, Toast.LENGTH_LONG).show();

        //Resets the score
        score = 0;
    }

    /**
     * Create summary of the test result.
     *
     * @param name  of the passing the test
     * @param score of the counting of right answers
     * @return text of the test result
     */
    private String createQuizResult(String name, int score) {
        String quiz_result = getString(R.string.hello) + " " + name + " " + getString(R.string.thanks);
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

        // Score is incremented of 1 point if user answered both Winbledon and Us Open
        if (isWimbledon && isUsopen) {
            incrementScore();
        }
    }

    /**
     * This method counts the correct answer in question 2
     * "How old was Roger Federer when he won a grand slam for the first time ? " (Answer is 21 years old)
     */
    public void question_2() {
        RadioButton answerRadio;
        RadioGroup answers = (RadioGroup) findViewById(R.id.answer_2);
        switch (answers.getCheckedRadioButtonId()) {
            case R.id.answer_q_2_rb_2:
                answerRadio = (RadioButton) findViewById(R.id.answer_q_2_rb_2);
                incrementScore();
                break;
            default:
                answerRadio = (RadioButton) findViewById(R.id.answer_q_2_rb_1);
        }
    }

    /**
     * This method counts the correct answer in question 3
     * "How many grand slams did Roger Federer win ? " (Answer is 19)
     */
    public void question_3() {
        // Figure out if the user chose "19" as answer
        EditText slamField = (EditText) findViewById(R.id.slam_field);
        String slam = slamField.getText().toString();
        // Score is incremented of 1 point if us answered 19
        if (slam.equals("19")) {
            incrementScore();
        }
    }


    /**
     * This method counts the correct answer in question 4
     * "During how many weeks has Federer been world N°1 in his career ? "  (Answer is 302)
     */
    public void question_4() {
        RadioButton answerRadio;
        RadioGroup answers = (RadioGroup) findViewById(R.id.answer_4);
        switch (answers.getCheckedRadioButtonId()) {
            case R.id.answer_q_4_rb_1:
                answerRadio = (RadioButton) findViewById(R.id.answer_q_4_rb_1);
                incrementScore();
                break;
            default:
                answerRadio = (RadioButton) findViewById(R.id.answer_q_4_rb_2);
        }
    }

    /**
     * This method counts the correct answer in question 5
     * "On which surface has Federer been the best tennis player ever ?"  (Answer is Grass)
     */
    public void question_5() {
        RadioButton answerRadio;
        RadioGroup answers = (RadioGroup) findViewById(R.id.answer_5);
        switch (answers.getCheckedRadioButtonId()) {
            case R.id.answer_q_5_rb_2:
                answerRadio = (RadioButton) findViewById(R.id.answer_q_5_rb_2);
                incrementScore();
                break;
            default:
                answerRadio = (RadioButton) findViewById(R.id.answer_q_5_rb_1);
                answerRadio = (RadioButton) findViewById(R.id.answer_q_5_rb_3);
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

package com.raisac.quizapp;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button submit;
    int totalMarks;
    CheckBox c1, c2, c3, c4, cAnswer6;
    //Instance variables for images to show correct or wrng answer after submission for the questions
    ImageView img_qn1, img_qn2, img_qn13, img_qn4, img_qn5, img_qn6;

    //instaance variable for edittext, radibuttons, String array from the string resource
    EditText text_field_answer2, text_field_answer3;
    RadioButton rAnswer4, rAnswer5;
    String[] answers;
    Resources res;
    String answerFor3, answerFor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instantiate the resource variable
        res = getResources();


        //extract answers from the strings-array  resource
        answers = res.getStringArray(R.array.editTex_answers);

        //call the viewIds() method for all the view ids used in the app
        viewIds();
        /*deactivate the sub,it button so that no submission can be made
         * if no answer is selected, submussinon can only be done if atleast one answer is given
         * to prevent errors*/

    }


    public void submit(View view) {
        //Resset the total marks counter everytime you press the button
        /* this avoids adding to the previous resulst*/
        totalMarks = 0;

        //call the method that checks for state of answer in question 1 if correct or wrong
        answer_1();

        //call the two EditText methods, that checks for answers entered in the text field
        edt2Answers();
        edt3Answers();
    }

    private void answer_1() {
        /* Here I am are checking to see in the checked check box has the correct answer,
         * i do not need to get the text from the check bx and match it with some sring stored in sme variable
         * all i did here was .... */

        /*checking to see if the user checked the check boxes with the correct answer
         * if the 2nd andd third boxes arec checked and the first and third are not checked
         * the we call the qnPassed() method that showing that answer is correct*/
        if (c3.isChecked() && c2.isChecked() && !c1.isChecked() && !c4.isChecked()) {

            qnPassed();
        }

        /* if any other action is performed apart ffrom the first say;
         * 1. if a person does not check any box
         * 2. a person chooses only one check box
         * 3. a person chooses check boxes that are not c2 and c3
         * all the lead to calling a method  textFailed()*/
        else {
            qnFailed();
        }

        /* for question 4 and 5 it has radio buttons, the trick is the
        check box that has the correct answer when it is checked
        then a green tick is displayed after submiting*/

        if (rAnswer4.isChecked()) {

            img_qn4.setVisibility(View.VISIBLE);
            img_qn4.setImageResource(R.drawable.passed);
            totalMarks += 1;
            displayTotal(totalMarks);
        }
        /* when any other Action is performed apart from choosing the
         * check box that has the correct answer then a FAIL is considered
         * so the wrong image View is dislayed red in color*/
        else {
            img_qn4.setVisibility(View.VISIBLE);
            img_qn4.setImageResource(R.drawable.wrong);
        }

        if (rAnswer5.isChecked()) {

            img_qn5.setVisibility(View.VISIBLE);
            img_qn5.setImageResource(R.drawable.passed);
            totalMarks += 1;
            displayTotal(totalMarks);
        } else {
            img_qn5.setVisibility(View.VISIBLE);
            img_qn5.setImageResource(R.drawable.wrong);

        }
    }

    //sample method for the failed
    private void qnFailed() {
        img_qn1.setImageResource(R.drawable.wrong);
        img_qn1.setVisibility(View.VISIBLE);
    }

    private void qnPassed() {

        img_qn1.setImageResource(R.drawable.passed);
        img_qn1.setVisibility(View.VISIBLE);
        totalMarks += 1;
        displayTotal(totalMarks);
    }

    private void viewIds() {
        //gettting ids for checkboxes
        c1 = findViewById(R.id.answer_1_qn1);
        c2 = findViewById(R.id.answer_2_qn1);
        c3 = findViewById(R.id.answer_3_qn1);
        c4 = findViewById(R.id.answer_4_qn1);

        //getting ids for textfields
        text_field_answer2 = findViewById(R.id.quesion_2Edt);
        text_field_answer3 = findViewById(R.id.question_3Edt);

        //gettting ids for radiobuttons with right answers only
        rAnswer4 = findViewById(R.id.court_answer_qn4);
        rAnswer5 = findViewById(R.id.answer_qn5);

        //getting ImageView ids showing the right and wrong
        img_qn1 = findViewById(R.id.img_qn1);
        img_qn2 = findViewById(R.id.img_qn2);
        img_qn13 = findViewById(R.id.img_qn3);
        img_qn4 = findViewById(R.id.img_qn4);
        img_qn5 = findViewById(R.id.img_qn5);
        //img_qn6 = findViewById(R.id.img_qn4);

        //get the submit buttons id
        submit = findViewById(R.id.submit_btn);

    }
    /* a method that checks the edit text to try t match the text entered with the
     * the string stored in the  variable, this String is the correct answer*/

    private void edt2Answers() {
        //getting text from edit text and comparing wit with the answers
        answerFor2 = text_field_answer2.getText().toString().trim();

        if (answerFor2.equals(answers[0]) || answerFor2.equals(answers[1])) {
            img_qn2.setImageResource(R.drawable.passed);
            img_qn2.setVisibility(View.VISIBLE);
            totalMarks += 1;
            displayTotal(totalMarks);
        } else if (answerFor2.isEmpty()) {
            text_field_answer2.setError(getString(R.string.error_message));
        } else {
            img_qn2.setVisibility(View.VISIBLE);
            img_qn2.setImageResource(R.drawable.wrong);
        }
    }

    /* a method that checks the edit text to try t match the text entered with the
     * the string stored in the  variable, this String is the correct answer*/

    private void edt3Answers() {
        answerFor3 = text_field_answer3.getText().toString().trim();

        /* comparing the entered text with the text stored in the String variable
         * if its correct the passed imageview is displayed, its visibility is set to Visible */
        if (answerFor3.equals(answers[4]) || answerFor3.equals(answers[5])) {
            img_qn13.setImageResource(R.drawable.passed);
            img_qn13.setVisibility(View.VISIBLE);

            // add the correct answer to the counter of totalMarks and display it
            //cast it to the displayTotal
            totalMarks += 1;
            displayTotal(totalMarks);

            //if the textField is empty set an Error message that reminds the user the field cant be empty
        } else if (answerFor3.isEmpty()) {
            text_field_answer3.setError(getString(R.string.error_message));
        }

        /*if any other thing happens for example wrong answer, the wrong ImageVie is set Visible
         * to show that answer is wrong */
        else {
            img_qn13.setVisibility(View.VISIBLE);
            img_qn13.setImageResource(R.drawable.wrong);

        }
    }

    //method to display the total marks obtained over the overall marks
    private void displayTotal(int totalMarks) {
        //onclick submit button display toast t show the total marks
        Toast.makeText(this, getString(R.string.ttal_marks_wrd) + totalMarks + getString(R.string.overall_total_marks), Toast.LENGTH_LONG).show();
    }
}




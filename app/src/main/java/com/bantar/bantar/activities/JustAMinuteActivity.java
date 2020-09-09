package com.bantar.bantar.activities;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bantar.bantar.R;
import com.bantar.bantar.utils.ExceptionUtil;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import static android.view.animation.AnimationUtils.loadAnimation;

public class JustAMinuteActivity extends AppCompatActivity {

    private Button button;
    private TextView question;
    private ArrayList<String> wordList = new ArrayList<String>();
    private ArrayList<String> usedList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Just a Minute");
        setContentView(R.layout.activity_justaminute);

        initialize();
    }

    private void initialize() {
        loadQuestionList();
        button = (Button) findViewById(R.id.buttonQuestion);
        question = (TextView) findViewById(R.id.question);
        displayNewQuestion();
    }

    private void loadQuestionList() {
        wordList = readFile();
    }

    public ArrayList<String> readFile() {
        ArrayList<String> lines = new ArrayList<>();

        StringBuffer sb = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.questions_justaminute);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));


        String line = "";

        if (is != null) {
            try {
                while ((line = br.readLine()) != null)
                    lines.add(line);
                is.close();
            } catch (Exception e) {
                ExceptionUtil.handle(e);
            }
        }
        return lines;
    }

    // event handler
    public void onClick(View v) {
        if (!(v == button))
            return;

        animFadeOut();
        displayNewQuestion();
    }

    private void displayNewQuestion() {
        while (true) {
            String s = getNewQuestion();
            if (s.matches(question.getText() + ""))
                continue;

            question.setText(s);
            break;
        }
        animFadeIn();
    }

    private String getNewQuestion() {
        // need to check if we have any new questions_icebreakers available...
        if((wordList.size() < 1)) {
            resetWordList();
        }

        Random rand = new Random();
        int i = rand.nextInt(wordList.size());

        String question = wordList.get(i);
        usedList.add(question);
        wordList.remove(i);

        return question;
    }

    private void resetWordList() {
        for(String s: usedList)
            wordList.add(s);
        usedList.clear();
    }

    //region Animations
    private void animFadeIn() {
        Animation aniFade = loadAnimation(getApplicationContext(),R.anim.fade_in);
        question.startAnimation(aniFade);
    }

    private void animFadeOut() {
        Animation aniFade = loadAnimation(getApplicationContext(),R.anim.fade_out);
        question.startAnimation(aniFade);
    }
    //endregion
}

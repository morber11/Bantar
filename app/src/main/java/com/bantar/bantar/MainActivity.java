package com.bantar.bantar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import static android.view.animation.AnimationUtils.loadAnimation;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView question;
    private ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadQuestionList();
        button = (Button) findViewById(R.id.button);
        question = (TextView) findViewById(R.id.question);
        displayNewQuestion();
    }

    private void loadQuestionList() {
        list = readFile();
    }

    public ArrayList<String> readFile() {
        ArrayList<String> lines = new ArrayList<>();

        StringBuffer sb = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.questions);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));


        String line = "";

        if (is != null) {
            try {
                while ((line = br.readLine()) != null)
                    lines.add(line);
                is.close();
            } catch (Exception e) {
                System.out.println("An error occured");
                e.printStackTrace();
            }
        }
        return lines;
    }

    private void fadeIn() {
        Animation aniFade = loadAnimation(getApplicationContext(),R.anim.fade_in);
        question.startAnimation(aniFade);
    }

    private void fadeOut() {
        Animation aniFade = loadAnimation(getApplicationContext(),R.anim.fade_out);
        question.startAnimation(aniFade);
    }

    // event handler
    public void onClick(View v) {
        if (!(v == button))
            return;

        fadeOut();
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
        fadeIn();
    }

    private String getNewQuestion() {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}

package hmh.edu.myquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private QuizViewModel quizViewModel;
    private QuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new QuestionAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        quizViewModel.getQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                adapter.setQuestions(questions);
            }
        });

        Button finishButton = findViewById(R.id.finishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int score = calculateScore();
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        });
    }

    private int calculateScore() {
        int score = 0;
        for (int i = 0; i < adapter.getItemCount(); i++) {
            Question question = adapter.getQuestions().get(i);
            int selectedOption = adapter.getSelectedOption(i);
            if (selectedOption == question.getCorrectAnswerIndex()) {
                score++;
            }
        }
        return score;
    }
}
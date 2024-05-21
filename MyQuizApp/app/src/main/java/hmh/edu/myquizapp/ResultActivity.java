package hmh.edu.myquizapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreText = findViewById(R.id.score_text);
        int score = getIntent().getIntExtra("score", 0);
        scoreText.setText(getString(R.string.your_score) + " " + score);
    }
}

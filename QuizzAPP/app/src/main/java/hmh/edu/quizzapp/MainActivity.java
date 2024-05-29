package hmh.edu.quizzapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private LinearLayout startScreen, quizScreen;
    private TextView questionTitle, timerText;
    private Button answer1, answer2, answer3, answer4, nextButton, retryButton;
    private String[] questions;
    private String[][] answers;
    private int[] correctAnswers;
    private int currentQuestionIndex = 0;
    private int correctAnswersCount = 0;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        startScreen = findViewById(R.id.start_screen);
        quizScreen = findViewById(R.id.quiz_screen);
        questionTitle = findViewById(R.id.question_title);
        timerText = findViewById(R.id.timer_text);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        nextButton = findViewById(R.id.next_button);
        retryButton = findViewById(R.id.retry_button);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        questions = new String[]{
                "Ngôn ngữ lập trình nào được sử dụng để phát triển Android?",
                "Hệ điều hành nào không phải là hệ điều hành di động?",
                "XML viết tắt của cụm từ nào?",
                "API là viết tắt của từ gì?",
                "Framework nào được sử dụng để phát triển ứng dụng di động đa nền tảng?",
                "Thiết bị nào dưới đây không phải là một loại điện thoại thông minh?",
                "IDE nào phổ biến nhất để phát triển ứng dụng Android?",
                "Dịch vụ đám mây nào không phải của Google?",
                "Lệnh nào để in dòng văn bản trong Java?",
                "SQLite là gì?",
                "Android là hệ điều hành dựa trên?",
                "Ngôn ngữ lập trình nào không phổ biến để phát triển Android?",
                "Ứng dụng Android được đóng gói thành định dạng nào?",
                "UI là viết tắt của từ gì?",
                "HTTP là viết tắt của từ gì?",
                "JSON là viết tắt của từ gì?",
                "APK là viết tắt của từ gì?",
                "Java được phát triển bởi công ty nào?",
                "Firebase là một sản phẩm của công ty nào?",
                "Ngôn ngữ lập trình nào không phải là ngôn ngữ lập trình web?"
        };

        answers = new String[][]{
                {"Java", "Kotlin", "Python", "Swift"},
                {"Android", "Windows", "iOS", "Blackberry"},
                {"Example Markup Language", "eXtra Modern Link", "eXtensible Markup Language", "X-Markup Language"},
                {"Application Programming Interface", "Advanced Programming Interface", "Application Process Integration", "None of the above"},
                {"React Native", "Django", "Flask", "Ruby on Rails"},
                {"Samsung Galaxy", "Nokia 3310", "iPhone", "OnePlus"},
                {"Android Studio", "Eclipse", "IntelliJ IDEA", "NetBeans"},
                {"Google Drive", "iCloud", "Microsoft Azure", "Firebase"},
                {"print()", "echo()", "console.log()", "System.out.println()"},
                {"Database", "Programming language", "Library", "Framework"},
                {"Linux", "Windows", "iOS", "MacOS"},
                {"Python", "Java", "Kotlin", "C++"},
                {"JAR", "EXE", "APK", "DLL"},
                {"User Interaction", "User Interface", "Unique Identifier", "Universal Interface"},
                {"HyperText Transfer Protocol", "HyperTransfer Text Protocol", "HighText Transfer Protocol", "HighTransfer Text Protocol"},
                {"JavaScript Object Notation", "JavaScript Object Network", "JavaScript Oriented Notation", "JavaScript Online Notation"},
                {"Android Package", "Android Package Kit", "Application Package Kit", "Application Package"},
                {"Microsoft", "Apple", "Sun Microsystems", "Google"},
                {"Apple", "Amazon", "Microsoft", "Google"},
                {"HTML", "CSS", "JavaScript", "C++"}
        };

        correctAnswers = new int[]{
                0, 1, 2, 0, 0, 1, 0, 1, 3, 0, 0, 0, 2, 1, 0, 0, 1, 2, 3, 3
        };

        findViewById(R.id.start_button).setOnClickListener(v -> {
            startScreen.setVisibility(View.GONE);
            quizScreen.setVisibility(View.VISIBLE);
            startTimer();
            showQuestion();
        });

        nextButton.setOnClickListener(v -> {
            currentQuestionIndex++;
            resetAnswerButtons();
            if (currentQuestionIndex < questions.length) {
                showQuestion();
            } else {
                showFinalScore();
            }
        });

        retryButton.setOnClickListener(v -> {
            currentQuestionIndex = 0;
            correctAnswersCount = 0;
            resetAnswerButtons();
            retryButton.setVisibility(View.GONE);
            answer1.setVisibility(View.VISIBLE);
            answer2.setVisibility(View.VISIBLE);
            answer3.setVisibility(View.VISIBLE);
            answer4.setVisibility(View.VISIBLE);
            startTimer();
            showQuestion();
        });

        answer1.setOnClickListener(v -> checkAnswer(0, answer1));
        answer2.setOnClickListener(v -> checkAnswer(1, answer2));
        answer3.setOnClickListener(v -> checkAnswer(2, answer3));
        answer4.setOnClickListener(v -> checkAnswer(3, answer4));
    }

    private void showQuestion() {
        questionTitle.setText(questions[currentQuestionIndex]);
        answer1.setText(answers[currentQuestionIndex][0]);
        answer2.setText(answers[currentQuestionIndex][1]);
        answer3.setText(answers[currentQuestionIndex][2]);
        answer4.setText(answers[currentQuestionIndex][3]);
        nextButton.setVisibility(View.VISIBLE);
    }

    private void resetAnswerButtons() {
        answer1.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        answer2.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        answer3.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        answer4.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
    }

    private void checkAnswer(int answerIndex, Button selectedButton) {
        if (answerIndex == correctAnswers[currentQuestionIndex]) {
            selectedButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            correctAnswersCount++;
            Toast.makeText(this, "ĐÚNG!", Toast.LENGTH_SHORT).show();
        } else {
            selectedButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            Toast.makeText(this, "SAI!", Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;
        resetAnswerButtons();
        if (currentQuestionIndex < questions.length) {
            showQuestion();
        } else {
            showFinalScore();
        }
    }

    private void showFinalScore() {
        nextButton.setVisibility(View.GONE);
        questionTitle.setText("HOÀN THÀNH CÁC CÂU HỎI\nSố câu đúng: " + correctAnswersCount + " / " + questions.length);
        answer1.setVisibility(View.GONE);
        answer2.setVisibility(View.GONE);
        answer3.setVisibility(View.GONE);
        answer4.setVisibility(View.GONE);
        retryButton.setVisibility(View.VISIBLE);
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerText.setText("Thời gian còn lại: " + millisUntilFinished / 1000 + " giây");
            }

            public void onFinish() {
                timerText.setText("Hết giờ!");
                showFinalScore();
            }
        }.start();
    }
}
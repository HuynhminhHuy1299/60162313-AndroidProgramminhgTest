package hmh.edu.myquizapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class QuizViewModel extends ViewModel {
    private MutableLiveData<List<Question>> questions;

    public QuizViewModel() {
        questions = new MutableLiveData<>();
        loadQuestions();
    }

    public LiveData<List<Question>> getQuestions() {
        return questions;
    }

    private void loadQuestions() {
        List<Question> questionList = new ArrayList<>();
        questionList.add(new Question("Câu hỏi 1", List.of("Lựa chọn 1", "Lựa chọn 2", "Lựa chọn 3", "Lựa chọn 4"), 0));
        questionList.add(new Question("Câu hỏi 2", List.of("Lựa chọn 1", "Lựa chọn 2", "Lựa chọn 3", "Lựa chọn 4"), 1));
        questionList.add(new Question("Câu hỏi 3", List.of("Lựa chọn 1", "Lựa chọn 2", "Lựa chọn 3", "Lựa chọn 4"), 2));
        questions.setValue(questionList);
    }
}

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
        questionList.add(new Question("Đuôi thì chẳng thấy, mà có hai đầu?", List.of("Cây cầu", "Con gà", "Con chó", "Con rắn"), 0));
        questionList.add(new Question("Vào tháng nào con người sẽ ngủ ít nhất trong năm?", List.of("Tháng 1", "Tháng 2", "Tháng 12", "Tháng 8"), 1));
        questionList.add(new Question("Loại xe không có bánh thường thấy ở đâu?", List.of("Nhà", "Xưởng", "Bàn cờ vua", "Siêu thị"), 2));
        questions.setValue(questionList);
    }
}

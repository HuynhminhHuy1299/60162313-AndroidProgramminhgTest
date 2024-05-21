package hmh.edu.myquizapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    private List<Question> questions = new ArrayList<>();
    private List<Integer> selectedOptions = new ArrayList<>();

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        return new QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question currentQuestion = questions.get(position);
        holder.bind(currentQuestion, position);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        selectedOptions.clear();
        for (int i = 0; i < questions.size(); i++) {
            selectedOptions.add(-1);
        }
        notifyDataSetChanged();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getSelectedOption(int position) {
        return selectedOptions.get(position);
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        private TextView questionText;
        private RadioGroup optionsGroup;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.question_text);
            optionsGroup = itemView.findViewById(R.id.options_group);
        }

        public void bind(Question question, int position) {
            questionText.setText(question.getQuestionText());
            optionsGroup.removeAllViews();
            for (int i = 0; i < question.getOptions().size(); i++) {
                RadioButton radioButton = new RadioButton(itemView.getContext());
                radioButton.setText(question.getOptions().get(i));
                radioButton.setId(i);
                optionsGroup.addView(radioButton);
            }

            optionsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    selectedOptions.set(position, checkedId);
                }
            });
        }
    }
}

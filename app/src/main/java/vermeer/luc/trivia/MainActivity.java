package vermeer.luc.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TriviaHelper.Callback{
    public Player player;
    private Question currentQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        player = (Player) intent.getSerializableExtra("player");

        TextView scoreView = findViewById(R.id.scoreView);
        TextView livesView = findViewById(R.id.livesView);

        scoreView.setText("Score: " + String.valueOf(player.getScore()));
        livesView.setText("Lives: " + String.valueOf(player.getLives()));

        TriviaHelper triviaHelper = new TriviaHelper(this);
        triviaHelper.getQuestion(this);
    }

    @Override
    public void gotQuestion(Question question) {
        this.currentQuestion = question;
        String questionText = question.getQuestion();
//        String categoryText = question.getCategory();

        TextView questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(questionText);
    }

    @Override
    public void gotQuestionError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void enterAnswer(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        boolean correctAnswer = currentQuestion.getAnswer();
//        Toast.makeText(this, radioButton.getText(), Toast.LENGTH_LONG).show();
        if (radioButton.getText() == "True") {
            rightAnswer();
        } else if (radioButton.getText() == "False") {
            rightAnswer();
        } else {
            wrongAnswer();
        }
        startActivity(intent);
        finish();
    }

    private void wrongAnswer(){
        Toast.makeText(this, "False!", Toast.LENGTH_LONG).show();
        player.setLives(player.getLives() - 1);
        if (player.getLives() == 0) {
            // end game
        }
    }

    private void rightAnswer(){
        Toast.makeText(this, "Correct!", Toast.LENGTH_LONG).show();
        player.setScore(player.getScore() + 1);
    }

}

package vermeer.luc.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();
        Player player = (Player) intent.getSerializableExtra("player");
        TextView score_text = findViewById(R.id.highScoreView);
        score_text.setText("Your score is " + String.valueOf(player.getScore()));
    }


}

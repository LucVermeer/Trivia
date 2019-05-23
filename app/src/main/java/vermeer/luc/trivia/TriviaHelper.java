package vermeer.luc.trivia;

import android.content.Context;
import android.text.Html;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TriviaHelper implements Response.Listener<JSONObject>, Response.ErrorListener {
    private String url = "https://opentdb.com/api.php?amount=1&type=boolean";
    private Context context;
    private Callback activity;

    public TriviaHelper(Context context) {
        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotQuestionError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try{
            JSONArray jsonArray = response.getJSONArray("results");
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            String questionString = fixString(jsonObject.getString("question"));
            boolean correctAnswer = jsonObject.getBoolean("correct_answer");
            String category = jsonObject.getString("category");

            Question question = new Question(questionString, correctAnswer, category);
            activity.gotQuestion(question);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public String fixString(String string) {
        string = string.replace("&quot;", "\"");
        string = string.replace("&#039;", "'");
        return string;
    }

    public interface Callback {
        void gotQuestion(Question question);
        void gotQuestionError(String message);
    }

    void getQuestion(Callback activity){
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                this, this);
        queue.add(jsonObjectRequest);
    }
}

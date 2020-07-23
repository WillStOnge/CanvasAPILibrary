package cavnas.controllers;

import cavnas.utils.structs.QuizSubmissionQuestion;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.util.List;


public class QuizSubmissionQuestionController extends Controller
{
    /**
     * @param canvasUrl URL to your canvas instance (Ex. https://test.instructure.com)
     * @param token     Bearer token used to authenticate with the Canvas API
     * @param submissionId  Submission you want to retrieve
     * @return Returns list of all submissions for this quiz. If there is an error, it will return null.
     **/
    public static List<QuizSubmissionQuestion> getQuizSubmissionQuestions(String canvasUrl, String token, Integer submissionId)
    {
        String json;

        try
        {
            URL url = new URL(canvasUrl + "/api/v1/quiz_submissions/" + submissionId + "/questions/" );
            json = run(Method.GET, url, token, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

        return new Gson().fromJson(json, QuizSubmissionQuestions.class).quiz_submission_questions;
    }

    /**
     * Private inner class for converting JSON into a QuizSubmission object properly.
     */
    private class QuizSubmissionQuestions
    {
        public List<QuizSubmissionQuestion> quiz_submission_questions;
    }
}

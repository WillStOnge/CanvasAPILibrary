package cavnas.controllers;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cavnas.utils.structs.Quiz;
import cavnas.utils.structs.QuizSubmissions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class QuizSubmissionController extends Controller
{
     /**
     * @param canvasUrl URL to your canvas instance (Ex. https://test.instructure.com)
     * @param token     Bearer token used to authenticate with the Canvas API
     * @param courseId  Course which contains the desired quiz
     * @param quizId    Quiz you want to get
     * @param submissionId  the submission ID (Ex. 1182288)
     * @return Returns list of all submissions for this quiz, Users who can view or manage grades for a course will have submissions from multiple users returned. A user who can only submit will have only their own submissions returned. When a user has an in-progress submission, only that submission is returned. When there isn't an in-progress quiz_submission, all completed submissions, including previous attempts, are returned.
     **/
    public static QuizSubmissions getQuizSubmission(String canvasUrl, String token, Integer courseId, Integer quizId, Integer submissionId)
    {
        String json;
        //Type quizSubmissionList = new TypeToken<List<QuizSubmissions>>(){}.getType();

        try
        {
            URL url = new URL(canvasUrl + "/api/v1/courses/" + courseId + "/quizzes/" + quizId + "/submissions/" + submissionId);
            json = run(Method.GET, url, token, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

        //ArrayList<QuizSubmissions> list = new Gson().fromJson(json, QuizSubmissions.class);
        System.out.println(json);
        return new Gson().fromJson(json, QuizSubmissions.class);

    }
}

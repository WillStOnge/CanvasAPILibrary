package cavnas.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.List;
import cavnas.utils.structs.QuizSubmission;
import com.google.gson.Gson;

public class QuizSubmissionController extends Controller
{
    /**
     * @param canvasUrl    URL to your canvas instance (Ex. https://test.instructure.com)
     * @param token        Bearer token used to authenticate with the Canvas API
     * @param courseId     Course which contains the desired quiz
     * @param quizId       Quiz you want to get the submission from
     * @param submissionId Submission you want to retrieve
     * @return Returns list of all submissions for this quiz. If there is an error, it will return null.
     **/
    public static QuizSubmission getQuizSubmission(String canvasUrl, String token, Integer courseId, Integer quizId, Integer submissionId)
    {
        String json;

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

        QuizSubmissions submissions = new Gson().fromJson(json, QuizSubmissions.class);

        if (submissions.quiz_submissions.size() > 0)
            return submissions.quiz_submissions.get(0);
        return null;
    }

    /**
     * @param canvasUrl URL to your canvas instance (Ex. https://test.instructure.com)
     * @param token     Bearer token used to authenticate with the Canvas API
     * @param courseId  Course which contains the desired quiz
     * @param quizId    Quiz you want to get the submission from
     * @return Returns list of all submissions for this quiz. If there is an error, it will return null.
     **/
    public static List<QuizSubmission> getQuizSubmissions(String canvasUrl, String token, Integer courseId, Integer quizId)
    {
        String json;

        try
        {
            URL url = new URL(canvasUrl + "/api/v1/courses/" + courseId + "/quizzes/" + quizId + "/submissions");
            json = run(Method.GET, url, token, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

        return new Gson().fromJson(json, QuizSubmissions.class).quiz_submissions;
    }

    /**
     * @param canvasUrl URL to your canvas instance (Ex. https://test.instructure.com)
     * @param token     Bearer token used to authenticate with the Canvas API
     * @param courseId  Course which contains the desired quiz
     * @param quizId    Quiz the submission is from
     * @param submisId  ID of the submission object
     * @return Returns a QuizSubmissionTime object with the end_at parameter and the time_left parameter
     */
    public static QuizSubmissionTime getCurrentQuizSubmissionTimes(String canvasUrl, String token, Integer courseId, Integer quizId, Integer submisId)
    {
        String json;
        try
        {
            URL url = new URL(canvasUrl + "/api/v1/courses/" + courseId + "/quizzes/" + quizId + "/submissions" + submisId + "/time");
            json = run(Method.GET, url, token, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

        return new Gson().fromJson(json, QuizSubmissionTime.class);
    }

    /**
     * Private inner class for converting JSON into a QuizSubmission object properly.
     */
    private class QuizSubmissions
    {
        public List<QuizSubmission> quiz_submissions;
    }

    /**
     * Public inner class for returning submission times
     */
    public class QuizSubmissionTime
    {
        public ZonedDateTime end_at;
        public int time_left;
    }
}
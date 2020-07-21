package cavnas.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import cavnas.utils.structs.Quiz;
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
        } catch (IOException e)
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
     * Private inner class for converting JSON into a QuizSubmission object properly.
     */
    private class QuizSubmissions
    {
        public List<QuizSubmission> quiz_submissions;
    }

    /**
     * @param canvasUrl URL to your canvas instance (Ex. https://test.instructure.com)
     * @param token     Bearer token used to authenticate with the Canvas API
     * @param courseId  Course which contains the desired quiz
     * @param quizId    Quiz you want to get the submission from
     * @return Returns list of all submissions for this quiz based on the user permissions. If there is an error, it will return null.
     **/
    public static QuizSubmission getQuizSubmissions(String canvasUrl, String token, Integer courseId, Integer quizId)
    {
        String json;
        Quiz.Permissions permissions = new Quiz().new Permissions();
        Quiz.LockInfo lockInfo = new Quiz().new LockInfo();

        try
        {
            URL url = new URL(canvasUrl + "/api/v1/courses/" + courseId + "/quizzes/" + quizId + "/submissions");
            json = run(Method.GET, url, token, null);
        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

        QuizSubmissions submissions = new Gson().fromJson(json, QuizSubmissions.class);

        /**
         * will return all submissions if user has manage or view permission
         * will return single submission by user who can only submit
         * will return a specific submission if a user has in_progress submissions
         * will return all completed submissions including previous attempts when there is no an in_progress quiz_submission
        **/
        if ( submissions.quiz_submissions.size() > 0 )
            if ( permissions.manage || lockInfo.can_view )
                return (QuizSubmission) submissions.quiz_submissions;
            else if ( permissions.submit )
                return submissions.quiz_submissions.get(0);
            else if (/*use has in_progress submissions*/)
                return /*that specific submission that is still in_progress*/
        return null;

    }

}
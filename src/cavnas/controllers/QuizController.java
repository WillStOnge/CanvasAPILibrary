package cavnas.controllers;

import java.io.IOException;
import java.net.URL;

/**
 * Controller for all operations on quizzes in Canvas.
 */
public class QuizController extends Controller
{
    /**
     * @param canvasUrl URL to your canvas instance (Ex. https://test.instructure.com)
     * @param token     Bearer token used to authenticate with the Canvas API
     * @param courseId  Course which contains the desired quiz
     * @param quizId    Quiz you want to delete
     * @return Returns true if the deletion was successful, false if not.
     */
    public static boolean deleteQuiz(String canvasUrl, String token, Integer courseId, Integer quizId)
    {
        try
        {
            URL url = new URL(canvasUrl + "/api/v1/courses/" + courseId + "/quizzes/" + quizId);
            run(Method.DELETE, url, token, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }


}
package cavnas.controllers;

import cavnas.utils.structs.Quiz;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

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

    /**
     * @param canvasUrl URL to your canvas instance (Ex. https://test.instructure.com)
     * @param token     Bearer token used to authenticate with the Canvas API
     * @param courseId  Course which contains the desired quiz
     * @param quizId    Quiz you want to get
     * @return Returns the quiz requested, if there is an error, the method will return null.
     */
    public static Quiz getQuiz(String canvasUrl, String token, Integer courseId, Integer quizId)
    {
        String json;

        try
        {
            URL url = new URL(canvasUrl + "/api/v1/courses/" + courseId + "/quizzes/" + quizId);
            json = run(Method.GET, url, token, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

        return new Gson().fromJson(json, Quiz.class);
    }

    /**
     * @param canvasUrl  URL to your canvas instance (Ex. https://test.instructure.com)
     * @param token      Bearer token used to authenticate with the Canvas API
     * @param courseId   Course which contains the desired quizzes
     * @param searchTerm The partial title of the quizzes to match and return
     * @return Returns an array list of quizzes containing the search term, if there is an error, returns null
     */
    public static ArrayList<Quiz> getQuizzes(String canvasUrl, String token, Integer courseId, String searchTerm)
    {
        String uRL = canvasUrl + "/api/v1/courses/" + courseId + "/quizzes";
        ArrayList<Quiz> quizArrayList;
        if(!searchTerm.equals(""))
        {
            uRL = uRL + "?search_term=" + searchTerm;
        }
        try
        {
            URL url = new URL(uRL);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = run(Method.GET, url, token, null);
            Quiz[] quizzes = gson.fromJson(jsonString, Quiz[].class);
            quizArrayList = new ArrayList<>(quizzes.length);
            for(int x = 0; x < quizzes.length; x++)
            {
                quizArrayList.add(quizzes[0]);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        return quizArrayList;
    }
}
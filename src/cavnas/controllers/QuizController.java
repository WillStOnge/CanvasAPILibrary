package cavnas.controllers;

import cavnas.utils.structs.Quiz;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;

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
     * @param searchTerm The partial title of the quizzes to match and return, if no search term wanted use null
     * @param page       Which page of the list to return. If null, defaults to 1
     * @param perPage    The number of quizzes per page. If null, defaults to 10
     * @return Returns a list of quizzes containing the search term, if there is an error, returns null
     */
    public static List<Quiz> getQuizzes(String canvasUrl, String token, Integer courseId, String searchTerm, Integer page, Integer perPage)
    {
        Type quizList = new TypeToken<List<Quiz>>(){}.getType();
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        String json;

        params.put("search_term", searchTerm);
        params.put("per_page", perPage.toString());
        params.put("page", page.toString());

        try
        {
            URL url = new URL(canvasUrl + "/api/v1/courses/" + courseId + "/quizzes");
            json = run(Method.GET, url, token, params);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

        return new Gson().fromJson(json, quizList);
    }

    /**
     * @param   canvasUrl URL to your canvas instance (Ex. https://test.instructure.com)
     * @param   token     Bearer token used to authenticate with the Canvas API
     * @param   courseId  Course which contains the desired quizzes
     * @param   quizId    Quiz that you would like to reorder
     * @param   orderId   REQUIRED: the associated item's unique identifier
     * @param   orderType The type of item is either 'question' or 'group', these are the only allowed values
     * @return  Returns true if the reorder was successful, false if not.
     */
    public static boolean postReorderQuizItems(String canvasUrl, String token, Integer courseId, Integer quizId, Integer orderId, String orderType)
    {
        try
        {
            URL url = new URL(canvasUrl + "/api/v1/courses/" + courseId + "/quizzes/" + quizId + "/reorder");
            LinkedHashMap<String, String> params = new LinkedHashMap<>();

            params.put("order[][id]", orderId.toString());
            params.put("order[][type]", orderType.toString());

            run(Method.POST, url, token, params);
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
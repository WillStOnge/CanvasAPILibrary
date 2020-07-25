package cavnas.controllers;

import cavnas.utils.structs.Quiz;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
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

    /**
     * @param   canvasUrl                   URL to your canvas instance (Ex. https://test.instructure.com)
     * @param   token                       Bearer token used to authenticate with the Canvas API
     * @param   courseId                    Course which contains the desired quizzes
     * @param   title                       REQUIRED: The Quiz Title
     * @param   description                 A description of the quiz
     * @param   quizType                    The type of quiz ALLOWED VALUES: practice_quiz, assignment, graded_survey, survey
     * @param   assignmentGroupID           The assignment group id to put the assignment in. Defaults to the top assignment group in the course. Only valid if the quiz is graded, i.e. if quiz_type is “assignment” or “graded_survey”.
     * @param   timeLimit                   Time limit to take this quiz, in minutes. Set to null for no time limit. Defaults to null.
     * @param   shuffleAnswers              If true, quiz answers for multiple choice questions will be randomized for each student. Defaults to false.
     * @param   hideResults                 Dictates if quiz results are hidden from students. Defaults to null, which students can see the results. Other values are: always, until_after_last_attempt
     * @param   showCorrectAnswers          Only valid if hide_results=null If false, hides correct answers from students when quiz results are viewed. Defaults to true
     * @param   showCorrectAnsLastAttempt   Only valid if showCorrectAnswers=true and allowed_attempts > 1 If true, hides correct answers from students when quiz results are viewed until they submit the last attempt for the quiz. Defaults to false.
     * @param   showCorrectAnswersAt        Only valid if showCorrectAnswers=true. Correct answers will be visible after this date. If not set answers are shown after quiz submission
     * @param   hideCorrectAnswersAt        Only valid if showCorrectAnswers=true. Correct answers will be hidden after this date. If not set answers are shown indefinitely
     * @param   allowedAttempts             Number of attempts allowed. Set to -1 for unlimited. Defaults to 1
     * @param   scoringPolicy               REQUIRED: only valid if allowedAttempts > 1. Scoring policy for quiz with multiple attempts. Allowed values: keep_highest, keep_latest
     * @param   oneQuestionAtATime          If true, shows quiz to student one question at a time. Defaults to false.
     * @param   cantGoBack                  Only valid if oneQuestionAtATime = true. Questions are locked after answering. Defaults to false.
     * @param   accessCode                  Restricts access to quiz with password. Set to null for no access code. Defaults to null
     * @param   ipFilter                    Restricts access to quiz to computers in a specified IP range. Filters can be a comma-separated list of addresses, or an address followed by a mask.
     * @param   dueAt                       The day/time the quiz is due Accepts times in ISO 8601 format, e.g. 2011-10-21T18:48Z.
     * @param   lockAt                      The day/time the quiz is locked for students Accepts times in ISO 8601 format, e.g. 2011-10-21T18:48Z.
     * @param   unlockAt                    The day/time the quiz is unlocked for students. Accepts times in ISO 8601 format, e.g. 2011-10-21T18:48Z.
     * @param   published                   Whether the quiz should have a draft state of published/unpublished. If students are taking the quiz you can't unpublish the quiz
     * @param   oneTimeResults              Whether students should be prevented from viewing their quiz results past the first time. hideResults must not be set to "always". Defaults to false.
     * @param   onlyVisibleToOverrides      Whether this quiz is only visible to overrides. Defaults to false
     * @return  Returns a Quiz
     */
    public static Quiz postCreateAQuiz(String canvasUrl, String token, Integer courseId, String title, String description, String quizType, Integer assignmentGroupID, Integer timeLimit, Boolean shuffleAnswers, String hideResults, Boolean showCorrectAnswers, Boolean showCorrectAnsLastAttempt, ZonedDateTime showCorrectAnswersAt, ZonedDateTime hideCorrectAnswersAt, Integer allowedAttempts, String scoringPolicy, Boolean oneQuestionAtATime, Boolean cantGoBack, String accessCode, String ipFilter, ZonedDateTime dueAt, ZonedDateTime lockAt, ZonedDateTime unlockAt, Boolean published, Boolean oneTimeResults, Boolean onlyVisibleToOverrides)
    {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        String json;

        params.put("quiz[title]", title);
        params.put("quiz[description]", description);
        params.put("quiz[quiz_type]", quizType);
        params.put("quiz[assignment_group_id]", assignmentGroupID.toString());
        params.put("quiz[time_limit]", timeLimit.toString());
        params.put("quiz[hide_results]", hideResults);
        params.put("quiz[show_correct_answers]", showCorrectAnswers.toString());
        params.put("quiz[show_correct_answers_last_attempt]", showCorrectAnsLastAttempt.toString());
        params.put("quiz[show_correct_answers_at]", showCorrectAnswersAt.truncatedTo(ChronoUnit.MINUTES).toString());
        params.put("quiz[hide_correct_answers_at]", hideCorrectAnswersAt.truncatedTo(ChronoUnit.MINUTES).toString());
        params.put("quiz[allowed_attempts]", allowedAttempts.toString());
        params.put("quiz[scoring_policy]", scoringPolicy);
        params.put("quiz[one_question_at_a_time]", oneQuestionAtATime.toString());
        params.put("quiz[cant_go_back]", cantGoBack.toString());
        params.put("quiz[access_code]", accessCode);
        params.put("quiz[ip_filter]", ipFilter);
        params.put("quiz[due_at]", dueAt.truncatedTo(ChronoUnit.MINUTES).toString());
        params.put("quiz[lock_at]", lockAt.truncatedTo(ChronoUnit.MINUTES).toString());
        params.put("quiz[unlock_at]", unlockAt.truncatedTo(ChronoUnit.MINUTES).toString());
        params.put("quiz[published]", published.toString());
        params.put("quiz[one_time_results]", oneTimeResults.toString());
        params.put("quiz[only_visible_to_overrides]", onlyVisibleToOverrides.toString());

        try
        {
            URL url = new URL(canvasUrl + "/api/v1/courses/" + courseId + "/quizzes");
            json = run(Method.POST, url, token, params);
            return new Gson().fromJson(json, Quiz.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
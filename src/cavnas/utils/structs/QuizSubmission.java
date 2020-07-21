package cavnas.utils.structs;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class QuizSubmission
{
    public int id;
    @SerializedName("quiz_id")
    public int quizId;
    @SerializedName("quiz_version")
    public int quizVersion;
    @SerializedName("user_id")
    public int userId;
    @SerializedName("submission_id")
    public int submissionId;
    public double score;
    @SerializedName("kept_score")
    public double keptScore;
    @SerializedName("started_at")
    public String startedAt;
    @SerializedName("end_at")
    public Date endAt;
    @SerializedName("finished_at")
    public Date finishedAt;
    public int attempt;
    @SerializedName("workflow_state")
    public String workflowState;
    @SerializedName("fudge_points")
    public double fudgePoints;
    @SerializedName("quiz_points_possible")
    public double quizPointsPossible;
    @SerializedName("extra_attempts")
    public int extraAttempts;
    @SerializedName("extra_time")
    public Date extraTime;
    @SerializedName("manually_unlocked")
    public boolean manuallyUnlocked;
    @SerializedName("validation_token")
    public String validationToken;
    @SerializedName("score_before_regrade")
    public double scoreBeforeRegrade;
    @SerializedName("has_seen_results")
    public boolean hasSeenResults;
    @SerializedName("time_spent")
    public int timeSpent;
    @SerializedName("attempts_left")
    public int attemptsLeft;
    @SerializedName("overdue_and_needs_submission")
    public boolean overdueAndNeedsSubmission;
    @SerializedName("excused?")
    public boolean excused;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("result_url")
    public String resultUrl;
}

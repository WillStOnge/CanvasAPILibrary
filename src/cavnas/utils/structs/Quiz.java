package cavnas.utils.structs;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Container for a quiz in Canvas.
 */
public class Quiz
{
    public int id;
    public String title;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("mobile_url")
    public String mobileUrl;
    public String description;
    @SerializedName("quiz_type")
    public String quizType;
    @SerializedName("time_limit")
    public int timeLimit;
    @SerializedName("timer_autosubmit_disabled")
    public boolean timerAutosubmitDisabled;
    @SerializedName("shuffle_answers")
    public boolean shuffleAnswers;
    @SerializedName("show_correct_answers")
    public boolean showCorrectAnswers;
    @SerializedName("scoring_policy")
    public String scoringPolicy;
    @SerializedName("allowed_attempts")
    public int allowedAttempts;
    @SerializedName("one_question_at_a_time")
    public boolean oneQuestionAtATime;
    @SerializedName("question_count")
    public int questionCount;
    @SerializedName("points_possible")
    public double pointsPossible;
    @SerializedName("cantGoBack")
    public boolean cant_go_back;
    @SerializedName("ip_filter")
    public String ipFilter;
    @SerializedName("due_at")
    public Date dueAt;
    @SerializedName("lock_at")
    public Date lockAt;
    @SerializedName("unlock_at")
    public Date unlockAt;
    public boolean published;
    @SerializedName("lockedForUser")
    public boolean locked_for_user;
    @SerializedName("lockInfo")
    public LockInfo lock_info;
    @SerializedName("lock_explanation")
    public String lockExplanation;
    @SerializedName("hide_results")
    public String hideResults;
    @SerializedName("showCorrectAnswersAt")
    public Date show_correct_answers_at;
    @SerializedName("hideCorrectAnswersAt")
    public Date hide_correct_answers_at;
    @SerializedName("all_dates")
    public List<AllDate> allDates;
    @SerializedName("can_update")
    public boolean canUpdate;
    @SerializedName("require_lockdown_browser")
    public boolean requireLockdownBrowser;
    @SerializedName("require_lockdown_browser_for_results")
    public boolean requireLockdownBrowserForResults;
    @SerializedName("require_lockdown_browser_monitor")
    public boolean requireLockdownBrowserMonitor;
    @SerializedName("lockdown_browser_monitor_data")
    public String lockdownBrowserMonitorData;
    public Permissions permissions;
    @SerializedName("quiz_reports_url")
    public String quizReportsUrl;
    @SerializedName("quiz_statistics_url")
    public String quizStatisticsUrl;
    @SerializedName("quiz_submission_versions_html_url")
    public String quizSubmissionVersionsHtmlUrl;
    @SerializedName("assignment_id")
    public int assignmentId;
    @SerializedName("one_time_results")
    public boolean oneTimeResults;
    @SerializedName("assignment_group_id")
    public int assignmentGroupId;
    @SerializedName("show_correct_answers_last_attempt")
    public boolean showCorrectAnswersLastAttempt;
    @SerializedName("version_number")
    public int versionNumber;
    @SerializedName("has_access_code")
    public boolean hasAccessCode;
    @SerializedName("post_to_sis")
    public boolean postToSis;
    @SerializedName("migration_id")
    public String migrationId;
    @SerializedName("question_types")
    public List<String> questionTypes;

    /**
     * Lock information for a quiz.
     */
    public class LockInfo
    {
        @SerializedName("lock_at")
        public Date lockAt;
        @SerializedName("can_view")
        public boolean canView;
        @SerializedName("asset_string")
        public String assetString;
    }

    /**
     * Element of the all_dates list which specifies when a quiz will lock/unlock and when its due.
     */
    public class AllDate
    {
        @SerializedName("due_at")
        public Date dueAt;
        @SerializedName("unlock_at")
        public Date unlockAt;
        @SerializedName("lock_at")
        public Date lockAt;
        public boolean base;
    }

    /**
     * Permissions for the accessor of the quiz.
     */
    public class Permissions
    {
        public boolean manage;
        public boolean read;
        public boolean update;
        public boolean create;
        public boolean submit;
        public boolean preview;
        public boolean delete;
        @SerializedName("read_statistics")
        public boolean readStatistics;
        public boolean grade;
        @SerializedName("review_grades")
        public boolean reviewGrades;
        @SerializedName("view_answer_audits")
        public boolean viewAnswerAudits;
    }
}
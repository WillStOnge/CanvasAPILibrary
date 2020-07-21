package cavnas.utils.structs;

import cavnas.controllers.QuizSubmissionController;

import java.util.Date;
import java.util.List;

public class QuizSubmissions
{

    public List<QuizSubmission> quiz_submissions;



    public class QuizSubmission
    {
        public Integer id;
        public Integer quizId;
        public Integer quizVersion;
        public Integer userId;
        public Integer submissionId;
        public Double score;
        public Double keptScore;
        public String startedAt;
        public Date endAt;
        public Date finishedAt;
        public Integer attempt;
        public String workflowState;
        public Double fudgePoints;
        public Double quizPointsPossible;
        public Integer extraAttempts;
        public Date extraTime;
        public Boolean manuallyUnlocked;
        public String validationToken;
        public Double scoreBeforeRegrade;
        public Boolean hasSeenResults;
        public Date timeSpent;
        public Integer attemptsLeft;
        public Boolean overdueAndNeedsSubmission;
        public Boolean excused;
        public String htmlUrl;

        @Override
        public String toString()
        {
            return "QuizSubmission{" +
                    "id=" + id +
                    ", quizId=" + quizId +
                    ", quizVersion=" + quizVersion +
                    ", userId=" + userId +
                    ", submissionId=" + submissionId +
                    ", score=" + score +
                    ", keptScore=" + keptScore +
                    ", startedAt='" + startedAt + '\'' +
                    ", endAt=" + endAt +
                    ", finishedAt=" + finishedAt +
                    ", attempt=" + attempt +
                    ", workflowState='" + workflowState + '\'' +
                    ", fudgePoints=" + fudgePoints +
                    ", quizPointsPossible=" + quizPointsPossible +
                    ", extraAttempts=" + extraAttempts +
                    ", extraTime=" + extraTime +
                    ", manuallyUnlocked=" + manuallyUnlocked +
                    ", validationToken='" + validationToken + '\'' +
                    ", scoreBeforeRegrade=" + scoreBeforeRegrade +
                    ", hasSeenResults=" + hasSeenResults +
                    ", timeSpent=" + timeSpent +
                    ", attemptsLeft=" + attemptsLeft +
                    ", overdueAndNeedsSubmission=" + overdueAndNeedsSubmission +
                    ", excused=" + excused +
                    ", htmlUrl='" + htmlUrl + '\'' +
                    '}';
        }
    }
}


package cavnas.utils.structs;

import java.util.Date;
import java.util.List;

/**
 * Container for a quiz in Canvas.
 */
public class Quiz
{
    public int id;
    public String title;
    public String html_url;
    public String mobile_url;
    public String description;
    public String quiz_type;
    public int time_limit;
    public boolean timer_autosubmit_disabled;
    public boolean shuffle_answers;
    public boolean show_correct_answers;
    public String scoring_policy;
    public int allowed_attempts;
    public boolean one_question_at_a_time;
    public int question_count;
    public double points_possible;
    public boolean cant_go_back;
    public String ip_filter;
    public Date due_at;
    public Date lock_at;
    public Date unlock_at;
    public boolean published;
    public boolean locked_for_user;
    public LockInfo lock_info;
    public String lock_explanation;
    public String hide_results;
    public Date show_correct_answers_at;
    public Date hide_correct_answers_at;
    public List<AllDate> all_dates;
    public boolean can_update;
    public boolean require_lockdown_browser;
    public boolean require_lockdown_browser_for_results;
    public boolean require_lockdown_browser_monitor;
    public String lockdown_browser_monitor_data;
    public Permissions permissions;
    public String quiz_reports_url;
    public String quiz_statistics_url;
    public String quiz_submission_versions_html_url;
    public int assignment_id;
    public boolean one_time_results;
    public int assignment_group_id;
    public boolean show_correct_answers_last_attempt;
    public int version_number;
    public boolean has_access_code;
    public boolean post_to_sis;
    public String migration_id;
    public List<String> question_types;

    /**
     * Lock information for a quiz.
     */
    public class LockInfo
    {
        public Date lock_at;
        public boolean can_view;
        public String asset_string;
    }

    /**
     * Element of the all_dates list which specifies when a quiz will lock/unlock and when its due.
     */
    public class AllDate
    {
        public Date due_at;
        public Date unlock_at;
        public Date lock_at;
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
        public boolean read_statistics;
        public boolean grade;
        public boolean review_grades;
        public boolean view_answer_audits;
    }
}
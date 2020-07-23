package cavnas.utils.structs;

import java.util.List;

public class QuizSubmissionQuestion
{
    public int id;
    public int quiz_id;
    public int quiz_group_id;
    public int assessment_question_id;
    public int position;
    public String question_name;
    public String question_type;
    public String question_text;
    public List<Answer> answers;
    public Object variables;
    public Object formulas;
    public Object answer_tolerance;
    public Object formula_decimal_places;
    public Object matches;
    public boolean flagged;
    public boolean correct;

    public class Answer {
        public int id;
        public String text;
    }
}



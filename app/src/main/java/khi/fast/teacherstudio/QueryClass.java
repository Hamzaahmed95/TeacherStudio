package khi.fast.teacherstudio;

/**
 * Created by Hamza Ahmed on 24-Dec-17.
 */

public class QueryClass {
    private String StudentName;
    private String Question;
    private String TeacherName;
    private String Answer;

    public QueryClass(String studentName, String question, String teacherName, String answer) {
        StudentName = studentName;
        Question = question;
        TeacherName = teacherName;
        Answer = answer;
    }

    public QueryClass() {
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }
}

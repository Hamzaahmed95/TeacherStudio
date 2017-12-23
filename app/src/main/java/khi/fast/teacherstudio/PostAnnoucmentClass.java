package khi.fast.teacherstudio;

/**
 * Created by Hamza Ahmed on 23-Dec-17.
 */

public class PostAnnoucmentClass {

    private String Annoucements;
    private String TeacherName;
    private String CourseName;

    public PostAnnoucmentClass(String Annoucements, String TeacherName, String CourseName) {
        this.Annoucements = Annoucements;
        this.TeacherName = TeacherName;
        this.CourseName = CourseName;
    }

    public PostAnnoucmentClass() {
    }

    public String getAnnoucements() {
        return Annoucements;
    }

    public void setAnnoucements(String Annoucements) {
        this.Annoucements = Annoucements;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String TeacherName) {
        this.TeacherName = TeacherName;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }
}

package khi.fast.teacherstudio;

/**
 * Created by Hamza Ahmed on 24-Dec-17.
 */

public class DemoSlotsClass {

    private String Timings;
    private String Course;
    private String Name;
    private String Status;

    public DemoSlotsClass(String timings, String course, String name, String status) {
        Timings = timings;
        Course = course;
        Name = name;
        Status = status;
    }

    public DemoSlotsClass() {
    }

    public String getTimings() {
        return Timings;
    }

    public void setTimings(String timings) {
        Timings = timings;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}

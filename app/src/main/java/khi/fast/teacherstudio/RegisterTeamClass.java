package khi.fast.teacherstudio;

/**
 * Created by Hamza Ahmed on 23-Dec-17.
 */

public class RegisterTeamClass {
    private String LeaderName;
    private String Member1;
    private String Member2;
    private String status;

    public RegisterTeamClass(String leaderName, String member1, String member2, String status) {
        LeaderName = leaderName;
        Member1 = member1;
        Member2 = member2;
        this.status = status;
    }

    public RegisterTeamClass() {
    }

    public String getLeaderName() {
        return LeaderName;
    }

    public void setLeaderName(String leaderName) {
        LeaderName = leaderName;
    }

    public String getMember1() {
        return Member1;
    }

    public void setMember1(String member1) {
        Member1 = member1;
    }

    public String getMember2() {
        return Member2;
    }

    public void setMember2(String member2) {
        Member2 = member2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

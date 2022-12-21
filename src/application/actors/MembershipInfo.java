package application.actors;

import java.time.LocalDate;
import java.util.ArrayList;

public class MembershipInfo {

    public enum MemberType {
        COMPETITIVE_SWIMMER,
        LEISURE_SWIMMER
    }

    public enum SwimmingDisciplineType {
        CRAWL,
        BACKCRAWL,
        FREESTYLE,
        BREASTSTROKE,
        BUTTERFLY
    }
    public record SwimmingDisciplineResult(String location, LocalDate dateOfEvent, int rank, boolean isCompetitive) {
    }

    protected MemberType memberType;
    protected boolean membershipStatus = true;
    protected ArrayList<SwimmingDisciplineType> activeDisciplines;
    protected ArrayList<SwimmingDisciplineResult> resultList;


    // Constructor -------------------------------------------------------------
    public MembershipInfo(MemberType memberType, ArrayList<SwimmingDisciplineType> activeDisciplines) {
        this.memberType = memberType;
        this.activeDisciplines = activeDisciplines;
        this.resultList = new ArrayList<>();
    }

    // Getter ------------------------------------------------------------------

    public MemberType getMemberType() {
        return memberType;
    }

    public ArrayList<SwimmingDisciplineType> getActiveDisciplines() {
        return activeDisciplines;
    }

    public ArrayList<SwimmingDisciplineResult> getResultList() {
        return resultList;
    }
    public boolean isMembershipStatus() {return membershipStatus;}

    // Setter -------------------------------------------------------------------
    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }
    public void setMembershipStatus() {
        membershipStatus = !membershipStatus;
    }
}

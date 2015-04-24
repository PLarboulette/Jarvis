package perso.jarvis.beans;

/**
 * Created by Pierre on 30/03/2015.
 */
public class Task {



    private String id;
    private String name;
    private String text;
    private String beginDate;
    private String endDate;
    private String duration;
    private  String achieved;





    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setAchieved(String achieved) {
        this.achieved = achieved;
    }
}


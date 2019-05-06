package lintend.pabson.bhaktapur;




public class DataModule {

    String senderName;
    String Date;
    String Time;
    String notice;
    String schoolId;

    String schoolPrincipal, schoolContact, schoolLocation;

    public String getSchoolNumber() {
        return SchoolNumber;
    }

    public void setSchoolNumber(String schoolNumber) {
        SchoolNumber = schoolNumber;
    }

    String SchoolNumber;


    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }



    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }


}

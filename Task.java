import java.time.*;
import java.time.format.*;

class Task {
  LocalDateTime dueDateTime;
  String name;
  Integer timeEstimate;
  double timePriority, datePriority, overallPriority;
  //Constructor
  Task(String sentName, int sentYear, int sentMonth, int sentDay, int sentHour, int sentMinute, Integer sentTime) {
    dueDateTime = LocalDateTime.of(sentYear, sentMonth, sentDay, sentHour, sentMinute);
    name = sentName;
    timeEstimate = sentTime;
  }
  //Getters
  public LocalDateTime getDateTime() {
    return dueDateTime;
  }
   public String getDateTimeString() {
    String DatePattern = "dd-MM-yyyy HH:mm";
    DateTimeFormatter DateFormatter = DateTimeFormatter.ofPattern(DatePattern);
    return (DateFormatter.format(dueDateTime));
  }
  public String getName() {
    return name;
  }

  public Integer getTimeEstimate() {
    return timeEstimate;
  }
  //seters
  public void setDatePriority(double sentDatePriority){
    datePriority = sentDatePriority;
  }
  public void setTimePriority(double sentTimePriority){
    datePriority = sentTimePriority;
  }
  //comparing
  public int compareDateTime(LocalDateTime sentDueDateTime) {
    return (this.dueDateTime.compareTo(sentDueDateTime));
  }

  public boolean inFuture(LocalDateTime sentDueDateTime) {
    if (this.dueDateTime.compareTo(sentDueDateTime) > 0) {
      return true;
    } else {
      return false;
    }
  }
  public String toFile(){
    return (name + "" + dueDateTime + timeEstimate + "&");
  }
}
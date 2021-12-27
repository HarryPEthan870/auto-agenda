import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
class Agenda{
  public ArrayList<Task> tasks, tasksDatePriority, tasksTimePriority, orderedTasks;
  public HashMap<Double, Task> priority;
  public ArrayList<Double> priorityValues, priorityValuesSorted;
  Agenda(){
    tasks = new ArrayList<Task>();
  }
  public void addTask(Task sentTask){
    tasks.add(sentTask);
  }
  //Sorting
  public ArrayList<Task> comaprePriority(){
    tasksDatePriority = new ArrayList<Task>(tasks);
    tasksDatePriority.sort((o1, o2)-> o1.getDateTime().compareTo(o2.getDateTime()));
    tasksTimePriority = new ArrayList<Task>(tasks);
    tasksTimePriority.sort((o1, o2)-> o1.getTimeEstimate().compareTo(o2.getTimeEstimate()));
    HashMap<Task, Integer> dateMap = new HashMap<Task, Integer>();
    HashMap<Task, Integer> timeMap = new HashMap<Task, Integer>();
    for(int i = 0; i < tasks.size(); i++){
      dateMap.put(tasksDatePriority.get(i), i);
      timeMap.put(tasksTimePriority.get(i), i);
    }
    priorityValues = new ArrayList<Double>();
    priority = new HashMap<Double, Task>();
    for (int i = 0; i < tasks.size(); i++){
      Double dateValue = (double) dateMap.get(tasks.get(i));
      Double timeValue = (double) timeMap.get(tasks.get(i));
      Double dateTimeValue = ((dateValue + timeValue)/2);
      priorityValues.add(dateTimeValue);
      priority.put(dateTimeValue, tasks.get(i));
    }
    Collections.sort(priorityValues);
    priorityValuesSorted = new ArrayList<Double>();
    for (int i = priorityValues.size() - 1; i >= 0; i--) {
            priorityValuesSorted.add(priorityValues.get(i));
        }
    orderedTasks = new ArrayList<Task>();
    for (int i = 0; i < priorityValues.size(); i++){
      orderedTasks.add(priority.get(priorityValuesSorted.get(i)));
    }
    return orderedTasks;
  }
}
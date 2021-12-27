 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Main extends JFrame implements ActionListener{
  public Agenda agenda;
  public JTextArea infoDateTime;
  public JTabbedPane tabs;
  public BorderLayout dateTimeBorder, tasksBorderLayout;
  public GridLayout dateTimeGrid, panes, buttonsGrid, tasksDatesGrid;
  public JPanel dateTime, dateTimeInput, buttonPanel, taskPanel, tasksDates;
  public JButton addDateTime;
  public ArrayList<Task> orderedTasks;
  //arrays for gui creation loop
  public String[] labelNames = {"Enter the name of the assignment:", "Enter the Year:", "Enter the Month:", "Enter the Day:", "Enter the Hour:", "Enter the Minute:", "Enter an estimate of the time to complete the assignment:"};
  public JTextArea labels[] = new JTextArea[labelNames.length];
  public JTextField input[] = new JTextField[labelNames.length];
  public String[] buttonName = {"Generate", "Import", "Export"};
  public JButton buttons[] = new JButton[buttonName.length];
    public Font titles, defaultTimesRoman;
    public Main(){
    //Creating the UI
    //Fonts
    titles = new Font("TimesRoman", Font.BOLD, 28);
    defaultTimesRoman = new Font("TimesRoman",Font.PLAIN, 20);
    //Genral config
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 600);
    Container pane = getContentPane();
    GridLayout panes = new GridLayout(1,1,5,5);
    pane.setLayout(panes);
    tabs = new JTabbedPane();
    //GUI creation outside of loop
    addDateTime = new JButton("Add");
    addDateTime.setActionCommand("Add");
    addDateTime.addActionListener(this);
    GridLayout dateTimeGrid = new GridLayout(7,2,5,5);
    JPanel dateTimeInput = new JPanel();
    dateTimeInput.setLayout(dateTimeGrid);
    BorderLayout dateTimeBorder = new BorderLayout();
    JPanel dateTime = new JPanel();
    dateTime.setLayout(dateTimeBorder);
    //creating the drop downs
    //GUI creation loop
    for (int i=0; i<7; i++){
      //labels
      labels[i] = new JTextArea(labelNames[i]);
      labels[i].setEnabled(false);
      labels[i].setLineWrap(true);
      labels[i].setFont(defaultTimesRoman);
      dateTimeInput.add(labels[i]);
      //input
      input[i] = new JTextField();
      input[i].setFont(defaultTimesRoman);
      dateTimeInput.add(input[i]);
    }
    //2nd tab creation
    GridLayout buttonsGrid = new GridLayout(1,3,5,5);
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(buttonsGrid);
    tasksBorderLayout = new BorderLayout();
    taskPanel = new JPanel();
    taskPanel.setLayout(tasksBorderLayout);
    for(int i=0; i<3; i++){
      buttons[i] = new JButton(buttonName[i]);
      buttons[i].setFont(defaultTimesRoman);
      buttons[i].setActionCommand(buttonName[i]);
      buttons[i].addActionListener(this);
      buttonPanel.add(buttons[i]);
    }
    //Gui creation after loop
    agenda = new Agenda();
    infoDateTime = new JTextArea("Enter the values for any one task, then click the add button to add the task to the tasks being evlauated. \n Note: the time must be in military time");
    infoDateTime.setEnabled(false);
    infoDateTime.setLineWrap(true);
    infoDateTime.setFont(titles);
    dateTime.add(infoDateTime, BorderLayout.NORTH);
    dateTime.add(dateTimeInput,BorderLayout.CENTER);
    addDateTime.setPreferredSize( new Dimension(100, 100 ));
    dateTime.add(addDateTime,BorderLayout.SOUTH);
    taskPanel.add(buttonPanel, BorderLayout.NORTH);
    tabs.add("Tasks", taskPanel);
    tabs.add("Add Item", dateTime);
    pane.add(tabs);
    setVisible(true);
  }
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    if (command.equals("Add")){
      String name = input[0].getText();
      int year = (Integer.parseInt(input[1].getText()));
      int month = (Integer.parseInt(input[2].getText()));
      int day = (Integer.parseInt(input[3].getText()));
      int hour = (Integer.parseInt(input[4].getText()));
      int minute = (Integer.parseInt(input[5].getText()));
      int timeEstimate = (Integer.parseInt(input[6].getText()));
      //tests that the inputs are entered correctly
      if (month < 1 || month > 12){
        infoDateTime.append("Error! The month must be inside of the range from 1-12.");
      } else if (day < 1 ||(day > 31 && (month == 1 ||month == 3 || month == 5 || month == 7 || month == 8|| month == 10 || month == 12))||(day > 30 && (month == 4 || month == 6 || month == 9 || month == 11))||(day > 29 && month==2 && (year % 4) == 0)||(day > 28 && month==2 && (year % 4) != 0)){
      infoDateTime.append("Error! The day is must be a day the ocurrs within the specified month.");
      } else if (hour < 1 || hour > 24){
        infoDateTime.append("Error! The hours must be inside of the range from 1-24.");
      } else if (minute < 1 || minute > 60){
      infoDateTime.append("Error! The minutes must be inside of the range from 1-60.");
      } else {
        Task task = new Task(name, year, month, day, hour, minute, timeEstimate);
        agenda.addTask(task);
      }
    }else if (command.equals("Generate")){
      orderedTasks = new ArrayList<Task>(agenda.comaprePriority());
      tasksDates = new JPanel();
      tasksDatesGrid = new GridLayout(orderedTasks.size(), 2, 5, 5);
      JTextArea tasks[] = new JTextArea[orderedTasks.size()];
      JTextArea dueDates[] = new JTextArea[orderedTasks.size()];
      tasksDates.setLayout(tasksDatesGrid);
      tasksDates.add(new JTextArea("Name:"));
      tasksDates.add(new JTextArea("Date Due:"));
      for (int i = 0; i < orderedTasks.size(); i++){
        Task task = orderedTasks.get(i);
        tasks[i] = new JTextArea(task.getName());
        tasks[i].setFont(defaultTimesRoman);
        tasksDates.add(tasks[i]);
        dueDates[i] = new JTextArea(task.getDateTimeString());
        dueDates[i].setFont(defaultTimesRoman);
        tasksDates.add(dueDates[i]);
      }
      taskPanel.add(tasksDates, BorderLayout.CENTER);
      revalidate();
      repaint();
    }
  }
  public static void main(String[] args) {
    Main example = new Main();
  }
}
package to.pkgdo.list;

import java.util.Scanner;

public class ToDoList {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        String weekDays[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        Day currentDay = addWeek(weekDays); //Add 7 days into the week
        String choice="";
        while(!"end".equals(choice)) {
            System.out.println("\nEnter \"add\" to Add a task:\nEnter \"print\" to print Day's tasks:\nEnter \"new\" to start a new day\nEnter \"modifyDay\" to modify a specific Day \nEnter \"printWeek\" to print week\nEnter \"end\" to Exit:");
            choice= input.next();
            if("add".equals(choice)){
            fillDay(currentDay);}
            else if("new".equals(choice)){
                currentDay=nextDay(currentDay);
            }
            else if("print".equals(choice)){
            System.out.println("\t"+currentDay.getName());
            System.out.println("-----------------------------");
            currentDay.printDay();
            
            }else if("printWeek".equals(choice)){
             printWeek(currentDay);
            }
            else if("modifyDay".equals(choice)){
                fillSpecificDay(currentDay);
            }
        }
    }

    public static Day dayGenerator(String name) {
        return new Day(name);
    }

    public static Day addWeek(String weekDays[]) {
        Day currentDay = dayGenerator(weekDays[0]);
        Day pointer = currentDay;
        for (int i = 1; i < 7; i++) {
            pointer.setNextDay(dayGenerator(weekDays[i]));
            pointer = pointer.getNextDay();
        }
        return currentDay;
    }

    public static void fillDay(Day currentDay) {
        String title;
        String info;
        Integer priority;
        System.out.println("\nEnter the task's Name:");
        title = input.next();
        System.out.println("Enter the task's Info:");
        info = input.next();
        System.out.println("Enter the task's priority: (0 for no priority)");
        priority = input.nextInt();
        if (priority == 0) {
            priority = null;
        }
        currentDay.addTask(priority, title, info);
    }
    public static Day nextDay(Day currentDay){
        Day yesterday = currentDay;
        currentDay=currentDay.getNextDay(); //move to the next day
        Day pointer=currentDay;
        while(pointer.getNextDay()!=null){
        pointer=pointer.getNextDay();
        }
        pointer.setNextDay(new Day(yesterday.getName()));
        return currentDay;
    }
    public static void printWeek(Day currentDay){
        Day pointer=currentDay;
        System.out.println("\t"+pointer.getName());
        System.out.println("-----------------------------");
        pointer.printDay();
        while(pointer.getNextDay()!=null){
        pointer=pointer.getNextDay();
        System.out.println("\t"+pointer.getName());
        System.out.println("-----------------------------");
        pointer.printDay();
        }
    }
    public static void fillSpecificDay(Day currentDay){
        System.out.println("Choose a day to modify:");
        String name=input.next();
        Day pointer =currentDay;
        while(!pointer.getName().equals(name)){
        pointer=pointer.getNextDay();
        }
        
            fillDay(pointer);
        
    }
    

}

package to.pkgdo.list;

public class Day {

    private String name; //day name
    private Task head;  // Tasks i should do in a certain
    private Day nextDay;

    Day(String name) { // in case there is nothing to do
        setName(name);
        setHead(null);
        setNextDay(null);
        
    }

    public void setNextDay(Day nextDay) {
        this.nextDay = nextDay;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHead(Task head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public Task getHead() {
        return head;
    }

    public Day getNextDay() {
        return nextDay;
    }

    public void addTask(Integer priority, String title, String info) {
        if (head == null) {
            head = new Task(title, info, priority);
        } else {
            if (priority == null) {
                Task pointer = head;
                while (pointer.getNextTask() != null) {
                    pointer = pointer.getNextTask();
                }
                pointer.setNextTask(new Task(title, info, priority));
            } else {
                if (head.getPriority() == null) {
                    Task newHead = new Task(title, info, priority);
                    newHead.setNextTask(head);
                    head = newHead;
                } else {
                    Task pointer = head;
                    if (pointer.getPriority() < priority) {
                        while (pointer.getNextTask() != null && (pointer.getNextTask().getPriority() != null && pointer.getNextTask().getPriority() < priority)) {
                            pointer = pointer.getNextTask();
                        }
                        Task newTask = new Task(title, info, priority);
                        newTask.setNextTask(pointer.getNextTask());
                        pointer.setNextTask(newTask);
                    } else { // in case the new Task has a higher priority than the head  
                        Task newHead = new Task(title, info, priority);
                        newHead.setNextTask(head);
                        head = newHead;
                    }
                }
            }
        }
    }

    public Task removeTask() {
        Task removedTask = head;
        head = head.getNextTask();
        return removedTask;
    }

    public void printDay() {
        if(head==null){
        System.out.println("There is nothing to do for today\n-----------------------------\n");
        }
        else{
        Task pointer = head;
        System.out.print("\t"+pointer.getTitle()+": "+pointer.getInfo()+"\t"+"\n-----------------------------\n");
        while (pointer.getNextTask() != null) {
            pointer = pointer.getNextTask();
            System.out.print("\t"+pointer.getTitle()+": "+pointer.getInfo()+"\t"+"\n-----------------------------\n");

        }
        }
    }
    

}

package to.pkgdo.list;
public class Task {
    private Integer priority;
    private String title;
    private String info;
    private Task nextTask;
    
    Task(String title, String info,Integer priority){ // in case there is priority
        setTitle(title);
        setInfo(info);
        setPriority(priority);
        setNextTask(null);
    }
    

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setNextTask(Task nextTask) {
        this.nextTask = nextTask;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public Task getNextTask() {
        return nextTask;
    }
    
    
    
}

package university;

public abstract class Student {
    private boolean requirements;  //to check if older than 18; has the min gpa
    private Major major;
    private int id;
    private String firstName;
    private String secName;
    private String lastName;
    private int age;
    private String email;     //auto-generated
    private double gpa; 
    private int earnedCredits = 0;

    public Student(Major major, String firstName, String secName, String lastName, int age, int earnedCredits,double gpa) {
        setRequirements(true);
        setMajor(major);
        setFirstName(firstName);
        setSecName(secName);
        setLastName(lastName);
        setAge(age);
        setEarnedCredits(earnedCredits);
        setGpa(gpa);

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRequirements(boolean requirements) {
        this.requirements = requirements;
    }
    

    public void setMajor(Major major) {
        this.major = major;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEarnedCredits(int earnedCredits){
        this.earnedCredits = earnedCredits + this.earnedCredits;
    
    }
    public void setAge(int age) {
        if (age > 17) {
            this.age = age;
        } else {
            requirements = false;
        }
    }

    public void setGpa(double gpa) {
        if (gpa<=4 && gpa >=0) {
            this.gpa = gpa;
        } else {
            requirements = false;
        }

    }
    public boolean isRequirements() {
        return requirements;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecName() {
        return secName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public int getEarnedCredits() {
        return earnedCredits;
    }

    public double getGpa() {
        return gpa;
    }

    public Major getMajor() {
        return major;
    }

    public abstract boolean isActive(); //to indicate whether the student is active or not

    @Override
    public String toString() {
        return "ID:" + getId()+ "\nMajor:"+ getMajor().getName()+ "\nName:" + getFirstName() +" "
                + getSecName() + " " + getLastName() + "\nEmail:" + getEmail()+"\nGPA:"+ getGpa() + "\nAge:" + getAge()
                + "\nEarnedCredits:" + getEarnedCredits();
    }
}

package university;

public abstract class Active extends Student {
    private static int studentCount = 0;
    private int registeredCredits;
    private double remainingFees;
    private double paidFees;
    
    public Active(int registeredCredits, Major major, String firstName, String secName, String lastName, int age, int earnedCredits, int year,double gpa,boolean isNew) {
        super(major, firstName, secName, lastName, age, earnedCredits,gpa);
        if(isNew){
        studentCount++;
        idGenerator(year);
        emailGenerator();}
        setPaidFees(0);
        setRegisteredCredits(registeredCredits);
        setRemainingFees(getFeePerCredit() * getRegisteredCredits());
    }
    public void decrementStudents(){ //used to avoid the incrementation of students incase the student is not saved "requirements not met" 
    studentCount--;
    }
    public int getRegisteredCredits() {
        return registeredCredits;
    }

    public double getRemainingFees() {
        return remainingFees;
    }
    

    public double getPaidFees() {
        return paidFees;
    }
    public void idGenerator(int year) {
        int i = 3;
        while (studentCount > Math.pow(10.0, i)) {
            i++;
        }
        setId(studentCount+(((int) Math.pow(10, i)) * year));
    }
    public void emailGenerator() {
        char i=getFirstName().charAt(0);
        char k=getSecName().charAt(0);
        char j=getLastName().charAt(0);
        String email= ""+i+k+j;
        setEmail(email+String.format("%04d",studentCount)+"@student.bau");

    }

    public void setRemainingFees(double remainingFees) {

        this.remainingFees = remainingFees;
    }
    public void decRemainingFees(){
    this.remainingFees=this.remainingFees-paidFees;
    }

    public void setPaidFees(double paidFees) {
        if (paidFees < remainingFees) {
            this.paidFees = paidFees;
        } else if (paidFees >= remainingFees) {
            this.paidFees = remainingFees;
        } else if (paidFees <= 0) {
            this.paidFees = 0;
        }

    }
    

    public String printFees() {
        String k = "" + getFeePerCredit() * getRegisteredCredits();
        return k;

    }

    public void setRegisteredCredits(int registeredCredits){
    this.registeredCredits=registeredCredits;
    }

    public abstract double getFeePerCredit();    //abstract function
    
    public abstract boolean isGrad();     //abstract function

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "\nRegistered Credits: " + getRegisteredCredits()
                + "\nFees: " + printFees() + "\nRemaining Fees: " + getRemainingFees() + "\nPaid fees: " + getPaidFees();

    }

}

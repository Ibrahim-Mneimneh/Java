package university;       
public class NonActive extends Student{
    private boolean grad; //true if the student is a graduate

    public NonActive(Major major, String firstName, String secName, String lastName, int age, int earnedCredits, double gpa,boolean grad) {
        super(major, firstName, secName, lastName, age, earnedCredits, gpa);
        setGrad(grad);
    }

    public void setGrad(boolean Grad) {
        this.grad = Grad;
    }

    public boolean isGrad() {
        return grad;
    }
    
    
    @Override
    public boolean isActive(){
    return false;
    }
    
    @Override
    public String toString(){
    return "Non-Active"+(isGrad()? " Graduate":" UnderGraduate")+super.toString();
    
    }
    
    
}

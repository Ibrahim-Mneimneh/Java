package university;

public class ActiveGraduate extends Active {
    
    public ActiveGraduate(int registeredCredits, Major major, String firstName, String secName, String lastName, int age, int earnedCredits, int year, double gpa,boolean isNew) {
        super(registeredCredits, major, firstName, secName, lastName, age, earnedCredits, year, gpa,isNew);
        
    }
    @Override
    public boolean isGrad(){
    return true;
    
    }

    @Override
    public void setEarnedCredits(int earnedCredits){
    if (getEarnedCredits()<getMajor().getRequiredCreditsG())
         super.setEarnedCredits(earnedCredits);
    else
        setRequirements(false);
    }
   
    @Override
    public double getFeePerCredit() {
        return getMajor().getFeePerCreditG();

    }

    @Override
    public void setRegisteredCredits(int registeredCredits) {
        if (registeredCredits < 3) {
            super.setRegisteredCredits(3);
        } else if ((registeredCredits > 6 && registeredCredits <= 9) && getEarnedCredits() > 9 && getGpa() >= 3) {
            super.setRegisteredCredits(registeredCredits);
        } else if ((registeredCredits > 6 && registeredCredits <= 9) && (getEarnedCredits() < 9 || (getGpa() < 3 && getGpa() > 2))) {
            super.setRegisteredCredits(6);
        } else if (registeredCredits > 9 && getEarnedCredits() > 9 && getGpa() >= 3) {
            super.setRegisteredCredits(9);
        } else if (registeredCredits > 9 && getEarnedCredits() < 9 && (getGpa() < 3 && getGpa() > 2)) {
            super.setRegisteredCredits(6);
        } else if (registeredCredits <= 6 && getGpa() > 2) {
            super.setRegisteredCredits(registeredCredits);
        } else {  //students with gpa =2
            super.setRegisteredCredits(3);
        }
        if (registeredCredits > (getMajor().getRequiredCreditsG() - getEarnedCredits())) {
            super.setRegisteredCredits(getMajor().getRequiredCreditsG() - getEarnedCredits());
        }

        setEarnedCredits(getRegisteredCredits());

    }
    @Override
    public String toString(){
    return "Active Graduate:  "+super.toString();
    
    }

}

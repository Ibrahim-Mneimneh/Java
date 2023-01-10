package university;

public class ActiveUnderGraduate extends Active {
    
    public ActiveUnderGraduate(int registeredCredits, Major major, String firstName, String secName, String lastName, int age, int earnedCredits, int year, double gpa,boolean isNew) {
        super(registeredCredits, major, firstName, secName, lastName, age, earnedCredits, year, gpa,isNew);
        
    }
    @Override
    public boolean isGrad(){
    return false;
    
    }

    @Override
    public void setEarnedCredits(int earnedCredits){
    if (getEarnedCredits()<getMajor().getRequiredCreditsUG())
         super.setEarnedCredits(earnedCredits);
    else
        setRequirements(false);
    }

    @Override
    public double getFeePerCredit() {
        return getMajor().getFeePerCreditUG();
    }

    @Override
    public void setRegisteredCredits(int registeredCredits) {
        if (registeredCredits < 12) {
            super.setRegisteredCredits(12);
        } else if ((registeredCredits > 18 && registeredCredits <= 21) && getEarnedCredits() > 42 && getGpa() >= 3) {
            super.setRegisteredCredits(registeredCredits);
        } else if ((registeredCredits > 18 && registeredCredits <= 21) && (getEarnedCredits() < 42 || (getGpa() < 3 && getGpa() > 2))) {
            super.setRegisteredCredits(18);
        } else if (registeredCredits > 21 && getEarnedCredits() > 42 && getGpa() >= 3) {
            super.setRegisteredCredits(21);
        } else if (registeredCredits > 21 && (getEarnedCredits() < 42 || (getGpa() < 3 && getGpa() > 2))) {
            super.setRegisteredCredits(18);
        } else if (registeredCredits <= 18 && getGpa() > 2) {
            super.setRegisteredCredits(registeredCredits);
        } else {
            super.setRegisteredCredits(12);
        }
        if (registeredCredits > (getMajor().getRequiredCreditsUG() - getEarnedCredits())) {
            setRegisteredCredits(getMajor().getRequiredCreditsUG() - getEarnedCredits());//The reg credits cant be more than the ones left to graduate
        }

        setEarnedCredits(getRegisteredCredits());   //the credits registered are now earned 

    }
    @Override
    public String toString(){
    return "Active UnderGraduate: "+super.toString();
    
    }

}

package university;

public class Major {

    private String name;
    private double feePerCreditG;
    private double feePerCreditUG;
    private int requiredCreditsG;
    private int requiredCreditsUG;

    public Major(String major, double feePerCreditG, double feePerCreditUG, int requiredCreditsG, int requiredCreditsUG) {
        setName(major);
        setFeePerCreditG(feePerCreditG);
        setFeePerCreditUG(feePerCreditUG);
        setRequiredCreditsG(requiredCreditsG);
        setRequiredCreditsUG(requiredCreditsUG);
    }

    public String getName() {
        return name;
    }

    public double getFeePerCreditG() {
        return feePerCreditG;
    }

    public double getFeePerCreditUG() {
        return feePerCreditUG;
    }

    public int getRequiredCreditsG() {
        return requiredCreditsG;
    }

    public int getRequiredCreditsUG() {
        return requiredCreditsUG;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFeePerCreditG(double feePerCreditG) {
        if (feePerCreditG == 0.0) {
            this.feePerCreditG = 200;
        } else if (feePerCreditG < 0.0) {
            setFeePerCreditG(feePerCreditG * -1);
        } else {
            this.feePerCreditG = feePerCreditG;
        }

    }

    public void setFeePerCreditUG(double feePerCreditUG) {
        if (feePerCreditUG == 0.0) {
            this.feePerCreditUG = 150;
        } else if (feePerCreditUG < 0.0) {
            setFeePerCreditUG(feePerCreditUG * -1);
        } else {
            this.feePerCreditUG = feePerCreditUG;
        }

    }

    public void setRequiredCreditsG(int requiredCreditsG) {
        if (requiredCreditsG < 0) {
            setRequiredCreditsG(requiredCreditsG * -1);
        } else if (requiredCreditsG == 0) {
            this.requiredCreditsG = 30;
        } else {
            this.requiredCreditsG = requiredCreditsG;
        }

    }

    public void setRequiredCreditsUG(int requiredCreditsUG) {
        if (requiredCreditsUG < 0) {
            setRequiredCreditsUG(requiredCreditsUG * -1);
        } else if (requiredCreditsUG == 0) {
            this.requiredCreditsUG = 100;
        } else {
            this.requiredCreditsUG = requiredCreditsUG;
        }

    }


}

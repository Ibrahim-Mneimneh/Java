package university;

import java.util.ArrayList;
import java.util.Scanner;    
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class University {

    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int studentCount = 0;
        String choice;
        Scanner input = new Scanner(System.in);
        Random rand =new Random();
        System.out.println("\t\t\tWelcome To The Student Record Management System");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("\t\t\tPlease enter the current year:");
        int year = input.nextInt();
        ArrayList<Major> ourMajors = new ArrayList<Major>();
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new NonActive(new Major("", 0, 0, 0, 0), "", "", "", 0, 0, 0, false)); //to facilitate reaching each student
        choice = "";
         
        while ((!"end".equals(choice))) {

            if (ourMajors.isEmpty() || ("AddMajor".equals(choice) || "addmajor".equals(choice))) {
                System.out.println("\t\t\tAdding Majors.....");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                ourMajors.add(newMajor());
                System.out.println("\t\t\tMajor Added Successfuly!");

            } else if ("AddGrad".equals(choice) || "addgrad".equals(choice) || "AddUnderGrad".equals(choice) || "addundergrad".equals(choice)) {
                System.out.println("\t\t\tAdding Students.....");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                boolean exists = true;
                Major k = new Major("", 0, 0, 0, 0);
                String majorName;
                System.out.print("\t\t\tEnter the student's Major:");
                majorName = input.next();
                while (exists) {

                    for (Major major : ourMajors) {
                        if (major.getName().equals(majorName)) {
                            k = major;
                            exists = false;
                        }
                    }
                    if (exists) {
                        System.out.print("\t\t\tPlease enter an existing student's Major:");
                        majorName = input.next();
                    }
                }
                double gpa = 0;
                System.out.print("\t\t\tEnter the student's name:");
                String firstName = input.next();
                String secName = input.next();
                String lastName = input.next();
                System.out.print("\t\t\tEnter the student's age:");
                int age = input.nextInt();
                System.out.print("\t\t\tEnter the student's EarnedCredits:");
                int earnedCredits = input.nextInt();
                if (earnedCredits > 0) {
                    System.out.print("\t\t\tEnter the student's GPA:");
                    gpa = input.nextDouble();
                }
                else{
                 gpa=rand.nextDouble(2,4);
                }

                System.out.print("\t\t\tEnter the student's Registered Credits:");
                int registeredCredits = input.nextInt();

                if ("AddGrad".equals(choice) || "addgrad".equals(choice)) {
                    ActiveGraduate k1 = new ActiveGraduate(registeredCredits, k, firstName, secName, lastName, age, earnedCredits, year, gpa, true);
                    if (k1.isRequirements()) {
                        studentCount++;
                        students.add(k1);
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("\t\t\tGraduate Student added Successfully!!");
                    } else {
                        System.out.println("\t\t\tSomething went wrong / Requirements not met!");
                        k1.decrementStudents();
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                    }
                }
                if ("AddUnderGrad".equals(choice) || "addundergrad".equals(choice)) {
                    ActiveUnderGraduate k1 = new ActiveUnderGraduate(registeredCredits, k, firstName, secName, lastName, age, earnedCredits, year, gpa, true);
                    if (k1.isRequirements()) {
                        studentCount++;
                        students.add(k1);
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("\t\t\tUnderGraduate Student added Successfully!!");
                    } else {
                        System.out.println("\t\t\tSomething went wrong / Requirements not met!");
                        k1.decrementStudents();
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                    }

                }

            } else if ("Remove".equals(choice) || "remove".equals(choice)) {
                System.out.println("\t\t\tDe-Activating Students.....");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\t\t\tEnter the Student's ID:");
                int remId = input.nextInt();
                System.out.println("\t\t\tThe student '" + students.get(remId - (idSearchAid(studentCount) * year)).getFirstName()
                        + "  " + students.get(remId - (idSearchAid(studentCount) * year)).getLastName() + "'?");
                System.out.print("\t\t\tEnter 'Confirm' or 'confirm' to confirm:");
                String confirm = input.next();

                if ((students.get(remId - (idSearchAid(students.size()) * year)) instanceof Active) && ("confirm".equals(confirm) || "Confirm".equals(confirm))) {
                    int position = remId - (idSearchAid(students.size()) * year);
                    Active remStudent = (Active) students.get(position);
                    NonActive nNonActive = new NonActive(remStudent.getMajor(), remStudent.getFirstName(), remStudent.getSecName(),
                            remStudent.getLastName(), remStudent.getAge(), remStudent.getEarnedCredits(), remStudent.getGpa(), remStudent.isGrad());
                    nNonActive.setEmail(remStudent.getEmail());
                    nNonActive.setId(remId);
                    students.set(position, nNonActive);
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("\t\t\tStudent Removed Successfuly!!!");
                } else if ("Confirm".equals(confirm) || "confirm".equals(confirm)) {
                    System.out.println("\t\t\tThe Student's account is already non-Active");
                } else {
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("\t\t\tSomething went wrong / Student already removed!!!");
                }
            } else if ("Activate".equals(choice) || "activate".equals(choice)) {
                System.out.println("\t\t\tActivating Students.....");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\t\t\tEnter the Student's ID:");
                int activeId = input.nextInt();
                int position = activeId - (idSearchAid(students.size()) * year);
                System.out.println("\t\t\tThe student '" + students.get(position).getFirstName()
                        + "  " + students.get(position).getLastName() + "'?");

                System.out.print("\t\t\tEnter 'Confirm' or 'confirm' to confirm:");
                String confirm = input.next();
                if ((students.get(position) instanceof NonActive) && ("confirm".equals(confirm) || "Confirm".equals(confirm))) {

                    NonActive nonActStud = (NonActive) students.get(position);
                    System.out.print("\t\t\tEnter the student's Registered Credits:");
                    int registeredCredits = input.nextInt();
                    if (nonActStud.isGrad()) {

                        ActiveGraduate activeAcc = new ActiveGraduate(registeredCredits, nonActStud.getMajor(), nonActStud.getFirstName(), nonActStud.getSecName(),
                                nonActStud.getLastName(), nonActStud.getAge(), nonActStud.getEarnedCredits(), year, nonActStud.getGpa(), false);
                        activeAcc.setId(nonActStud.getId());
                        activeAcc.setEmail(nonActStud.getEmail());
                        students.set(position, activeAcc);

                    } else {

                        ActiveUnderGraduate activeAcc = new ActiveUnderGraduate(registeredCredits, nonActStud.getMajor(), nonActStud.getFirstName(), nonActStud.getSecName(),
                                nonActStud.getLastName(), nonActStud.getAge(), nonActStud.getEarnedCredits(), year, nonActStud.getGpa(), false);
                        activeAcc.setId(nonActStud.getId());
                        activeAcc.setEmail(nonActStud.getEmail());
                        students.set(position, activeAcc);

                    }
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("\t\t\tStudent Activated Successfuly!!!");

                } else {
                    System.out.println("\t\t\tSomething went wrong / Student already removed!!!");
                }

            } else if ("PrintInfo".equals(choice) || "printinfo".equals(choice)) {
                System.out.println("\t\t\tPrinting Info.....");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\t\t\tEnter the student's ID:");
                int id = input.nextInt();
                System.out.println(students.get(id - (idSearchAid(students.size()) * year)).toString());

            } else if ("Print".equals(choice) || "print".equals(choice)) {
                BufferedWriter writer;
                System.out.println("\t\t\tPrinting to a FILE.....");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                try {
                    writer = new BufferedWriter(new FileWriter("UniversityStudents.txt"));
                    
                    for (Student currentStudent : students) {

                        writer.write(currentStudent.toString());
                        writer.write("\n------------------------------------");
                        writer.write("\n------------------------------------\n");

                    }
                    writer.close();
                System.out.println("\t\t\tStudents Printed Successfuly.....");
                
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if ("Fees".equals(choice) || "fees".equals(choice)) {
                System.out.println("\t\t\tManaging Fees.....");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\t\t\tEnter the student's ID:");
                int id = input.nextInt();
                if (students.get(id - (idSearchAid(students.size()) * year)) instanceof Active) {
                    Active studentFee = (Active) students.get(id - (idSearchAid(students.size()) * year));
                    System.out.print("\t\t\tEnter the payed amount:");
                    float amountPayed = input.nextFloat();
                    studentFee.setPaidFees(amountPayed);
                    studentFee.decRemainingFees();
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("\t\t\tFees Paid Successfuly!");
                } else {
                    System.out.println("Enter an active student ID in order to manage fees!");
                }
            }else if("Modify".equals(choice) || "modify".equals(choice)){
                System.out.println("\t\t\tModifying Students.....");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\t\t\tEnter the student's ID:");
                int id = input.nextInt();
                if(students.get(id - (idSearchAid(students.size()) * year)).isActive()){
                Active modified = (Active) students.get(id - (idSearchAid(students.size()) * year));
                System.out.print("\t\t\tEnter the student's name:");
                String firstName = input.next();
                String secName = input.next();
                String lastName = input.next();
                System.out.print("\t\t\tEnter the student's age:");
                int age = input.nextInt();
                boolean exists = true;   //to check if the major exists
                Major k = new Major("", 0, 0, 0, 0);
                String majorName;
                System.out.print("\t\t\tEnter the student's Major:");
                majorName = input.next();
                while (exists) {

                    for (Major major : ourMajors) {
                        if (major.getName().equals(majorName)) {
                            k = major;
                            exists = false;
                        }
                    }
                    if (exists) {
                        System.out.print("\t\t\tPlease enter an existing student's Major:");
                        majorName = input.next();
                    }
                }
                modified.setAge(age);
                modified.setFirstName(firstName);
                modified.setSecName(secName);
                modified.setLastName(lastName);
                modified.emailGenerator();
                modified.setAge(age);
                modified.setMajor(k);
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("\t\t\tStudent's Record Modified Successfuly!");
                
                        }
                else {
                    System.out.println("\n\n\nEnter an active student ID in order to Modify a Student's Record!");
                }
            }
            else if ("PrintActive".equals(choice) || "printactive".equals(choice)) {
                System.out.println("\t\t\tPrinting Active Students.....");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                for (Student currentStudent : students) {
                    if (currentStudent instanceof Active) {
                        System.out.println(currentStudent.toString());
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                    }

                }
            } else if ("PrintMajor".equals(choice) || "printmajor".equals(choice)) {
                System.out.println("\t\t\tPrinting a Major's Students.....");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\t\t\tEnter the Students' Major:");
                String majorName = input.next();
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                boolean exists = true;
                while (exists) {
                    for (Major major : ourMajors) {
                        if (major.getName().equals(majorName)) {
                            exists = false;
                        }
                    }
                    if (exists) {
                        System.out.println("\t\t\tPlease enter an existing student's Major:");
                        majorName = input.next();
                    }

                }
                for (Student currentStudent : students) {
                    if (currentStudent instanceof Active) {
                        if (currentStudent.getMajor().getName().equals(majorName)) {
                            System.out.println(currentStudent.toString());
                            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                        }
                    }

                }
                System.out.println("\t\t\tStudents Printed Successfuly!");
                
            } else {
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("\t\t\tPlease enter a valid option!!");

            }
            choiceRep();
            choice = input.next();

        }//end of while

    }

    public static int idSearchAid(int studentNumber) {
        int i = 3;
        while (studentNumber > Math.pow(10, i)) {
            i++;
        }
        return (int) Math.pow(10, i);

    }

    public static Major newMajor() {
        Scanner input = new Scanner(System.in);
        System.out.print("\t\t\tEnter the Majors's name:");
        String majorName = input.next();
        System.out.print("\t\t\tEnter the Majors's Graduate Fee per Credit:");
        float feePerCreditG = input.nextFloat();
        System.out.print("\t\t\tEnter the Majors's UnderGraduate Fee per Credit:");
        float feePerCreditUG = input.nextFloat();
        System.out.print("\t\t\tEnter the Majors's Graduate Required Credits:");
        int requiredCreditsG = input.nextInt();
        System.out.print("\t\t\tEnter the Majors's UnderGraduate Required Credit:");
        int requiredCreditsUG = input.nextInt();
        return new Major(majorName, feePerCreditG, feePerCreditUG, requiredCreditsG, requiredCreditsUG);

    }

    public static void choiceRep() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\t\t\tEnter 'AddMajor' or 'addmajor' to Add a New Major");
        System.out.println("\t\t\tEnter 'AddGrad' or 'addgrad' to Add a Graduate Student");
        System.out.println("\t\t\tEnter 'AddUnderGrad' or 'addundergrad' to Add an UnderGraduate Student");
        System.out.println("\t\t\tEnter 'Remove' or 'remove' to De-activate a Student's Record");
        System.out.println("\t\t\tEnter 'Activate' or 'activate' to Activate a Student's Record");
        System.out.println("\t\t\tEnter 'Modify' or 'modify' to Modify a Student's Record");
        System.out.println("\t\t\tEnter 'Fees' or 'fees' to pay a Student's Fee");
        System.out.println("\t\t\tEnter 'Print' or 'print' to print Students in a File");
        System.out.println("\t\t\tEnter 'PrintInfo' or 'printinfo' to print a Student's Info");
        System.out.println("\t\t\tEnter 'PrintActive' or 'printactive' to print Active Students");
        System.out.println("\t\t\tEnter 'PrintMajor' or 'printMajor' to print a Major's students");
        System.out.println("\t\t\tEnter 'End' or 'end' to end the program!");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("\t\t\tEnter here:");
    }

}

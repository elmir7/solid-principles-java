interface Payable {
    double calculateSalary();
}

interface PaidLeaveEligible {
    void takePaidLeave(int days);
}

interface InsuranceEligible {
    void enrollInHealthInsurance();
}

class Contractor implements Payable {

    @Override
    public double calculateSalary() {
        return 5000.0;
    }

}

class FullTimeEmployee implements Payable, PaidLeaveEligible, InsuranceEligible {
    
    @Override
    public double calculateSalary() {
        return 7000.0;
    }

    @Override
    public void takePaidLeave(int days) {
        System.out.println("Full time employee takes " + days + " days paid leave.");
    }

    @Override
    public void enrollInHealthInsurance() {
        System.out.println("Full time employee enrolled in health insurance.");
    }
}

class Main {

    public static void runPayroll(Payable[] employees) {
        for(Payable employee : employees) {
            System.out.println(employee.calculateSalary() + "$");
        }
    }
    
    public static void main(String[] args) {
        
        Contractor contractor1 = new Contractor();
        System.out.println(contractor1.calculateSalary() + "$");
        
        FullTimeEmployee fullTime1 = new FullTimeEmployee();
        System.out.println(fullTime1.calculateSalary() + "$");

        fullTime1.takePaidLeave(7);
        fullTime1.enrollInHealthInsurance();

        Payable employees[] = {contractor1 , fullTime1};

        runPayroll(employees);
    }
}
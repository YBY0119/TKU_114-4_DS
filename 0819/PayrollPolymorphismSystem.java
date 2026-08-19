// PayrollPolymorphismSystem.java
abstract class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculatePay();
}

class SalariedEmployee extends Employee {
    private double monthlySalary;

    public SalariedEmployee(String name, double monthlySalary) {
        super(name);
        this.monthlySalary = Math.max(0, monthlySalary);
    }

    @Override
    public double calculatePay() {
        return monthlySalary;
    }
}

class HourlyEmployee extends Employee {
    private double hourlyRate;
    private double hours;

    public HourlyEmployee(String name, double hourlyRate, double hours) {
        super(name);
        this.hourlyRate = Math.max(0, hourlyRate);
        this.hours = Math.max(0, hours);
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hours;
    }
}

class CommissionEmployee extends Employee {
    private double baseSalary;
    private double salesAmount;
    private double commissionRate;

    public CommissionEmployee(String name, double baseSalary, double salesAmount, double commissionRate) {
        super(name);
        this.baseSalary = Math.max(0, baseSalary);
        this.salesAmount = Math.max(0, salesAmount);
        this.commissionRate = Math.max(0, commissionRate);
    }

    @Override
    public double calculatePay() {
        return baseSalary + (salesAmount * commissionRate);
    }
}

public class PayrollPolymorphismSystem {
    public static void main(String[] args) {
        Employee[] employees = new Employee[] {
            new SalariedEmployee("張經理 (月薪制)", 65000),
            new HourlyEmployee("李同學 (時薪工讀)", 195, 120),
            new CommissionEmployee("王業務 (業務底薪加成)", 30000, 500000, 0.08),
            new SalariedEmployee("陳工程師 (月薪制)", 58000)
        };

        double totalPay = 0;
        double maxPay = 0;
        Employee highestPaidEmployee = null;

        System.out.println("=== 員工薪資結算清單 ===");
        for (Employee emp : employees) {
            double pay = emp.calculatePay();
            totalPay += pay;

            if (pay > maxPay) {
                maxPay = pay;
                highestPaidEmployee = emp;
            }

            System.out.printf("員工: %-18s | 本月薪資: NT$ %,10.2f%n", emp.getName(), pay);
        }

        System.out.println("==========================================");
        System.out.printf("全體員工薪資總額: NT$ %,10.2f%n", totalPay);
        if (highestPaidEmployee != null) {
            System.out.printf("最高薪員工: %s (NT$ %,10.2f)%n", 
                              highestPaidEmployee.getName(), maxPay);
        }
    }
}
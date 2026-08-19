// EmployeeConstructorChain.java
abstract class EmployeeBase {
    protected String id;
    protected String name;

    public EmployeeBase(String id, String name) {
        System.out.println("-> [Constructor] 執行 EmployeeBase (id=" + id + ", name=" + name + ")");
        this.id = id;
        this.name = name;
    }

    public abstract double calculatePay();
}

class FullTimeEmployee extends EmployeeBase {
    private double monthlySalary;

    public FullTimeEmployee(String id, String name, double monthlySalary) {
        super(id, name);
        // 邊界條件：負數薪資轉為 0
        this.monthlySalary = Math.max(0, monthlySalary);
        System.out.println("-> [Constructor] 執行 FullTimeEmployee (月薪: " + this.monthlySalary + ")");
    }

    @Override
    public double calculatePay() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends EmployeeBase {
    private double hourlyRate;
    private double hoursWorked;

    public PartTimeEmployee(String id, String name, double hourlyRate, double hoursWorked) {
        super(id, name);
        // 邊界條件：時薪或時數為負數時轉為 0
        this.hourlyRate = Math.max(0, hourlyRate);
        this.hoursWorked = Math.max(0, hoursWorked);
        System.out.println("-> [Constructor] 執行 PartTimeEmployee (時薪: " + this.hourlyRate + ", 時數: " + this.hoursWorked + ")");
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }
}

public class EmployeeConstructorChain {
    public static void main(String[] args) {
        System.out.println("=== 建立全職員工 (測試建構順序與負數處理) ===");
        EmployeeBase emp1 = new FullTimeEmployee("FT01", "Alice", 45000);
        System.out.printf("實領薪資: NT$ %.1f%n%n", emp1.calculatePay());

        System.out.println("=== 建立兼職員工 (測試建構順序與負數防護) ===");
        EmployeeBase emp2 = new PartTimeEmployee("PT01", "Bob", -190, 80);
        System.out.printf("實領薪資: NT$ %.1f (負時薪已修正為 0)%n", emp2.calculatePay());
    }
}
class Equipment {
    private String id;
    private String name;
    private int availableCount;

    public Equipment(String id, String name, int availableCount) {
        this.id = (id == null || id.trim().isEmpty()) ? "Unknown" : id;
        this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name;
        this.availableCount = Math.max(0, availableCount);
    }

    public boolean borrowOne() {
        if (availableCount > 0) {
            availableCount--;
            return true;
        }
        return false;
    }

    public void returnItems(int quantity) {
        if (quantity > 0) {
            availableCount += quantity;
        }
    }

    @Override
    public String toString() {
        return String.format("设备编号: %s, 名称: %s, 可借数量: %d", id, name, availableCount);
    }
}

public class EquipmentInventory {
    public static void main(String[] args) {
        Equipment eq1 = new Equipment("EQ01", "投影机", 1);
        Equipment eq2 = new Equipment("", "", -5); // 测试异常初始化

        System.out.println("=== 初始化设备状态 ===");
        System.out.println(eq1);
        System.out.println(eq2);

        System.out.println("\n=== 测试借用操作 ===");
        System.out.println("eq1 第一次借用: " + (eq1.borrowOne() ? "成功" : "失败"));
        System.out.println("eq1 第二次借用: " + (eq1.borrowOne() ? "成功" : "失败")); // 应当失败

        System.out.println("\n=== 测试归还操作 ===");
        eq1.returnItems(2);
        System.out.println("eq1 归还 2 个后状态: " + eq1);
        
        eq1.returnItems(-3); // 不应改变库存
        System.out.println("eq1 归还 -3 个后状态: " + eq1);
    }
}
import java.util.Arrays;

final class InventorySnapshot {
    private final String warehouseId;
    private final int[] quantities;

    public InventorySnapshot(String warehouseId, int[] quantities) {
        this.warehouseId = warehouseId;
        // 防御性复制 (Defensive Copy) & null 处理
        if (quantities == null) {
            this.quantities = new int[0];
        } else {
            this.quantities = Arrays.copyOf(quantities, quantities.length);
        }
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public int[] getQuantities() {
        // 防御性复制返回
        return Arrays.copyOf(quantities, quantities.length);
    }

    public int totalQuantity() {
        int sum = 0;
        for (int q : quantities) {
            sum += q;
        }
        return sum;
    }

    public int outOfStockCount() {
        int count = 0;
        for (int q : quantities) {
            if (q == 0) count++;
        }
        return count;
    }
}

public class InventorySnapshotPractice {
    public static void main(String[] args) {
        int[] testData = {5, 0, 3, 0};
        InventorySnapshot snapshot = new InventorySnapshot("WH-01", testData);

        System.out.println("仓库 ID: " + snapshot.getWarehouseId());
        System.out.println("总数: " + snapshot.totalQuantity());
        System.out.println("缺货品项数: " + snapshot.outOfStockCount());

        // 验证外部修改不影响内部状态
        testData[1] = 99;
        snapshot.getQuantities()[0] = 99;
        System.out.println("外部修改后总数（应仍为 8）: " + snapshot.totalQuantity());
    }
}
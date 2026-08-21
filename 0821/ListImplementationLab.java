import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {

    public static void testListOperations(List<Integer> list) {
        // 尾端新增
        list.add(10);
        list.add(20);
        list.add(30);

        // 指定位置插入
        list.add(1, 15); // [10, 15, 20, 30]

        // 搜尋
        int searchVal = 20;
        int foundIndex = list.indexOf(searchVal);

        // 刪除
        list.remove(Integer.valueOf(15)); // [10, 20, 30]

        // 總和
        int sum = 0;
        for (int num : list) {
            sum += num;
        }

        System.out.println("Type: " + list.getClass().getSimpleName());
        System.out.println("List Content: " + list);
        System.out.println("Search " + searchVal + " Index: " + foundIndex);
        System.out.println("Sum: " + sum);
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        testListOperations(new ArrayList<>());
        testListOperations(new LinkedList<>());

        System.out.println("【內部成本差異說明】");
        System.out.println("1. ArrayList: 底層為連續陣列。隨機存取快 O(1)，但中間插入/刪除需搬移元素 O(N)；擴容時需複製陣列。");
        System.out.println("2. LinkedList: 底層為雙向鏈結。隨機存取需逐一走訪 O(N)，但在已知 Node 位置插入/刪除為 O(1) 且無連續記憶體開銷。");
    }
}
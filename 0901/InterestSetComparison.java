import java.util.*;

public class InterestSetComparison {
    public static <T> Set<T> getUnion(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    public static <T> Set<T> getIntersection(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    public static <T> Set<T> getFirstOnly(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }

    public static <T> Set<T> getSecondOnly(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set2);
        result.removeAll(set1);
        return result;
    }

    public static void main(String[] args) {
        Set<String> personA = new HashSet<>(Arrays.asList("閱讀", "游泳", "電影", "旅行"));
        Set<String> personB = new HashSet<>(Arrays.asList("電影", "旅行", "程式", "音樂"));

        System.out.println("聯集 (Union): " + getUnion(personA, personB));
        System.out.println("交集 (Intersection): " + getIntersection(personA, personB));
        System.out.println("僅 A 擁有 (First-only): " + getFirstOnly(personA, personB));
        System.out.println("僅 B 擁有 (Second-only): " + getSecondOnly(personA, personB));
    }
}
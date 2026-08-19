import java.util.Objects;

class LibraryMember {
    private String memberId;
    private String name;
    private String email;

    public LibraryMember(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LibraryMember other = (LibraryMember) obj;
        return Objects.equals(this.memberId, other.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }

    @Override
    public String toString() {
        return String.format("Member[ID: %s, Name: %s, Email: %s]", memberId, name, email);
    }
}

public class MemberEqualityPractice {
    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember("M001", "Alice", "alice@example.com");
        LibraryMember m2 = new LibraryMember("M001", "Alice", "alice_new@example.com");

        System.out.println("物件 1: " + m1);
        System.out.println("物件 2: " + m2);

        System.out.println("\n=== 比较结果 ===");
        System.out.println("m1 == m2: " + (m1 == m2));
        System.out.println("m1.equals(m2): " + m1.equals(m2));
        System.out.println("m1.equals(null): " + m1.equals(null)); // 边界测试
    }
}
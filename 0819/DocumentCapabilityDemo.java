// DocumentCapabilityDemo.java
interface Exportable {
    void export(String format);
}

interface Compressible {
    void compress(int level);
}

class BackupDocument implements Exportable, Compressible {
    private String docName;

    public BackupDocument(String docName) {
        this.docName = docName;
    }

    @Override
    public void export(String format) {
        System.out.println("檔案 [" + docName + "] 成功匯出為 " + format.toUpperCase() + " 格式。");
    }

    @Override
    public void compress(int level) {
        System.out.println("檔案 [" + docName + "] 正在以壓縮等級 " + level + " 進行壓縮...");
    }

    public void displayInfo() {
        System.out.println("文件名稱: " + docName);
    }
}

public class DocumentCapabilityDemo {
    public static void main(String[] args) {
        BackupDocument backupDoc = new BackupDocument("期末報告備份.docx");

        // 宣告不同介面參考指向同一個物件實體
        Exportable expRef = backupDoc;
        Compressible compRef = backupDoc;

        System.out.println("=== 物件參考一致性驗證 ===");
        System.out.println("expRef 與 compRef 是否指向同一個物件實體: " + (expRef == compRef));
        System.out.println("expRef 記憶體位址 Hash: " + System.identityHashCode(expRef));
        System.out.println("compRef 記憶體位址 Hash: " + System.identityHashCode(compRef));

        System.out.println("\n=== 介面可視方法呼叫測試 ===");
        // expRef 只能看到 export 方法，看不到 compress
        expRef.export("pdf");

        // compRef 只能看到 compress 方法，看不到 export
        compRef.compress(9);
    }
}
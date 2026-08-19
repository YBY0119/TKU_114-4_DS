// ReportExporterFactory.java
interface ReportExporter {
    void export(String title, int[] values);
}

class CsvExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.println("--- [CSV 匯出] " + title + " ---");
        if (values == null || values.length == 0) {
            System.out.println("Index,Value\n(空無資料)");
            return;
        }
        System.out.println("Index,Value");
        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + "," + values[i]);
        }
    }
}

class JsonExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.println("--- [JSON 匯出] " + title + " ---");
        System.out.print("{\n  \"title\": \"" + title + "\",\n  \"data\": [");
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                System.out.print(values[i] + (i < values.length - 1 ? ", " : ""));
            }
        }
        System.out.println("]\n}");
    }
}

class TextExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.println("--- [純文字報表] " + title + " ---");
        if (values == null || values.length == 0) {
            System.out.println("查無任何數值資料。");
            return;
        }
        for (int val : values) {
            System.out.print("[" + val + "] ");
        }
        System.out.println();
    }
}

public class ReportExporterFactory {
    // 依據字串格式回傳對應 Exporter；不支援的格式預設回傳 TextExporter
    public static ReportExporter createExporter(String format) {
        if (format == null) {
            return new TextExporter();
        }
        return switch (format.trim().toLowerCase()) {
            case "csv" -> new CsvExporter();
            case "json" -> new JsonExporter();
            default -> new TextExporter();
        };
    }

    // exportReport 只依賴 ReportExporter 介面，完全不使用 instanceof
    public static void exportReport(ReportExporter exporter, String title, int[] values) {
        exporter.export(title, values);
    }

    public static void main(String[] args) {
        int[] scores = {95, 88, 76, 100, 62};

        System.out.println("=== 測試正常格式匯出 ===");
        ReportExporter csvExp = createExporter("csv");
        exportReport(csvExp, "期中考成績", scores);
        System.out.println();

        ReportExporter jsonExp = createExporter("json");
        exportReport(jsonExp, "期中考成績", scores);
        System.out.println();

        System.out.println("=== 測試不支援格式 (自動降級為 TextExporter) ===");
        ReportExporter unknownExp = createExporter("xml");
        exportReport(unknownExp, "未知格式測試", scores);
        System.out.println();

        System.out.println("=== 測試 values 為 null 防護機制 ===");
        ReportExporter fallbackExp = createExporter("json");
        exportReport(fallbackExp, "空報表測試", null);
    }
}
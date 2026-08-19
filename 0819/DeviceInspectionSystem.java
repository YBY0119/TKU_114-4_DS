// DeviceInspectionSystem.java
class Device {
    protected String serialNumber;

    public Device(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void runDiagnostic() {
        System.out.println("[Device " + serialNumber + "] 執行基本通訊與電源自我檢測。");
    }
}

class Laptop extends Device {
    public Laptop(String serialNumber) {
        super(serialNumber);
    }

    @Override
    public void runDiagnostic() {
        System.out.println("[筆記型電腦 " + serialNumber + "] 檢測螢幕面板、鍵盤與電池健康度。");
    }
}

class Router extends Device {
    public Router(String serialNumber) {
        super(serialNumber);
    }

    @Override
    public void runDiagnostic() {
        System.out.println("[路由器 " + serialNumber + "] 檢測 WAN/LAN 端口與封包轉發延遲。");
    }
}

class Printer extends Device {
    public Printer(String serialNumber) {
        super(serialNumber);
    }

    @Override
    public void runDiagnostic() {
        System.out.println("[印表機 " + serialNumber + "] 檢測進紙滾輪狀態與墨水存量。");
    }

    public void cleanPrintHead() {
        System.out.println(">>> 執行印表機 [" + serialNumber + "] 噴頭自動深度清潔作業...");
    }
}

public class DeviceInspectionSystem {
    public static void main(String[] args) {
        Device[] devices = new Device[] {
            new Laptop("LAP-2026-01"),
            new Printer("PRN-EPSON-88"),
            new Router("RTR-ASUS-AX3000"),
            new Printer("PRN-HP-PRO400")
        };

        System.out.println("=== 設備定期巡檢系統 ===");
        for (Device device : devices) {
            // 1. 多型執行 runDiagnostic()，不寫多餘 cast
            device.runDiagnostic();

            // 2. Pattern matching instanceof 只對 Printer 進行清潔
            if (device instanceof Printer p) {
                p.cleanPrintHead();
            }
            System.out.println("------------------------------------");
        }
    }
}
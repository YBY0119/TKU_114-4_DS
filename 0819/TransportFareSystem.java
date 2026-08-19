// TransportFareSystem.java
abstract class Transport {
    protected String routeName;

    public Transport(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteName() {
        return routeName;
    }

    public abstract double calculateFare(int distance);
}

class Bus extends Transport {
    private static final double BASE_FARE = 15.0;

    public Bus(String routeName) {
        super(routeName);
    }

    @Override
    public double calculateFare(int distance) {
        // 公車：基本票價 15 元，超過 10 公里每公里加收 2 元
        if (distance <= 10) {
            return BASE_FARE;
        }
        return BASE_FARE + (distance - 10) * 2.0;
    }
}

class Taxi extends Transport {
    private static final double STARTING_FARE = 70.0;

    public Taxi(String routeName) {
        super(routeName);
    }

    @Override
    public double calculateFare(int distance) {
        // 計程車：起跳 70 元，每公里加收 25 元
        return STARTING_FARE + distance * 25.0;
    }
}

public class TransportFareSystem {
    public static void main(String[] args) {
        Transport[] transports = new Transport[] {
            new Bus("紅26公車 (短途)"),
            new Bus("307幹線公車 (長途)"),
            new Taxi("台灣大車隊 (市區短程)"),
            new Taxi("多元計程車 (機場接送)")
        };

        int[] distances = {5, 20, 3, 35};

        System.out.println("=== 交通票價多型計算系統 ===");
        // 不得使用 instanceof，完全依靠 overridden calculateFare
        for (int i = 0; i < transports.length; i++) {
            Transport t = transports[i];
            int dist = distances[i];
            double fare = t.calculateFare(dist);
            System.out.printf("路線/名稱: %-18s | 里程: %2d km | 票價: NT$ %.1f%n", 
                              t.getRouteName(), dist, fare);
        }
    }
}
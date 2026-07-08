import java.util.ArrayList;
import java.util.List;

interface TransportUnit {
    void moveGoods();
}

class Employee implements TransportUnit {
    private String fullName;

    @Override
    public void moveGoods() {
        System.out.println("Employee is moving goods...");
    }
}

class Robot implements TransportUnit {
    private String robotName;
    private int batteryPercent;

    @Override
    public void moveGoods() {
        System.out.println("Robot is lifting goods with its gripper...");
    }
}

public class SmartLogis {
    static class Order {
        private final String orderId;

        public Order(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderId() {
            return orderId;
        }

        @Override
        public String toString() {
            return "Order{orderId='" + orderId + "'}";
        }
    }

    static class Warehouse {
        private final String warehouseName;
        private final List<Order> orders = new ArrayList<>();

        public Warehouse(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public void addOrder(Order order) {
            orders.add(order);
        }

        public void printWarehouseInfo() {
            System.out.println("Warehouse: " + warehouseName);
            System.out.println("Orders in warehouse: " + orders);
        }
    }

    public static void main(String[] args) {
        Order order = new Order("DH_001");
        System.out.println("Order created independently: " + order);

        Warehouse warehouse = new Warehouse("Hanoi Warehouse");
        warehouse.addOrder(order);
        warehouse.printWarehouseInfo();

        warehouse = null;
        System.out.println("Warehouse reference has been set to null.");
        System.out.println("Order still exists independently after destroying the warehouse reference: " + order);
        System.out.println("Order ID is still accessible: " + order.getOrderId());
    }
}

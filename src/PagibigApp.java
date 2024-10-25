public class PagibigApp {
    public static void main(String[] args) {
        // This sets the start point of the queue
        QueueManager queueManager = QueueManager.getInstance(1);
        createCustomers(queueManager);
        serveCustomers(queueManager);
    }

    // Customer creation
    private static void createCustomers(QueueManager queueManager) {
        printCustomerCreation(queueManager, "Professor Jerry");
        printCustomerCreation(queueManager, "Richmond Baltazar");
        printCustomerCreation(queueManager, "Kevin Lisboa");
    }

    private static void printCustomerCreation(QueueManager queueManager, String customerName) {
        try {
            System.out.println(queueManager.createCustomer(customerName));
        } catch (Exception e) {
            System.err.println("Error creating customer: " + e.getMessage());
        }
    }

    // Serve customers
    private static void serveCustomers(QueueManager queueManager) {
        while (true) {
            serveNextCustomer(queueManager);
        }
    }

    private static void serveNextCustomer(QueueManager queueManager) {
        try {
            String message = queueManager.serveNextCustomer();
            System.out.println(message);
        } catch (IllegalStateException e) {
            System.err.println("Error serving customer: " + e.getMessage());
            System.exit(0);
        }
    }
}

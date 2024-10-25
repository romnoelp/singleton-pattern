import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class QueueManager {
    private static QueueManager instance;
    private final AtomicInteger currentQueueNumber;
    private final List<Customer> customers;

    // Private constructor to prevent instantiation
    private QueueManager(int startingQueueNumber) {
        this.currentQueueNumber = new AtomicInteger(startingQueueNumber);
        this.customers = new ArrayList<>();
    }

    public static synchronized QueueManager getInstance(int startingQueueNumber) {
        if (instance == null) {
            instance = new QueueManager(startingQueueNumber);
        }
        return instance;
    }

    public String createCustomer(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid customer name.");
        }

        int customerQueueNumber = currentQueueNumber.get();
        Customer customer = new Customer(name, customerQueueNumber);
        customers.add(customer);
        currentQueueNumber.incrementAndGet();
        return name + " has been added with queue number: " + customerQueueNumber;
    }

    public String serveNextCustomer() {
        if (customers.isEmpty()) {
            throw new IllegalStateException("No customers in the queue.");
        }

        Customer nextCustomer = customers.removeFirst();
        return "Serving customer: " + nextCustomer.getName() + " (Your queue number: " + nextCustomer.getQueueNumber() + ")";
    }
}

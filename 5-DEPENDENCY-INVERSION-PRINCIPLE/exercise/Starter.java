import java.util.Vector;

interface OrderRepository {
    void save(String order);
}

class MySQLOrderRepository implements OrderRepository {
    @Override
    public void save(String order){
        System.out.println("Saving order to MySQL: " + order);
    }
}

class MongoOrderRepository implements OrderRepository {
    @Override
    public void save(String order){
        System.out.println("Saving order to Mongo: " + order);
    }
}

class InMemoryOrderRepository implements OrderRepository {
    private Vector<String>  listOrder = new Vector<>();
    
    @Override
    public void save(String order){
        System.out.println("Saving order to Memory: " + order);
        this.listOrder.add(order);
    }
    Vector<String> getList(){
        return listOrder;
    }
}

class OrderProcessor {
    private OrderRepository repository;

    OrderProcessor(OrderRepository repository) {
    this.repository = repository;
}

    public void process(String order) {
        System.out.println("Processing order: " + order);
        repository.save(order);
    }
}

class Main {
    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor(new MySQLOrderRepository());
        processor.process("Order #1001");
        
        InMemoryOrderRepository memory = new InMemoryOrderRepository();
        
        OrderProcessor processor1 = new OrderProcessor(memory);
        processor1.process("Order #1002");
        processor1.process("Order #1003");

        System.out.println(memory.getList() + " Orders in The Memory");
    }
}
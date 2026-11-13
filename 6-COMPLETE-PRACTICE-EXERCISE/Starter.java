// 1 discount application
interface DiscountPolicy {
    double apply(double amount);
}

class RegularCustomerDiscount implements DiscountPolicy {
    @Override
    public double apply(double amount){
        return amount - (amount * 0.05);
    }
}

class StudentCustomerDiscount implements DiscountPolicy {
    @Override
    public double apply(double amount){
        return amount - (amount * 0.15);
     }
}

// 2 payment and refund application

interface PaymentService {
    void pay(double amount);
}

interface RefundablePayment {
    void refund(double amount);
}

class CreditCardPayment implements PaymentService,RefundablePayment {
    @Override
    public void pay(double amount){
        System.out.println("Paid: "+ amount + "$ with a credit card");
    }
    @Override
    public void refund(double amount) {
        System.out.println("Refund: " + amount + "$ to the credit card");
    }
}

class PayPalPayment implements PaymentService, RefundablePayment {
    @Override
    public void pay(double amount){
        System.out.println("Paid: " + amount + "$ with a PayPal");
    }
    @Override
    public void refund(double amount) {
        System.out.println("Refund: " + amount + "$ to the PayPal");
    }
}

class BankPayment implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.println("Paid: " + amount + "$ with a bank transfer");
    }
}


// 3 database application

interface OrderRepository {
    void save();
}

class MySQLOrderRepository implements OrderRepository {
    @Override
    public void save(){
        System.out.println("Saving order to MySQL: ");
    }
}

// 4 notification application

interface NotificationService {
    void send(String text);
}

class EmailService implements NotificationService {
    @Override
    public void send(String text) {
        System.out.println("Sending email to " + text);
    }
}

// 5 logging application

interface LoggerService {
    void log(String message);
}

class ConsoleLogger implements LoggerService {
    @Override
    public void log(String message){
        System.out.println(message);
    }
}

class OrderService {
    private DiscountPolicy discount;
    private PaymentService payment;
    private OrderRepository repository;
    private NotificationService notification;
    private LoggerService logger;
    private double total;

    public OrderService(DiscountPolicy discount, PaymentService payment, OrderRepository repository, NotificationService notification, LoggerService logger){
        this.discount = discount;
        this.payment = payment;
        this.repository = repository;
        this.notification = notification;
        this.logger = logger;
        this.total = 0;
    }

    void checkout(double amount, String email) {
        total = discount.apply(amount);
        payment.pay(total);
        repository.save();
        notification.send(email);
        logger.log("Order paid: " + total);
    }

    double getTotalAmount(){
        return this.total;
    }
}

class Main {
    public static void main(String[] args) {
        double amount = 100;

        DiscountPolicy stu = new StudentCustomerDiscount();
        PayPalPayment payment = new PayPalPayment();
        OrderRepository repository = new MySQLOrderRepository();
        NotificationService notification = new EmailService();
        LoggerService logger = new ConsoleLogger();

        OrderService orderSer = new OrderService(stu , payment , repository , notification , logger);

        orderSer.checkout(amount , "example@gmail.com");
        double total = orderSer.getTotalAmount();
        
        payment.refund(total);
    }
}
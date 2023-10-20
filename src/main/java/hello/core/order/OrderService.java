package hello.core.order;

public interface OrderService {
    Order createOrder(Long memderId,String itemName,int itemPrice);
}

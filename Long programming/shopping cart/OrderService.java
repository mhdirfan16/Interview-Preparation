package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderService {
    private ArrayList<Order> orders=new ArrayList<>();
    public void createOrder(CartService cartService) {
        orders.add(new Order(cartService.getCartItem(), cartService.calTotal(), LocalDateTime.now()));
        cartService.checkout();
    }
    public ArrayList<Order> getOrders(){
        return orders;
    }
}
class Order{
    private ArrayList<CartItem> cartItems;
    private Double totalCost;
    private LocalDateTime dateTime;

    public Order(ArrayList<CartItem> cartItems, Double totalCost, LocalDateTime dateTime) {
        this.cartItems = cartItems;
        this.totalCost = totalCost;
        this.dateTime = dateTime;
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "cartItems=" + cartItems +
                ", totalCost=" + totalCost +
                ", dateTime=" + dateTime +
                '}';
    }

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
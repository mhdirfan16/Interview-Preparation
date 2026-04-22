package org.example;

import java.util.ArrayList;

public class CartService {
    private ArrayList<CartItem> cartItem=new ArrayList<>();
    public void addTo(Product product,int quantity) {
        cartItem.add(new CartItem(product,quantity));
        System.out.println("Product added!");

    }
    public ArrayList<CartItem> getCartItem(){
        return (ArrayList)cartItem.clone();
    }
    
    public void showCart() {
        if(cartItem.isEmpty()){
            System.out.println("Cart is empty!");
        }
        cartItem.forEach(item->System.out.println(item));
    }

    public ArrayList<CartItem> checkout() {
        ArrayList<CartItem> temp=cartItem;
        cartItem.clear();
        return temp;
    }
    public Double calTotal(){
        Double sum=0D;
        for(CartItem item:cartItem){
            sum+=item.getProduct().getProductCost()*item.getQuantity();
        }
        return sum;
    }

    public void removeProduct(String id1) {
        cartItem.removeIf(item -> id1.equals(item.getProduct().getProductId()));
        System.out.println("Product removed");
    }
}
class CartItem{
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
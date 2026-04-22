package org.example;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        OrderService orderService=new OrderService();
        CartService cartService=new CartService();
        ProductService productService=new ProductService();
        while(true){
        System.out.println("-------MAIN MENU-------");
        System.out.println("1.See Products");
        System.out.println("2.My Cart");
        System.out.println("3.My Order");
        System.out.println("4.Quit");
        System.out.print("Enter the option:");
        int option=sc.nextInt();
        if(option==4){
            break;
        }
        switch(option){
            case 1:
                productService.printAllProduct();
                System.out.println("Enter product Id to buy");
                sc.nextLine();
                String id=sc.nextLine();
                Product product = productService.getProductById(id);
                if(product==null){
                    System.out.println("Invalid Id");
                    continue;
                }
                System.out.println("Enter quantity");
                int quantity=sc.nextInt();
                cartService.addTo(product,quantity);
                break;
            case 2:
                cartService.showCart();
                System.out.println("1.Checkout");
                System.out.println("2.Remove CartItem");
                System.out.println("3.Main menu");
                int choice=sc.nextInt();
                if(choice==1){
                    if(cartService.getCartItem().isEmpty()){
                        System.out.println("Empty cart, Returning to main menu");
                        continue;
                    }
                    orderService.createOrder(cartService);
                    System.out.println("Order Placed!");
                }else if(choice==2){
                    if(cartService.getCartItem().isEmpty()){
                        System.out.println("Empty cart, Returning to main menu");
                        continue;
                    }
                    System.out.println("Enter product id");
                    sc.nextLine();
                    String id1=sc.nextLine();
                    cartService.removeProduct(id1);
                }
                else{
                    continue;
                }
                break;
            case 3:
                ArrayList<Order> orders = orderService.getOrders();
                orders.forEach(o ->System.out.println(o) );
                break;
            default:
                System.out.println("Invalid option");
        }
        }

    }
}
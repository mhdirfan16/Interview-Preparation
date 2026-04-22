package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductService {
    private List<Product> productList= List.of(new Product("1","Chocolate",10.0),
            new Product("2","Icecream",15.0),
            new Product("3","Candy",5.0),
            new Product("4","Beevers",10.0),
            new Product("5","Biscuit",20.0));

    public void printAllProduct() {
        productList.forEach(p->System.out.println(p));
    }
    public Product getProductById(String id){
        for(Product product:productList){
            if(id.equals(product.getProductId())){
                return product;
            }
        }
        return null;
    }

}
class Product{
    private String productId;
    private String productName;
    private Double productCost;

    public Product(String productId, String productName, Double productCost) {
        this.productId = productId;
        this.productName = productName;
        this.productCost = productCost;
    }

    public Product() {

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productCost=" + productCost +
                '}';
    }

    public Double getProductCost() {
        return productCost;
    }

    public void setProductCost(Double productCost) {
        this.productCost = productCost;
    }
}

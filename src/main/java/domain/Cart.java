package main.java.domain;

public class Cart {
    private Products product;
    private int count;          //总数
    private double total;       //小计

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotal(){
        return total = product.getPrice()*count;
    }

    public void setTotal(){
        this.total = total;
    }

}

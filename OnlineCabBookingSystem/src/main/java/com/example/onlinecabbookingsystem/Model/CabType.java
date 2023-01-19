package com.example.onlinecabbookingsystem.Model;

public enum CabType {
    ECONOMY, COMPACT, MUV, LUXURY;

    double price;

    public void setPrice(double price) {
        this.price = price;
    }
    public int checkCapacity() {

        return switch (this) {
            case ECONOMY -> 4;
            case COMPACT -> 4;
            case MUV -> 8;
            case LUXURY -> 2;
            default -> 4;
        };
    }

    public double providePrice() {

        double price = 5;

        return switch (this) {
            case ECONOMY -> price = price * 1.5;
            case COMPACT -> price = price * 2;
            case MUV -> price = price * 4;
            case LUXURY -> price = price * 8;
            default -> price;
        };
    }
}

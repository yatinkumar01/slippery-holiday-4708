package com.cab.fab5cabbooking.Model;

public enum CabType {
    ECONOMY, COMPACT, MUV, LUXURY;

    Double price;

    public void setPrice(double price) {
        this.price = price;
    }

    public int sittingCapacity() {

        switch (this) {
            case ECONOMY:
                return 5;
            case COMPACT:
                return 4;
            case MUV:
                return 6;
            case LUXURY:
                return 2;
            default:
                return 4;
        }

        /*return switch (this) {
            case ECONOMY -> 4;
            case COMPACT -> 4;
            case MUV -> 8;
            case LUXURY -> 2;
            default -> 4;
        };*/
    }

    public double providePrice() {

        double price = 2;

        switch (this) {
            case ECONOMY:
                return price *= 1.25;
            case COMPACT:
                return price *= 1.5;
            case MUV:
                return price *= 2;
            case LUXURY:
                return price *= 8;
            default:
                return price;
        }

        /*return switch (this) {
            case ECONOMY -> price = price * 1.5;
            case COMPACT -> price = price * 2;
            case MUV -> price = price * 4;
            case LUXURY -> price = price * 8;
            default -> price;
        };*/
    }
}

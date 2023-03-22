package lionsCheckpointC;

public class CheckoutLane {

    private Customer checkoutCustomer;

    public CheckoutLane(){}

    public Customer getCheckoutCustomer() {
        return checkoutCustomer;
    }

    public void setCheckoutCustomer(Customer checkoutCustomer) {
        this.checkoutCustomer = checkoutCustomer;
    }

    public boolean isInUse(){
        return checkoutCustomer != null;
    }
}

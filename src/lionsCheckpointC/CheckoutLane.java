package lionsCheckpointC;

public class CheckoutLane {

    private Customer checkoutCustomer;
    private Customer next;

    public CheckoutLane(){}
    
    public void exchange() {
    	checkoutCustomer = next;
    	setNext(null);
    }

    public Customer getCheckoutCustomer() {
        return checkoutCustomer;
    }

    public void setCheckoutCustomer(Customer checkoutCustomer) {
        this.checkoutCustomer = checkoutCustomer;
    }
    
    public Customer getNext() {
		return next;
	}

	public void setNext(Customer next) {
		this.next = next;
	}

	public boolean isInUse(){
        return checkoutCustomer != null;
    }
}

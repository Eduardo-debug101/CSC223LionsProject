package lionsCheckpointE;

public class CheckoutLane implements Comparable<CheckoutLane>{

    private Customer checkoutCustomer;
    private int laneId;
    private static int nextLaneId = 1;

    public CheckoutLane() {
        laneId = nextLaneId;
        nextLaneId++;
    }

    public Customer getCheckoutCustomer() {
        return checkoutCustomer;
    }

    public void setCheckoutCustomer(Customer checkoutCustomer) {
        this.checkoutCustomer = checkoutCustomer;
    }

    public boolean isInUse() {
        return checkoutCustomer != null;
    }

    public int getLaneId() {
        return laneId;
    }

    public void setLaneId(int laneId) {
        this.laneId = laneId;
    }

    public static void clear(){
        nextLaneId = 1;
    }

    public int compareTo(CheckoutLane o) {

        if(this.isInUse() && o.isInUse()){
            if(this.checkoutCustomer.getFinishTime() == o.checkoutCustomer.getFinishTime()){
                return 0;
            } else if (this.checkoutCustomer.getFinishTime() < o.checkoutCustomer.getFinishTime()) {
                return 1;
            }else {
                return -1;
            }
        }else{
            if(!this.isInUse() && !o.isInUse()){
                return 0;
            }else if(!this.isInUse()){
                return -1;
            }else{
                return 1;
            }
        }
    }
}

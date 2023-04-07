package lionsCheckpointE;


class EmptyQueueException extends RuntimeException {
    public EmptyQueueException() {
        super("Error: The queue is empty");
    }
}

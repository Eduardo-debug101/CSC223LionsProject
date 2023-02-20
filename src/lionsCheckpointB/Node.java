package lionsCheckpointB;

public class Node {
	String element; // list element
	Node next; // successor link

	Node(String el, Node n) {
		element = el;
		next = n;
	}

	Node(String el) {
		element = el;
		next = null;
	}

}

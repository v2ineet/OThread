package org.noclassdef.example.cache;

import java.util.HashMap;
import java.util.Map;

class CustomLRUCache<K, V> {

	private final int capacity;

	Node<K, V> head = null;
	Node<K, V> tail = null;

	Map<K, Node<K, V>> cacheMap;

	private class Node<K, V> {
		private K key;
		private V value;

		private Node<K, V> next;
		private Node<K, V> previous;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public Node<K, V> getNext() {
			return next;
		}

		public void setNext(Node<K, V> next) {
			this.next = next;
		}

		public Node<K, V> getPrevious() {
			return previous;
		}

		public void setPrevious(Node<K, V> previous) {
			this.previous = previous;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		@Override
		public String toString() {
			return "Key: " + key + "value: " + value;
		}
	}

	@Override
	public String toString() {
		if (head == null) {
			return "--Blank--";
		}

		Node<K, V> node = head;
		StringBuilder builder = new StringBuilder();

		do {
			builder.append(node);
			builder.append(" => ");
		} while ((node = node.getNext()) != null);

		return builder.toString();
	}

	public CustomLRUCache(int capacity) {
		this.capacity = capacity;
		this.cacheMap = new HashMap<K, Node<K, V>>(capacity); // will round to
																// next 2^n

	}

	public void add(K key, V value) {
		Node<K, V> valueFromCache = this.cacheMap.get(key);

		if (valueFromCache == null) {
			Node<K, V> node = addToList(key, value);
			this.cacheMap.put(key, node);

			if (cacheMap.size() > this.capacity) {
				removeLast();
			}
		} else {
			bringToFront(valueFromCache);
		}

	}

	public V get(K key) {
		Node<K, V> node = cacheMap.get(key);
		if (node != null) {
			node.getValue();
		}

		return null;
	}

	private Node<K, V> addToList(K key, V value) {
		Node<K, V> node = new Node<>(key, value);

		if (head == null) {// first node
			head = node;
			tail = head;
		} else {
			head.setPrevious(node);
			node.setNext(head);
			head = node;
		}

		return node;

	}

	private void bringToFront(Node<K, V> node) {
		Node<K, V> previous = node.getPrevious();
		Node<K, V> next = node.getNext();

		if (previous == null) {
			return; // head
		} else if (next == null) {// tail node
			tail = previous;

		} else {// somewhere in middle

			previous.setNext(next);
			next.setPrevious(previous);

		}

		node.setNext(head);
		node.setPrevious(null);
		
		head.setPrevious(node);
		head = node;

	}

	private void removeLast() {
		Node<K, V> previous = tail.getPrevious();
		previous.setNext(null);
		this.cacheMap.remove(tail.getKey());

		tail = previous;
	}

}

public class LRUCacheDemo {

	public static void main(String[] args) {

		CustomLRUCache<String, Integer> cache = new CustomLRUCache<>(5);
		cache.add("1", 11);
		System.out.println(cache.toString());
		cache.add("2", 22);
		System.out.println(cache.toString());
		cache.add("3", 33);
		System.out.println(cache.toString());
		cache.add("4", 44);
		System.out.println(cache.toString());
		cache.add("5", 55);
		System.out.println(cache.toString());
		cache.add("6", 66);
		System.out.println(cache.toString());
		cache.add("7", 77);
		System.out.println(cache.toString());
		cache.add("8", 88);
		System.out.println(cache.toString());
		cache.add("9", 99);
		System.out.println(cache.toString());
		cache.add("6", 66);
		System.out.println(cache.toString());
	}

}

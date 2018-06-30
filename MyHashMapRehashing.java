import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

public class MyHashMapRehashing<K, V> {
        
    public static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V Value) {
            this.value = value;
        }
    }

    public static final int DEFAULT_EXPAND_RATE = 2;
    public static final int DEFAULT_CAPACITY = 16;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    

    private int size;
    private Node<K, V>[] bucket;
    private float loadFactor;

    public MyHashMapRehashing() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMapRehashing(int capacity, float loadFactor) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity can not be <= zero");
        }

        this.bucket = (Node<K, V>[])(new Node[capacity]);
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    public V put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> head = bucket[bucketIndex];
        Node<K, V> node = head;

        while (node != null) {
            if (equalsKey(node.key, key)) {
                V result = node.value;
                node.value = value;
                return result;
            }
            node = node.next;
        }
        
        Node<K, V> newNode = new Node(key, value);
        newNode.next = head;
        bucket[bucketIndex] = newNode;
        size++;
        if (needRashing()) {
            rehashing();
        }
        return null;
     }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> node = bucket[bucketIndex];

        while (node != null) {
            if (equalsKey(node.key, key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> curr = bucket[bucketIndex];
        Node<K, V> prev = null;

        while (curr != null) {
            if (equalsKey(curr.key, key)) {
                if (prev == null) {
                    bucket[bucketIndex] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
        }

        return null;
    }

    public void clear() {
        Arrays.fill(bucket, null);
    }

    public boolean containsKey(K key) {
        if (isEmpty()) {
            return false;
        }

        int bucketIndex = getBucketIndex(key);
        Node<K, V> node = bucket[bucketIndex];
      
        while (node != null) {
            if (equalsKey(node.key, key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public boolean containsValue(V Value) {
        if (isEmpty()) {
            return false;
        }

        for (Node<K, V> node : bucket) {
            while (node != null) {
                if (equalsValue(node.value, value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        /*
        int code = key.hashCode();
        return code >= 0 ? code : -code;

        range = [-2^31, 2^31-1]
        -Integer.MAX_VALUE => Overflow
        */
        return key.hashCode() & 0x7FFFFFFF;
    }

    private int getBucketIndex(K key) {
        return hash(key) % bucket.length;
    }

    private boolean equalsKey(K k1, K k2) {
        if (k1 == null) {
            return k2 == null;
        }

        return k1.equals(k2);
    }

    private boolean equalsValue(V v1, V v2) {
        if (v1 == null) {
            return k2 == null;
        }

        return v1.equals(v2);
    }

    private boolean needRashing() {
        float ratio = (size + 0.0f) / bucket.length;
        return ratio >= loadFactor;
    }

    private void rehashing() {
        Node<K, V> oldBucket = bucket;
        bucket = (Node<K, V>[])(new Node[bucket.length * DEFAULT_EXPAND_RATE]);

        for (Node<K, V> node : oldBucket) {
            while (node != null) {
                put(node.getKey(), node.getValue());
            }
        }
    }
        
    
    public static void main(String[] args) {
        MyHashMapRehashing<Integer, String> map = new MyHashMapRehashing<>();
        map.put(1,"one");
        map.put(2,"two");
        System.out.println(map.get(1));
    }
}

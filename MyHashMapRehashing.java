import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

public class MyHashMapRehashing<K, V> {
        
    public static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;
        Entry(K key, V value) {
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
    private Entry<K, V>[] bucket;
    private float loadFactor;

    public MyHashMapRehashing() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMapRehashing(int capacity, float loadFactor) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity can not be <= zero");
        }

        this.bucket = (Entry<K, V>[])(new Entry[capacity]);
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    public V put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> head = bucket[bucketIndex];
        Entry<K, V> Entry = head;

        while (Entry != null) {
            if (equalsKey(Entry.key, key)) {
                V result = Entry.value;
                Entry.value = value;
                return result;
            }
            Entry = Entry.next;
        }
        
        Entry<K, V> newEntry = new Entry(key, value);
        newEntry.next = head;
        bucket[bucketIndex] = newEntry;
        size++;
        if (needRashing()) {
            rehashing();
        }
        return null;
     }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> Entry = bucket[bucketIndex];

        while (Entry != null) {
            if (equalsKey(Entry.key, key)) {
                return Entry.value;
            }
            Entry = Entry.next;
        }
        return null;
    }

    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> curr = bucket[bucketIndex];
        Entry<K, V> prev = null;

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
        Entry<K, V> Entry = bucket[bucketIndex];
      
        while (Entry != null) {
            if (equalsKey(Entry.key, key)) {
                return true;
            }
            Entry = Entry.next;
        }
        return false;
    }

    public boolean containsValue(V Value) {
        if (isEmpty()) {
            return false;
        }

        for (Entry<K, V> Entry : bucket) {
            while (Entry != null) {
                if (equalsValue(Entry.value, value)) {
                    return true;
                }
                Entry = Entry.next;
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
        Entry<K, V> oldBucket = bucket;
        bucket = (Entry<K, V>[])(new Entry[bucket.length * DEFAULT_EXPAND_RATE]);

        for (Entry<K, V> Entry : oldBucket) {
            while (Entry != null) {
                put(Entry.getKey(), Entry.getValue());
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

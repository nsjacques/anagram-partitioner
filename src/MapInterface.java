/*

*/

import java.util.Iterator;


public interface MapInterface<K,V> extends Iterable<Z>{

	//public void clear();

	public boolean containsKey(Object key);

	//public boolean containsValue(Object value);

	//public boolean equals(Object o);

	public boolean isEmpty();

	public V put(K key, V value);

	public V get(K key);

	//public V remove(Object key);

	public int size();

	public Iterator<Z> iterator();

}
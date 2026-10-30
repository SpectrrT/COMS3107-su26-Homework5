import java.util.*;

public class Combiner {

	public static <K, V> Map<K, Set<V>> combine(Map<K, V>... maps) {

		Map<K, Set<V>> result = new TreeMap<>();

		for (Map<K, V> map : maps) {
			for (Map.Entry<K, V> entry : map.entrySet()) {
				Set<V> values = result.getOrDefault(entry.getKey(), new HashSet<V>());
				values.add(entry.getValue());
				result.put(entry.getKey(), values);
			}
		}

		return result;
	}

}

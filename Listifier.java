import java.util.*;

public class Listifier {

	public static <T> List<T> listify(T... values) {
		List<T> unique = new LinkedList<>();
		for (T value : values) {
			if (!unique.contains(value)) unique.add(value);
		}
		return unique;
	}
}

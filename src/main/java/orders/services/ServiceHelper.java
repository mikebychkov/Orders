package orders.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.GregorianCalendar;
import java.util.Optional;

public class ServiceHelper {

    public static GregorianCalendar getRecentDate() {
        GregorianCalendar date = new GregorianCalendar();
        date.setTimeInMillis(System.currentTimeMillis());
        return date;
    }

    public static <V> void checkValue(V value, String name) {
        if (value == null) {
            throw new IllegalArgumentException(String.format("%s not specified!", name));
        }
    }

    public static <T, K> Optional<T> checkId(K id, JpaRepository<T, K> repo, String name) {
        checkValue(id, name);
        Optional<T> op = repo.findById(id);
        if (op.isEmpty()) {
            throw new IllegalArgumentException(String.format("Invalid %s specified!", name));
        }
        return op;
    }
}

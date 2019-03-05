import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public abstract class LocationData {

    // Здесь хранятся все тараканы. Нужно для дальнейшей отрисовки и "убийства" потоков, а так же для того, чтобы подогнать тараканов
    // выбрал множество, а не список потому что при добавлении уже существующего элемента, он просто обновляется. список множит

    public static Set<Cockroach> setOfCoordinates = Collections.newSetFromMap(new ConcurrentHashMap<>());

}

package JavaTest.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.junit.Test;

public class TestGeneric {

    @Test
    public void testG(){
        List<Fruit> fruits = new ArrayList<>();

        fruits.add(new Apple());
        fruits.add(new Orange());
        fruits.add((Fruit)new Stone());

        fruits.forEach(e -> System.out.println(e.getColor()));

    }

    <T, R, V> Function<T,R> andThen(Function<T, V> first, Function<? super V,? extends R> second){
        return ( t -> second.apply(first.apply(t)));

    }

    @Test
    public void testAndThen(){
        Function<Integer, Integer> first = x -> x+1;
        Function<Number, Integer> second = x -> x.intValue()*2;

        Function<Integer, Integer> combined = andThen(first, second);
    }

    @Test
    public void testMapObject(){
        Map<String, Object> map = new HashMap<>();
        map.put("a", new HashMap<String, Object>());
        getObjectFromMap(map, Fruit.class);

    }

    public <T> T getObjectFromMap(Map<String, Object> map, Class<?> cls){
        //todo: generate a concret object from name/valeu map
        return null;
    }
}
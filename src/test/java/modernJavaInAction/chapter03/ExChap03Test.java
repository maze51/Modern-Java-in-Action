package modernJavaInAction.chapter03;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ExChap03Test {

    @Test
    void process() {
        //assertThat();
        ExChap03.process(() -> System.out.println("Run"));
    }

    @Test
    void filter() {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("");
        listOfStrings.add("1");

//        List<String> nonEmpty = ExChap03.filter(listOfStrings, new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return !s.isEmpty();
//            }
//        });
        List<String> nonEmpty = ExChap03.filter(listOfStrings, s -> !s.isEmpty());
        assertThat(nonEmpty.size()).isEqualTo(1);
    }

    @Test
    void forEach() {
        ExChap03.forEach(Arrays.asList(1, 2, 3, 4, 5), i -> System.out.println(i));
    }

    @Test
    void map() {
        List<Integer> list = ExChap03.map(Arrays.asList("lambdas", "in", "action"), s -> s.length());
        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(0)).isEqualTo(7);
    }
}
package modernJavaInAction.chapter02;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static modernJavaInAction.chapter02.ExChap02.Color.*;
import static org.assertj.core.api.Assertions.*;

class ExChap02Test {

    List<Apple> inventory = Arrays.asList(
            new Apple(80, GREEN),
            new Apple(130, GREEN),
            new Apple(180, GREEN),
            new Apple(155, RED)
    );

    @Test
    void filterGreenApples() {
        List<Apple> apples = ExChap02.filterGreenApples(inventory);
        assertThat(apples.size()).isEqualTo(3);
        for(Apple apple : apples) {
            assertThat(apple.getColor()).isEqualTo(GREEN);
        }
    }

    @Test
    void filterApplesByColor() {
        List<Apple> apples = ExChap02.filterApplesByColor(inventory, RED);
        assertThat(apples.size()).isEqualTo(1);
    }

    @Test
    void filterApplesByWeight() {
        List<Apple> apples = ExChap02.filterApplesByWeight(inventory, 150);
        for (Apple apple : apples) {
            assertThat(apple.getWeight()).isGreaterThan(150);
        }
    }

    @Test
    void filterApplesByFlagParam() {
        List<Apple> apples = ExChap02.filterApplesByFlagParam(inventory, GREEN, 100, true);
        assertThat(apples.size()).isEqualTo(3);
        for (Apple apple : apples) {
            assertThat(apple.getColor()).isEqualTo(GREEN);
        }
    }

    @Test
    void filterApples() {
    /*
        List<Apple> apples = ExChap02.filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor().equals(RED) && apple.getWeight() < 150;
            }
        });
    */
        // 람다식 변환 전-후 비교
        List<Apple> apples = ExChap02.filterApples(inventory, apple -> apple.getColor().equals(RED) && apple.getWeight() < 150);
        assertThat(apples.size()).isEqualTo(0);
    }

    @Test
    void filterApplesByT() {
        List<Apple> apples = ExChap02.filter(inventory, apple -> apple.getColor().equals(RED));
        for(Apple apple : apples) {
            assertThat(apple.getColor()).isEqualTo(RED);
        }
    }

    @Test
    void filterNumber() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(100);
        numbers.add(80);
        numbers.add(55);

        List<Integer> evenNumbers = ExChap02.filter(numbers, integer -> integer % 2 == 0);

        assertThat(evenNumbers.size()).isEqualTo(2);
    }

}
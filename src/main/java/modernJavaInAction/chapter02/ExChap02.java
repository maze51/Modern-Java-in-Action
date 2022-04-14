package modernJavaInAction.chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static modernJavaInAction.chapter02.ExChap02.Color.*;

public class ExChap02 {

    enum Color {
        RED, GREEN
    }

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, GREEN),
                new Apple(130, GREEN),
                new Apple(180, GREEN),
                new Apple(155, RED)
        );

        /*
            퀴즈 2-1 유연한 prettyPrintApple 메서드 구현하기
            사과 리스트를 인수로 받아 다양한 방법으로 문자열을 생성(커스터마이징된 다양한 toString() 메서드와 같이)할 수 있도록 파라미터화된 prettyPrintApple 메서드 구현하기.
            예를 들어 prettyPrintApple 메서드가 각각의 사과 무게를 출력하도록 지시할 수 있다. 혹은 각각의 사과가 무거운지, 가벼운지 출력하도록 지시할 수 있다.
         */

        prettyPrintApple(inventory, new AppleHeavyOrLightPrint());
        prettyPrintApple(inventory, new AppleWeightPrint());

    }

    // 1. 녹색 사과만 필터링하고 싶다
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    // 2. 특정 색깔의 사과만 필터링하고 싶다
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    // 3. 무게가 얼마 이상인 사과만 필터링하고 싶다(무게는 바뀔 수 있다)
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    // 4. 하나의 메서드로 색 또는 무게 기준으로 사과를 필터링하고 싶다
    public static List<Apple> filterApplesByFlagParam(List<Apple> inventory, Color color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if ((flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }
    // 동작은 하지만 flag 값의 의미도 불분명하고, 확장하기도 좋지 않은 코드다.
    // filterApples 에 어떤 기준으로 사과를 필터링할 지 효과적으로 전달할 방법은?

    // 5. ApplePredicate interface 를 활용한 필터링
    // 파라미터를 추가하는 방식이 아닌, 변화하는 요구사항에 좀 더 유연하게 대응할 수 있는 방법 => 메서드의 동작을 파라미터화 하는 것.
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();

        for(Apple apple : inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    // 퀴즈 2-1: 각각의 사과가 무거운지 가벼운지 출력한다.
    public static class AppleHeavyOrLightPrint implements AppleFormatter {
        @Override
        public String applePrettyPrint(Apple apple) {
            String printHeavyOrLightApple = apple.getWeight() > 150 ? "heavy" : "light";
            return "A " + printHeavyOrLightApple + " " + apple.getColor() + " Apple";
        }
    }

    // 퀴즈 2-1: 각각의 사과 무게를 출력한다.
    public static class AppleWeightPrint implements AppleFormatter {

        @Override
        public String applePrettyPrint(Apple apple) {
            return "An Apple of " + apple.getWeight() + "g";
        }
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for(Apple apple : inventory) {
            String output = formatter.applePrettyPrint(apple);
            System.out.println(output);
        }
    }

    // 7. 리스트 형식으로 추상화
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for(T e : list) {
            if(p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}
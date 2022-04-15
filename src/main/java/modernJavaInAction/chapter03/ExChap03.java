package modernJavaInAction.chapter03;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ExChap03 {
/*
    람다 표현식은 기술적으로 Java 8 이전의 Java로 할 수 없었던 일을 할 수 있게 만들지 않음.
    하지만 더 간결하게, 간단하게 동작을 구현하고 전달할 수 있다.

    람다 표현식의 특성
    1. 익명
    2. 함수 : 메서드처럼 특정 클래스에 종속되지 않으므로 함수라 부른다.
             하지만 메서드처럼 파라미터 리스트, 바디, 반환 방식, 가능한 예외 리스트를 가질 수 있다.
    3. 전달 : 람다 표현식을 메서드 인자로 전달하거나 변수로 저장할 수 있다.
    4. 간결성

    람다 표현식의 구성 : 람다 파라미터(리스트) -> 람다 바디(반환값에 해당)
    (Apple a) -> a.getWeight() > 150

    람다 표현식의 형태
    1. 표현식 스타일 : (parameters) -> expression
    2. 블록 스타일 : (parameters) -> { statements; }

    람다 표현식을 사용할 수 있는 대상 : 단 하나의 추상 메서드로 구성된 함수형 인터페이스(@FunctionalInterface annotation을 붙일 수 있다).
    참고) 함수형 인터페이스는 한 개 이상의 디폴트 메서드를 포함할 수 있다. 디폴트 메서드가 많든 적든 추상 메서드가 하나면 함수형 인터페이스이다.

 */

    Runnable r1 = () -> System.out.println("Hello World 1");

    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Hello World 2");
        }
    };

    public static void process(Runnable r) {
        r.run();
    }

    // 함수형 인터페이스 사용
    // 1. Predicate : T 형식의 객체를 사용하는 boolean 표현식이 필요할 때 사용.
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if(p.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    // 2. Consumer : T 형식의 객체를 인수로 받아서 어떤 동작을 수행하고 싶을 때 사용.
    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for(T t : list) {
            c.accept(t);
        }
    }

    // 3. Function : 입력을 출력으로 매핑하는 람다를 정의할 때 활용.
    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }

    // 기본형(primitive type) 특화 함수형 인터페이스 : 비용이 필요한 오토박싱 과정을 없앨 수 있는 인터페이스(IntPredicate, IntConsumer 등)

}

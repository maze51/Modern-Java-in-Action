package modernJavaInAction.chapter02;

public interface ApplePredicate {
    // true or false 를 반환하는 함수 : Predicate
    // 이 인터페이스는 선택 조건을 결정한다.
    boolean test (Apple apple);
}

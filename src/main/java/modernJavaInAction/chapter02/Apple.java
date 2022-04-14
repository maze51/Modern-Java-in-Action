package modernJavaInAction.chapter02;

public class Apple {
    private int weight;
    private ExChap02.Color color;

    public Apple(int weight, ExChap02.Color color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ExChap02.Color getColor() {
        return color;
    }

    public void setColor(ExChap02.Color color) {
        this.color = color;
    }
}

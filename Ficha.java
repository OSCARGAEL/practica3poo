
public class Ficha {
    private int lado1;
    private int lado2;

    public Ficha(int lado1, int lado2) {
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    public int getLado1() {
        return lado1;
    }

    public int getLado2() {
        return lado2;
    }

    @Override
    public String toString() {
        return "[" + lado1 + "|" + lado2 + "]";
    }
}

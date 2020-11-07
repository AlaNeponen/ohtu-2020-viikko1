package main.java.ohtu.ohtuvarasto;

public class Varasto {
    private int luku;
    public Varasto() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.luku++;
            }
        }
    }
}
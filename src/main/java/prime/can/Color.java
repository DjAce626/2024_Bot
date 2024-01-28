package prime.can;

public class Color {
    public byte r;
    public byte g;
    public byte b;

    public Color(byte r, byte g, byte b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getIntColor() {
        return (r << 16) | (g << 8) | b;
    }
}

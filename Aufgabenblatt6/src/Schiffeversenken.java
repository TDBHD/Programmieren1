//für einen spieler
public class Schiffeversenken {
    public static int frei = 0;
    public static int boot = 1;
    public static int getroffen = 2;
    public static int erraten = 3;

    public void spielfeld() {
        EinUndAusgabe einlesen = new EinUndAusgabe();
        System.out.println("Wie groß ist ihr Spielfeld?");
        int breite = einlesen.leseInteger();

        int spielfeld[][] = new int[breite][breite];

        for (int zeile = 0; zeile < breite; zeile++) {

        }
    }
}

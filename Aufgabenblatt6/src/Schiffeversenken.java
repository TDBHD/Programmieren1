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
                for (int zaehler = 0; zaehler < spielfeld.length; zaehler++) {
                    System.out.print("  " + zaehler + " ");
                }
                System.out.println();
                for (int x = 0; x < spielfeld.length; x++) {
                    for (int ersteZeile = 0; ersteZeile < breite; ersteZeile++) {
                        System.out.print("+---");
                    }
                    System.out.print("+");
                    System.out.println();
                    for (int y = 0; y < spielfeld.length; y++) {

                        switch (spielfeld[x][y]) {
                            case 0:
                                System.out.print("|   ");
                                break;
                            case 1:
                                System.out.print("| 1 ");
                                break;
                            case 2:
                                System.out.print("| 2 ");
                                break;
                            case 3:
                                System.out.print("| 3 ");
                                break;
                            default:
                                System.out.println("oops, something went wrong");
                                break;
                        }
                    }
                    System.out.print("|");
                    System.out.print(" " + x);
                    System.out.println();
                }
                for (int letzteZeile = 0; letzteZeile < breite; letzteZeile++) {
                    System.out.print("+---");
                }
                System.out.print("+");
                System.out.println();
            }

        }



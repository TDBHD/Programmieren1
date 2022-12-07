import java.util.Arrays;
import java.util.Random;

public class Slider {
    Random r = new Random();

    int zufaelligeZahl;
    int spielfeldgroesse = 4;
    int zeile;
    int spalte;
    String tausche;
    EinUndAusgabe ea = new EinUndAusgabe();
    int[][] spielfeld = new int[spielfeldgroesse][spielfeldgroesse];
    int[] einzigartigeZahl = new int[(spielfeldgroesse*spielfeldgroesse)];
    boolean ueberpruefen = false;

    void start(){
        getSpielfeldgroesse();
        zufaelligeZahl();
        spielfeldArray();
        while (richtigeReihenfolge()==false) {
            feldUeberpruefen();
            spielfeldAusdrucken();
            while (ueberpruefen == false) {
                setBenutzereingabe();
                benutezereingabeUeberpruefen();
            }
            ueberpruefen = false;
            if (leeresFeldUeberpruefen() == true) {
                tausche();
            }
        }
        System.out.println("Geschafft!");

    }


    void zufaelligeZahl() { http://www.java2s.com/ref/java/java-array-fill-unique-random-number.html  <- unique random numbers in array

    for (int i = 0; i < einzigartigeZahl.length; i++) {
        boolean einzigartig = true;
        zufaelligeZahl = r.nextInt((spielfeldgroesse*spielfeldgroesse)+1);
        for (int x : einzigartigeZahl) {
            if (x == zufaelligeZahl) {
                einzigartig = false;
            } else if (zufaelligeZahl == spielfeldgroesse*spielfeldgroesse) ;
            zufaelligeZahl = zufaelligeZahl;
        }
        if (einzigartig == true) {
            einzigartigeZahl[i] = zufaelligeZahl;
        } else {
            i -= 1;
        }
    }
    }

    void spielfeldArray() {
        int a = 0;
        for (int x = 0; x < spielfeld.length; x++) {
            for (int i = 0; i < spielfeld.length; i++) {
                spielfeld[i][x] = einzigartigeZahl[a];
                a++;
            }
        }
    }
    void spielfeldAusdrucken() {
        for (int i = 0; i < spielfeld.length; i++){
            System.out.print("  " + i);
        }
        System.out.println();
        for (int i = 0; i<spielfeld.length; i++) {
            for (int s = 0; s<spielfeldgroesse; s++) {
                System.out.print("+--");
            }
            System.out.print("+");
            System.out.println();
            for (int x = 0; x<spielfeld.length; x++){
                if (spielfeld[i][x] < 10){
                    System.out.print("|"+"0"+spielfeld[i][x]);
                }else if (spielfeld[i][x] == spielfeldgroesse*spielfeldgroesse){
                    System.out.print("|  ");
                }else {
                    System.out.print("|"+spielfeld[i][x]);
                }
            }
            System.out.print("|");
            System.out.print(" "+ i);
            System.out.println();
        }
        for (int s = 0; s<spielfeldgroesse; s++) {
            System.out.print("+--");
        }
        System.out.print("+");
        System.out.println();
    }
    void setBenutzereingabe(){
        System.out.println("zeile: ");
        zeile = ea.leseInteger();
        System.out.println("spalte: ");
        spalte = ea.leseInteger();
    }
    boolean benutezereingabeUeberpruefen(){
        if (spalte > spielfeldgroesse || zeile > spielfeldgroesse){
            System.out.println("Fehler: Zahl muss kleiner als Spielfeldgroesse sein!");
            return ueberpruefen = false;
        }
        else {
            return ueberpruefen = true;
        }
    }
    boolean feldUeberpruefen(){
        for (int[] x:spielfeld){
            for (int[] i:spielfeld){

            }
        }
        return false;
    }
    int getSpielfeldgroesse(){
        System.out.println("spielfeldgroesse: ");
        spielfeldgroesse = ea.leseInteger();
        return spielfeldgroesse;
    }
    boolean leeresFeldUeberpruefen(){
        for (int i = 0; i< spielfeld.length; i++) {
            for (int y = 0; y< spielfeld.length; y++) {
                if(16 == spielfeld[i][y]){
                    if(i+1<=spielfeldgroesse-1){
                        if (spielfeld[zeile][spalte] == spielfeld[i+1][y]) {
                            tausche = "Unten";
                            return true;
                        }
                    }
                    if(i-1>=0){
                        if (spielfeld[zeile][spalte] == spielfeld[i-1][y]) {
                            tausche = "Oben";
                            return true;
                        }
                    }
                    if(y+1<=spielfeldgroesse-1){
                        if (spielfeld[zeile][spalte] == spielfeld[i][y+1]) {
                            tausche = "Rechts";
                            return true;
                        }
                    }
                    if(y-1>=0) {
                        if (spielfeld[zeile][spalte] == spielfeld[i][y - 1]) {
                            tausche = "Links";
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }
    void tausche () {
        int zwischenspeicher;
        switch (tausche) {
            case "Rechts":
                zwischenspeicher = spielfeld[zeile][spalte];
                spielfeld[zeile][spalte] = 16;
                spielfeld[zeile][spalte-1] = zwischenspeicher;
                break;
            case "Links":
                zwischenspeicher = spielfeld[zeile][spalte];
                spielfeld[zeile][spalte] = 16;
                spielfeld[zeile][spalte+1] = zwischenspeicher;
                break;
            case "Oben":
                zwischenspeicher = spielfeld[zeile][spalte];
                spielfeld[zeile][spalte] = 16;
                spielfeld[zeile+1][spalte] = zwischenspeicher;
                break;
            case "Unten":
                zwischenspeicher = spielfeld[zeile][spalte];
                spielfeld[zeile][spalte] = 16;
                spielfeld[zeile-1][spalte] = zwischenspeicher;
                break;
            default:
                System.out.println("something went wrong");
        }
    }


    boolean richtigeReihenfolge() {
        int p = 1;
        int u;
        for (int y = 0; y < spielfeldgroesse; y++) {
            for (int z = 0; z < spielfeldgroesse; z++) {
                u = spielfeld[y][z];

                if (u != p) {
                    if(spielfeld[0][0]==16){
                        return true;
                    }
                    return false;
                } else {
                    p++;
                }

            }
        }
        return true;
    }
}

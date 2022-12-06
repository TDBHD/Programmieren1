import java.util.Random;
//TODO: maximale Punkte, die es zu erspielen gibt, auswerten
//TODO: Punkte auf das Konto des jeweiligen Spielers einbuchen
//TODO: Spieler abwechselnd spielen lassen; Spieler, die ein Schiff getroffen haben, dürfen jedoch noch einen Zug machen

public class SchiffeVersenkenVersionJarik {
    EinUndAusgabe ea = new EinUndAusgabe();
    int spielfeldgroesse = getSpielfeldgroesse();
    int[][] ausgegebenesSpielfeld = new int[spielfeldgroesse][spielfeldgroesse];
    int[][] spielfeldP1 = new int[spielfeldgroesse][spielfeldgroesse];
    int[][] spielfeldP2 = new int[spielfeldgroesse][spielfeldgroesse];
    int zeile;
    int spalte;
    int tempoaerePunkte;
    int maximalErreichbarePunkte = Integer.MAX_VALUE; //siehe Z. 203
    int punktestandP1;
    int punktestandP2;
    int anzahlSchiffe;
    int x;
    int y;
    boolean ueberpruefen = false;
    boolean startpunktzahl = false;
    boolean getroffen = true;
    Random zufaellig = new Random();

    void start() {
        anzahlSchiffe(); //rechnet die Anzahl der Schiffe aus
        platzierungSchiffe(spielfeldP1); //Platziert die Schiffe
        platzierungSchiffe(spielfeldP2);
        ausgegebenesSpielfeld = spielfeldP1;
        maximalpunkte(); //ermittelt die Maximalpunktzahl von SpielfeldP1
        startpunktzahl = false;
        ausgegebenesSpielfeld = spielfeldP2;
        maximalpunkte(); //ermittelt die Maximalpunktzahl von SpielfeldP2 und kontrolliert, ob die eingegebene Punktzahl größer ist als die Maximalpunktzahl von SpielfeldP2
        while (punktestandP1 < maximalErreichbarePunkte && punktestandP2 < maximalErreichbarePunkte) { // Kontrolliert, ob die zu erreichende Punktzahl erreicht wurde. Falls P2 ein Feld getroffen wurde, wird das spiel wiederholt.
            while ((getroffen == true) && (punktestandP1 < maximalErreichbarePunkte && punktestandP2 < maximalErreichbarePunkte)) {
                // Falls der Spieler das Feld getroffen hat und gewinnt, muss die Schleife (Z.35) beenden können.
                System.out.println("P1 ist dran");
                System.out.println("P1 hat " + punktestandP1 + " Punkte");
                ausgegebenesSpielfeld = spielfeldP2;
                spielfeldAusdrucken();
                while (ueberpruefen == false) { //Eingabe und ueberpruefung der Eingabe
                    setBenutzereingabe();
                    benutezereingabeUeberpruefen();
                }
                ueberpruefen = false;
                trefferUeberpruefen(); // falls getroffen: rechnet die Quersumme des Feldes aus und wird auf "temporaerePunkte" zurückgegeben
                punktestandP1 += tempoaerePunkte;
                tempoaerePunkte = 0;
            }
            getroffen = true;
            while (punktestandP1 < maximalErreichbarePunkte && punktestandP2 < maximalErreichbarePunkte) {
                while ((getroffen == true) && (punktestandP1 < maximalErreichbarePunkte && punktestandP2 < maximalErreichbarePunkte)) {
                    System.out.println("P2 ist dran");
                    System.out.println("P2 hat " + punktestandP2 + " Punkte");
                    ausgegebenesSpielfeld = spielfeldP1;
                    spielfeldAusdrucken();
                    while (ueberpruefen == false) { //Eingabe und ueberpruefung der Eingabe
                        setBenutzereingabe();
                        benutezereingabeUeberpruefen();
                    }
                    ueberpruefen = false;
                    trefferUeberpruefen();
                    punktestandP2 += tempoaerePunkte;

                }

            }
            getroffen = true;
        }

        if (punktestandP1 >= maximalErreichbarePunkte) {
            System.out.println("Herzlichen Glückwunsch P1. Sie haben gewonnen!");
        } else {
            System.out.println("Herzlichen Glückwunsch P2. Sie haben gewonnen!");
        }

    }

    int getSpielfeldgroesse() {
        System.out.println("spielfeldgroesse: ");
        this.spielfeldgroesse = ea.leseInteger();
        return spielfeldgroesse;
    }

    int anzahlSchiffe() { // bestimmt die Anzahl der Schiffe auf dem Spielfeld (10% Schiffe 90% wasser)
        anzahlSchiffe = (spielfeldgroesse * spielfeldgroesse) * 10 / 100;
        return anzahlSchiffe;
    }

    void platzierungSchiffe(int spielfeld[][]) { //platziert die Schiffe zufaellig im Array
        for (int platzieren = 0; platzieren < anzahlSchiffe; platzieren++) {
            this.y = zufaellig.nextInt(spielfeldgroesse);
            this.x = zufaellig.nextInt(spielfeldgroesse);
            spielfeld[this.y][this.x] = 1;
        }

    }


    void spielfeldAusdrucken() {
        for (int zaehler = 0; zaehler < ausgegebenesSpielfeld.length; zaehler++) {
            System.out.print("  " + zaehler + " ");
        }
        System.out.println();
        for (int x = 0; x < ausgegebenesSpielfeld.length; x++) {
            for (int ersteZeile = 0; ersteZeile < spielfeldgroesse; ersteZeile++) {
                System.out.print("+---");
            }
            System.out.print("+");
            System.out.println();
            for (int y = 0; y < ausgegebenesSpielfeld.length; y++) {

                switch (ausgegebenesSpielfeld[x][y]) {
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
        for (int letzteZeile = 0; letzteZeile < spielfeldgroesse; letzteZeile++) {
            System.out.print("+---");
        }
        System.out.print("+");
        System.out.println();
    }

    boolean benutezereingabeUeberpruefen() {
        if (spalte > spielfeldgroesse || zeile > spielfeldgroesse) {
            System.out.println("Fehler: Zahl muss kleiner als Spielfeldgroesse sein!");
            return ueberpruefen = false;
        } else {
            return ueberpruefen = true;
        }
    }

    void setBenutzereingabe() {
        System.out.println("zeile: ");
        this.zeile = ea.leseInteger();
        System.out.println("spalte: ");
        this.spalte = ea.leseInteger();
    }

    void trefferUeberpruefen() { //Ueberprueft, ob der Tipp auf einem Schiff liegt oder ob da wasser ist oder bereits dort getippt wurde
        switch (ausgegebenesSpielfeld[this.zeile][this.spalte]) {
            case 0:
                ausgegebenesSpielfeld[this.zeile][this.spalte] = 3;
                getroffen = false;
                break;
            case 1:
                ausgegebenesSpielfeld[this.zeile][this.spalte] = 2;
                this.tempoaerePunkte = quersumme(this.zeile) + quersumme(this.spalte);
                getroffen = true;
                break;
            case 2:
                System.out.println("Dieses Feld wurde bereits getroffen!");
                getroffen = false;
                break;
            case 3:
                System.out.println("Dieses Feld haben sie bereits ausprobiert!");
                getroffen = false;
            default:
                System.out.println("fehler trefferUeberpruefen!");
        }
    }

    int quersumme(int ueberpruefendeZahl) { //Quelle der Formel: https://wiki.freitagsrunde.org/Javakurs/Übungsaufgaben/Quersumme/Musterloesung <-- steht aber so nicht drin
        for (tempoaerePunkte = 0; ueberpruefendeZahl != 0; ueberpruefendeZahl = ueberpruefendeZahl / 10) {
            tempoaerePunkte += ueberpruefendeZahl % 10;
        }
        return tempoaerePunkte;
    }

    void maximalpunkte() {

        for (int y = 0; y < ausgegebenesSpielfeld.length; y++) {
            for (int x = 0; x < ausgegebenesSpielfeld.length; x++) {
                if (ausgegebenesSpielfeld[y][x] == 1) {
                    tempoaerePunkte += quersumme(y) + quersumme(x);
                    System.out.println(y + "; " + x);
                }
            }
        }

        if (maximalErreichbarePunkte > tempoaerePunkte) {
            while (startpunktzahl == false) {

                System.out.println("Sie können maximal " + tempoaerePunkte + " erspielen.");
                System.out.println("Wie viele punkte möchten sie erspielen? ");
                maximalErreichbarePunkte = ea.leseInteger();
                if (maximalErreichbarePunkte < tempoaerePunkte) {
                    startpunktzahl = true;
                    tempoaerePunkte = 0;
                } else {
                    System.out.println("Bitte geben Sie eine kleinere Zahl an");
                    startpunktzahl = false;
                }
            }
        }
        tempoaerePunkte = 0;
    }
}





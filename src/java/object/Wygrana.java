/*
 * Klasa wspomaga rozdzielanie wygranych
 */
package object;

/**
 *
 * @author rekah4
 */
public class Wygrana {
    private int[] win_count;
    private int pula;
    int rekord;
    public Wygrana(int pula){
        this.pula = pula;
        rekord = Conf.OSZUST; //Ile kradniemy z puli :P
        win_count = new int[Conf.LOS_LICZBY];
    }
    
    /**
     * Metoda zlicza dzielnik wygranej dla kazdej osoby
     * @param ile_traf ilosc trafien
     */
    public void add(int ile_traf){
        switch(ile_traf){
            case 6 :
                rekord += Conf.WIN_NUM_6;
                break;
            case 5 :
                rekord += Conf.WIN_NUM_5;
                break;
            case 4 :
                rekord += Conf.WIN_NUM_4;
                break;
            case 3 :
                rekord += Conf.WIN_NUM_3;
                break;
            case 2 :
                rekord += Conf.WIN_NUM_2;
                break;
            case 1 :
                rekord += Conf.WIN_NUM_1;
                break;
        }
        if(rekord == 0) return;
        
        win_count[0] = pula * Conf.WIN_NUM_1 / rekord;
        win_count[1] = pula * Conf.WIN_NUM_2 / rekord;
        win_count[2] = pula * Conf.WIN_NUM_3 / rekord;
        win_count[3] = pula * Conf.WIN_NUM_4 / rekord;
        win_count[4] = pula * Conf.WIN_NUM_5 / rekord;
        win_count[5] = pula * Conf.WIN_NUM_6 / rekord;
    }

    /**
     * Metoda zwraca wysokosc wygranej dla danego przedzialu
     * @param ile_traf iletrafila dana osoba
     * @return tablica wygranych
     */
    public int getOnWin(int ile_traf){
        return win_count[ile_traf];
    }
}

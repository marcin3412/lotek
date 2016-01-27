/*
    Obiekt przechowujący dane losu
*/
package object;

import java.sql.Date;

/**
 *
 * @author rekah4
 */
public class Los {
    private int los_id;
    private int[] nums = new int[Conf.LOS_LICZBY];
    private int klient_id;
    private Date create_at;
    private boolean wygrany;
    private int kumulacja_id;
    private int ile_traf;
    private int wygrana_pula;
    private boolean uzyty;
    
    /**
     * Inicializacja obiektu los
     * @param los_id identyfikator losu
     * @param nums tablica z wybranymi liczbami w losie
     * @param klient_id identyfikator gracza
     * @param create_at data utworzenia losu
     * @param kumulacja_id identyfikator przypisanej kumulacji
     * @throws Exception Błędy związane z obiektem Los
     */
    public Los(int los_id,int[] nums,int klient_id,Date create_at,int kumulacja_id) throws Exception{
        if(los_id < Conf.TABLE_BEGIN)
            throw new Exception("Los posiada zły identyfikator");
        if(nums == null)
            throw new Exception("Los nie posiada liczb");
        if(klient_id < Conf.TABLE_BEGIN)
            throw new Exception("Los klient_id jest mniejszy od zera");
        if(create_at == null)
            throw new Exception("Los nie posiada daty");
        if(kumulacja_id < Conf.TABLE_BEGIN)
            throw new Exception("Los posiada zla kumulacje kumulacja_id < 0");
        
        this.los_id = los_id;
        this.nums = nums;
        this.klient_id = klient_id;
        this.create_at = create_at;
        this.kumulacja_id = kumulacja_id;
        
        this.ile_traf = 0;
        this.wygrana_pula = 0;
        this.wygrany = false;
        this.uzyty = false;
    }
    
    
    /**
     * Metoda sprawdza czy los zawiera podaną liczbe
     * @param liczba szukana liczba
     * @return t/f
     */
    private boolean czyZawieraLiczbe(int liczba){
        for(int num : nums){
            if(num == liczba)
                return true;
        }
        return false;
    }
    
    /**
     * Metoda zwraca ilość zgadzających się liczb z parametrem
     * @param liczby tablica liczb
     * @return 
     */
    public int ileZawieraLiczby(int[] liczby){
        int licznik = 0;
        
        for(int liczba:liczby){
            if(czyZawieraLiczbe(liczba))
                licznik++;
        }
        
        return licznik;
    }
    
    /**
     * Metoda ustawia los na wygrany lub przegrany
     * @param ile_traf ilosc trafien
     * @param wygrana_pula wartosc wygranej
     * @throws java.lang.Exception złe parametry
     */
    public void ustawLos(int ile_traf,int wygrana_pula) throws Exception{
        if(ile_traf < 0)
            throw new Exception("Nie mozna ustawic wartosci ujemnej w ile_traf");
        if(wygrana_pula < 0)
            throw new Exception("Chyba nie chcial bys doplacac za wygrana xD");
        this.uzyty = true;
        if(ile_traf > 0 && wygrana_pula > 0){
            this.wygrany = true;
            this.wygrana_pula = wygrana_pula;
            this.ile_traf = ile_traf;
        }
        else{
            this.wygrany = false;
        }
    }

    /**
     * @return the create_at
     */
    public Date getCreate_at() {
        return create_at;
    }

    /**
     * @return the wygrany
     */
    public boolean isWygrany() {
        return wygrany;
    }
    
    /**
     * @return the ile_traf
     */
    public int getIle_traf() {
        return ile_traf;
    }

    /**
     * @return the kumulacja_id
     */
    public int getKumulacja_id() {
        return kumulacja_id;
    }

    /**
     * @return the uzyty
     */
    public boolean isUzyty() {
        return uzyty;
    }

    /**
     * @return the wygrana_pula
     */
    public int getWygrana_pula() {
        return wygrana_pula;
    }
    
    
    
    
    
}

/*
 * Klasa do obsługi totalizatora
 */
package object;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author rekah4
 */
public class Kumulacja{
    private int pula;
    private int kumulacja_id;
    private Date data_losowania;
    private boolean zakonczone;
    private int[] win_nums = new int[Conf.LOS_LICZBY]; 
    private ArrayList<Los> losy;
    
    /**
     * Konstruktor tworzący podstawę do przeprowadzenia kumulacji
     * @param pula maksymalna wygrana z kumulacji
     * @param kumulacja_id identyfikator kumulacji
     * @param data_losowania data losowania kumulacji
     * @throws Exception błędy w parametrach
     */
    public Kumulacja(int pula,int kumulacja_id,Date data_losowania) throws Exception{
        if(pula <= 0)
            throw new Exception("Wygrana z kumulacji nie moze byc mniejsza od 1");
        if(kumulacja_id < Conf.TABLE_BEGIN)
            throw new Exception("Identyfikator kumulacji jest nie poprawny");
        if(data_losowania == null)
            throw new Exception("Nie ustawiono daty losowania w kumulacji");
        this.pula = pula;
        this.kumulacja_id = kumulacja_id;
        this.zakonczone = false;
        this.data_losowania = data_losowania;
    }
    
    /**
     * Metoda dodaje losy do kumulacji
     * @param losy kolekcja losów
     * @throws Exception błędy w parametrach
     */
    public void wprowadzLosy(ArrayList<Los> losy) throws Exception{
        if(losy.size() <= 0)
            throw new Exception("Bez losów nie da się utworzyć kumulacji");
        this.losy = losy;
    }
    
    /**
     * Metoda sprawdza poprawnosc losow w kumulacji
     * @return true lub program sie sypie
     * @throws Exception czemu program sie posypal
     */
    public boolean sprawdzLosy() throws Exception{
        if(losy == null)
            throw new Exception("Halo! nie ma losow w kumulacji, nie moge ich sprawdzic");
        for(Los los :losy){
            if(los.isUzyty())
                throw new Exception("Kumulacja zawiera nieaktywne losy");
            if(los.isWygrany())
                throw new Exception("Kumulacja zawiera juz wygrany los)");
            if(los.getCreate_at().after(getData_losowania()))
                throw new Exception("Kumulacja zawiera los z data pozniejsza niz kumulacja");
            if(los.getKumulacja_id() != getKumulacja_id())
                throw new Exception("Kumulacja zawiera los z innego losowania Oo");
        }
        return true;
    }
    
    /**
     * Metoda zwraca wylosowane liczby bez powtorzen
     * @param max_num maksymalna liczba do wylosowania
     * @param array_size rozmiar tablicy
     * @return 
     */
    private int[] array_rand(int max_num,int array_size){
       int[] random_array = new int[array_size];
		
		if(array_size > max_num){
			System.out.println("Invalid data");
			return null;
		}
		else{
			Random random_generator = new Random();
			HashSet<Integer> set = new HashSet();
			
			while(set.size() != array_size){
				set.add(random_generator.nextInt(max_num+1));
			}
			int i = 0;
			for(int n: set){
				random_array[i++] = n;
			}
		}
		for(int i=0; i<random_array.length; i++)
			System.out.print(random_array[i]+" ");
                
		return random_array;
    }
    
    public void play() throws Exception{
        this.win_nums = array_rand(Conf.MAX_NUM, Conf.LOS_LICZBY);
        Wygrana wygrana = new Wygrana(pula);
        
        for(Los los :this.losy){
            wygrana.add(los.ileZawieraLiczby(win_nums));
        }
        
        for(Los los :this.losy){
            los.ustawLos(
                    los.ileZawieraLiczby(win_nums),
                    wygrana.getOnWin(los.ileZawieraLiczby(win_nums))
            );
        }
        
    }
    /**
     * @return the data_losowania
     */
    public Date getData_losowania() {
        return data_losowania;
    }

    /**
     * @return the kumulacja_id
     */
    public int getKumulacja_id() {
        return kumulacja_id;
    }
    
}

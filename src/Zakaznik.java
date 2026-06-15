// Trida Zakaznik uklada informace o jednom zakaznikovi autopujcovny, podobne jako trida Auto. Pamatuje si, kolikrat si uz zak. pujcil auto a ktera auta ma aktualne pujcena
public class Zakaznik {
    private int id;
    private String jmeno;
    private String prijmeni;
    private int telCislo;
    private int pujckyCount;
    // Pole pro auta, ktera ma zakaznik prave pujcena
    private Auto[] pujcenaAuta = new Auto[20];

    // Pocet aktualne pujcenych aut urcuje, kolik mist v poli pujcenaAuta je obsazenych.
    private int pocetPujcenychAut = 0;



    // Konstruktor nastavi zakladni udaje zakaznika
    public Zakaznik(int id, String jmeno, String prijmeni, int telCislo) {
        this.id = id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.telCislo = telCislo;
    }



    // Zvysi celkovy pocet pujcek zakaznika, podle tohoto cisla se pozdeji urcuje pripadna vernostni sleva
    public void zvyseniPoctuZakPujcek() {
        pujckyCount++;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public int getTelCislo() {
        return telCislo;
    }

    public void setTelCislo(int telCislo) {
        this.telCislo = telCislo;
    }

    public int getId() {
        return id;
    }

    public int getPujckyCount() {
        return pujckyCount;
    }

    // Pri vraceni auta se snizi pocet aktualne pujcenych aut
    public void snizPocetPujcek(){
        pocetPujcenychAut--;
        System.out.println("Auto bylo vráceno, zbývající půjčená auta: " + pocetPujcenychAut);
    }

    // Prida auto mezi auta, ktera ma zakaznik zrovna pujcena
    public void setPujceneAuto(Auto pujceneAuto) {
        pocetPujcenychAut++;
        for (int i = 0; i < pocetPujcenychAut; i++) {
            this.pujcenaAuta[i] = pujceneAuto;
        }
    }

    // Vypise auta, ktera ma zakaznik aktualne pujcena
    public void vypisPujcenychAut() {
        for (int i = 0; i < pocetPujcenychAut; i++) {
            System.out.println(pujcenaAuta[i]);
        }
    }

    @Override
    public String toString() {
        return "Zákazník: " +
                " Id = " + id +
                ", jméno = " + jmeno +
                ", příjmení = " + prijmeni +
                ", telefonní číslo = " + telCislo;
    }
}

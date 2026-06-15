// Trida Pujcka predstavuje jednu konkretni pujcku auta zakaznikem
// Spojuje dohromady auto, zakaznika, pocet dni a pripadnou pokutu
public class Pujcka {
    private int pocetDni;
    private Auto auto;

    public Zakaznik getZakaznik() {
        return zakaznik;
    }

    private Zakaznik zakaznik;
    private int pokuta = 0;


    // Konstruktor nastavi udaje objektu pujcky
    public Pujcka(int pocetDni, Auto auto, Zakaznik zakaznik) {
        this.pocetDni = pocetDni;
        this.auto = auto;
        this.zakaznik = zakaznik;
    }


    // Vypocita celkovou cenu pujcky - cena x pocet dni
    public double totalCena() {
        double celkCena = auto.getCenaZaDen() * pocetDni;

        // Pri pujceni auta na vice nez 7 dni se pouzije sleva 10 %
        if (pocetDni > 7) {
            celkCena *= 0.9;
        }

        // Se treti a dalsi pujckou dostane zak. vernostni slevu 15 %
        if (zakaznik.getPujckyCount() >= 3) {
            celkCena *= 0.85;
        }

        return celkCena;
    }


    // Pokud zakaznik vrati auto pozdeji, nez puvodne uvedl, aplikuje se pokuta (za kazdy den navic zakaznik plati 500 Kc a samozrejme i denni cenu auta)
    public int pokuta(int faktDniPujcene) {
        if (faktDniPujcene > pocetDni) {
            pokuta = (faktDniPujcene - pocetDni) * 500 + ((faktDniPujcene - pocetDni) * auto.getCenaZaDen());
            return pokuta;
        }
        return 0;
    }


    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }



    @Override
    public String toString() {

        // Zakladni cena pred slevami slouzi hlavne aby zak. vedel, ze dostal slevu
        double zakladCeny = pocetDni * auto.getCenaZaDen();

        // StringBuilder postupne sklada text o tom, jake slevy byly pouzity
        StringBuilder slevaInfo = new StringBuilder();

        if (pocetDni > 7){
            slevaInfo.append(" --> Aplikovaná sleva 10% --> ");
        }

        if (zakaznik.getPujckyCount() >= 3){
            slevaInfo.append(" --> Aplikovaná věrnostní sleva 15% --> ");
        }

        return " Počet dní: " + pocetDni + ", "
                 + auto +
                "\nZákladní cena: " + zakladCeny + " Kč " +
                slevaInfo.toString() +
                " Konečná cena: "  + (totalCena()+pokuta) + " Kc ";
    }
}

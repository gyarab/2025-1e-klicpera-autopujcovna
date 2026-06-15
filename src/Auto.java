// Trida Auto popisuje jedno konkretni auto v autopujcovne
// Uklada jeho zakladni udaje, cenu, stav a jestli je zrovna volne
public class Auto {
    // Atributy auta - tyto hodnoty si objekt pamatuje po celou dobu programu.
    private int id;
    private String znacka;
    private String model;
    private boolean jeManual;
    private int rokVyroby;
    private int cenaZaDen;
    private boolean jeVolne;
    private boolean tedPravePujcene;

    // Konstruktor se zavola pri vytvoreni noveho objektu auta pres "new Auto..."
    // Nastavi vsechny hodnoty a auto se na zacatku oznaci jako volne
    public Auto (int id, String znacka, String model, boolean jeManual, int rokVyroby, int cenaZaDen) {
        this.id = id;
        this.znacka = znacka;
        this.model = model;
        this.jeManual = jeManual;
        this.rokVyroby = rokVyroby;
        setCenaZaDen(cenaZaDen);
        this.jeVolne = true;
        this.tedPravePujcene = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTedPravePujcene(boolean tedPravePujcene){
        this.tedPravePujcene = tedPravePujcene;
    }

    public String getZnacka() {
        return znacka;
    }

    public void setZnacka(String znacka) {
        this.znacka = znacka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isJeManual() {
        return jeManual;
    }

    public void setJeManual(boolean jeManual) {
        this.jeManual = jeManual;
    }

    public int getRokVyroby() {
        return rokVyroby;
    }

    public void setRokVyroby(int rokVyroby) {
        this.rokVyroby = rokVyroby;
    }

    public int getCenaZaDen() {
        return cenaZaDen;
    }

    public void setCenaZaDen(int cenaZaDen) {

        // Kontrola, aby neslo zadat nesmyslnou cenu
        if (cenaZaDen <= 0) {
            throw new IllegalArgumentException("Cena nemůže být nižší nebo rovna 0");
        }

        this.cenaZaDen = cenaZaDen;
    }

    public boolean getJeVolne() {
        return jeVolne;
    }

    public void setJeVolne(boolean jeVolne) {
        this.jeVolne = jeVolne;
    }


    @Override
    public String toString() {

        // variable = (condition) ? expressionTrue :  expressionFalse;
        String typPrevodovky = jeManual ? ", převodovka: manuální" : ", převodovka: automatická";

        // Podle stavu auta se pripravi text, ktery se pak vypise v nabidce
        String isVolne;
        if (jeVolne) {
            isVolne = "auto je volné ";
        } else if (tedPravePujcene) {
            isVolne = " auto jste si právě půjčil";
        } else {
            isVolne = " auto je momentálně půjčené ";
        }


        // Vraceni celeho textoveho popisu auta pri vypsani auta
        return
                "Id: " + id + ", " +
                znacka +
                " " +
                model +
                ", rok: " + rokVyroby +
                typPrevodovky +
                ", cena za den: " + cenaZaDen + " Kč, " +
                isVolne;
    }
}

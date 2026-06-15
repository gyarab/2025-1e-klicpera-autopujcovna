// Trida Pujcovna je hlavni spravce celeho programu - obsahuje pole aut, zakazniku, pujcek a obsahuje metody pro praci s nimi

public class Pujcovna {
    // Vytvoril jsem pole aut, zakazniku a pujcek a to s velikosti na 20 objektu
    private Auto[] auta = new Auto[20];
    private Zakaznik[] zakaznici = new Zakaznik[20];
    private Pujcka[] pujcky = new Pujcka[20];

    // Pocty ukazuji, kolik mist v polich je realne obsazenych
    private int pocetAut = 0;
    private int pocetZakazniku = 0;
    private int pocetPujcek = 0;

    // pridani objektu auta do prvniho volneho mista v poli a zvyseni poctu aut
    public void addAuto(Auto auto) {
        auta[pocetAut++] = auto;
    }

    // Pridani objektu zakaznika do pujcovny, stejne jako auto
    public void addZakaznik(Zakaznik zakaznik) {
        zakaznici[pocetZakazniku++] = zakaznik;
    }

    // Pridani hotove pujcky do seznamu aktivnich pujcek
    public void addPujcka(Pujcka pujcka) {
        pujcky[pocetPujcek++] = pujcka;
    }

    public int getPocetAut(){
        return pocetAut;
    }

    // Vypise objekty vsech aut v poli aut (diky poctu aut bezi for loopa prave tolikrat, kolik je zrovna v poli aut - proto nepouzivam auta.length)
    public void vypisAut() {
        for (int i = 0; i < pocetAut; i++) {
            System.out.println(auta[i]);
        }
    }

    // Vypise vsechny ulozene zakazniky (stejne jako auta)
    public void vypisZakazniku() {
        for (int i = 0; i < pocetZakazniku; i++) {
            System.out.println(zakaznici[i]);
        }
    }

    // Vypise vsechny aktivni pujcky (stejne jako auta a zakaznici)
    public void vypisPujcek() {
        for (int i = 0; i < pocetPujcek; i++) {
            System.out.println(pujcky[i]);
        }
    }

    public Auto[] getAuta() {
        return auta;
    }

    // Seradi auta podle ceny od nejlevnejsiho po nejdrazsi (pouzivam bubble sort - porovnaji se vzdy ceny dvou sousednich auta a kdyz jsou ve spatnem poradi, prohodi je)
    public void serazeniCenaAVypis() {
        for (int i = 0; i < pocetAut - 1; i++) {
            for (int j = 0; j < pocetAut - i - 1; j++) {
                if (auta[j].getCenaZaDen() > auta[j+1].getCenaZaDen()) {
                    Auto temp = auta[j];
                    auta[j] = auta[j+1];
                    auta[j+1] = temp;
                }
            }
        }
        vypisAut();
    }



    // Vyhleda auta podle modelu (contains nam umozni vyhledat auto i kdyz nezadame cely nazev modelu)
    // Prvni for loopa nejdrive spocita shody, aby mohla vytvorit pole presne potrebne velikosti
    public Auto[] findAuto(String hledejModelAuta) {
        int pocetShod = 0;
        for (int i = 0; i < pocetAut; i++) {
            if (auta[i].getModel().toLowerCase().contains(hledejModelAuta.toLowerCase())) {
                pocetShod++;
            }
        }

        // Druha loopa uz nalezena auta opravdu vlozi do vysledneho pole, ktere se nakonec vrati
        Auto[] autaShody = new Auto[pocetShod];
        int index = 0;
        for (int i = 0; i < pocetAut; i++) {
            if (auta[i].getModel().toLowerCase().contains(hledejModelAuta.toLowerCase())) {
                autaShody[index++] = auta[i];
            }
        }

        return autaShody;
    }




    // Stejny princip jako findAuto, ale vyhledava zakazniky podle prijmeni
    public Zakaznik[] findZakaznik(String hledejZakPrijmeni) {

        int pocetShod = 0;
        for (int i = 0; i < pocetZakazniku; i++) {
            if (zakaznici[i].getPrijmeni().toLowerCase().contains(hledejZakPrijmeni.toLowerCase())) {
                pocetShod++;
            }
        }

      Zakaznik[] nalezeneShody = new Zakaznik[pocetShod];
      int index = 0;
        for (int i = 0; i < pocetZakazniku; i++) {
            if (zakaznici[i].getPrijmeni().toLowerCase().contains(hledejZakPrijmeni.toLowerCase())) {
               nalezeneShody[index++] = zakaznici[i];
            }
        }
        return nalezeneShody;
    }





  // Najde zakaznika podle jeho ID, pokud zadne ID nesedi, vrati null
  public Zakaznik findZakId(int hledejZakId) {
        for (int i = 0; i < pocetZakazniku; i++) {
            if (zakaznici[i].getId() == hledejZakId) {
              return zakaznici[i];
          }
        }
        return null;
  }





  // filtrCena vypise pomoci for loopy auta, ktera maji cenu za den mensi nebo rovnou zadane maximalni cene
  public void filtrCena(int maxCena) {
        boolean nalezeno = false;

        for (int i = 0; i < pocetAut; i++) {
            if (auta[i].getCenaZaDen() <= maxCena) {
                nalezeno = true;
                System.out.println(auta[i]);
            }
        }

        if (!nalezeno) {
            System.out.println("Nebyla nalezena žádná auta do požadované ceny za den.");
        }
  }




  // pokud je auto volne tak se vytvori nova pujcka, zmeni se stav auta na pujcene, ulozi se pujcka do pole a prida se zakaznikovi
  public Pujcka pujcitAuto(int pocetDni, Auto auto, Zakaznik zakaznik) {

        try {
            // Pokud auto neni volne, vyhodi se vyjimka a pujcka se nevytvori
            if (!auto.getJeVolne()) {
                throw new IllegalStateException("Auto je momentálně vypůjčené");
            }

            // Auto prestane byt volne a pouzije se specialni stav TedPravePujcene pro okamzity vypis po pujceni
            auto.setJeVolne(false);
            auto.setTedPravePujcene(true);

            Pujcka pujckaX = new Pujcka(pocetDni, auto, zakaznik);

            pujcky[pocetPujcek++] = pujckaX;

            zakaznik.zvyseniPoctuZakPujcek();

            zakaznik.setPujceneAuto(auto);

            return pujckaX;

        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return null;
        }
  }



  // Vrati auto podle ID a podle zakaznika, ktery ho mel pujcene (pokud bylo auto vraceno pozde, vypocita a vypise se pokuta)

  public void vratitAuto(int idAuta, int faktDniPujcene, Zakaznik zakaznik) {
        for (int i = 0; i < pocetPujcek; i++) {

            if (pujcky[i].getAuto().getId() == idAuta && pujcky[i].getZakaznik() == zakaznik) {
                int pokuta = pujcky[i].pokuta(faktDniPujcene);
                if (pokuta > 0) {
                    System.out.println("Bylo Vám naúčtováno " + pokuta + " Kč navíc za pozdní vrácení půjčenného auta.");
                }
                pujcky[i].getAuto().setJeVolne(true);

                // Pujcka se odstrani tak, ze se na jeji misto presune posledni pujcka v poli, diky cemuz v poli nevznikne dira mezi aktivnimi pujckami
                zakaznik.snizPocetPujcek();
                pujcky[i] = pujcky[pocetPujcek - 1];
                pujcky[pocetPujcek - 1] = null;
                pocetPujcek --;
                zakaznik.vypisPujcenychAut();
            }

        }
  }



  // getter pro pocetPujcek
  public int getPocetPujcek() {
        return pocetPujcek;
  }



  // Spocita, kolik aut v pujcovne je volnych
  public int getPocetVolnychAut() {
        int volnaAuta = 0;

        for (int i = 0; i < pocetAut; i++) {
            if (auta[i].getJeVolne()) {
                volnaAuta++;
            }
        }
        return volnaAuta;
  }


  // Secte konecne ceny vsech aktualne aktivnich pujcek (pro statistiky pujcovny)
  public double celkVydelek() {
        double suma = 0;
        for(int i = 0; i < pocetPujcek; i++) {
            suma += pujcky[i].totalCena();
        }
        return suma;
  }
}

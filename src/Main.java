import java.util.Scanner;

public class Main {
    public static void main() {
    // Scanner slouzi ke cteni vstupu od uzivatele z konzole.
    Scanner sc = new Scanner(System.in);

    // Vytvoreni objektu aut

    Auto auto1 = new Auto(1,"Škoda", "Octavia 2 Combi", false, 2012, 550);
    Auto auto2 = new Auto(2,"Škoda", "Octavia 1 vRS", true, 2002, 1200);
    Auto auto3 = new Auto(3,"Škoda", "Octavia 3 Scout", true, 2016, 900);
    Auto auto4 = new Auto(4, "Subaru", "Impreza WRX STI", true, 1998, 1700);
    Auto auto5 = new Auto(5, "Mitsubishi", "Lancer Evo VI", true, 2001, 1700);
    Auto auto6 = new Auto(6, "Mazda", "Miata MX-5 NA", true, 1995, 1000);
    Auto auto7 = new Auto(7,"Renault", "Twingo", true, 1996, 400);
    Auto auto8 = new Auto(8, "Toyota", "GR86", true, 2024, 1100);
    Auto auto9 = new Auto(9, "BMW", "M3 E46 CSL", true, 2005, 2200);
    Auto auto10 = new Auto(10, "BMW", "M4 Competition G82", false, 2025, 2000);
    Auto auto11 = new Auto(11, "Honda", "CRV", false, 2016, 800);
    Auto auto12 = new Auto(12, "Toyota", "Land Cruiser", true, 2010, 800);
    Auto auto13 = new Auto(13, "Nissan", "GT-R R34", true, 2001, 2000);
    Auto auto14 = new Auto(14, "Datsun", "240z", true, 1973, 1500);
    Auto auto15 = new Auto(15, "Honda", "NSX", true, 1992, 3000);
    Auto auto16 = new Auto(16, "Volkswagen", "Golf Mk.IV", false, 2000, 500);

    // Vytvoreni pujcovny - cela pujcovna je taky objekt

    Pujcovna pujcovna = new Pujcovna();

    // Pridani aut do pujcovny pres metody add

    pujcovna.addAuto(auto1);
    pujcovna.addAuto(auto2);
    pujcovna.addAuto(auto3);
    pujcovna.addAuto(auto4);
    pujcovna.addAuto(auto5);
    pujcovna.addAuto(auto6);
    pujcovna.addAuto(auto7);
    pujcovna.addAuto(auto8);
    pujcovna.addAuto(auto9);
    pujcovna.addAuto(auto10);
    pujcovna.addAuto(auto11);
    pujcovna.addAuto(auto12);
    pujcovna.addAuto(auto13);
    pujcovna.addAuto(auto14);
    pujcovna.addAuto(auto15);
    pujcovna.addAuto(auto16);

// vytvoreni a pridani zakazniku do pujcovny

    Zakaznik zakaznik1 = new Zakaznik(1, "Lukáš", "Klicpera", 123456789);
//    Zakaznik zakaznik2 = new Zakaznik(2, "Keichi", "Tsuchiya", 222333444);
//    Zakaznik zakaznik3 = new Zakaznik(3, "Rowan", "Atkinson", 987654321);

    // Zakaznik se musi pridat do pujcovny, aby ho pozdeji slo najit podle ID.
    pujcovna.addZakaznik(zakaznik1);
//    pujcovna.addZakaznik(zakaznik2);
//    pujcovna.addZakaznik(zakaznik3);


// Zacatek menu - nejdrive vytvoreni vstupNum (diky podmince bezi podle hodnoty promenne nasledujici kod)

    int vstupNum;

    System.out.println();
    System.out.println("Vítejte v autopůjčovně!");
    System.out.println();
    System.out.println("Pokud si půjčíte auto na více než 7 dní, tak Vám klesá denní cena o 10%!");
    System.out.println("Při půjčení třetího auta získáváte věrnostní slevu 15%!");
    System.out.println();

    // do loopa - vhodna pro pripady jako tenhle, kdy chceme aby bezel kod nez neco nenastane (v tomto pripade kdyz je vstupNum 0)
    do {
        System.out.println();
        System.out.println("Postupujte podle pokynů:");
        System.out.println();
        System.out.println("Pro výpis nabídky aut stiskněte 1+Enter");
        System.out.println("Pro vyhledávání auta stiskněte 2+Enter");
        System.out.println("Pro půjčení auta stiskněte 3+Enter");
        System.out.println("Pro vráceni auta stiskněte 4+Enter");
        System.out.println("Pro vyhledávání aut do určité ceny stiskněte 5+Enter");
        System.out.println("Pro statistiky půjčovny stiskněte 6+Enter");
        System.out.println("Pro ukončení programu stiskněte 0+Enter");
        System.out.println();
        System.out.println("Vaše volba:");
        System.out.println();
        vstupNum = sc.nextInt();

        // Vypsani aut podle ceny diky metode serazeniCenaAVypis, ve ktere je dalsi metoda vypis aut

        if (vstupNum == 1) {
            pujcovna.serazeniCenaAVypis();
        }

        // Vyhledavani auta podle modelu, pouzije se scanner, nahraje se vstup uzivatele do Stringu model, najdou se shody v Pujcovne a pokud se nic nenajde, program to vypise a skonci

        if (vstupNum == 2) {
            sc.nextLine();
            System.out.println("Zadejte model auta: ");
            String model = sc.nextLine();
            // metoda findAuto vrati pole vsech aut, jejichz model se shoduje se vstupem uzivatele
            Auto[] nalezenaAuta =  pujcovna.findAuto(model);
            if (nalezenaAuta.length == 0) {
                System.out.println("Nebyly nalezené žádné shody.");
                break;
            }

            System.out.println("Nalezené shody: ");
            // Pokud existuji shody, projdou se cyklem a vypisou se uzivateli
            for (int i = 0; i < nalezenaAuta.length; i++) {
                System.out.println(nalezenaAuta[i]);
            }
        }
        // Volba 3 resi pujcovani auta podle ID
        if (vstupNum == 3) {
            System.out.println();
            System.out.println("Zadejte ID auta, které si chcete půjčit:");
            int ID = sc.nextInt();

            // Z pujcovny vezmeme pole aut, abychom v nem mohli najit auto podle zadaneho ID
            Auto[] getAutaMain = pujcovna.getAuta();

            Auto vybraneAuto = null;
            // Loopa hleda auto s ID, ktere uzivatel zadal
            for (int i = 0; i < pujcovna.getPocetAut(); i++) {
                if (getAutaMain[i].getId() == ID) {
                    vybraneAuto = getAutaMain[i];
                    break;
                }
            }

            // Kdyz se zadne auto nenajde, program se vrati na zacatek menu
            if (vybraneAuto == null) {
                System.out.println("ID se neshoduje s žádným nalezeným autem.");
                System.out.println();
                break;
            }

            System.out.println("Zadejte na kolik dní si chcete auto vypůjčit:");
            int dniPujcene = sc.nextInt();
            // Zatim se pouziva zakaznik s ID 1 (neboli uzivatel pocitace, vice zakazniku je protoze jsem chtel aby projekt zpracovaval vice zakazniku, ale nechtel jsem to zbytecne zkomplikovavat pro uzivatele)
            Zakaznik zakaznik = pujcovna.findZakId(1);

            // Samotne pujceni auta probiha v metode pujcitAuto ve tride Pujcovna, tady se jen metoda pouzije
            Pujcka pujcka =  pujcovna.pujcitAuto(dniPujcene, vybraneAuto, zakaznik);


            System.out.println("Pujcka: " + pujcka);
            // Po vypsani aktualni pujcky se "specialni stav" TedPravePujcene vypne
            vybraneAuto.setTedPravePujcene(false);
        }

        // Volba 4 umoznuje vratit auto zpet do pujcovny
        if (vstupNum == 4) {
            System.out.println("Zadejte ID auta, které chcete vrátit:");
            int idAuta = sc.nextInt();

            System.out.println("Zadejte kolik dní jste skutečně auto měl:");
            int faktDniPujcene = sc.nextInt();

            // Podle skutecneho poctu dni muze metoda vratitAuto pripocitat pokutu
            pujcovna.vratitAuto(idAuta, faktDniPujcene, zakaznik1);
        }



        // Volba 5 filtruje auta podle maximalni denni ceny - skvele uplatnitelna schopnost online pujcovny
        if (vstupNum == 5) {
            System.out.println("Zadejte kolik nejvíce byste chtěli denně zaplatit za auto: ");
            int maxCena = sc.nextInt();
            System.out.println("Auta odpovídající Vaší zvolené maximální ceně: ");
            pujcovna.filtrCena(maxCena);

        }




        // Volba 6 vypise jednoduche statistiky aktualniho stavu pujcovny - informace spise pro majitele pujcovny nez uzivatele, ale chtel jsem to sem zahrnout
        if (vstupNum == 6) {
            System.out.println("Momentálně je/jsou aktivní " + pujcovna.getPocetPujcek() + " půjček/ky.");
            System.out.println("Momentálně máme " + pujcovna.getPocetVolnychAut() + " volné/á/ých aut(o/a).");
            System.out.println("Celkový výdělek z momentálně probíhajících půjček bude " + pujcovna.celkVydelek() + " Kč.");
        }


    }
    // Jakmile uzivatel zada 0, nastavi se tak na 0 promenna vstupNum a podminka while loopy prestane platit, cimz program skonci
    while (vstupNum != 0);

}
}

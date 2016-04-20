package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int OLETUSKAPASITEETTI = 5,
            OLETUSKASVATUS = 5;

    private int kasvatuskoko;
    private int[] lukujono;
    private int alkioidenLkm;

    public IntJoukko() {
        this(OLETUSKAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        tarkistaArvot(kapasiteetti, kasvatuskoko);
        alustaLukujono(kapasiteetti);

        this.kasvatuskoko = kasvatuskoko;

    }

    private void tarkistaArvot(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti ei saa olla alle nolla");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetin kasvatuskoko ei saa olla alle nolla");
        }
    }

    private void alustaLukujono(int kapasiteetti) {
        lukujono = new int[kapasiteetti];

        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }

        alkioidenLkm = 0;
    }
    
    public int get(int index) {
        return lukujono[index];
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            kasvataTilaaTarvittaessa();
            return true;
        }
        return false;
    }

    private void kasvataTilaaTarvittaessa() {
        if (alkioidenLkm % lukujono.length == 0) {
            int[] vanhaTaulukko = lukujono;
            lukujono = new int[alkioidenLkm + kasvatuskoko];
            kopioiTaulukko(vanhaTaulukko, lukujono);
        }
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }

        return false;
    }

    public boolean poista(int luku) {
        int kohta = etsiKohta(luku);

        if (kohta != -1) {
            siirraVasemmalle(kohta);
            alkioidenLkm--;
            return true;
        }

        return false;
    }

    private int etsiKohta(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return i;
            }
        }

        return -1;
    }

    private void siirraVasemmalle(int alkuKohta) {
        for (int j = alkuKohta; j < alkioidenLkm - 1; j++) {
            lukujono[j] = lukujono[j + 1];
            lukujono[j + 1] = 0;
        }
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);

    }

    public int alkioita() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else {
            return formatArray();
        }
    }

    private String formatArray() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            sb.append(lukujono[i]).append(", ");
        }
        sb.append(lukujono[alkioidenLkm - 1]).append("}");

        return sb.toString();
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(lukujono, 0, taulu, 0, taulu.length);
        return taulu;
    }
    
    private IntJoukko kopio() {
        IntJoukko kopio = new IntJoukko();
        for (int x : toIntArray()) {
            kopio.lisaa(x);
        }
        
        return kopio;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = a.kopio();
        
        for (int i = 0; i < b.alkioita(); i++) {
            x.lisaa(b.get(i));
        }
        
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        
        for (int i = 0; i < a.alkioita(); i++) {
            for (int j = 0; j < b.alkioita(); j++) {
                if (a.get(i) == b.get(j)) {
                    x.lisaa(b.get(j));
                }
            }
        }
        
        return x;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko x = a.kopio();
        
        for (int i = 0; i < b.alkioita(); i++) {
            x.poista(b.get(i));
        }

        return x;
    }

}

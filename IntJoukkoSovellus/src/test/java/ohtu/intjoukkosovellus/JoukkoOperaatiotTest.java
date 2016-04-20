package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JoukkoOperaatiotTest {

    @Test
    public void testSomething() {
        IntJoukko eka = teeJoukko(1, 2);
        IntJoukko toka = teeJoukko(3, 4);

        IntJoukko tulos = IntJoukko.yhdiste(eka, toka);
        int[] vastauksenLuvut = tulos.toIntArray();
        Arrays.sort(vastauksenLuvut);

        int[] odotettu = {1, 2, 3, 4};

        assertArrayEquals(odotettu, vastauksenLuvut);
    }

    @Test
    public void testYhdiste() {
        IntJoukko a = teeJoukko(1, 2, 3);
        IntJoukko b = teeJoukko(2, 3, 4, 5, 6);

        IntJoukko tulos = IntJoukko.yhdiste(a, b);
        int[] vastauksenLuvut = tulos.toIntArray();
        Arrays.sort(vastauksenLuvut);

        int[] odotettu = {1, 2, 3, 4, 5, 6};

        assertArrayEquals(odotettu, vastauksenLuvut);
    }

    @Test
    public void testLeikkaus() {
        IntJoukko a = teeJoukko(1, 2, 3, 4, 5);
        IntJoukko b = teeJoukko(4, 5, 6, 7);

        IntJoukko tulos = IntJoukko.leikkaus(a, b);
        int[] vastauksenLuvut = tulos.toIntArray();
        Arrays.sort(vastauksenLuvut);

        int[] odotettu = {4, 5};

        assertArrayEquals(odotettu, vastauksenLuvut);
    }

    @Test
    public void testErotus() {
        IntJoukko a = teeJoukko(1, 2, 3, 4, 5, 6);
        IntJoukko b = teeJoukko(3, 4, 7);

        IntJoukko tulos = IntJoukko.erotus(a, b);
        int[] vastauksenLuvut = tulos.toIntArray();
        Arrays.sort(vastauksenLuvut);

        int[] odotettu = {1, 2, 5, 6};

        assertArrayEquals(odotettu, vastauksenLuvut);
    }

    private IntJoukko teeJoukko(int... luvut) {
        IntJoukko joukko = new IntJoukko();

        for (int luku : luvut) {
            joukko.lisaa(luku);
        }

        return joukko;
    }
}

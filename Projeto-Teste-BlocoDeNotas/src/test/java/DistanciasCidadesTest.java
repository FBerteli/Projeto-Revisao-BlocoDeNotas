import Cidade.DistanciasCidades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanciasCidadesTest {
    DistanciasCidades distanciasCidades;

    @BeforeEach
    void setUp() {
        distanciasCidades = new DistanciasCidades("DistanciasCidadesCSV.csv");
    }


    @Test
    public void devePegarADistanciaEntreCidades() {
        double distanciaEsperada = distanciasCidades.pegaDistancia("PORTO ALEGRE", "ARACAJU");
        double distanciaNegativa = distanciasCidades.pegaDistancia("CANOAS", "NATAL");

        assertEquals(3296.0, distanciaEsperada);
        assertEquals(-1.0, distanciaNegativa);
    }

    @Test
    public void deveVerificarCidade() {
        boolean cidadeEsperada = distanciasCidades.verificaCidade("ARACAJU");
        boolean cidadeNaoEsperada = distanciasCidades.verificaCidade("CANOAS");

        assertEquals(true, cidadeEsperada);
        assertEquals(false, cidadeNaoEsperada);
    }
}

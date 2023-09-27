
import java.util.ArrayList;
import java.util.List;

public class Cementerio {
    private List<Ficha> fichasCementerio = new ArrayList<>();

    public void agregarFicha(Ficha ficha) {
        fichasCementerio.add(ficha);
    }

    public boolean contieneMula() {
        for (Ficha ficha : fichasCementerio) {
            if (ficha.getLado1() == 6 || ficha.getLado2() == 6) {
                return true;
            }
        }
        return false;
    }

    public Ficha obtenerFicha() {
        if (!fichasCementerio.isEmpty()) {
            Ficha ficha = fichasCementerio.get(0);
            fichasCementerio.remove(0);
            return ficha;
        }
        return null;
    }
}

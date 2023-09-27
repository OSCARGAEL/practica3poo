import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private List<Ficha> mano = new ArrayList<>();

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public void recibirFicha(Ficha ficha) {
        mano.add(ficha);
    }
    public boolean tieneFichaJugable(int valor) {
        for (Ficha ficha : mano) {
            if (ficha.getLado1() == valor || ficha.getLado2() == valor) {
                return true;
            }
        }
        return false;
    }
    public void jugarFicha(Ficha ficha) {
        mano.remove(ficha);
    }

    public List<Ficha> getMano() {
        return mano;
    }

    public int calcularPuntos() {
        int puntos = 0;
        for (Ficha ficha : mano) {
            puntos += ficha.getLado1() + ficha.getLado2();
        }
        return puntos;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

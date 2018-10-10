package cam.fructuso.matias.cam;

public class Cuestionario {
    private int CIN;
    private String name;
    private String estado;

    public Cuestionario(int CIN, String name, String estado) {
        this.CIN = CIN;
        this.name = name;
        this.estado = estado;
    }

    public int getCIN() {
        return CIN;
    }
    public void setCIN(int CIN) {
        this.CIN = CIN;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}

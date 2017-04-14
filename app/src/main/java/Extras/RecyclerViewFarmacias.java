package Extras;

/**
 * Created by bravo3465 on 31/10/15.
 */
public class RecyclerViewFarmacias  {

    private int iconId;
    private int idFarmacia;
    private String NomeFarmacia;
    private String MediaNota;
    private String MediaTempo;
    private boolean Aberto;

    public int getIdFarmacia() {
        return idFarmacia;
    }

    public void setIdFarmacia(int idFarmacia) {
        this.idFarmacia = idFarmacia;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getNomeFarmacia() {
        return NomeFarmacia;
    }

    public void setNomeFarmacia(String nomeFarmacia) {
        NomeFarmacia = nomeFarmacia;
    }

    public String getMediaNota() {
        return MediaNota;
    }

    public void setMediaNota(String mediaNota) {
        MediaNota = mediaNota;
    }

    public String getMediaTempo() {
        return MediaTempo;
    }

    public void setMediaTempo(String mediaTempo) {
        MediaTempo = mediaTempo;
    }

    public boolean isAberto() {
        return Aberto;
    }

    public void setAberto(boolean aberto) {
        Aberto = aberto;
    }
}

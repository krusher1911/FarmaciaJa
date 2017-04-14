package Entidades;

/**
 * Created by bravo3465 on 11/10/15.
 */
public class Farmacia
{
    private int _id;
    private String descFarmacia;
    private String endereco;
    private float mediaTempoEntrega;
    private float mediaNotaAtendimento;
    private String informacoesFarmacia;


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDescFarmacia() {
        return descFarmacia;
    }

    public void setDescFarmacia(String descFarmacia) {
        this.descFarmacia = descFarmacia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public float getMediaTempoEntrega() {
        return mediaTempoEntrega;
    }

    public void setMediaTempoEntrega(float mediaTempoEntrega) {
        this.mediaTempoEntrega = mediaTempoEntrega;
    }

    public float getMediaNotaAtendimento() {
        return mediaNotaAtendimento;
    }

    public void setMediaNotaAtendimento(float mediaNotaAtendimento) {
        this.mediaNotaAtendimento = mediaNotaAtendimento;
    }

    public String getInformacoesFarmacia() {
        return informacoesFarmacia;
    }

    public void setInformacoesFarmacia(String informacoesFarmacia) {
        this.informacoesFarmacia = informacoesFarmacia;
    }

    @Override
    public String toString() {
        return "Farmacia{" +
                "_id=" + _id +
                ", descFarmacia='" + descFarmacia + '\'' +
                ", endereco='" + endereco + '\'' +
                ", mediaTempoEntrega=" + mediaTempoEntrega +
                ", mediaNotaAtendimento=" + mediaNotaAtendimento +
                ", informacoesFarmacia='" + informacoesFarmacia + '\'' +
                '}';
    }
}

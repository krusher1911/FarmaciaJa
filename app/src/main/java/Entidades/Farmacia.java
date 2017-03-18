package Entidades;

/**
 * Created by bravo3465 on 11/10/15.
 */
public class Farmacia
{
    private int _id;
    private String descFarmacia;
    private Endereco endereco;
    private float mediaTempoEntrega;
    private float mediaNotaAtendimento;

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
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
}

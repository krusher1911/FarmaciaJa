package Entidades;

/**
 * Created by bravo3465 on 11/10/15.
 */
public class Produto {

    private int _id;
    private int farmacia_id;
    private String descProduto;
    private String nomeProduto;
    private Double valorProduto;

    public Produto(int _id, int farmacia_id, String descProduto, String nomeProduto, Double valorProduto) {
        this._id = _id;
        this.farmacia_id = farmacia_id;
        this.descProduto = descProduto;
        this.nomeProduto = nomeProduto;
        this.valorProduto = valorProduto;
    }

    public Produto() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int produto_id) {
        this._id = produto_id;
    }

    public int getFarmacia_id() {
        return farmacia_id;
    }

    public void setFarmacia_id(int farmacia_id) {
        this.farmacia_id = farmacia_id;
    }

    public String getDescProduto() {
        return descProduto;
    }

    public void setDescProduto(String descProduto) {
        this.descProduto = descProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }
}

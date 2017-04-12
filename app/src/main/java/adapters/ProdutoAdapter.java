package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import Entidades.Produto;
import br.com.farmaciaja.una.tidir.farmaciaja.R;

/**
 * Created by Krusher on 11/04/2017.
 */

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {

    private List<Produto> produtoList;

    public ProdutoAdapter(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Produto p = produtoList.get(position);
        holder.nomeProduto.setText(p.getNomeProduto());
        holder.valorProduto.setText(String.valueOf(p.getValorProduto()));
    }

    @Override
    public int getItemCount() {
        return produtoList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_produto, parent, false);
        return new MyViewHolder(v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeProduto;
        public TextView valorProduto;

        public MyViewHolder(View view) {
            super(view);
            nomeProduto = (TextView) view.findViewById(R.id.textView_nome_produto);
            valorProduto = (TextView) view.findViewById(R.id.textView_precoProduto);
        }
    }

}

package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import Entidades.Produto;
import br.com.farmaciaja.una.tidir.farmaciaja.R;
import br.com.farmaciaja.una.tidir.farmaciaja.activities.Act_Produto;

/**
 * Created by Krusher on 11/04/2017.
 */

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {

    Context context;
    private List<Produto> produtoList;

    public ProdutoAdapter(List<Produto> produtoList) {
        this.produtoList = produtoList;
        this.context = context;
    }

    public ProdutoAdapter(List<Produto> produtoList, Context context) {
        this.produtoList = produtoList;
        this.context = context;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Produto p = produtoList.get(position);
        holder.nomeProduto.setText(p.getNomeProduto());
        holder.valorProduto.setText(NumberFormat.getCurrencyInstance().format(p.getValorProduto()));
    }

    @Override
    public int getItemCount() {
        return produtoList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_produto, parent, false);
        return new MyViewHolder(v);


    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nomeProduto;
        public TextView valorProduto;

        public MyViewHolder(View view) {
            super(view);
            nomeProduto = (TextView) view.findViewById(R.id.textView_nome_produto);
            valorProduto = (TextView) view.findViewById(R.id.textView_precoProduto);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, Act_Produto.class);
            intent.putExtra("nomeProduto", nomeProduto.getText());
            context.startActivity(intent);
        }
    }


}

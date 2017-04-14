package adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import Extras.RecyclerViewFarmacias;
import br.com.farmaciaja.una.tidir.farmaciaja.R;
import br.com.farmaciaja.una.tidir.farmaciaja.activities.Act_farmacia;

/**
 * Created by bravo3465 on 31/10/15.
 */
public class FarmaciasAdapter extends RecyclerView.Adapter<FarmaciasAdapter.FarmaciasViewHolder>
{
    List<RecyclerViewFarmacias> farmaciasList = Collections.emptyList();
    Context context;
    private LayoutInflater layoutInflater;

    public FarmaciasAdapter(Context context, List<RecyclerViewFarmacias> data)
    {
        layoutInflater = LayoutInflater.from(context);
        farmaciasList = data;
        this.context = context;
    }

    @Override
    public FarmaciasViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = layoutInflater.inflate(R.layout.row_farmacias, parent, false);

        FarmaciasViewHolder holder = new FarmaciasViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(FarmaciasAdapter.FarmaciasViewHolder holder, int position)
    {
        RecyclerViewFarmacias itens = farmaciasList.get(position);

        holder.icon.setImageResource(R.drawable.account_circle);
        holder.txtNomeFarmacia.setText(itens.getNomeFarmacia());
        holder.txtTempoAtendimento.setText(itens.getMediaTempo());
        holder.txtMediaNota.setText(itens.getMediaNota());
        holder.idFarmacia.setText(Integer.toString(itens.getIdFarmacia()));

        if(itens.isAberto())
        {
            holder.txtAbertoFechado.setText(R.string.txt_aberto);

            holder.txtAbertoFechado.setTextColor(context.getResources().getColor(R.color.verde));
        }
        else
        {
            holder.txtAbertoFechado.setText(R.string.txt_fechado);

            holder.txtAbertoFechado.setTextColor(context.getResources().getColor(R.color.vermelho));
        }
    }

    @Override
    public int getItemCount()
    {
        return farmaciasList.size();
    }

    class FarmaciasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView icon;
        TextView txtNomeFarmacia;
        TextView txtTempoAtendimento;
        TextView txtMediaNota;
        TextView txtAbertoFechado;
        TextView idFarmacia;


        public FarmaciasViewHolder(View itemView) {
            super(itemView);

            idFarmacia = (TextView) itemView.findViewById(R.id.id_farmacia);
            txtNomeFarmacia = (TextView) itemView.findViewById(R.id.txtNomeFarmacia);
            icon = (ImageView) itemView.findViewById(R.id.imgFarmacia);
            txtTempoAtendimento = (TextView) itemView.findViewById(R.id.txtTempoAtendimento);
            txtMediaNota = (TextView) itemView.findViewById(R.id.txtMediaAtendimento);
            txtAbertoFechado = (TextView) itemView.findViewById(R.id.txtAbertoFechado);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, Act_farmacia.class);
            intent.putExtra("idFarmacia", idFarmacia.getText());
            context.startActivity(intent);
        }
    }
}

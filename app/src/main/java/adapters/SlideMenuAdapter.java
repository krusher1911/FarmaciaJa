package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import Extras.RecyclerViewSlideMenuItens;
import br.com.farmaciaja.una.tidir.farmaciaja.R;

/**
 * Created by bravo3465 on 22/10/15.
 */
public class SlideMenuAdapter extends RecyclerView.Adapter<SlideMenuAdapter.MyVieweHolder> {

    private LayoutInflater layoutInflater;
    List<RecyclerViewSlideMenuItens> slideMenuItens = Collections.emptyList();

    public SlideMenuAdapter(Context context, List<RecyclerViewSlideMenuItens> data)
    {
        layoutInflater = LayoutInflater.from(context);
        slideMenuItens = data;
    }

    @Override
    public MyVieweHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.slide_menu_custom_row, parent, false);

        MyVieweHolder holder = new MyVieweHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyVieweHolder holder, int position) {

        RecyclerViewSlideMenuItens itens = slideMenuItens.get(position);

        holder.tittle.setText(itens.getTitulo());
        holder.icon.setImageResource(itens.getIconId());
    }

    @Override
    public int getItemCount() {
        return slideMenuItens.size();
    }

    class MyVieweHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tittle;
        ImageView icon;

        public MyVieweHolder(View itemView) {
            super(itemView);

            tittle = (TextView) itemView.findViewById(R.id.list_text);
            icon = (ImageView) itemView.findViewById(R.id.list_icon);
        }

        @Override
        public void onClick(View v) {


        }
    }
}

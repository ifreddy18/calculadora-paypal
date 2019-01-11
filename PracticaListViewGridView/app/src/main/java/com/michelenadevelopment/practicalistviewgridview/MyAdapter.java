package com.michelenadevelopment.practicalistviewgridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<String> names;

    public MyAdapter(Context context, int layout, List<String> names){
        this.context = context;
        this.layout = layout;
        this.names = names;
    }

    @Override
    public int getCount() {
        return names.size(); // regresa la longitud de la lista
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
    /*
        // Copiamos la vista
        View v = convertView;

        // Inflamos la vista que ha llegado con un layout personalizado
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.listview_layout, null);

        // Nos traemos el valor actual de la posicion
        final String currentName = names.get(position);

        // Referenciamos el elemento a modificar y lo rellenamos
        TextView textView = v.findViewById(R.id.textView);
        textView.setText(currentName);

        return v;

    */

        // ViewHolder Pattern
         ViewHolder holder;

         if (convertView == null){
             // Inflamos la vista que nos ha llegado con nuestro layout personalizado
             LayoutInflater layoutInflater = LayoutInflater.from(this.context);
             convertView = layoutInflater.inflate(layout, null);

             holder = new ViewHolder();

             //Referenciamos el elemento a modificar y lo rellenamos
             holder.nameTextView = convertView.findViewById(R.id.textView);
             convertView.setTag(holder);

         } else {
            holder = (ViewHolder) convertView.getTag();
         }

         // Nos traemos el valor de la posicion actual
         String currentName = names.get(position);

         // Referenciamos el elemento a modificar y lo rellenamos
         holder.nameTextView.setText(currentName);

         // Devolvemos la vista inflada y referenciada con nuestros datos
        return convertView;
    }

    private static class ViewHolder {
        private TextView nameTextView;
        private ImageButton imageButton;
    }
}

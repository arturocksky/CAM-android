package cam.fructuso.matias.cam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Cuestionario> list;
    private int layout;

    public MyAdapter(Context context, List<Cuestionario> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Cuestionario getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder();
            vh.cin = (TextView) convertView.findViewById(R.id.textViewCIN);
            vh.name = (TextView) convertView.findViewById(R.id.textViewName);
            vh.estado = (TextView) convertView.findViewById(R.id.textViewEstado);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Cuestionario currentCues = list.get(position);

        vh.cin.setText(currentCues.getCIN() + "");
        vh.name.setText(currentCues.getName());
        vh.estado.setText(currentCues.getEstado());

        return convertView;
    }

    public class ViewHolder {
        TextView cin;
        TextView name;
        TextView estado;
    }

}

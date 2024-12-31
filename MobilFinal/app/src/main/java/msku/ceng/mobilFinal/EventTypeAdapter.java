package msku.ceng.mobilFinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

public class EventTypeAdapter extends BaseAdapter {
    private Context context;
    private String[] eventTypes;
    private int[] eventIcons;
    private boolean[] checkedEvents; // CheckBox durumlarını izlemek için

    // Constructor
    public EventTypeAdapter(Context context, String[] eventTypes, int[] eventIcons, boolean[] checkedEvents) {
        this.context = context;
        this.eventTypes = eventTypes;
        this.eventIcons = eventIcons;
        this.checkedEvents = checkedEvents;
    }

    @Override
    public int getCount() {
        return eventTypes.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            // View henüz yoksa şişir (inflate et)
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_item_event_type, parent, false);

            // ViewHolder kullanarak performansı artır
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.event_icon);
            holder.checkBox = convertView.findViewById(R.id.event_checkbox);
            convertView.setTag(holder);
        } else {
            // Mevcut ViewHolder'ı al
            holder = (ViewHolder) convertView.getTag();
        }

        // İkon ve metni doldur
        holder.imageView.setImageResource(eventIcons[position]);
        holder.checkBox.setText(eventTypes[position]);

        // CheckBox durumunu güncelle
        holder.checkBox.setChecked(checkedEvents[position]);

        // CheckBox durum değişikliğini dinle
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // checkedEvents dizisini güncelle
            checkedEvents[position] = isChecked;
        });

        return convertView;
    }

    // Performans için ViewHolder pattern
    static class ViewHolder {
        ImageView imageView;
        CheckBox checkBox;
    }
}

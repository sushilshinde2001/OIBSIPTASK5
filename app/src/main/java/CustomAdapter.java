import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {
    private ArrayList<String> lapTimes;

    public CustomAdapter(Context context, int resource, ArrayList<String> lapTimes) {
        super(context, resource, lapTimes);
        this.lapTimes = lapTimes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        String lapTime = lapTimes.get(position);

        // Set the text with the serial number and lap time
        String serialNumber = String.valueOf(position + 1);
        String lapTimeWithSerial = serialNumber + ". " + lapTime;
        textView.setText(lapTimeWithSerial);

        return convertView;
    }
}

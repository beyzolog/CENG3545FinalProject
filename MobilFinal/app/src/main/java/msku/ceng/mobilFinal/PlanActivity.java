package msku.ceng.mobilFinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlanActivity extends AppCompatActivity {

    private Button nextButton;
    private boolean[] checkedEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        // UI elemanlarını bağla
        GridView gridView = findViewById(R.id.event_type_grid);
        nextButton = findViewById(R.id.nextButton);

        // Etkinlik türleri ve ikonları
        String[] eventTypes = {"Birthday", "Wedding", "Celebration", "Dinner"};
        int[] eventIcons = {R.drawable.birthday_icon, R.drawable.wedding_icon, R.drawable.celebration_icon, R.drawable.dinner_icon};

        // checkedEvents dizisini başlat
        checkedEvents = new boolean[eventTypes.length];

        // Adapter'i oluştur ve GridView'e bağla
        EventTypeAdapter adapter = new EventTypeAdapter(this, eventTypes, eventIcons,checkedEvents);
        gridView.setAdapter(adapter);

        // GridView öğelerine tıklamayı yönet
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            // CheckBox'ı bul
            CheckBox checkBox = view.findViewById(R.id.event_checkbox);
            if (checkBox != null) {
                // CheckBox'ın durumunu tersine çevir ve checkedEvents dizisini güncelle
                boolean isChecked = !checkedEvents[position];
                checkedEvents[position] = isChecked;
                checkBox.setChecked(isChecked);
            }
        });

        // "Next" butonu tıklama işlemi
        nextButton.setOnClickListener(v -> {
            if (isAnyEventChecked()) {
                // Seçim yapıldıysa bir sonraki aktiviteye geçiş yap
                Intent intent = new Intent(PlanActivity.this, EventDateActivity.class);
                startActivity(intent);
            } else {
                // Kullanıcıyı uyar
                Toast.makeText(this, "Please select at least one event type.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Seçili etkinlik var mı kontrol eden yardımcı metot
    private boolean isAnyEventChecked() {
        for (boolean checked : checkedEvents) {
            if (checked) return true;
        }
        return false;
    }
}

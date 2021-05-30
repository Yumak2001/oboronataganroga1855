package org.lentils.oboronataganroga1855.adpter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.lentils.oboronataganroga1855.R;

import java.util.List;

public class AdapterCardViewSettings extends RecyclerView.Adapter<AdapterCardViewSettings.SettingsViewHolder>{
    private Context mContext;
    private List<settings_cards> cards;
    Dialog dialog;


   public AdapterCardViewSettings(Context mContext, List<settings_cards> cards){
        this.mContext = mContext;
        this.cards = cards;
    }


    @NonNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_settings_card, parent,false);
        return new SettingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {
        holder.ImgSettings.setImageDrawable(cards.get(position).getImg());
        holder.TextSettings1.setText(cards.get(position).getText1());
        holder.TextSettings2.setText(cards.get(position).getText2());

        dialog = new Dialog(holder.cv.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        holder.itemView.setOnClickListener(view -> {
            switch(cards.get(position).getText1()) {
                case "Променад":
                    dialog.setContentView(R.layout.dialog_reset_profile);
                    dialog.findViewById(R.id.OK_button).setOnClickListener(v -> dialog.dismiss());
                    dialog.findViewById(R.id.Cancle_button).setOnClickListener(v -> dialog.dismiss());
                    dialog.show();
                    break;
                case "Звук":

                    break;
                case "У меня проблема":

                    break;
                case "Сбросить прогресс":

                    break;
                case "Регистрация/Авторизация":

                    break;
                case "О Разработчиках":

                    break;
            }
        });

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class SettingsViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/  {
        CardView cv;
        ImageView ImgSettings;
        TextView TextSettings1;
        TextView TextSettings2;

        SettingsViewHolder(View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.cv);
            ImgSettings = itemView.findViewById(R.id.img_settings);
            TextSettings1 = itemView.findViewById(R.id.text_settings1);
            TextSettings2 = itemView.findViewById(R.id.text_settings2);
        }
    }
}

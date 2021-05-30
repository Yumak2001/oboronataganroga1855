package org.lentils.oboronataganroga1855.ui.account;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lentils.oboronataganroga1855.R;
import org.lentils.oboronataganroga1855.adpter.AdapterCardViewSettings;
import org.lentils.oboronataganroga1855.adpter.settings_cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountFragment extends Fragment{

    private CardView[] cardViewProg = new CardView[4];
    int directionNumber;

    static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        RecyclerView recycleViewSettings = view.findViewById(R.id.recycleViewSettings);
        recycleViewSettings.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recycleViewSettings.setLayoutManager(llm);

        List<settings_cards> cardsSettings = new ArrayList<>();
        cardsSettings.add(new settings_cards("Променад", "Пешая прогулка по набережной", getResources().getDrawable(R.drawable.promenad)));
        cardsSettings.add(new settings_cards ("Бал", "Вальс, Полька, Полонез...", getResources().getDrawable(R.drawable.bal)));
        cardsSettings.add(new settings_cards("Парусная регата", "Ветер в лицо, брызги волн", getResources().getDrawable(R.drawable.parys)));
        cardsSettings.add(new settings_cards("Гонка на ялах", "Ялы...интересное слово...", getResources().getDrawable(R.drawable.ili)));
        cardsSettings.add(new settings_cards("Реконструкция боя", "Учавствуйте в обороне Таганрога", getResources().getDrawable(R.drawable.sabli)));
        cardsSettings.add(new settings_cards("Конкурс костюма", "Сделай свой костюм и получи приз", getResources().getDrawable(R.drawable.kostim)));
        cardsSettings.add(new settings_cards("Конкурс рисунка", "Нарисуй тематический рисунок",getResources().getDrawable(R.drawable.risunok)));

        recycleViewSettings.setAdapter(new AdapterCardViewSettings(getContext(), cardsSettings));

        return view;
    }
}
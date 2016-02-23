package fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rksixers.gpstreker.R;

import java.util.Locale;

public class PlanetFragment extends Fragment {
    public static final String ARG_PLANET_NUMBER = "planer_number";

    public PlanetFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test, container, false);
        int i = getArguments().getInt(ARG_PLANET_NUMBER);
        String planetName = getResources().getStringArray(R.array.planets_array)[i];
        String planetNameTitle = getResources().getStringArray(R.array.planets_array)[i];
        int imageID = getResources().getIdentifier(planetName.toLowerCase(Locale.ROOT), "drawable", getActivity().getPackageName());
        ((ImageView) rootView.findViewById(R.id.image_planet)).setImageResource(imageID);
        getActivity().setTitle(planetNameTitle);

        return rootView;
    }
}

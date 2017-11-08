package fr.megagame.demotheme;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    private Button btnEvent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_blank, container, true);

        btnEvent = (Button) rootView.findViewById(R.id.btnEvent);

        btnEvent.setOnClickListener(event);

        return rootView;
    }

    private View.OnClickListener event = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "hey salut", Toast.LENGTH_LONG).show();
        }
    };
}

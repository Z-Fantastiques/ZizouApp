package co.ogury.ferreolgodebarge.testapk.tabFragment;

import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;

import co.ogury.ferreolgodebarge.testapk.R;
import co.ogury.ferreolgodebarge.testapk.utils.Translations;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OguryTestAds.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OguryTestAds#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OguryTestAds extends Fragment {

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment OguryTestAds.
     */
    // TODO: Rename and change types and number of parameters
    public static OguryTestAds newInstance() {
        OguryTestAds fragment = new OguryTestAds();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public OguryTestAds() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (container == null) {
            return null;
        }

        RelativeLayout ll = (RelativeLayout) inflater.inflate(R.layout.fragment_ogury_test_ads, container, false);
        //Rotate the Zizou
        final ImageView right = (ImageView) ll.findViewById(R.id.RightZidane);
        final ImageView left = (ImageView) ll.findViewById(R.id.leftZidane);
        final ImageView view = (ImageView) ll.findViewById(R.id.imageView);

        //Calculating metrics
        DisplayMetrics dm = new DisplayMetrics();
        this.getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );
        int xDest = dm.widthPixels/2;
        xDest -= (view.getMeasuredWidth()/2);

        //Zizou to the right
        TranslateAnimation rightAnimation = Translations.toTheSides(xDest, 1);
        //Zizou to the left
        TranslateAnimation leftAnimation = Translations.toTheSides(xDest, -1);

        // Start animating the image
        left.startAnimation(leftAnimation);
        right.startAnimation(rightAnimation);

        return ll;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}

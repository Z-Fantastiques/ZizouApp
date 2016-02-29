package co.ogury.ferreolgodebarge.testapk.tabFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;




import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import co.ogury.ferreolgodebarge.testapk.R;
import co.ogury.ferreolgodebarge.testapk.utils.ISoundPoolLoaded;
import co.ogury.ferreolgodebarge.testapk.utils.SoundPoolManager;


public class Soundbox extends Fragment {

    //Sound list
    static List<Integer> SOUNDS = new ArrayList<Integer>();

    //Random sound picker
    private static Random randomGenerator;

    public static Soundbox newInstance() {
        Soundbox fragment = new Soundbox();
        Bundle args = new Bundle();
        fragment.setArguments(args);


        //Initialize pool tools
        createPool();
        randomGenerator = new Random();

        return fragment;
    }


    public Soundbox() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            SoundPoolManager.getInstance().InitializeSoundPool(getActivity(), new ISoundPoolLoaded() {
                @Override
                public void onSuccess() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        // Inflate the layout for this fragment
        RelativeLayout ll =  (RelativeLayout)
                inflater.inflate(R.layout.fragment_soundbox, container, false);

        // Images on click
        final ImageView speakingZidane = (ImageView) ll.findViewById(R.id.speakingZidane);
        speakingZidane.setScaleType(ImageView.ScaleType.FIT_XY);

        speakingZidane.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playSound();
                    }
                }
        );

        return ll;
    }

    public static void createPool() {
        //Create
        SoundPoolManager.CreateInstance();
        SOUNDS = new ArrayList<Integer>();

        //Sounds
        SOUNDS.add(R.raw.ballon_en_bois);
        SOUNDS.add(R.raw.fait_une_main);
        SOUNDS.add(R.raw.glissant_crampon);
        SOUNDS.add(R.raw.le_temps);
        SOUNDS.add(R.raw.prend_rentre);
        SOUNDS.add(R.raw.ramener_semelle);
        SOUNDS.add(R.raw.semelle_chambrer);
        SOUNDS.add(R.raw.souleve);

        //Set OK
        SoundPoolManager.getInstance().setSounds(SOUNDS);
        SoundPoolManager.getInstance().setPlaySound(true);
    }

    public void playSound() {
        //Create random and avoid same one as 5 before
        int index = randomGenerator.nextInt(SOUNDS.size());

        //Find it
        final Integer item = SOUNDS.get(index);

        //Play it
            SoundPoolManager.getInstance().playSound(item);
    }
}

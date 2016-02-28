package co.ogury.ferreolgodebarge.testapk.tabFragment;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.ogury.ferreolgodebarge.testapk.R;
import co.ogury.ferreolgodebarge.testapk.utils.ISoundPoolLoaded;
import co.ogury.ferreolgodebarge.testapk.utils.SoundPoolManager;

public class Soundbox extends Fragment {


    public static Soundbox newInstance() {
        Soundbox fragment = new Soundbox();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    public Soundbox() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


        final ImageView speakingZidane = (ImageView) ll.findViewById(R.id.speakingZidane);
        speakingZidane.setOnClickListener(
                new View.OnClickListener(){
                    @Override public void onClick(View v) {
                        SoundPoolManager.CreateInstance();
                        final List<Integer> sounds = new ArrayList<Integer>();
                        sounds.add(R.raw.ballon_en_bois);
                        SoundPoolManager.getInstance().setSounds(sounds);
                        try {
                            SoundPoolManager.getInstance().InitializeSoundPool(getActivity(), new ISoundPoolLoaded() {
                                @Override
                                public void onSuccess() {
                                    SoundPoolManager.getInstance().setPlaySound(true);
                                    SoundPoolManager.getInstance().playSound(R.raw.ballon_en_bois);
                                    //TODO Rate of sounds
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        return ll;
    }

    public void playSound() {
        //Playable sound
        final MediaPlayer player = new MediaPlayer();
        final Resources res = getResources();


        AssetFileDescriptor afd = res.openRawResourceFd(R.raw.ballon_en_bois);
        try {
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer player) {
                player.start();
            }
        });

    }
}

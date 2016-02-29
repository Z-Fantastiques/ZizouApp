package co.ogury.ferreolgodebarge.testapk.old;



import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;

import co.ogury.ferreolgodebarge.testapk.R;
import co.ogury.ferreolgodebarge.testapk.utils.Translations;
import io.presage.Presage;
import io.presage.utils.IADHandler;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.Button;
import android.view.* ;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;


public class OldMainActivity extends AppCompatActivity {


    private String gaid=null;
    public int CPT = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        //Setting display
        setContentView(R.layout.activity_main);

        //Setting up Presage
        Presage.getInstance().setContext(this.getBaseContext());
        Presage.getInstance().start();

        //Calling adtoserve on button click
        Button ad = (Button) findViewById(R.id.ad);
        ad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Presage.getInstance().adToServe("interstitial", new IADHandler() {

                    @Override
                    public void onAdNotFound() {
                        Log.i("PRESAGE", "ad not found");
                    }

                    @Override
                    public void onAdFound() {
                       Log.i("PRESAGE", "ad found");
                    }

                    @Override
                    public void onAdClosed() {
                        Log.i("PRESAGE", "ad closed");
                    }
                });
            }
        });

        //Get AAID
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    String Tmpgaid = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext()).getId();
                    if (Tmpgaid != null) {
                        gaid = Tmpgaid;
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    gaid = "IllegalStateException";
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                    gaid = "GooglePlayServicesRepairableException";
                } catch (IOException e) {
                    e.printStackTrace();
                    gaid = "IOException";
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                    gaid = "GooglePlayServicesNotAvailableException";
                }
            }
        });
        t.start();

        //Setting aaid to screen
        Button ado = (Button) findViewById(R.id.aaidButton);
        ado.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView aaid = (TextView) findViewById(R.id.aaid);
                aaid.setText("My AAID = " + gaid);

            }
        });


        //Rotate the Zizou
        final ImageView right = (ImageView) findViewById(R.id.RightZidane);
        final ImageView left = (ImageView) findViewById(R.id.leftZidane);
        final ImageView view = (ImageView) findViewById(R.id.imageView);

        //Calculating metrics
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics( dm );
        int xDest = dm.widthPixels/2;
        xDest -= (view.getMeasuredWidth()/2);

        //Zizou to the right
        TranslateAnimation rightAnimation = Translations.toTheSides(xDest, 1);
        //Zizou to the left
        TranslateAnimation leftAnimation = Translations.toTheSides(xDest, -1);

        // Start animating the image
        left.startAnimation(leftAnimation);
        right.startAnimation(rightAnimation);


    }

}

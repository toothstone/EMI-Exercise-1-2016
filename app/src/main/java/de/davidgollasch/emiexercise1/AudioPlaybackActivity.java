package de.davidgollasch.emiexercise1;

import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Virtualizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import java.io.IOException;

public class AudioPlaybackActivity extends AppCompatActivity {

    /**
     * Switches (play/pause music)
     */
    private Switch sEasternEmotion, sReggaeFeeling;

    /**
     * Media Players (components to control MP3 playback)
     */
    private MediaPlayer mpEasternEmotion, mpReggaeFeeling;

    /**
     * Toggle Buttons to enable or disable Bass Boost and Virtualiser
     */
    private ToggleButton tbtnBassBoost, tbtnVirtualizer;

    /**
     * BassBoost and Virtualiser components
     */
    private BassBoost bassBoost;
    private Virtualizer virtualizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_playback);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.tuAkzentfarbe1BlauHell)));

        InitializeActivity();
    }

    /**
     * Initialises widgets and event handlers
     */
    private void InitializeActivity() {
        sEasternEmotion = (Switch) findViewById(R.id.switchEasternEmotion);
        sReggaeFeeling = (Switch) findViewById(R.id.switchReggaeFeeling);

        sEasternEmotion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EasternEmotionToggled();
            }
        });

        sReggaeFeeling.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ReggaeFeelingToggled();
            }
        });

        mpEasternEmotion = MediaPlayer.create(this, R.raw.eastern_emotion_terrasound_de);
        mpReggaeFeeling = MediaPlayer.create(this, R.raw.reggae_feeling_terrasound_de);

        mpEasternEmotion.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                sEasternEmotion.setChecked(false);
            }
        });

        mpReggaeFeeling.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                sReggaeFeeling.setChecked(false);
            }
        });

        tbtnBassBoost = (ToggleButton) findViewById(R.id.toggleButtonBassBoost);
        tbtnVirtualizer = (ToggleButton) findViewById(R.id.toggleButtonVirtualizer);

        tbtnBassBoost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BassBoostClicked();
            }
        });

        tbtnVirtualizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VirtualizerClicked();
            }
        });

    }

    /**
     * Configures the sound FX setup based on a given session
     * @param sessionID The session to apply FX on
     */
    private void ConfigureSoundEffects(int sessionID) {
        // Disable all prior FX
        if(bassBoost != null) {
            bassBoost.setEnabled(false);
        }
        if(virtualizer != null) {
            virtualizer.setEnabled(false);
        }

        // BASS BOOST
        bassBoost = new BassBoost(0, sessionID);
        if(bassBoost.getStrengthSupported()) {
            bassBoost.setStrength((short) 1000);
        }

        if(tbtnBassBoost.isChecked()) {
            bassBoost.setEnabled(true);
        }

        // VIRTUALIZER
        virtualizer = new Virtualizer(0, sessionID);
        virtualizer.setStrength((short) 1000);

        if(tbtnVirtualizer.isChecked()) {
            virtualizer.setEnabled(true);
        }
    }

    /**
     * Handle toggling of Eastern Emotion switch
     */
    private void EasternEmotionToggled() {
        if (sEasternEmotion.isChecked()){
            if(sReggaeFeeling.isChecked()){
                sReggaeFeeling.toggle();
            }
            PlaybackEasternEmotion();
        } else {
            PauseEasternEmotion();
        }


    }

    /**
     * Starts playback of first audio
     */
    private void PlaybackEasternEmotion() {

        ConfigureSoundEffects(mpEasternEmotion.getAudioSessionId());
        mpEasternEmotion.start();

    }

    /**
     * Halts playback of first audio
     */
    private void PauseEasternEmotion() {mpEasternEmotion.pause();}

    /**
     * Handle toggling of Reggae Feeling switch
     */
    private void ReggaeFeelingToggled() {

        if (sReggaeFeeling.isChecked()){
            if (sEasternEmotion.isChecked()){
                sEasternEmotion.toggle();}
            PlaybackReggaeFeeling();
        } else {
            PauseReggaeFeeling();
        }
    }

    /**
     * Starts playback of second audio
     */
    private void PlaybackReggaeFeeling() {
        ConfigureSoundEffects(mpReggaeFeeling.getAudioSessionId());
        mpReggaeFeeling.start();
    }

    /**
     * Halts playback of second audio
     */
    private void PauseReggaeFeeling() {mpReggaeFeeling.pause();}

    /**
     * Handle Bass Boost Switch
     */
    private void BassBoostClicked() {
        ConfigureSoundEffects(mpEasternEmotion.getAudioSessionId());
        ConfigureSoundEffects(mpReggaeFeeling.getAudioSessionId());
    }

    /**
     * Handle Virtualizer Switch
     */
    private void VirtualizerClicked() {
        ConfigureSoundEffects(mpEasternEmotion.getAudioSessionId());
        ConfigureSoundEffects(mpReggaeFeeling.getAudioSessionId());
    }

}

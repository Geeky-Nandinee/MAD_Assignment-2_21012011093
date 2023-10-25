package com.example.mad_assignment_2_21012011093

// ConfirmationSoundService.kt

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class ConfirmationSoundService : Service() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Initialize and play the confirmation sound
        mediaPlayer = MediaPlayer.create(this, R.raw.confirmation_sound) // Replace 'your_confirmation_sound' with your sound resource
        mediaPlayer?.start()

        // Stop the service after the sound finishes playing
        mediaPlayer?.setOnCompletionListener {
            stopSelf()
        }

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}

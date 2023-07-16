package com.example.implicitintents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import com.example.implicitintents.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.openWebsiteButton.setOnClickListener{
            openWebsite(binding.openWebsiteButton)
        }

        binding.openLocationButton.setOnClickListener {
            openLocation(binding.openLocationButton)
        }

        binding.shareTextButton.setOnClickListener {
            shareText(binding.shareTextButton)
        }
    }

    fun openWebsite(view: View) {
        val url: String = binding.websiteEdittext.text.toString()
        val webSite = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webSite)
        if (intent.resolveActivity(packageManager) !=null){
            startActivity(intent)
        }
        else{
            Log.d("ImplicitIntents","Can't handle this!")
        }
    }

    fun openLocation(view: View){
        val loc: String = binding.locationEdittext.text.toString()
        val addressUri = Uri.parse("geo:0,0?q=$loc")
        val intent = Intent(Intent.ACTION_VIEW,addressUri)
        if (intent.resolveActivity(packageManager) !=null){
            startActivity(intent)
        }
        else{
            Log.d("ImplicitIntents", "Can't handle this intent!")
        }

    }

    fun shareText(view: View){
        val txt: String = binding.shareEdittext.text.toString()
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Share this text with: ")
            .setText(txt)
            .startChooser();

    }
}
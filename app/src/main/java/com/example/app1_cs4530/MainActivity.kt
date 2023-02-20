package com.example.app1_cs4530

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private val REQUEST_IMAGE_CAPTURE = 1
    lateinit var clickImageId: ImageView
    var photo : Bitmap? = null
    lateinit var First : EditText
    lateinit var Middle : EditText
    lateinit var Last : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val b = findViewById<Button>(R.id.button_second);
        clickImageId = findViewById(R.id.imageView);
        First = findViewById(R.id.et_First)
        Middle = findViewById(R.id.et_Middle)
        Last = findViewById(R.id.et_Last)

        b.setOnClickListener(View.OnClickListener { v: View? ->
            // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // Start the activity with camera_intent, and request pic id
            startActivityForResult(cameraIntent, 123)
        })

        val submit = findViewById<Button>(R.id.button_first)
        submit.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            intent.putExtra("First", First.text.toString())
            intent.putExtra("Middle", Middle.text.toString())
            intent.putExtra("Last", Last.text.toString())
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Match the request 'pic id with requestCode
        if (requestCode == 123) {
            // BitMap is data structure of image file which store the image in memory
            photo = data!!.extras!!["data"] as Bitmap?
            // Set the image in imageview for display
            clickImageId.setImageBitmap(photo)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("bitmap", photo);
        outState.putString("First", First.text.toString())
        outState.putString("Middle", Middle.text.toString())
        outState.putString("Last", Last.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.let {
            val temp = it.get("bitmap") as Bitmap?
            clickImageId.setImageBitmap(temp)
            photo = temp
            val firstName = it.get("First") as String
            val lastName = it.get("Last") as String
            val middleName = it.get("Middle") as String
            First.setText(firstName)
            Middle.setText(middleName)
            Last.setText(lastName)
        }
    }
}
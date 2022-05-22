package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import java.io.File

private const val TAG = "MainActivity"
private const val REQUEST_IMAGE_CAPTURE = 100
private const val REQUEST_READ_STORAGE = 500


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer
    private var untilFinished = 10000L

    private val viewModel  by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.open_camera).setOnClickListener {
            openNativeCamera()
        }

        findViewById<Button>(R.id.open_details).setOnClickListener {
            openDetailsActivity()
        }

        findViewById<Button>(R.id.show_dialog).setOnClickListener{
            showAppDialog()
        }

        findViewById<Button>(R.id.show_snackbar).setOnClickListener(){
            showAppSnackbar()
        }

        findViewById<Button>(R.id.startTimer).setOnClickListener(){
            startTimer()
        }

        val tvStartTimer = findViewById<TextView>(R.id.tv_counter)

        viewModel.timerLiveData.observe(this) { count ->
            tvStartTimer.text = count.toString()

            if(count == 0L)
                loadImage()
        }
    }

    override fun onResume() {
        super.onResume()

        startCountDownTimer(untilFinished)
    }

    override fun onPause() {
        super.onPause()

        timer.cancel()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode== REQUEST_IMAGE_CAPTURE && requestCode== RESULT_OK){
            val imageBitMap = data?.extras?.get("data") as Bitmap
            findViewById<ImageView>(R.id.imageView).setImageBitmap(imageBitMap)
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

    private fun openNativeCamera(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    private fun openDetailsActivity() {
        val intent = Intent(this, DetailsActivity::class.java)
        startActivity(intent)
    }

    private fun showAppDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.dialog_title)
        builder.setMessage(R.string.dialog_message)
        builder.apply {
            setPositiveButton(R.string.dialog_action_ok) { _, _ ->
                Toast.makeText(this@MainActivity, R.string.dialog_action_ok_selected, Toast.LENGTH_SHORT).show()
            }
            setNegativeButton(R.string.dialog_action_cancel) { _, _ ->
                Log.d(TAG, "Dialog cancelled")
            }
        }
        builder.create().show()
    }

    private fun showAppSnackbar() {
        Snackbar.make(
            findViewById<ConstraintLayout>(R.id.container),
            R.string.snackbar_message,
            Snackbar.LENGTH_LONG)
            .setAction(R.string.snackbar_action_thanks) {
                Toast.makeText(this@MainActivity, R.string.snackbar_action_thanks_selected, Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    private fun startTimer() {

        if(!checkPermissionAndRequest()){
            return
        }

        viewModel.startTimer(5000)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if(requestCode == REQUEST_READ_STORAGE){

            if(permissions[0] == Manifest.permission.READ_EXTERNAL_STORAGE &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                startTimer()


            }
        } else {

            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun checkPermissionAndRequest(): Boolean{

        if(ContextCompat
                .checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){

            ActivityCompat
                .requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_READ_STORAGE)


            return false
        }

        return true
    }

    private fun startCountDownTimer(time : Long){
        timer = object: CountDownTimer(time, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                untilFinished = millisUntilFinished
                findViewById<TextView>(R.id.countdown).text = getString(R.string.time_remaining, (untilFinished / 1000))
            }

            override fun onFinish() {
                findViewById<TextView>(R.id.countdown).text = getString(R.string.time_done)
            }

        }

        timer.start()
    }

    private fun loadImage(){

        val file = File("file:///C:/Users/ASUS/Pictures/students_creed.webp")

        val uri = Uri.fromFile(file)

        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.setImageURI(uri)
    }
}

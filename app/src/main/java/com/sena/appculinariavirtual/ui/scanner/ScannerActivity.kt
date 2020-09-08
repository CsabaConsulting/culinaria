package com.sena.appculinariavirtual.ui.scanner

import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.ar.core.*
import com.google.ar.core.exceptions.CameraNotAvailableException
import com.google.ar.sceneform.ArSceneView
import com.google.ar.sceneform.ux.ArFragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.sena.appculinariavirtual.R
import kotlinx.android.synthetic.main.activity_scanner.*
import java.io.IOException

class ScannerActivity : AppCompatActivity(), PermissionListener {

    private lateinit var arView: ArSceneView
    private lateinit var session: Session
    private var shouldConfigureSession: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        arView = sceneView
        setupSession()
        initSceneView()
    }

    private fun initSceneView() {

        arView.scene.addOnUpdateListener {
            val frame = arView.arFrame
            val updateAugmentedImage: Collection<AugmentedImage> = frame!!.getUpdatedTrackables(AugmentedImage::class.java)

            for (image: AugmentedImage in updateAugmentedImage){
                if (image.trackingState == TrackingState.TRACKING){

                    if (image.name == "lampara"){
                        val node = MyARNode(this, R.raw.model)
                        node.setImage(image)
                        arView.scene.addChild(node)
                    }
                }
            }
        }
    }

    private fun solicitPermissionCamera(){
        Dexter.withContext(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(this)
            .check()
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        setupSession()
    }

    override fun onPermissionRationaleShouldBeShown(request: PermissionRequest?, token: PermissionToken?) {
        TODO("Not yet implemented")
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(this, "Los permisos de la camara son necesarios", Toast.LENGTH_LONG).show()
    }

    private fun setupSession(){
        session = Session(this)
        shouldConfigureSession = true

        if (shouldConfigureSession){
            configSession()
            shouldConfigureSession = false
            arView.setupSession(session)
        }

        try {
            session.resume()
            arView.resume()
        } catch (e: CameraNotAvailableException){
            e.printStackTrace()
            return
        }
    }

    private fun configSession() {
        val config = Config(session)

        if (!buildDatabase(config)){
            Toast.makeText(this, "Error en Database", Toast.LENGTH_LONG).show()
        }

        config.updateMode = Config.UpdateMode.LATEST_CAMERA_IMAGE
        session.configure(config)
    }

    private fun buildDatabase(config: Config): Boolean {
        val bitmap: Bitmap = loadImage() ?: return false

        val augmentedImageDatabase = AugmentedImageDatabase(session)
        augmentedImageDatabase.addImage("lampara", bitmap)
        config.augmentedImageDatabase = augmentedImageDatabase

        return true
    }

    private fun loadImage(): Bitmap? {
        try {
            val inputStream = assets.open("lamparaqr.png")
            return BitmapFactory.decodeStream(inputStream)

        } catch (e: IOException){
            e.printStackTrace()
        }
        return null;
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}
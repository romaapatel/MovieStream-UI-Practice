package com.example.moviestream_ui_practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.moviestream_ui_practice.utils.Constant
import kotlinx.android.synthetic.main.activity_upload_image.btnSelect
import kotlinx.android.synthetic.main.activity_upload_image.ivUpload

class UploadImageActivity : AppCompatActivity() {

    lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_image)

        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { response ->
                if (response.resultCode == RESULT_OK) {
                    ivUpload.setImageBitmap(response.data?.getParcelableExtra(Constant.DATA))
                    ivUpload.setImageURI(response.data?.data)
                } else if (response.resultCode == RESULT_CANCELED) {
                    Toast.makeText(this@UploadImageActivity, R.string.no_image, Toast.LENGTH_SHORT).show()
                }
            }
        )
        btnSelect.setOnClickListener {
            getImageFromGallery()
        }
    }
    private fun getImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.putExtra(Constant.CAMERA, false)
        launcher.launch(intent)
    }
}
package com.pci.mykotlintest

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toFile
import androidx.fragment.app.Fragment
import com.pci.mylibrary.utils.UriUtils
import java.io.File

/**
 * Created by h on 2022/6/6.
 * 作用：
 */
class GetPhotoFragment:Fragment() {


    private lateinit var takePicture :ActivityResultLauncher<Uri>
    private lateinit var pickPicture :ActivityResultLauncher<String>
    private var uri:Uri? = null

    private var takePhotoListener:GetPhotoUtils.TakePhotoListener? = null
    private var pickPhotoListener:GetPhotoUtils.PickPhotoListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()){
            takePhotoListener?.callBack(it,uri)
        }

        pickPicture = registerForActivityResult(ActivityResultContracts.GetContent()){
            it?.let {
                pickPhotoListener?.callBack(File(UriUtils.getImagePath(requireContext(),it)?:""),it)
            }
        }
    }

    fun takePhoto(takePhotoListener: GetPhotoUtils.TakePhotoListener,uri: Uri){
        this.takePhotoListener = takePhotoListener
        this.uri = uri
        takePicture.launch(uri)
    }
    fun pickPhoto(pickPhotoListener: GetPhotoUtils.PickPhotoListener){
        this.pickPhotoListener = pickPhotoListener
        pickPicture.launch("image/*")
    }
}
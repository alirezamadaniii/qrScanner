package com.example.qrscanner.view

import android.app.Dialog
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.budiyev.android.codescanner.*
import com.example.qrscanner.ApiClient
import com.example.qrscanner.ApiService
import retrofit2.create



private const val CAMERA_CODE=101

class MainActivity : AppCompatActivity() {
//    private lateinit var codeScanner:CodeScanner
//    private lateinit var nationalCode:String
//    lateinit var  scannerView:CodeScannerView
//

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        scannerView =findViewById<CodeScannerView>(R.id.scanner_view)
//        setUpPermission()
//        codeScanner()
//
//    }
//    fun getDataUser():String{
//        val bundle=intent.extras
//        val id=bundle?.get("id")
//        return id.toString()
//    }
//
//
//    private fun codeScanner(){
//        codeScanner= CodeScanner(this,scannerView)
//        codeScanner.apply {
//            camera=CodeScanner.CAMERA_BACK
//            formats=CodeScanner.ALL_FORMATS
//
//            autoFocusMode=AutoFocusMode.SAFE
//            scanMode=ScanMode.CONTINUOUS
//            isAutoFocusEnabled=true
//            isFlashEnabled=false
//
//            decodeCallback = DecodeCallback {
//                runOnUiThread{
//                    Log.i("TAG", "codeScanner: ${it.text}")
//                    if (it.text.isNotEmpty()){
//                        Log.i("TAG", "codeScanner: ${it.text}")
//                        val dialog=Dialog(this@MainActivity)
//                        dialog.setContentView(R.layout.dialog_status)
//                        dialog.show()
//                        codeScanner.stopPreview()
//                        val textResultDialog=dialog.findViewById<TextView>(R.id.textViewDialog)
//                        dialog.findViewById<Button>(R.id.buttonBacktoScanner)
//                            .setOnClickListener(){
//
//                                dialog.cancel()
//                                dialog.dismiss()
//                                codeScanner.startPreview()
//                        }
//
//                        nationalCode=it.text.toString().substring(48,it.text.lastIndexOf("'"))
//                        Log.i("TAG", "codeScanner: $nationalCode")
//
//
//                        val apiService: ApiService by lazy {
//                            ApiClient.getRetrofit("https://hozor.nasraa.ir/api/").create()
//                        }
//
//                        lifecycleScope.launchWhenCreated {
//                            textResultDialog.text=apiService.responseUser(nationalCode,getDataUser())
//                                .body()?.result
//                        }
//                    }
//                }
//            }
//
//            errorCallback= ErrorCallback {
//                runOnUiThread{
//                    Log.e("TAG", "codeScanner: ")
//                }
//            }
//        }
//
//        scannerView.setOnClickListener{
//            codeScanner.startPreview()
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        codeScanner.startPreview()
//    }
//
//    override fun onPause() {
//        codeScanner.releaseResources()
//        super.onPause()
//    }
//
//    private fun setUpPermission(){
//        val permission=ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)
//        if (permission!=PackageManager.PERMISSION_GRANTED){
//            makeRequest()
//        }
//    }
//
//    private fun makeRequest(){
//        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),
//            CAMERA_CODE)
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when(requestCode){
//           CAMERA_CODE->{
//               if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
//                   Toast.makeText(this,"permission",Toast.LENGTH_LONG).show()
//               }
//           }
//       }
//    }

}
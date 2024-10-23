package com.example.qrscanner.view

import android.app.Dialog
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.qrscanner.ApiClient
import com.example.qrscanner.ApiService
import com.example.qrscanner.R
import com.example.qrscanner.databinding.FragmentQrBinding
import retrofit2.create

private const val CAMERA_CODE=101

class QrFragment : Fragment() {

    private lateinit var _binding : FragmentQrBinding
    private lateinit var codeScanner: CodeScanner
    private lateinit var nationalCode: String
    private lateinit var dialog: Dialog
    private lateinit var textResultDialog: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQrBinding.inflate(inflater,container,false)

        setUpPermission()
        codeScanner()

        return _binding.root
    }
        private fun getDataUser():String = arguments?.getString("code_class").toString()



    private fun codeScanner(){
        codeScanner= CodeScanner(requireContext(),_binding.scannerView)
        codeScanner.apply {
            camera=CodeScanner.CAMERA_BACK
            formats=CodeScanner.ALL_FORMATS

            autoFocusMode= AutoFocusMode.SAFE
            scanMode= ScanMode.CONTINUOUS
            isAutoFocusEnabled=true
            isFlashEnabled=false

            dialog= Dialog(requireContext())


            decodeCallback = DecodeCallback {
                requireActivity().runOnUiThread{
                    Log.i("TAG", "codeScanner: $it")
                    if (it.text.isNotEmpty()){


                        try {
                            nationalCode=it.text.toString().substring(47,it.text.lastIndexOf("'"))
                            Log.i("TAG", "codeScanner: $nationalCode")
                            val apiService: ApiService by lazy {
                                ApiClient.getRetrofit().create()
                            }

                            lifecycleScope.launchWhenCreated {
                                _binding.progressBar.visibility = View.VISIBLE

                                dialog.setContentView(R.layout.dialog_status)
                                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                                dialog.window?.setLayout(_binding.root.width,_binding.root.height)
                                codeScanner.stopPreview()

                                textResultDialog=dialog.findViewById(R.id.textViewDialog)
                                dialog.findViewById<Button>(R.id.buttonBacktoScanner)
                                    .setOnClickListener{
                                        dialog.cancel()
                                        dialog.dismiss()
                                        codeScanner.startPreview()
                                    }
                                textResultDialog.text=apiService.responseUser(nationalCode,getDataUser())
                                    .body()?.result
                                _binding.progressBar.visibility = View.GONE

                                dialog.show()
                            }
                        }catch (e:StringIndexOutOfBoundsException){
                            textResultDialog.text="کارت نامعتبر است!"
                        }

                    }
                }
            }

            errorCallback= ErrorCallback {
                requireActivity().runOnUiThread{
                    Log.e("TAG", "codeScanner: ")
                }
            }
        }

        _binding.scannerView.setOnClickListener{
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setUpPermission(){
        val permission= ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.CAMERA)
        if (permission!=PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    private fun makeRequest(){
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
           CAMERA_CODE->{
               if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                   Toast.makeText(requireContext(),"permission",Toast.LENGTH_LONG)
               }
           }
       }
    }


}
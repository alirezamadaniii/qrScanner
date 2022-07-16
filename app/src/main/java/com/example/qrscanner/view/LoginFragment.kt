package com.example.qrscanner.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.qrscanner.*
import com.example.qrscanner.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import retrofit2.create


class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container,false)

        binding.materialButtonLogin.setOnClickListener{
            getDataFromUser()

            //show dialog from class dialogs
            binding.progressBarLoginPage.visibility = View.VISIBLE
            hideKeyboard(requireActivity())


        }


        return binding.root
    }

    private fun getDataFromUser() {
        val username = binding.textInputUsername.text.toString()
        val password = binding.textInputPassword.text.toString()

        lifecycleScope.launchWhenCreated {
            val apiService: ApiService = ApiClient.getRetrofit().create()
            val status = apiService.loginAdmin(username , password)
            if (status.body()?.result.equals("fail")){
                binding.progressBarLoginPage.visibility= View.GONE
                Snackbar.make(binding.root,"نام کاربری یا رمز عبور اشتباه است",Snackbar.LENGTH_LONG).show()
            }
            else{
                binding.progressBarLoginPage.visibility = View.GONE
                val bundle=Bundle()
                bundle.putString("result",status.body()?.result)
                Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_courseFragment,bundle)

                lifecycleScope.launch {
                    DataStore.data(requireContext(),"username", binding.textInputUsername.text.toString())
                    DataStore.data(requireContext(),"password", binding.textInputPassword.text.toString())
                    DataStore.data(requireContext(),"status", status.body()?.result.toString())
                }
            }


        }
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }





}
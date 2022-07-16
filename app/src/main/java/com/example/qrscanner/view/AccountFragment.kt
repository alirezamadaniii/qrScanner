package com.example.qrscanner.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.qrscanner.DataStore
import com.example.qrscanner.R
import com.example.qrscanner.databinding.FragmentAccountBinding
import com.google.android.material.snackbar.Snackbar


class AccountFragment : Fragment() {

    private lateinit var _binding :FragmentAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAccountBinding.inflate(inflater)
        _binding.textViewNameUser.text = DataStore.fetch(requireContext(),"username").toString()
        _binding.constraintLayoutContactUs.setOnClickListener{
            Snackbar.make(_binding.root,"02188928214",Snackbar.LENGTH_LONG).show()
        }
        _binding.constraintExitApp.setOnClickListener{
            DataStore.editor?.clear()?.apply()
            Navigation.findNavController(_binding.root).navigate(R.id.action_accountFragment_to_loginFragment)

        }

        return _binding.root
    }

    override fun onResume() {
        super.onResume()

        if (view == null) {
            return
        }

        view!!.isFocusableInTouchMode = true
        view!!.requestFocus()
        view!!.setOnKeyListener { v: View?, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
//                    Toast.makeText(getContext(), "back pressed", Toast.LENGTH_SHORT).show();
                return@setOnKeyListener true
            }
            false
        }
    }
}
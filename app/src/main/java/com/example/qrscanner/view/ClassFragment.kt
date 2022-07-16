package com.example.qrscanner.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.qrscanner.AdapterClassList
import com.example.qrscanner.ApiClient
import com.example.qrscanner.ApiService
import com.example.qrscanner.R
import com.example.qrscanner.databinding.FragmentClassBinding
import kotlinx.coroutines.coroutineScope
import retrofit2.create

class ClassFragment : Fragment() {

    private lateinit var binding:FragmentClassBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentClassBinding.inflate(inflater)

        binding.collapsingClass.apply {
            setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
            setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
            setCollapsedTitleTextColor(Color.BLACK)
        }

        getData()
        binding.swipeRefreshClass.setOnRefreshListener {
            getData()
            binding.swipeRefreshClass.isRefreshing = false
        }

        return binding.root
    }


    private fun getData(){
        lifecycleScope.launchWhenCreated {
            val apiService: ApiService = ApiClient.getRetrofit().create()
            val  listClass=apiService.classList("class",
                arguments?.getString("course_id").toString()).body()?.list
            val adapter= AdapterClassList(listClass,requireContext())
            binding.recyclerViewClass.adapter=adapter
            if (listClass?.size==0)
                binding.collapsingClass.title= "درسی موجود نیست"
            else
                binding.collapsingClass.title= arguments?.getString("course_name")
            Log.i("TAG", "getData: ${adapter.context}")
            Log.i("TAG", "${listClass?.size} : ")
            viewModelFactory {
                coroutineScope {
                    kotlinx.coroutines.DisposableHandle {
                        getData().run {

                        }
                    }
                }
            }
        }
    }
}
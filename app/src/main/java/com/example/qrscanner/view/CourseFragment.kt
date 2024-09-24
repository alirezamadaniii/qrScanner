package com.example.qrscanner.view


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.qrscanner.AdapterCourseList
import com.example.qrscanner.ApiClient
import com.example.qrscanner.ApiService
import com.example.qrscanner.R
import com.example.qrscanner.databinding.FragmentCourseBinding
import retrofit2.create


class CourseFragment : Fragment() {

    private lateinit var binding: FragmentCourseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCourseBinding.inflate(inflater)


        val userId : String? = arguments?.getString("result")


        binding.collapsingCourse.apply {
            setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
            setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
            setCollapsedTitleTextColor(Color.BLACK)
            title= "دوره ها"
        }
        getData(userId.toString())

        binding.swipeRefresh.setOnRefreshListener {
            Log.i("TAG", "onCreateView: ")
            getData(userId.toString())
            binding.swipeRefresh.isRefreshing = false
        }


        return binding.root
    }

    private fun getData(userId : String){

        lifecycleScope.launchWhenCreated {
            val apiService:ApiService=ApiClient.getRetrofit().create()
            val adapter=AdapterCourseList(apiService.courseList("course",
                userId).body()?.list,requireContext())
            binding.recyclerView.adapter=adapter
            binding.courseProgress.visibility =View.GONE
        }
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
                return@setOnKeyListener true
            }
            false
        }
    }
}
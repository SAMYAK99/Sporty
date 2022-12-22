package com.projects.trending.sporty.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.projects.trending.sporty.MainActivity
import com.projects.trending.sporty.R
import com.projects.trending.sporty.Repository
import com.projects.trending.sporty.adapters.newsAdapter
import com.projects.trending.sporty.databinding.FragmentHomeBinding
import com.projects.trending.sporty.models.Article
import com.projects.trending.sporty.remote.ApiUtilites
import com.projects.trending.sporty.utils.Constants
import com.projects.trending.sporty.utils.Resource
import com.projects.trending.sporty.viewmodel.MainViewModel
import com.projects.trending.sporty.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MainViewModel
    lateinit var adapter: newsAdapter
    private lateinit var list : ArrayList<Article>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        list = ArrayList()

        // TODO : USE DAGGER FOR INJECTION
        val newsRepository = Repository()
        val viewModelProviderFactory = MainViewModelFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[MainViewModel::class.java]

//        setupRecyclerView()

        viewModel.breakingNews.observe(viewLifecycleOwner , Observer {response ->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    // If response data is not null
                    response.data?.let {
                      list.addAll(response.data.articles)
                        rv_news.adapter = newsAdapter(requireContext(),list)
                        rv_news.layoutManager = LinearLayoutManager(requireContext())
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Log.d("Error Message" , response.message)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })


        return binding.root
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

//    private fun setupRecyclerView() {
//        adapter = newsAdapter(requireContext(),list)
//        rv_news.apply {
//            this.adapter = adapter
////            this.layoutManager = LinearLayoutManager(requireContext())
//        }
//    }
}
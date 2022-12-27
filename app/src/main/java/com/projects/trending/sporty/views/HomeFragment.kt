package com.projects.trending.sporty.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.projects.trending.sporty.data.Repository
import com.projects.trending.sporty.adapters.newsAdapter
import com.projects.trending.sporty.databinding.FragmentHomeBinding
import com.projects.trending.sporty.data.local.NewsDatabase
import com.projects.trending.sporty.models.Article
import com.projects.trending.sporty.utils.Resource
import com.projects.trending.sporty.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*



@AndroidEntryPoint
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
        adapter = newsAdapter(requireContext())

        // TODO : USE DAGGER FOR INJECTION
//        val newsRepository = Repository(NewsDatabase(requireContext()))
//        val viewModelProviderFactory = MainViewModelFactory(newsRepository)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

//        setupRecyclerView()

        binding.rvNews.adapter = adapter
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())

        viewModel.breakingNews.observe(viewLifecycleOwner , Observer {response ->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    // If response data is not null
                    response.data?.let {
                        adapter.setData(response.data.articles)
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
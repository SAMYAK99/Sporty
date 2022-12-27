package com.projects.trending.sporty.views


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.projects.trending.sporty.data.Repository
import com.projects.trending.sporty.adapters.bookmarkAdapter
import com.projects.trending.sporty.databinding.FragmentBookMarkBinding
import com.projects.trending.sporty.data.local.NewsDatabase
import com.projects.trending.sporty.models.Article
import com.projects.trending.sporty.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.paginationProgressBar


@AndroidEntryPoint
class BookMarkFragment : Fragment() {

    private lateinit var binding: FragmentBookMarkBinding
    private lateinit var viewModel: MainViewModel
    lateinit var  bookmarkAdapter: bookmarkAdapter

     lateinit var list: ArrayList<Article>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBookMarkBinding.inflate(layoutInflater)


        setupRecyclerView()

        // TODO : USE DAGGER FOR INJECTION
//        val newsRepository = Repository(NewsDatabase(requireContext()))
//        val viewModelProviderFactory = MainViewModelFactory(newsRepository)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer { articles ->
            Log.d("XAM", articles.size.toString())
            try {
                hideProgressBar()
               bookmarkAdapter.setData(articles)
            } catch (e : Exception) {
                showProgressBar()
            }

        })

//        swipeToDelete(binding.rvBookmarkNews)

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = bookmarkAdapter.list[position]
                viewModel.deleteArticle(article)
                Snackbar.make(binding.root, "Successfully deleted article", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveArticle(article)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvBookmarkNews)
        }

          bookmarkAdapter = bookmarkAdapter( context = requireContext())
        binding.rvBookmarkNews.apply {
            adapter = bookmarkAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        return binding.root
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {

    }




}
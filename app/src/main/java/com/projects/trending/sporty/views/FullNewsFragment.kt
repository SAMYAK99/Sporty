package com.projects.trending.sporty.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.projects.trending.sporty.databinding.FragmentFullNewsBinding


class FullNewsFragment : Fragment() {

    private lateinit var binding: FragmentFullNewsBinding
    private val item: FullNewsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFullNewsBinding.inflate(layoutInflater)

        val url : String = item.url!!
        loadFullNews(url)


        return binding.root
    }

    private fun loadFullNews(url: String) {
       binding.fullNewsWebView.apply {
           settings.javaScriptEnabled = true
           setLayerType(View.LAYER_TYPE_SOFTWARE, null)
           loadUrl(url)
       }
    }

}
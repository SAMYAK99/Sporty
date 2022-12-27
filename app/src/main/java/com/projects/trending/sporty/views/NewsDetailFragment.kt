package com.projects.trending.sporty.views

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.projects.trending.sporty.data.Repository
import com.projects.trending.sporty.databinding.FragmentNewsDetailBinding
import com.projects.trending.sporty.data.local.NewsDatabase
import com.projects.trending.sporty.models.Article
import com.projects.trending.sporty.viewmodel.MainViewModel



class NewsDetailFragment : Fragment() {

    private lateinit var binding: FragmentNewsDetailBinding
    private val item: NewsDetailFragmentArgs by navArgs()
    lateinit var viewModel: MainViewModel

    lateinit var exoPlayerView: SimpleExoPlayerView

    // on below line we are creating a
    // variable for exo player
    lateinit var exoPlayer: SimpleExoPlayer

    // on below line we are creating a string variable for our video url
    var videoURL =
        "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNewsDetailBinding.inflate(layoutInflater)


        // TODO : USE DAGGER FOR INJECTION
//        val newsRepository = Repository(NewsDatabase(requireContext()))
//        val viewModelProviderFactory = MainViewModelFactory(newsRepository)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]


        val data: Article = item.data!!

        setUpDetails(data)

        binding.btnSave.setOnClickListener {

//            viewModel.isDataExist(data.url).observe(viewLifecycleOwner){ flag ->
//                // data does not exist in DB
//
//            }

//            // DATA DOES NOT EXIST
//            if(viewModel.isDataExist(data.url) == 0){
//
//            }
//            else{
//                Toast.makeText(requireContext(),"Data already exist in DB !!!" , Toast.LENGTH_SHORT).show()
//            }
            viewModel.saveArticle(data)
            Toast.makeText(requireContext(),"Article Saved !!!" , Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun setUpDetails(data: Article) {
        binding.apply {
            exoPlayerView = this.idExoPlayerVIew
            setUpExo()

            this.newsItemHeadingTextView.text = data.title
            this.newsItemSubheadinTextView.text = data.description

            this.readFullNewsBtn.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    NewsDetailFragmentDirections.actionNewsDetailFragmentToFullNewsFragment(url = data.url)
                )
            }
        }
    }

    private fun setUpExo() {
        try {
            // bandwidthmeter is used for
            // getting default bandwidth
            val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()

            // track selector is used to navigate between
            // video using a default seekbar.
            val trackSelector: TrackSelector =
                DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))

            // we are adding our track selector to exoplayer.
            exoPlayer = ExoPlayerFactory.newSimpleInstance(requireContext(), trackSelector)

            // we are parsing a video url
            // and parsing its video uri.
            val videoURI: Uri = Uri.parse(videoURL)

            // we are creating a variable for datasource factory
            // and setting its user agent as 'exoplayer_view'
            val dataSourceFactory: DefaultHttpDataSourceFactory =
                DefaultHttpDataSourceFactory("Exoplayer_video")

            // we are creating a variable for extractor factory
            // and setting it to default extractor factory.
            val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory();

            // we are creating a media source with above variables
            // and passing our event handler as null,
            val mediaSourse: MediaSource =
                ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null)

            // inside our exoplayer view
            // we are setting our player
            exoPlayerView.player = exoPlayer

            // we are preparing our exoplayer
            // with media source.
            exoPlayer.prepare(mediaSourse)

            // we are setting our exoplayer
            // when it is ready.
            exoPlayer.playWhenReady = true


        } catch (e: Exception) {
            // on below line we
            // are handling exception
            e.printStackTrace()
        }
    }


}
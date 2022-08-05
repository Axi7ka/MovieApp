package com.axichise.movieapp.ui.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.axichise.movieapp.databinding.FragmentMovieDetailsBinding
import com.axichise.movieapp.ui.utils.Constants
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentMovieDetails : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(requireActivity()).get(MovieDetailViewModel::class.java)

        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.IO) {
            viewModel.movie = viewModel.getMoviesDetails()
            withContext(Dispatchers.Main) {
                binding.tvTitle.text = viewModel.movie?.title
                binding.tvYear.text = viewModel.movie?.releaseDate
                binding.tvDescription.text = viewModel.movie?.overview
                Glide.with(binding.ivPoster.context)
                    .load(Constants.IMAGE_URL + viewModel.movie?.posterPath)
                    .into(binding.ivPoster)

                loadYtbVideos()
            }
        }

    }

    private fun loadYtbVideos() {
        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                viewModel.movie?.videos?.results?.get(0)
                    ?.let { youTubePlayer.loadVideo(findYoutubeTrailer(), 0f) }
            }
        })
    }


    private fun findYoutubeTrailer(): String {
        viewModel.movie?.videos?.results?.let { videoList ->
            for (video in videoList) {
                if (video.type == "Trailer")
                    return video.key
            }
        }
        return ""
    }
}
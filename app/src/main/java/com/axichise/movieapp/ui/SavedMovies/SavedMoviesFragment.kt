package com.axichise.movieapp.ui.SavedMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.axichise.movieapp.databinding.FragmentSavedMoviesBinding
import com.axichise.movieapp.ui.movies.Movies
import com.axichise.movieapp.ui.tab_activity.ui.main.PlaceholderFragment
import com.axichise.movieapp.ui.tab_activity.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher

class SavedMoviesFragment : Fragment() {

    private var _binding: FragmentSavedMoviesBinding? = null
    private val tabTitle = arrayOf(
        "Favorite",
        "Watched"
    )
    private val movies: ArrayList<Movies> = ArrayList()
    private var adapter:AdapterTabPager? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val savedMoviesViewModel =
            ViewModelProvider(this).get(SavedMoviesViewModel::class.java)

        _binding = FragmentSavedMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager2 = binding.viewPager
        val tabs: TabLayout = binding.tabs
        adapter = activity?.let { AdapterTabPager(it) }
        adapter?.addFragment(FavoriteListFragment(), tabTitle[0])
        adapter?.addFragment(WatchedListFragment(), tabTitle[1])

        viewPager.adapter = adapter

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = adapter?.getTitle(position)
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
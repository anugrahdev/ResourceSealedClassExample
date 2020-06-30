package com.anugrahdev.resourcesealedclassexample.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anugrahdev.resourcesealedclassexample.R
import com.anugrahdev.resourcesealedclassexample.databinding.ItemNewsBinding
import com.anugrahdev.resourcesealedclassexample.network.ApiService
import com.anugrahdev.resourcesealedclassexample.network.NewsRepository
import com.anugrahdev.resourcesealedclassexample.vo.Resource
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var mAdapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val api = ApiService()
        val repo = NewsRepository(api)
        val factory = NewsViewModelFactory(repo)
        viewModel = ViewModelProvider(this,factory).get(NewsViewModel::class.java)
        viewModel.headlineNews.observe(viewLifecycleOwner, Observer {response->
            when(response){
                is Resource.Success->{
                    response.data?.let {
                        mAdapter= NewsAdapter()
                        recycler_view.apply {
                            adapter = mAdapter
                        }
                        mAdapter.differ.submitList(it)
                        loading.visibility = View.GONE
                    }
                }
                is Resource.Error->{
                    response.message?.let {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading->{
                    loading.visibility = View.VISIBLE
                }
            }
        })
    }


}
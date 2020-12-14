package com.saulwiggin.gan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saulwiggin.gan.viewadapters.CharactersAdapter
import com.saulwiggin.gan.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_first.*
import org.koin.android.viewmodel.ext.android.viewModel
import com.saulwiggin.gan.databinding.FragmentFirstBinding
import com.saulwiggin.gan.viewadapters.CharacterClick

class ListFragment: Fragment(R.layout.fragment_first) {

    private var viewModelAdapter: CharactersAdapter? = null
    private lateinit var v: View

    val mainViewModel:ListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container:ViewGroup?, savedInstance: Bundle?): View? {
        val binding: FragmentFirstBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)
        v = binding.root.findViewById(R.id.CharacterList_Fragment_Layout)
        binding.setLifecycleOwner ( viewLifecycleOwner )
        binding.viewmodel = mainViewModel

        viewModelAdapter = CharactersAdapter(CharacterClick { char ->
            mainViewModel.displayPropertyDetails(char)
        })

        binding.root.findViewById<RecyclerView>(R.id.rvList).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        mainViewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController()
                    .navigate(CharacterDetails_FragmentDirections.actionFirstFragmentToSecondFragment())
                mainViewModel.displayPropertyDetailsComplete()
            }
        })
        binding.root.findViewById<SearchView>(R.id.etSearch)
            .setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(text: String?): Boolean {
                    if (text != null) mainViewModel.setSearch(text.toString())
                    return false
                }
            })

        setHasOptionsMenu(true);

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearch()
    }

    private fun setupRecyclerView(){
        viewModelAdapter = CharactersAdapter(CharacterClick { char ->
            mainViewModel.displayPropertyDetails(char)
        })
        rvList.apply {
            adapter = viewModelAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun setupSearch(){

    }


}
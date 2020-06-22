package com.swenson.ui.currenciesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.swenson.R
import com.swenson.data.entities.CurrencyRate
import com.swenson.ui.currenciesList.adapter.CurrencyListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.currency_list_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class CurrencyListFragment : Fragment() {

    @Inject
    lateinit var viewModel: CurrencyListViewModel

    private lateinit var mAdapter: CurrencyListAdapter
    private var currencyRateList = mutableListOf<CurrencyRate>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.currency_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeCurrencyList()
        observeErrors()
        observeProgress()
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                DividerItemDecoration.VERTICAL
            )
        )
        mAdapter = CurrencyListAdapter(currencyRateList,
            object : CurrencyListAdapter.OnItemClickListener {
                override fun onItemClicked(item: CurrencyRate) {
                    viewModel.setItemSymbol(item.symbolText)
                    findNavController().navigate(R.id.action_CurrencyListFragment_to_CurrencyConverterFragment)
                }
            })
        recyclerView.adapter = mAdapter
    }

    private fun observeProgress() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.GONE

        })
    }

    private fun observeCurrencyList() {
        viewModel.currencyResponse.observe(viewLifecycleOwner, Observer {
            if (currencyRateList.isEmpty()) {
                currencyRateList.addAll(it)
                mAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun observeErrors() {
        viewModel.error.observe(viewLifecycleOwner, Observer { errorMesage ->
            Toast.makeText(requireActivity(), errorMesage, Toast.LENGTH_LONG).show()
        })
    }
}
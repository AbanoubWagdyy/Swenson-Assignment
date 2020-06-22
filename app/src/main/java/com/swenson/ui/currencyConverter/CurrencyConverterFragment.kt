package com.swenson.ui.currencyConverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.swenson.R
import com.swenson.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.currency_converter_fragment.*
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class CurrencyConverterFragment : Fragment() {

    @Inject
    lateinit var viewModel: CurrencyConverterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.currency_converter_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeConvertedRate()
    }

    private fun initViews() {
        symbol.text = viewModel.getSymbol()
        etBaseCurrencyValue.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                if (editable.toString().isNotEmpty()) {
                    try {
                        val amount = editable.toString().toDouble()
                        viewModel.convert(amount)
                    } catch (e: Exception) {
                        showToast("Incorrect Value ,please choose valid one")
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        etBaseCurrencyValue.setText("1.0")
    }

    private fun observeConvertedRate() {
        viewModel.convertedRate.observe(viewLifecycleOwner, Observer {
            convertedValue.text = it.toString()
        })
    }
}
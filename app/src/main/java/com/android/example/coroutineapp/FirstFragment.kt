package com.android.example.coroutineapp

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.android.example.coroutineapp.databinding.FragmentFirstBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch{
            val value1 : Int? = provideValue1()
            val value2 : Int = provideValue2()
            printValue(value1, value2)
        }
    }

    suspend fun provideValue1(): Int? {
        var tempValue1 = binding.editText.text.toString().toIntOrNull()
        return tempValue1
    }

    suspend fun provideValue2(): Int {
        var tempValue2 = 0
        binding.buttonFirst.setOnClickListener{
            tempValue2++
        }
        return tempValue2
    }

    suspend fun printValue(value1: Int?, value2: Int) {
        delay(2000)
        if (value1 != null) {
            binding.textview.text = "value is: ${value1 + value2}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
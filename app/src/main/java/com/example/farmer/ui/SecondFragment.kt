package com.example.farmer.ui

import android.app.Application
import android.content.Context
import com.example.farmer.databinding.FragmentSecondBinding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.farmer.Model.Farmer
import com.example.farmer.R
import com.example.farmer.application.FarmerApp

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val farmerViewModel: FarmerViewModel by viewModels {
        FarmerViewModelFactory((applicationContext as FarmerApp).repository)
    }

    private val args: SecondFragmentArgs by navArgs()
    private var farmer:Farmer? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        farmer = args.farmer
        if (farmer != null){
            binding.deleteButton.visibility = View.VISIBLE
            binding.saveButton.text="Ubah"
            binding.nameeditText.setText(farmer?.name)
            binding.addressEditText.setText(farmer?.address)
        }

        val name = binding.nameeditText.text
        val address = binding.addressEditText.text
        binding.saveButton.setOnClickListener {
            if (name.isEmpty()) {
            Toast.makeText(context,"nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }else if (name.isEmpty()) {
                Toast.makeText(context, "Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show()

            }else {
                if (farmer == null) {
                    val farmer = Farmer(0, name.toString(), address.toString())
                    farmerViewModel.insert(farmer)
                } else {
                    val farmer = Farmer(farmer?.id!!, name.toString(), address.toString())
                    farmerViewModel.update(farmer)
                }


                findNavController().popBackStack()
//          findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }
        binding.deleteButton.setOnClickListener{
            farmer?.let { farmerViewModel.delete(it) }
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
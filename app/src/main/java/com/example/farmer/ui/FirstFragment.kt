package com.example.farmer.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmer.R
import com.example.farmer.application.FarmerApp
import com.example.farmer.databinding.FragmentFirstBinding
import com.example.farmer.uipackage.FarmerListAdapter


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val farmerViewModel: FarmerViewModel by viewModels {
        FarmerViewModelFactory((applicationContext as FarmerApp).repository)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter =FarmerListAdapter { farmer ->
            val action=FirstFragmentDirections.actionFirstFragmentToSecondFragment(farmer)
            findNavController().navigate(action)
        }

        binding.dataRecyclerView.adapter=adapter
        binding.dataRecyclerView.layoutManager= LinearLayoutManager(context)
        farmerViewModel.allFarmers.observe(viewLifecycleOwner){farmers ->
            farmers.let {
                if (farmers.isEmpty()){
                    binding.textView.visibility= View.VISIBLE
                    binding.illustrationimageView.visibility=View.VISIBLE
                }else{
                    binding.textView.visibility= View.GONE
                    binding.illustrationimageView.visibility=View.GONE
                }
                adapter.submitList(farmers)
            }
        }

        binding.addFAB.setOnClickListener {
        val action=FirstFragmentDirections.actionFirstFragmentToSecondFragment(null)
        findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
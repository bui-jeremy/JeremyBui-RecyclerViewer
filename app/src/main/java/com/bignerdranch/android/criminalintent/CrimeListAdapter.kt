package com.bignerdranch.android.criminalintent

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimePoliceBinding

class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class PoliceCrimeHolder(
    private val binding: ListItemCrimePoliceBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(seriousCrime: Crime) {
        binding.crimeTitle.text = seriousCrime.title
        binding.crimeDate.text = seriousCrime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${seriousCrime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        if (viewType == 0) {
            val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
            return CrimeHolder(binding)
        } else  {
            val binding = ListItemCrimePoliceBinding.inflate(inflater, parent, false)
            return PoliceCrimeHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val crime = crimes[position]
        if (getItemViewType(position) == 1){
            (holder as PoliceCrimeHolder).bind(crime)
        } else {
            (holder as CrimeHolder).bind(crime)
        }
    }

    override fun getItemCount() = crimes.size

    override fun getItemViewType(position: Int): Int {
        if (crimes[position].requirePolice){
            return 1
        } else {
            return 0
        }
    }
}

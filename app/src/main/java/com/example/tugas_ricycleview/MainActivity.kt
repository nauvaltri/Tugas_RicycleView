package com.example.tugas_ricycleview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas_ricycleview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var newBuahList: ArrayList<Buah>
    lateinit var gambarBuah: Array<Int>
    lateinit var namaBuah: Array<String>
    lateinit var bioBuah: Array<String>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gambarBuah = arrayOf(
            R.drawable.anggur,
            R.drawable.apel,
            R.drawable.melon,
            R.drawable.pisang,
            R.drawable.semangka
        )

        namaBuah = arrayOf(
            "Anggur",
            "Apel",
            "Melon",
            "Pisang",
            "Semangka"
        )

        bioBuah= arrayOf(
            getString(R.string.bio_anggur),
            getString(R.string.bio_apel),
            getString(R.string.bio_melon),
            getString(R.string.bio_pisang),
            getString(R.string.bio_semangka)
        )

        with(binding) {
            rvBuah.layoutManager = LinearLayoutManager(this@MainActivity);
            rvBuah.setHasFixedSize(true)

            newBuahList = arrayListOf<Buah>()
            getUserData(newBuahList, rvBuah)
        }
    }

    private fun getUserData(list: ArrayList<Buah>, recyclerView: RecyclerView) {
        for(i in gambarBuah.indices) {
            val buah = Buah(gambarBuah[i], namaBuah[i], bioBuah[i])
            newBuahList.add(buah)
        }

        var adapter = BuahAdapter(list)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : BuahAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, BuahDetail::class.java)
                intent.putExtra("nama buah", newBuahList[position].namaBuah)
                intent.putExtra("gambar buah", newBuahList[position].gambarBuah)
                intent.putExtra("bio buah", newBuahList[position].bioBuah)

                startActivity(intent)
            }

        })
    }

}
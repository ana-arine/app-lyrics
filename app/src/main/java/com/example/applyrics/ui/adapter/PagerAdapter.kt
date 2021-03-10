package com.example.applyrics.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.applyrics.ui.fragment.BuscarMusicasFragment
import com.example.applyrics.ui.fragment.LetraMusicasFragment

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int) : Fragment {
        return when (position){
            0 -> BuscarMusicasFragment()
            1 -> LetraMusicasFragment()
//            2 -> Activity_Tela_Fragments.ListaFragment()
            else -> BuscarMusicasFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Buscar"
            1 -> "Letra"
//            2 -> "Lista de Letras"
            else -> super.getPageTitle(position)
        }
    }
}
package com.example.applyrics

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int) : Fragment {
        return when (position){
            0 -> Activity_Tela_Fragments.BuscarFragment()
            1 -> Activity_Tela_Fragments.LetraFragment()
            2 -> Activity_Tela_Fragments.ListaFragment()
            else -> Activity_Tela_Fragments.BuscarFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Buscar"
            1 -> "Letra"
            2 -> "Lista de Letras"
            else -> super.getPageTitle(position)
        }
    }
}
package com.susyimes.fastwebapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.susyimes.fastwebapp.database.WebData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val fragmentList by lazy { ArrayList<WebFragment>() }
    var currentPos = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        immersionBar {
            statusBarColor(R.color.white)
            navigationBarColor(R.color.white)
            autoDarkModeEnable(true)
            fitsSystemWindows(true)
        }
        val boxFor = ObjectBox.get()?.boxFor<WebData>()
        boxFor?.all

        main_pager.adapter = WebAdapter()
        main_pager.isUserInputEnabled = false
        main_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPos = position
            }
        })
    }

    inner class WebAdapter : FragmentStateAdapter(this) {
        override fun getItemCount(): Int {
            return 1
        }

        override fun createFragment(position: Int): Fragment {
            val newInstance = WebFragment.newInstance("https://coinmarketcap.com/")
            fragmentList.add(position, newInstance)
            return newInstance
        }
    }

    override fun onBackPressed() {
        fragmentList[currentPos].backPress()
    }

}
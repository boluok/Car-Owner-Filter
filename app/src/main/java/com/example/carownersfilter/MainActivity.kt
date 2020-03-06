package com.example.carownersfilter


import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.carownersfilter.utils.BaseActivity
import com.example.carownersfilter.views.*
import com.ncapdevi.fragnav.FragNavController


class MainActivity : BaseActivity() {
    private val baseFragments by lazy {
        listOf(
            SplashScreenFragment(),
            MyFiltersFragment()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavigation()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.actionbar, menu)
        return true
    }


    private fun setUpNavigation() {
        initFragNavController(this, baseFragments, "MAIN", supportFragmentManager, R.id.root)
    }


    companion object {
        val SPLASH_SCREEN = FragNavController.TAB1
        val MYFILTERS = FragNavController.TAB2
//        val GETTING_FILE = FragNavController.TAB3
//        val MYFILTERS = FragNavController.TAB4
//        val NO_FILE = FragNavController.TAB5
    }

    override fun onFragmentTransaction(
        fragment: Fragment?,
        transactionType: FragNavController.TransactionType
    ) {
        //supportActionBar?.setDisplayHomeAsUpEnabled(basefragNavController.isRootFragment.not())
        setActionBar(fragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBar(fragment: Fragment?) {
        if (supportActionBar != null && basefragNavController != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(!basefragNavController.isRootFragment)
        }
        if (fragment is MyFiltersFragment || fragment is FilteredCarOwnersFragment) {
            supportActionBar?.show()
        } else {
            supportActionBar?.hide()
        }
    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {
        setActionBar(fragment)


    }
}


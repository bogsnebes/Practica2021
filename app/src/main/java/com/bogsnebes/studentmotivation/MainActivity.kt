package com.bogsnebes.studentmotivation

import android.content.res.TypedArray
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(R.layout.activity_main),
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var navigationView: NavigationView
    private lateinit var mainList: RecyclerView
    private lateinit var lentaAdapter: LentaAdapter
    private val listMessages = ArrayList<ItemList>()
    private val listLenta = ArrayList<ItemList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigationView = findViewById(R.id.navigation_menu)
        navigationView.setNavigationItemSelectedListener(this)

        mainList = findViewById(R.id.mainList)

        listLenta.addAll(
            fillArrays(
                resources.getStringArray(R.array.news),
                resources.getStringArray(R.array.news_description),
                getImageId(R.array.news_image)
            )
        )

        listMessages.addAll(
            fillArrays(
                resources.getStringArray(R.array.messages),
                resources.getStringArray(R.array.messages_description),
                getImageId(R.array.messages_image)
            )
        )

        mainList.hasFixedSize()
        mainList.layoutManager = LinearLayoutManager(this)
        lentaAdapter = LentaAdapter(listLenta, this)
        mainList.adapter = lentaAdapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.myMarks -> {
                lentaAdapter.updateAdapter(
                    fillArrays(
                        resources.getStringArray(R.array.news),
                        resources.getStringArray(R.array.news_description),
                        getImageId(R.array.news_image)
                    )
                )
            }
            R.id.news -> {
                lentaAdapter.updateAdapter(
                    fillArrays(
                        resources.getStringArray(R.array.news),
                        resources.getStringArray(R.array.news_description),
                        getImageId(R.array.news_image)
                    )
                )
                mainList.adapter = lentaAdapter
            }
            R.id.messages -> mainList.adapter = MessageAdapter(listMessages, this)
            R.id.tests ->
            {
                lentaAdapter.updateAdapter(
                    fillArrays(
                        resources.getStringArray(R.array.tests),
                        resources.getStringArray(R.array.tests_description),
                        getImageId(R.array.tests_image)
                    )
                )
                mainList.adapter = lentaAdapter
            }
        }

        return true
    }

    private fun fillArrays(
        titleArray: Array<String>,
        contentArray: Array<String>,
        imageArray: IntArray
    ): ArrayList<ItemList> {
        val listItemArray = ArrayList<ItemList>()
        for (i in titleArray.indices) {
            val itemList = ItemList(imageArray[i], titleArray[i], contentArray[i])
            listItemArray.add(itemList)
        }
        return listItemArray
    }

    private fun getImageId(imageArrayId: Int): IntArray {
        val tArray: TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = tArray.length()
        val ids = IntArray(count)
        for (i in ids.indices) {
            ids[i] = tArray.getResourceId(i, 0)
        }
        tArray.recycle()
        return ids
    }
}

package com.example.onsopt_1st

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(){
    private lateinit var homeAdapter: homeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeAdapter = homeAdapter(this)

        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(homeAdapter))
        itemTouchHelper.attachToRecyclerView(rcv)

        rcv.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
        }

        homeAdapter.data = mutableListOf(
                homeData("이름", "박효송", "안녕 내 이름은 송이", "20.10.26"),
                homeData("나이", "23", "안녕 나는 23살 98년생", "20.10.26"),
                homeData("파트", "안드로이드", "안녕 나는 안드로이드 YB", "20.10.26"),
                homeData ("주소", "서울시 중랑구", "중랑구는 구로구랑 영등포구 싫어요", "20.10.26"),
                homeData("인스타그램", "@ssssong_e__", "인스타쟁이는 맞팔 환영", "20.10.26"),
                homeData("깃허브", "https://github.com/hyooosong/ONSOPT", "과제 열심히 하는 중", "20.10.26")
        )

        homeAdapter.notifyDataSetChanged()

    }

    //option menu 추가
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            //로그아웃 선택 시
            R.id.logout -> {
                val sharedPref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
                val sharedEdit: SharedPreferences.Editor = sharedPref.edit()

                sharedEdit.remove("ID")
                sharedEdit.remove("PW")
                sharedEdit.clear()
                sharedEdit.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
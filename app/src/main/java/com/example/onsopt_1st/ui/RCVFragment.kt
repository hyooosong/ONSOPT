package com.example.onsopt_1st.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.onsopt_1st.R
import com.example.onsopt_1st.data.homeData
import com.example.onsopt_1st.util.ItemTouchHelperCallback
import com.example.onsopt_1st.util.homeAdapter
import kotlinx.android.synthetic.main.fragment_rcv.*

class RCVFragment : Fragment() {
    var gridcheck : Boolean = false
    private lateinit var homeAdapter: homeAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_rcv, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeAdapter = homeAdapter(view?.context)

        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(homeAdapter))
        itemTouchHelper.attachToRecyclerView(rcv)

        rcv.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(view?.context)
        }

        //GridLayout 변경
        gridSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                if (gridcheck == false) {
                    rcv.apply {
                        adapter = homeAdapter
                        layoutManager = GridLayoutManager(view?.context, 2)
                    }
                    gridcheck = true
                } else {
                    rcv.apply {
                        adapter = homeAdapter
                        layoutManager = LinearLayoutManager(view?.context)
                    }
                    gridcheck = false
                }
            }
            else {
                rcv.apply {
                    adapter = homeAdapter
                    layoutManager = LinearLayoutManager(view?.context)
                }
                gridcheck = false
            }
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

}
package com.example.onsopt_1st.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onsopt_1st.R
import com.example.onsopt_1st.api.SoptServiceImpl
import com.example.onsopt_1st.data.ResponseUserData
import com.example.onsopt_1st.data.UserListData
import com.example.onsopt_1st.util.adapter.UserListAdapter
import kotlinx.android.synthetic.main.fragment_user_list.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListFragment : Fragment() {
    private lateinit var userlistadapter : UserListAdapter
    private var userlistdata = mutableListOf<UserListData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val call : Call<ResponseUserData> = SoptServiceImpl.userListService.loadUserList(page = 2)
        call.enqueue(object : Callback<ResponseUserData> {
            override fun onResponse(
                call: Call<ResponseUserData>,
                response: Response<ResponseUserData>
            ) {
                response.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let { it ->
                        for(i in 0..it.data.size-1) {
                            userlistdata.add(UserListData(response.body()!!.data[i].first_name,response.body()!!.data[i].last_name,
                                                    response.body()!!.data[i].email,response.body()!!.data[i].avatar))
                            userlistadapter.data = userlistdata
                            userlistadapter.notifyDataSetChanged()
                        }
                    }
            }
            override fun onFailure(call: Call<ResponseUserData>, t: Throwable) {
                Log.d("실패",t.toString())
            }
        })

        userlistadapter = UserListAdapter(view.context)
        rcv_userlist.apply {
            adapter = userlistadapter
            layoutManager = LinearLayoutManager(view.context)
        }

        imageview_back.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.frameLayout6, SettingFragment()).commit()
        }
        textview_back.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.frameLayout6, SettingFragment()).commit()
        }
    }
}
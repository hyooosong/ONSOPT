package com.example.onsopt_1st.ui

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onsopt_1st.R
import com.example.onsopt_1st.api.SoptServiceImpl
import com.example.onsopt_1st.data.RequestSearchData
import com.example.onsopt_1st.data.ResponseSearchData
import com.example.onsopt_1st.data.SearchData
import com.example.onsopt_1st.util.adapter.SearchAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private lateinit var kakaoadapter : SearchAdapter
    private var searchdata = mutableListOf<SearchData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_search, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search_imageview_back.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.frameLayout8, SettingFragment()).commit()
        }
        search_textview_back.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.frameLayout8, SettingFragment()).commit()
        }

        search_btn.setOnClickListener {
            val call : Call<ResponseSearchData> = SoptServiceImpl.searchService.kakaoSearch(query = search_edittext.text.toString())
            call.enqueue(object : Callback<ResponseSearchData> {
                override fun onResponse(call: Call<ResponseSearchData>, response: Response<ResponseSearchData>) {
                    response.takeIf { it.isSuccessful }
                            ?.body()
                            ?.let { it ->
                                for (i in 0..it.documents.size-1) {
                                    searchdata.add(SearchData(removeHtmlTag(response.body()!!.documents[i].title),
                                            removeHtmlTag(response.body()!!.documents[i].contents),
                                            response.body()!!.documents[i].url,
                                            response.body()!!.documents[i].datetime.slice(IntRange(0,9))
                                            + " " +response.body()!!.documents[i].datetime.slice(IntRange(11,15))))
                                }
                                    kakaoadapter.data = searchdata
                                    kakaoadapter.notifyDataSetChanged()
                            } ?: showError(response.errorBody())

                }
                private fun showError(error: ResponseBody?) {
                    val e = error ?: return
                    val ob = JSONObject(e.string())
                    Toast.makeText(view.context, ob.getString("message"), Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<ResponseSearchData>, t: Throwable) {
                    Log.d("통신 실패", t.toString())
                }

                fun removeHtmlTag(html : String) : String {
                    return Html.fromHtml(html).toString()
                }

            })
        }

        kakaoadapter = SearchAdapter(view.context)
        rcv_search.apply {
            adapter = kakaoadapter
            layoutManager = LinearLayoutManager(view.context)
        }

    }
}

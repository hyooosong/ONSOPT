package com.example.onsopt_1st.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onsopt_1st.R
import com.example.onsopt_1st.data.ProjectData
import com.example.onsopt_1st.util.adapter.ProjectAdapter
import kotlinx.android.synthetic.main.fragment_project.*

class ProjectFragment : Fragment() {
    private lateinit var ProjectAdapter: ProjectAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_project, container, false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ProjectAdapter = ProjectAdapter(view?.context)

        rcv_project.apply {
            adapter = ProjectAdapter
            layoutManager = LinearLayoutManager(view?.context)
        }

        ProjectAdapter.data = mutableListOf(
            ProjectData("SOPT", "20.09.26 ~ 21.01.23", "SOPT 27th YB"),
            ProjectData("ANDROID", "20.09.26 ~ 21.01.23", "PART ANDROID"),
            ProjectData("PAUSE", "20.11.21 ~ 21.11.22", "27th SOPKATHON")

        )

        ProjectAdapter.notifyDataSetChanged()

    }
}
package com.example.onsopt_1st

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.onsopt_1st.api.SoptServiceImpl
import com.example.onsopt_1st.data.RequestLoginData
import com.example.onsopt_1st.data.ResponseSignData
import com.example.onsopt_1st.ui.ProfileVPActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val sharedEdit: SharedPreferences.Editor = sharedPref.edit()

        //로그인 버튼 클릭 시 홈 화면 넘어감
        login_btn.setOnClickListener {
            if (editID.length() == 0 || editPW.length() == 0) {
                Toast.makeText(this, "아이디와 비밀번호를 올바르게 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                sharedEdit.putString("ID", editID.text.toString())
                sharedEdit.putString("PW", editPW.text.toString())
                sharedEdit.commit()

                val email = editID.text.toString()
                val password = editPW.text.toString()
                val call: Call<ResponseSignData> = SoptServiceImpl.service.postLogin(
                    RequestLoginData(email = email, password = password)
                )
                call.enqueue(object : Callback<ResponseSignData> {
                    override fun onFailure(call: Call<ResponseSignData>, t: Throwable) {
                        Log.d("통신 실패", t.toString())
                    }

                    override fun onResponse(
                        call: Call<ResponseSignData>,
                        response: Response<ResponseSignData>
                    ) {
                        response.takeIf { it.isSuccessful }
                            ?.body()
                            ?.let { it ->
                                Toast.makeText(this@MainActivity, "로그인 되었습니다!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@MainActivity, ProfileVPActivity::class.java)
                                startActivity(intent)
                            } ?: showError(response.errorBody())
                    }
                    private fun showError(error: ResponseBody?) {
                        val e = error ?: return
                        val ob = JSONObject(e.string())
                        Toast.makeText(this@MainActivity, ob.getString("message"), Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

        // 회원가입 버튼 클릭 시 회원가입 화면 넘어감
        signup_btn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent,500)
        }

        if (sharedPref.getString("ID", "").toString().isNotBlank() &&
                sharedPref.getString("PW", "").toString().isNotBlank()
        ) {
            Toast.makeText(
                    this,
                    sharedPref.getString("ID", "").toString() + "님 자동로그인 되었습니다!",
                    Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ProfileVPActivity::class.java)
            startActivity(intent)
        }


    }

    //회원가입 정보 로그인 화면으로 가져오기
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 500) {
            editID.setText(data!!.getStringExtra("ID"))
            editPW.setText(data.getStringExtra("PW"))
        }
    }

}
package com.example.onsopt_1st.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.example.onsopt_1st.R
import com.example.onsopt_1st.api.SoptServiceImpl
import com.example.onsopt_1st.data.RequestSignupData
import com.example.onsopt_1st.data.ResponseSignData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // 회원가입 버튼 클릭 시
        Ssign_btn.setOnClickListener() {

            //editText 값 받아오기
            val signName = editName.text.toString()
            val signID = editSID.text.toString()
            val signPW = editSPW.text.toString()

            //공백 포함/미포함 여부로 toast 출력 나눔
            if (signName.isEmpty() || signID.isEmpty() || signPW.isEmpty()) {
                Toast.makeText(this, "내용을 전부 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                val call : Call<ResponseSignData> = SoptServiceImpl.service.postSignup(
                    RequestSignupData(email=signID, password=signPW, userName=signName)
                )
                call.enqueue(object : Callback<ResponseSignData> {
                    override fun onResponse(
                            call: Call<ResponseSignData>,
                            response: Response<ResponseSignData>
                    ) {
                        response.takeIf { it.isSuccessful }
                            ?.body()
                            ?.let { it ->
                                Toast.makeText(this@SignUpActivity, "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@SignUpActivity, MainActivity::class.java)

                                //ID, PW 값에 회원가입 정보 넣기
                                intent.putExtra("ID", signID)
                                intent.putExtra("PW", signPW)
                                setResult(RESULT_OK, intent)
                                finish()
                            } ?: showError(response.errorBody())
                    }

                    private fun showError(error: ResponseBody?) {
                        val e = error ?: return
                        val ob = JSONObject(e.string())
                        Toast.makeText(this@SignUpActivity, ob.getString("message"), Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<ResponseSignData>, t: Throwable) {
                    }
                })
            }
        }
    }
}

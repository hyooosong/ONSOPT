package com.example.onsopt_1st

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*

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
                Toast.makeText(this, "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)

                //ID, PW 값에 회원가입 정보 넣기
                intent.putExtra("ID", signID)
                intent.putExtra("PW", signPW)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}

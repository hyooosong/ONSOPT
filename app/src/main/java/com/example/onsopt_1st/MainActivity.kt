package com.example.onsopt_1st

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


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
            } else {
                sharedEdit.putString("ID", editID.text.toString())
                sharedEdit.putString("PW", editPW.text.toString())
                sharedEdit.commit()

                Toast.makeText(this, "로그인 되었습니다!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ProfileVPActivity::class.java)
                startActivity(intent)
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
## 1주차 과제 (20.10.20 완료)  
* :pig: 회원가입 (필수)   
  * ToastMessage 출력(빈칸 여부)  
  * EditText 속성 (hint, inputType_textPassword)  
* :pig: 화면이동 + 정보저장 (성장1)    
  * 회원가입 성공 시 이전 로그인 화면 돌아오기  
  * 아이디와 비밀번호 입력 상태 (startActivityForResult)  
* :pig: 자동로그인 (성장2)  
  * 로그인 버튼 -> HomeActivity  
  * 로그인 성공 시 id, password 기억하여 다음부터 자동 로그인 (SharedPreferences)  
  * 자동 로그인일 경우 자동로그인 Toast Message  

**:heavy_check_mark: 회원가입 완료 과정**   
   
![login](/image/회원가입.png)
![login](/image/빈칸토스트.png)   
![login](/image/회원정보.png)
![login](/image/회원가입완료.png)    

**:heavy_check_mark: 로그인 과정**   
    
![login](/image/빈칸로그인.png)
![login](/image/일반로그인.png)
![login](/image/자동로그인.png)   
   
    
:cherries: 회원가입 빈칸 여부에 따른 Toast Message 출력
* activity_sign_up.xml의 editText 값 가져오기   
* isEmpty() 함수로 빈칸 여부 확인   

```kotlin
val signName = editName.text.toString()
val signID = editSID.text.toString()
val signPW = editSPW.text.toString()
            
 if (signName.isEmpty() || signID.isEmpty() || signPW.isEmpty()) {
                Toast.makeText(this, "내용을 전부 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
```
:cherries: editText 속성 hint(미리보기) 와 inputType 설정   
```kotlin
android:inputType="textPassword"
android:hint="비밀번호를 입력하세요"
```

:cherries: 회원가입 성공 시 이전 로그인 화면 돌아오기 (아이디, 비밀번호 입력 상태)   

* intent.putExtra(key, value) 를 통해 value 값을 key에 저장  

_SignUpActivity.kt_  
```kotlin
intent.putExtra("ID", signID)
intent.putExtra("PW", signPW)
setResult(RESULT_OK, intent)
finish()
```

* startActivityForResult(intent, requestCode) 이용하여 Activity에서 결과값 받아오기
* resultCode == RESULT_OK 와 호출한 requestCode값 일치 시 로그인 화면에 아이디와 비밀번호 값 입력   

_MainActivity.kt_  
```kotlin
signup_btn.setOnClickListener {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivityForResult(intent,500)
}

 override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 500) {
            editID.setText(data!!.getStringExtra("ID"))
            editPW.setText(data.getStringExtra("PW"))
        }
    }
```

:cherries: 자동 로그인  
* SharedPreferences 생성 -> getSharedPreferences(key, mode)
* 데이터를 저장, 수정, 삭제하기 위해 SharedPreferences.Editor 이용   

```kotlin
 val sharedPref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
 val sharedEdit: SharedPreferences.Editor = sharedPref.edit()
```
* 데이터 저장 editor.putString(key, value) 이용
* 무조건 commit() 또는 apply() 적용해주어야함 :pencil2: :pencil2: :pencil2:

```kotlin
sharedEdit.putString("ID", editID.text.toString())
sharedEdit.putString("PW", editPW.text.toString())
sharedEdit.commit()
```

* ID의 key(="ID") 값이 blank가 아닌 경우 저장된 ID 값을 불러와서 자동로그인 안내
* HomeActivity 이동

```kotlin
 if (sharedPref.getString("ID", "").toString().isNotBlank() &&
                sharedPref.getString("PW", "").toString().isNotBlank()
        ) {
            Toast.makeText(
                    this,
                    sharedPref.getString("ID", "").toString() + "님 자동로그인 되었습니다!",
                    Toast.LENGTH_SHORT).show()

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
```

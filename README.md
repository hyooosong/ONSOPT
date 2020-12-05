[1주차 과제](#1%EC%A3%BC%EC%B0%A8-%EA%B3%BC%EC%A0%9C-201020-%EC%99%84%EB%A3%8C)     
[2주차 과제](#2%EC%A3%BC%EC%B0%A8-%EA%B3%BC%EC%A0%9C-201029-%EC%99%84%EB%A3%8C)     
[3주차 과제](#3%EC%A3%BC%EC%B0%A8-%EA%B3%BC%EC%A0%9C-201106-%EC%99%84%EB%A3%8C)    
[6주차 과제](#6%EC%A3%BC%EC%B0%A8-%EA%B3%BC%EC%A0%9C-201205-%EC%99%84%EB%A3%8C)


## 6주차 과제 (20.12.05 완료)  
+ :pig: 로그인/회원가입 서버 통신 (필수)   
+ :pig: 더미데이터를 이용한 LIST USERS 통신 (성장1)    
+ :pig: 카카오 웹 검색 API (성장2)  


**:heavy_check_mark: 회원가입/로그인 화면**   
     
![6th](/image/signupok.gif)             ![6th](/image/signuperror.gif)   

**:heavy_check_mark: 로그인 성공 POSTMAN**   
    
![6th](/image/postman_loginok.PNG)   

**:heavy_check_mark: 회원가입 성공 및 실패(중복 이메일) POSTMAN**   
    
![6th](/image/postman_signuperror.PNG)          ![6th](/image/postman_signup_ok.PNG)     

:cherries:  준비 과정
+ Retrofit 라이브러리 설정   
`implementation 'com.squareup.retrofit2:retrofit:2.9.0’`   
`implementation 'com.squareup.retrofit2:retrofit-mock:2.9.0'`   
+ Gson 라이브러리 설정       
`implementation 'com.google.code.gson:gson:2.8.6’`    
`implementation 'com.squareup.retrofit2:converter-gson:2.9.0'`    
+ 인터넷 권한 허용 및 http 프로토콜 접속 예외 처리 (Manifest.xml 작성)         
`<uses-permission android:name="android.permission.INTERNET"/>`         
`<application android:usesCleartextTraffic="True"/>`      


:cherries:  Retrofit Interface 설계 (SoptService)    
+ Headers 작성 (Content-Type:application/json)
+ [POST/GET/PUT/DELETE] ("자원 식별 URL")    

```kotlin
interface SoptService {
    @Headers("Content-Type:application/json")
    @POST ("/users/signup")
    fun postSignup(
        @Body body : RequestSignupData
    ) : Call<ResponseSignData>

    @POST("/users/signin")
    fun postLogin(
        @Body body : RequestLoginData
    ) : Call<ResponseSignData>
}
```    
:cherries:  RequestLoginData    
+ email, password 요청 필요
```kotlin
data class RequestLoginData (
    val email: String,
    val password: String
)
```   

:cherries:  RequestSignupData
+ email, password, username 요청 필요
```kotlin
data class RequestSignupData (
    val email: String,
    val password: String,
    val userName: String
)
```   

:cherries:  ResponseSignData
+ status (400 == error) , success, message(toast 출력), data(email, password, username) 응답 바디
```kotlin
data class ResponseSignData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data : Data
) {
    data class Data(
        val email: String,
        val password: String,
        val userName: String
    )
}
```

:cherries: Retrofit Interface 실제 구현체 (SoptServiceImpl)   
+ `object ServiceImpl { }` -> 싱글톤 객체로 사용하기 위해 object 선언
+ `BASE_URL=" "`로 메인 서버 변수 선언
+ Retrofit 객체 생성
  - `Retrofit.Builder()` : retrofit 빌더 생성
  - `baseUrl(BASE_URL)` : 빌더 객체의 baseUrl 호출, 서버 메인 URL 전달
  - `addConverterFactory(GsonConverterFactory.create())` : gson 연동 (retrofit 데이터 다루기 쉽게)
  - `build()` : Retrofit 객체 
  
```kotlin
  object SoptServiceImpl {
    private const val BASE_URL="http://15.164.83.210:3000"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
  }
```    

+ Activity에 Callback 등록 후 통신 요청
  - Call<Type> : 비동기적으로 Type을 받아오는 객체
  - Callback<Type> : Type 객체를 받아왔을 때 프로그래머의 행동
  - enqueue : 실제 서버 통신을 비동기적으로 요청   
  
_SignupActivity.kt_      
```kotlin
   val call : Call<ResponseSignData> = SoptServiceImpl.service.postSignup(
                    RequestSignupData(email=signID, password=signPW, userName=signName))
               call.enqueue(object : Callback<ResponseSignData> {
                    override fun onResponse(
                            call: Call<ResponseSignData>,
                            response: Response<ResponseSignData>
                    ) {
                        response.takeIf { it.isSuccessful }
                            ?.body()
                            ?.let { it ->
                                Toast.makeText(this@SignUpActivity, "회원가입이 완료되었습니다!",
                                Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@SignUpActivity, MainActivity::class.java)

                                //ID, PW 값에 회원가입 정보 넣기
                                intent.putExtra("ID", signID)
                                intent.putExtra("PW", signPW)
                                setResult(RESULT_OK, intent)
                                finish()
                            } 
                    }
```   
_MainActivity.kt_    
```kotlin
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
                                sharedEdit.putString("ID", email)
                                sharedEdit.putString("PW", password)
                                sharedEdit.putString("Name", it.data.userName)
                                sharedEdit.commit()
                                Toast.makeText(this@MainActivity, sharedPref.getString("Name", "").toString()+ 
                                " 님 로그인 되었습니다!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@MainActivity, ProfileVPActivity::class.java)
                                startActivity(intent)
                            }
```          

**:heavy_check_mark: dummy USERLIST 통신 & 카카오 웹 검색**   
    
![6th](/image/dummy_kakao.gif)   



---     


## 1주차 과제 (20.10.20 완료)  
+ :pig: 회원가입 (필수)   
  + ToastMessage 출력(빈칸 여부)  
  + EditText 속성 (hint, inputType_textPassword)  
+ :pig: 화면이동 + 정보저장 (성장1)    
  + 회원가입 성공 시 이전 로그인 화면 돌아오기  
  + 아이디와 비밀번호 입력 상태 (startActivityForResult)  
+ :pig: 자동로그인 (성장2)  
  + 로그인 버튼 -> HomeActivity  
  + 로그인 성공 시 id, password 기억하여 다음부터 자동 로그인 (SharedPreferences)  
  + 자동 로그인일 경우 자동로그인 Toast Message  

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
+ activity_sign_up.xml의 editText 값 가져오기   
+ isEmpty() 함수로 빈칸 여부 확인   

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

+ intent.putExtra(key, value) 를 통해 value 값을 key에 저장  

_SignUpActivity.kt_  
```kotlin
intent.putExtra("ID", signID)
intent.putExtra("PW", signPW)
setResult(RESULT_OK, intent)
finish()
```

+ startActivityForResult(intent, requestCode) 이용하여 Activity에서 결과값 받아오기
+ resultCode == RESULT_OK 와 호출한 requestCode값 일치 시 로그인 화면에 아이디와 비밀번호 값 입력   

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
+ SharedPreferences 생성 -> getSharedPreferences(key, mode)
+ 데이터를 저장, 수정, 삭제하기 위해 SharedPreferences.Editor 이용   

```kotlin
 val sharedPref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
 val sharedEdit: SharedPreferences.Editor = sharedPref.edit()
```
+ 데이터 저장 editor.putString(key, value) 이용
+ 무조건 commit() 또는 apply() 적용해주어야함 :pencil2: :pencil2: :pencil2:

```kotlin
sharedEdit.putString("ID", editID.text.toString())
sharedEdit.putString("PW", editPW.text.toString())
sharedEdit.commit()
```

+ ID의 key(="ID") 값이 blank가 아닌 경우 저장된 ID 값을 불러와서 자동로그인 안내
+ HomeActivity 이동

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
   
     
---   
    
      
## 2주차 과제 (20.10.29 완료)    
     
+ :pig: 상세보기 제공 Recyclerview (필수)     
+ :pig: GridLayout으로 변경하기 (성장1)      
+ :pig: Recyclerview Item 이동 및 삭제 구현 (성장2)  
  * ItemTouchHelpterCallback, notifyItemMoved(), notifyItemRemoved() 이용     
  
  
**:heavy_check_mark: 상세화면 및 아이템 이동, 삭제 실행화면**   
   
  ![2nd](/image/2nd필수과제.gif)    ![2nd](/image/2nd성장과제.gif)      
  
**:heavy_check_mark: GridLayout 변경 화면**     
   
  ![2nd](/image/GridLayout.png)   
    
    
:cherries: RecyclerView 제작   
+ 아이템 형태 결정 (item_list.xml)   
+ 데이터 형태 결정 (Data class)  
```kotlin
data class homeData(
    val title : String,
    val subTitle : String,
    val detail : String,
    val date : String
)
```
+ 어디에 어떤 데이터? (ViewHolder)      
  + View를 저장할 수 있는 변수 (ViewHolder(itemView : View))  
  + View와 데이터를 연결시키는 함수 (onBind)   
```kotlin
class homeViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val title : TextView = itemView.findViewById(R.id.item_title)
    private val subTitle : TextView = itemView.findViewById(R.id.item_subTitle)

    fun onBind(data : homeData) {
        title.text=data.title
        subTitle.text=data.subTitle
    }
}
```
+ Recyclerviedw에 어떻게 데이터를 연결? (Adapter)  
  + 3가지 함수 override 필요
```kotlin
class homeAdapter (private val context: Context) : RecyclerView.Adapter<homeViewHolder>() {

    var data = mutableListOf<homeData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): homeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return homeViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: homeViewHolder, position: Int) {
        holder.onBind(data[position])
    }
```
+ Activity에 대입      
  + Adapter 생성 후 data 넣기
  + 어댑터 안에 데이터가 바뀌었다는 notify      
```kotlin
rcv.apply {
    adapter = homeAdapter
    layoutManager = LinearLayoutManager(this@HomeActivity)
}

homeAdapter.data = mutableListOf(
     homeData("title", "subTitle", "detail", "date"),
)

homeAdapter.notifyDataSetChanged()
  ```
       
         
:cherries: Recyclerview item 클릭 시 상세보기    
+ Adapter 내에 SetOnClickListener 생성 (key 값에 data 내용 저장)    
       
```kotlin
holder.itemView.setOnClickListener {
    val intent = Intent(holder.itemView.context, DetailActivity::class.java)
    intent.putExtra("title", data[position].title)
    intent.putExtra("subTitle", data[position].subTitle)
    intent.putExtra("detail", data[position].detail)
    intent.putExtra("date", data[position].date)
    startActivity(holder.itemView.context, intent, null)
}
```   
+ activity_detail.xml 생성 (상세화면)    
+ DetailActivity 생성 (상세화면의 내용 변경)    

```kotlin
   this.detailTitle.text = intent.getStringExtra("title")
   this.detailSubTitle.text = intent.getStringExtra("subTitle")
   this.detail.text = intent.getStringExtra("detail")
   this.date.text = intent.getStringExtra("date")
```
     
:cherries: GridLayout으로 변경
+ LayoutManager를 GridLayoutManager로 변경 (context, spanCount)
``` kotlin
   LayoutManager = GridLayoutManager(this@HomeActivity, 2)
```   

:cherries: Recyclerview Item 이동 및 삭제
+ ItemTouchHelperCallback Class 생성
  - 3가지 함수 override 필요  
    + getMovementFlags -> Drag, Swipe 방향 지정
    + onMove -> Drag 시 onItemMoved 호출, from과 to position 전달
    + onSwiped -> Swipe 시 onItemSwiped 호출, 현재 위치 전달
 
   
 ```kotlin
override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
     val dragFlags = ItemTouchHelper.DOWN or ItemTouchHelper.UP or ItemTouchHelper.START or ItemTouchHelper.END
     val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
     return makeMovementFlags(dragFlags, swipeFlags)
}
override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, 
                     target: RecyclerView.ViewHolder): Boolean {
    adapter.onItemMoved(viewHolder!!.adapterPosition, target!!.adapterPosition)
    return true
}

override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    adapter.onItemSwiped(viewHolder!!.adapterPosition)
    }
 ```

 
* Adapter 내에 OnItemMoved(), onItemSwiped() 구현
```kotlin
 fun onItemMoved(from: Int, to: Int) {
        if(from<to) {
            for(i in from until to)
                Collections.swap(data,i,i+1)
        } else {
            for(i in from downTo to+1)
                Collections.swap(data,i,i-1)
        }
        notifyItemMoved(from, to)
        notifyDataSetChanged()
    }

    fun onItemSwiped(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }
```
* itemTouchHelper 적용하기
```kotlin
  val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(homeAdapter))
  itemTouchHelper.attachToRecyclerView(rcv)
```     

---   

## 3주차 과제 (20.11.06 완료)  
+ :pig: BottomNavigationView를 이용한 fragment 변경   
  + 첫번째 화면 - 프로필
  + 두번째 화면 - 2주차 Recyclerview
  + 세번째 화면 - 빈 화면
+ :pig: TabLayout을 이용한 fragment 변경    

**:heavy_check_mark: 3주차 과제**     
   
  ![2nd](/image/3rd과제.gif)   
      
:cherries: [전체 화면] BottomNavigationView와 Viewpager로 구성
+ Viewpager를 구성할 3가지의 fragment 필요
  + ThirdFragment()
  + RCVFragment() -> 2nd_assignment
  + ProfileFragment()
    + :fire: fragment_profile을 구성하는 fragment.kt
    - onviewCreated() 함수를 이용해 viewpager(profileVP)에 adapter 할당 및 클릭 구현 
   
_ProfileFragment.kt_    
```kotlin
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var index by Delegates.notNull<Int>()

        prof_viewpagerAdapter = ProfileVPAdapter(childFragmentManager)
        prof_viewpagerAdapter.fragments = listOf(
            FirstFragment(),
            SecondFragment()
        )

        profileVP.adapter = prof_viewpagerAdapter
        tabLayout.setupWithViewPager(profileVP)

        tabLayout.apply {
            getTabAt(0)?.text="INFO"
            getTabAt(1)?.text="OTHER"
        }
    }
```    

_ProfileVPActivity.kt_    
     
* 전체화면의 Activity
* BottomNavigationview 와 전체 Viewpager에 관한 움직임 담당
      
``` kotlin
   //하단 탭 클릭 시 뷰페이저 변경
 bottom_navi.setOnNavigationItemSelectedListener {
       var index by Delegates.notNull<Int>()

       when(it.itemId) {
           R.id.account -> index = 0
           R.id.home -> index = 1
           R.id.check -> index = 2
       }
       viewpager.currentItem = index
       true
}
  // 뷰페이저 슬라이드 시 하단 탭 변경
viewpager.addOnPageChangeListener(object  : ViewPager.OnPageChangeListener {
     override fun onPageScrollStateChanged(state: Int) {}

     override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
     ) {}
     override fun onPageSelected(position: Int) {
        bottom_navi.menu.getItem(position).isChecked = true
     }
})
```

+ Adapter : ViewPagerAdapter 역할을 위해 FragmentStatePagerAdapter 상속
  + `var fragments = ListOf<Fragment>()` 이용하여 Fragment 리스트 만듦
  + getItem()과 getCount() 메소드 반드시 override 필요
  
```kotlin
class ProfileVPAdapter (fm : FragmentManager)
    : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    var fragments = listOf<Fragment>()

    override fun getItem(position: Int): Fragment = when(position) {
        0 -> FirstFragment()
        1 -> SecondFragment()

        else -> throw IllegalStateException("Unexpected position $position")
    }
    override fun getCount(): Int = 2
}
```         

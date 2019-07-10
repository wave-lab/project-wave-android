package com.song2.wave.UI.Main.Home

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.AudioTest.PlayerActivity
import com.song2.wave.Data.GET.GetHomeInfoResponse
import com.song2.wave.Data.model.Home.HomeSongData
import com.song2.wave.Data.model.Home.MyWaitingSongData
import com.song2.wave.Data.model.Home.TOP10Data
import com.song2.wave.Data.model.HomeUserInfoData
import com.song2.wave.R
import com.song2.wave.UI.Main.Home.Adapter.*
import com.song2.wave.UI.Main.Home.Top10.Top10Fragment
import com.song2.wave.UI.Signup.SignupFirstActivity
import com.song2.wave.Util.Network.ApplicationController
import com.song2.wave.Util.Network.NetworkService
import kotlinx.android.synthetic.main.fragment_home_on.*
import kotlinx.android.synthetic.main.fragment_home_on.view.*
import retrofit2.Call
import retrofit2.Response

class HomeOnFragment : Fragment() {

    val networkService: NetworkService by lazy { ApplicationController.instance.networkService
    }

    lateinit var authorization_info : String

    lateinit var myWaitingSongDataList: ArrayList<MyWaitingSongData>
    lateinit var waitingSongDataList: ArrayList<HomeSongData>
    lateinit var hitSongHomeDataList: ArrayList<HomeSongData>
    lateinit var recommendSongHomeDataList: ArrayList<HomeSongData>
    lateinit var top10GenreDataList: ArrayList<TOP10Data>
    lateinit var top10MoodDataList: ArrayList<TOP10Data>


    lateinit var myWaitingSongHomeAdapter: MyWaitingSongHomeAdapter
    lateinit var waitingSongHomeAdapter: WaitingSongHomeAdapter
    lateinit var hitSongHomeAdapter: HitSongHomeAdapter
    lateinit var recommendSongHomeAdapter: RecomentSongHomeAdapter
    lateinit var top10GenreAdapter: Top10GenreAdapter
    lateinit var top10MoodAdapter: Top10GenreAdapter

    //lateinit var top10MoodAdapter: Top10MoodAdapter


    lateinit var requestManager: RequestManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v : View = inflater.inflate(R.layout.fragment_home_on, container, false)


        authorization_info = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxMDUsImlhdCI6MTU2MjcyMjQ5MCwiZXhwIjoxNTY1MzE0NDkwfQ.CdVtW28EY4XOWV_xlt2dlYFMdEdFcIRN6lmsmJ8_jKQ"


        //통신
        getHomeInfoResponse()

        v.iv_home_frag_wavelogo.setOnClickListener {
            var intent = Intent(context, PlayerActivity::class.java)
            startActivity(intent)
        }

        v.tv_home_frag_ment.setOnClickListener {
            var intent = Intent(context, SignupFirstActivity::class.java)
            startActivity(intent)
        }

        v.iv_home_frag_top10_genre_more_btn.setOnClickListener {
            HomeFragment.homeFragment.replaceFragment(Top10Fragment())
        }

        v.iv_home_frag_top10_mood_more_btn.setOnClickListener {
            HomeFragment.homeFragment.replaceFragment(Top10Fragment())
        }

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        attachRecyclerView()

    }

    fun attachRecyclerView(){

        waitingSongDataList = ArrayList()
        myWaitingSongDataList = ArrayList()
        hitSongHomeDataList = ArrayList()
        recommendSongHomeDataList = ArrayList()
        top10GenreDataList = ArrayList()
        top10MoodDataList = ArrayList()

        requestManager = Glide.with(this)

        insertExampleData()

        top10GenreAdapter = Top10GenreAdapter(top10GenreDataList, requestManager)
        rv_home_frag_top10_genre_list.adapter = top10GenreAdapter
        rv_home_frag_top10_genre_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        top10MoodAdapter = Top10GenreAdapter(top10MoodDataList, requestManager)
        rv_home_frag_top10_mood_list.adapter = top10MoodAdapter
        rv_home_frag_top10_mood_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        recommendSongHomeAdapter = RecomentSongHomeAdapter( recommendSongHomeDataList, requestManager)
        rv_home_frag_scoring_recommend_list.adapter = recommendSongHomeAdapter
        rv_home_frag_scoring_recommend_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        hitSongHomeAdapter = HitSongHomeAdapter( hitSongHomeDataList, requestManager)
        rv_home_frag_scoring_hit_list.adapter = hitSongHomeAdapter
        rv_home_frag_scoring_hit_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        waitingSongHomeAdapter = WaitingSongHomeAdapter(waitingSongDataList, requestManager)
        rv_home_frag_scoring_waiting.adapter = waitingSongHomeAdapter
        rv_home_frag_scoring_waiting.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        myWaitingSongHomeAdapter = MyWaitingSongHomeAdapter(myWaitingSongDataList, requestManager)
        rv_home_frag_scoring_waiting_mine.adapter = myWaitingSongHomeAdapter
        rv_home_frag_scoring_waiting_mine.layoutManager =  LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun insertExampleData() {
        top10MoodDataList.add(TOP10Data("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "힙합"))
        top10MoodDataList.add(TOP10Data("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "힙합"))
        top10MoodDataList.add(TOP10Data("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "힙합"))
        top10MoodDataList.add(TOP10Data("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "힙합"))

        top10GenreDataList.add(TOP10Data("https://images.otwojob.com/product/x/U/6/xU6PzuxMzIFfSQ9.jpg/o2j/resize/852x622%3E", "신나는"))
        top10GenreDataList.add(TOP10Data("https://images.otwojob.com/product/x/U/6/xU6PzuxMzIFfSQ9.jpg/o2j/resize/852x622%3E", "힙한"))
        top10GenreDataList.add(TOP10Data("https://images.otwojob.com/product/x/U/6/xU6PzuxMzIFfSQ9.jpg/o2j/resize/852x622%3E", "우울한"))
        top10GenreDataList.add(TOP10Data("https://images.otwojob.com/product/x/U/6/xU6PzuxMzIFfSQ9.jpg/o2j/resize/852x622%3E", "우울한"))

        recommendSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        recommendSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        recommendSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        recommendSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        recommendSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        recommendSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))

        hitSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        hitSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        hitSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        hitSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        hitSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        hitSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))

        waitingSongDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        waitingSongDataList.add(HomeSongData("https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "똥꼬2", "류지훈", "양승희"))
        waitingSongDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬3", "류지훈", "양승희"))
        waitingSongDataList.add(HomeSongData("https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "똥꼬4", "류지훈", "양승희"))
        waitingSongDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬5", "류지훈", "양승희"))

        myWaitingSongDataList.add(MyWaitingSongData(1, "https://images.otwojob.com/product/x/U/6/xU6PzuxMzIFfSQ9.jpg/o2j/resize/852x622%3E", "노래제목1", "가숫1"))
        myWaitingSongDataList.add(MyWaitingSongData(2, "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "노래제목2", "가숫2"))
        myWaitingSongDataList.add(MyWaitingSongData(3, "https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "노래제목3", "가숫3"))
        myWaitingSongDataList.add(MyWaitingSongData(4, "https://images.otwojob.com/product/x/U/6/xU6PzuxMzIFfSQ9.jpg/o2j/resize/852x622%3E", "노래제목1", "가숫1"))
        myWaitingSongDataList.add(MyWaitingSongData(5, "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "노래제목2", "가숫2"))
        myWaitingSongDataList.add(MyWaitingSongData(6, "https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "노래제목3", "가숫3"))
    }

    fun getHomeInfoResponse(){
        //xx일 경우
        val getHomeInfoResponse = networkService.getHomeInfoResponse("application/json",authorization_info)

        getHomeInfoResponse.enqueue(object: retrofit2.Callback<GetHomeInfoResponse>{
            override fun onFailure(call : Call<GetHomeInfoResponse>, t : Throwable){
                Log.e("home user info fail", t.toString())
            }
            override fun onResponse(call : Call<GetHomeInfoResponse>, response: Response<GetHomeInfoResponse>){
                if (response.isSuccessful) {
                    val temp: HomeUserInfoData = response.body()!!.data

                    if (authorization_info != null){
                        showInfo(temp)
                    }
                }
            }
        })
    }

    fun showInfo(temp: HomeUserInfoData){
        //tv_home_frag_ment.setText(temp.nickname)
        tv_home_frag_ment.setText(temp.nickname+"님!\n업로드 곡이\n평가를 기다리고 있어요!")
        tv_home_frag_scoring_cnt.setText(temp.rateSongCount.toString())
        tv_home_frag_perfect_cnt.setText(temp.hitSongCount.toString())
        tv_home_frag_total_point.setText(temp.totalPoint.toString() + "P")
    }
}


package com.song2.wave.UI.Main.Scoring.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.SongData
import com.song2.wave.R
import com.song2.wave.UI.Main.Search.Adapter.SongSearchViewHolder
import com.song2.wave.UI.MainPlayer.MainPlayerActivity
import com.song2.wave.Util.Audio.AudioApplication

class ScoringWaitAdapter (var context : Context, private var songData : ArrayList<SongData>, var requestManager : RequestManager) : RecyclerView.Adapter<SongSearchViewHolder>(){

    var mContext = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongSearchViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_song_view, parent, false)
        return SongSearchViewHolder(mainView)
    }

    override fun getItemCount(): Int = songData.size

    override fun onBindViewHolder(holder: SongSearchViewHolder, position: Int) {

        requestManager.load(songData[position].songCoverImg).centerCrop().into(holder.songCoverImg)
        holder.songName.text = songData[position].songName
        holder.originArtistName.text = " - " + songData[position].originArtistName
        holder.coverArtistName.text = songData[position].coverArtistName
        holder.songField.text = songData[position].songField!![0]

        holder.itemView.setOnClickListener{
            var intent = Intent(mContext, MainPlayerActivity::class.java)
            //AudioApplication.getInstance().serviceInterface.setPlayList(holder.adapterPosition) // 재생목록등록
            AudioApplication.getInstance().serviceInterface.play(songData[holder.adapterPosition]._id, songData[holder.adapterPosition].songUrl, songData[position].originArtistName, songData[position].coverArtistName, songData[position].songName) // 선택한 오디오재생
            intent.putExtra("_id", songData[holder.adapterPosition]._id)
            Log.v("ASdf","테스트1 id = " + songData[holder.adapterPosition]._id)
            intent.putExtra("songUrl", songData[holder.adapterPosition].songUrl)
            intent.putExtra("title", songData[holder.adapterPosition].songName)
            intent.putExtra("originArtist", songData[holder.adapterPosition].originArtistName)
            intent.putExtra("coverArtist", songData[holder.adapterPosition].coverArtistName)
            intent.putExtra("songImgUrl", songData[holder.adapterPosition].songCoverImg)
            intent.putExtra("rating_flag", 1)
            mContext.startActivity(intent)
        }
    }
}
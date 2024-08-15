package com.example.todaynan.ui.adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todaynan.R
import com.example.todaynan.data.remote.post.PostList
import com.example.todaynan.databinding.ItemPostBinding
import java.time.format.DateTimeFormatter

class PostRVAdapter(private var pList: List<PostList>) : RecyclerView.Adapter<PostRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(post: PostList)
    }

    private lateinit var myItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        myItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPostBinding = ItemPostBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            myItemClickListener.onItemClick(pList[position])
        }
        holder.bind(pList[position])
    }

    override fun getItemCount(): Int {
        return pList.size
    }

    inner class ViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostList) {
            val createdAt = post.createdAt
            /*val formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm")
            val time = createdAt.format(formatter)*/
            val day = createdAt.split("T")
            val time = day[1].split(".")[0]

            // 사용자 ID로 이미지를 매핑하거나 서버에서 받아온 URL로 이미지를 로드하는 등의 방법을 사용
            binding.postProfileIv.setImageResource(R.drawable.circle_green) // 기본 이미지 설정
            binding.postUserNameTv.text = post.userNickname
            binding.postUserLocationTv.text = post.userAddress
            binding.postLikeNumberTv.text = post.postLike.toString()
            binding.postReplyNumberTv.text = post.postComment.toString()
            binding.likedPostCreationTimeTv.text = day[0] + " " + time
            binding.likedPostTitleTv.text = post.postTitle
            binding.likedPostContentTv.text = post.postContent
        }
    }
}

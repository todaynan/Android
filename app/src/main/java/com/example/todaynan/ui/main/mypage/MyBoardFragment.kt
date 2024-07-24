package com.example.todaynan.ui.main.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todaynan.ui.adapter.BoardLikedRVAdapter
import com.example.todaynan.data.entity.MyLikedPost
import com.example.todaynan.R
import com.example.todaynan.databinding.FragmentBoardBinding
import com.example.todaynan.databinding.FragmentMyBoardBinding
import com.example.todaynan.ui.BaseFragment

class MyBoardFragment : BaseFragment<FragmentMyBoardBinding>(FragmentMyBoardBinding::inflate) {
    override fun initAfterBinding() {

    val items = generateDummyItems() // 데이터 생성 (임시 함수)
    val boardAdapter = BoardLikedRVAdapter(items)
    binding.likedPostRv.adapter = boardAdapter
    binding.likedPostRv.layoutManager = LinearLayoutManager(context)


    binding.boardBackBtn.setOnClickListener {
        parentFragmentManager.popBackStack()
    }
    binding.likeBoard.setOnClickListener{
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_frm, MyBoardLikeFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()
        }
        binding.replyBoard.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MyBoardReplyFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
        binding.writeBoard.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MyBoardWriteFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }

}
    private fun generateDummyItems(): List<MyLikedPost> {
        val items = ArrayList<MyLikedPost>()
        items.add(MyLikedPost("띠드버거", R.drawable.default_profile_img,"05.06 14:30","마포구 상암동","잠실 진저베어 신상","안녕하떼여 띠드버거임니당!\n이번 주말에 딩딩이랑 잠실에 갔는데요,,,","추천 게시판",21,15))
        items.add(MyLikedPost("재밌으면 짖는 개", R.drawable.default_profile_img,"05.04 14:20","광명시 철산동","성심당","없어지면 내가 가게 차린다ㅋㅋ\n마라탕후루집으로 차릴거임","잡담 게시판",33,50))
        items.add(MyLikedPost("AI입니다", R.drawable.default_profile_img,"05.02 11:30","구로구 구로동","현대미술관 띱","저랑 같이 보러 가실 분?\n밥 사드림","구인 게시판",15,10))
        items.add(MyLikedPost("띠드버거", R.drawable.default_profile_img,"05.06 14:30","마포구 상암동","잠실 진저베어 신상","안녕하떼여 띠드버거임니당!\n이번 주말에 딩딩이랑 잠실에 갔는데요,,,","추천 게시판",21,15))
        items.add(MyLikedPost("재밌으면 짖는 개", R.drawable.default_profile_img,"05.04 14:20","광명시 철산동","성심당","없어지면 내가 가게 차린다ㅋㅋ\n마라탕후루집으로 차릴거임","잡담 게시판",33,50))
        items.add(MyLikedPost("AI입니다", R.drawable.default_profile_img,"05.02 11:30","구로구 구로동","현대미술관 띱","저랑 같이 보러 가실 분?\n밥 사드림","구인 게시판",15,10))
        items.add(MyLikedPost("띠드버거", R.drawable.default_profile_img,"05.06 14:30","마포구 상암동","잠실 진저베어 신상","안녕하떼여 띠드버거임니당!\n이번 주말에 딩딩이랑 잠실에 갔는데요,,,","추천 게시판",21,15))
        items.add(MyLikedPost("재밌으면 짖는 개", R.drawable.default_profile_img,"05.04 14:20","광명시 철산동","성심당","없어지면 내가 가게 차린다ㅋㅋ\n마라탕후루집으로 차릴거임","잡담 게시판",33,50))
        items.add(MyLikedPost("AI입니다", R.drawable.default_profile_img,"05.02 11:30","구로구 구로동","현대미술관 띱","저랑 같이 보러 가실 분?\n밥 사드림","구인 게시판",15,10))
        return items
    }
}

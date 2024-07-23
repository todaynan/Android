package com.example.todaynan.ui.main.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todaynan.ui.adapter.PostListRVAdapter
import com.example.todaynan.R
import com.example.todaynan.data.entity.PostList
import com.example.todaynan.databinding.FragmentPostBinding
class PostFragment : Fragment() {

    private lateinit var binding: FragmentPostBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostBinding.inflate(inflater, container, false)


        val items = generateDummyItems() // 데이터 생성 (임시 함수)
        val postAdapter = PostListRVAdapter(items)
        binding.postListRv.adapter = postAdapter
        binding.postListRv.layoutManager = LinearLayoutManager(context)


        binding.postBackBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding.root
    }
    private fun generateDummyItems(): List<PostList> {
        val items = ArrayList<PostList>()
        items.add(PostList("전주비빔", R.drawable.default_profile_img,"언제쯤 도착하시나요?","05.06 14:30",false))
        items.add(PostList("피까삐까",
            R.drawable.default_profile_img,"인사이드 아웃2 스포해도 돼요??","05.06 14:30",false))
        items.add(PostList("바라바라밤", R.drawable.default_profile_img,"수수수수퍼노바","05.06 14:30",true))
        items.add(PostList("배고파배고파", R.drawable.default_profile_img,"마라탕 사주세여","05.06 14:30",true))
        items.add(PostList("전주통닭", R.drawable.default_profile_img,"으어어어졸려","05.06 14:30",true))
        items.add(PostList("날짜는", R.drawable.default_profile_img,"안바꿀래여 흥","05.06 14:30",true))

        return items
    }

}

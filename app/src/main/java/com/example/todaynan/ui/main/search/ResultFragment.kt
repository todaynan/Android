package com.example.todaynan.ui.main.search

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todaynan.R
import com.example.todaynan.base.AppData
import com.example.todaynan.data.remote.getRetrofit
import com.example.todaynan.data.remote.place.GeminiItem
import com.example.todaynan.data.remote.place.GoogleItem
import com.example.todaynan.data.remote.place.Outside
import com.example.todaynan.data.remote.place.PlaceInterface
import com.example.todaynan.data.remote.place.PlaceResponse
import com.example.todaynan.databinding.FragmentResultBinding
import com.example.todaynan.ui.BaseFragment
import com.example.todaynan.ui.adapter.OutsideRVAdapter
import com.example.todaynan.ui.adapter.InsideRVAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultFragment : BaseFragment<FragmentResultBinding>(FragmentResultBinding::inflate) {

    var showType: Int = 0   //0: 크게 보기, 1: 작게 보기
    lateinit var screenAddress: String
    private var insideItemList : ArrayList<GeminiItem>? = null
    private var outsideItemList: ArrayList<GoogleItem>? = null

    override fun initAfterBinding() {

        screenAddress = AppData.address
        binding.locInfoTv.text = screenAddress.split(" ").last()

        val word = arguments?.getString("keyword")
        binding.resultEt.setText(word)

        chooseType()
        changeScreen()

        val place = arguments?.getString("place")
        if(place == "inside"){
            // 안 결과 데이터
            arguments?.let {
                insideItemList = it.getSerializable("insideItem") as? ArrayList<GeminiItem>
            }
            val insideRVAdapter1 = InsideRVAdapter(insideItemList,1)
            binding.resultListRv.adapter = insideRVAdapter1
            binding.resultListRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val insideRVAdapter2 = InsideRVAdapter(insideItemList,2)
            binding.resultBlockRv.adapter = insideRVAdapter2
            binding.resultBlockRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        } else{ // place == "outside"
            // 밖 결과 데이터
            arguments?.let {
                outsideItemList = it.getSerializable("outsideItem") as? ArrayList<GoogleItem>
            }
            val outsideRVAdapter1 = OutsideRVAdapter(outsideItemList,1)
            binding.resultListRv.adapter = outsideRVAdapter1
            binding.resultListRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val outsideRVAdapter2 = OutsideRVAdapter(outsideItemList,2)
            binding.resultBlockRv.adapter = outsideRVAdapter2
            binding.resultBlockRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

    }

    private fun changeScreen(){
        binding.searchImageBt1.setOnClickListener {
            hideKeyboard()
            binding.resultEt.text = binding.resultEt.text
            // 검색 결과 갱신
            outsideResult(binding.resultEt.text.toString())
        }
        binding.resultEt.setOnEditorActionListener { v, actionId, event ->
            if (event.keyCode == KeyEvent.KEYCODE_ENTER) {
                hideKeyboard()
                binding.resultEt.text = binding.resultEt.text
                // 검색 결과 갱신
                outsideResult(binding.resultEt.text.toString())
                true // 이벤트 처리 완료
            } else {
                false // 이벤트 처리 안 함
            }
        }

        binding.searchBackIv.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
    private fun outsideResult(keyword: String){
        val placeService = getRetrofit().create(PlaceInterface::class.java)

        val auth = "Bearer "+AppData.appToken
        val searchWord = "$screenAddress $keyword"
        Log.d("TAG_outside", searchWord)
        placeService.searchOutside(auth, searchWord, null).enqueue(object:
            Callback<PlaceResponse<Outside>> {
            override fun onResponse(
                call: Call<PlaceResponse<Outside>>,
                response: Response<PlaceResponse<Outside>>
            ) {
                Log.d("TAG_outsideSuccess", response.toString())
                Log.d("TAG_outsideSuccess", response.body().toString())
                val resp = response.body()
                if(resp?.isSuccess == true && resp?.code == "OK200"){
                    val bundle = Bundle().apply {
                        putSerializable("outsideItem", resp!!.result.googlePlaceResultDTOList)
                        putString("place", "outside")
                        putString("keyword", keyword)
                    }
                    val resultFragment = ResultFragment()
                    resultFragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, resultFragment)
                        .commitAllowingStateLoss()
                }else{
                    Toast.makeText(context, "다시 요청해주세요", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PlaceResponse<Outside>>, t: Throwable) {
                Log.d("TAG_outsideFail", t.message.toString())
            }

        })
    }


    private fun chooseType(){
        binding.resultMenuIv.setOnClickListener{
            if(showType == 0)
                binding.resultMenuIv.setImageResource(R.drawable.search_menu_list_dark)
            else
                binding.resultMenuIv.setImageResource(R.drawable.search_menu_block_dark)

            val typeList = mutableListOf<PopupValue>().apply {
                add(PopupValue(R.drawable.search_menu_list,"크게 보기"))
                add(PopupValue(R.drawable.search_menu_block, "작게 보기"))
            }

            SearchMenuPopup(context = requireContext(), popupList = typeList){ _, _, position->
                when (position) {
                    0 -> { //크게 보기
                        binding.resultMenuIv.setImageResource(R.drawable.search_menu_list)
                        binding.resultListRv.isVisible = true
                        binding.resultBlockRv.isVisible = false
                        showType = 0
                    }

                    1 -> { //작게 보기
                        binding.resultMenuIv.setImageResource(R.drawable.search_menu_block)
                        binding.resultListRv.isVisible = false
                        binding.resultBlockRv.isVisible = true
                        showType = 1
                    }
                }
            }.apply {
                isOutsideTouchable = true
                isTouchable = true
                showAsDropDown(it, -270, 20) //resultMenuIv 기준으로 팝업메뉴 위치
            }
        }
    }

}
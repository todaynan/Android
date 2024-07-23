package com.example.todaynan.ui.main.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todaynan.R
import com.example.todaynan.databinding.FragmentChangeInfoBinding
class ChangeInfoFragment : Fragment() {

    private lateinit var binding: FragmentChangeInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangeInfoBinding.inflate(inflater, container, false)


        binding.changeInfoBackBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.changeNickname.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, ChangeNicknameFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
        binding.changeLocation.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, ChangeLocationFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
        binding.changeInterest.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, ChangeInterestFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
        binding.changeLogout.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, ChangeLogoutFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
        binding.changeWithdraw.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, ChangeWithdrawFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }

        return binding.root
    }
}

package com.example.todaynan.ui.main.mypage

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.todaynan.base.AppData
import com.example.todaynan.data.remote.getRetrofit
import com.example.todaynan.data.remote.user.SignOutResponse
import com.example.todaynan.data.remote.user.UserInterface
import com.example.todaynan.data.remote.user.UserResponse
import com.example.todaynan.databinding.FragmentChangeSignoutBinding
import com.example.todaynan.ui.BaseFragment
import com.example.todaynan.ui.signup.SignUpActivity // SignUpActivity import
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangeSignOutFragment : BaseFragment<FragmentChangeSignoutBinding>(FragmentChangeSignoutBinding::inflate) {
    private val userService = getRetrofit().create(UserInterface::class.java)

    override fun initAfterBinding() {

        binding.changeWithdrawBackBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        showWithdrawConfirmationDialog()
    }

    private fun showWithdrawConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("회원 탈퇴")
        builder.setMessage("정말 회원탈퇴를 진행하시겠습니까?")

        // 긍정 버튼
        builder.setPositiveButton("예") { dialog, which ->
            performSignOut() // 회원 탈퇴 API 호출
        }

        // 부정 버튼
        builder.setNegativeButton("아니요") { dialog, which ->
            // 아무 작업도 하지 않고 다이얼로그를 닫습니다.
            dialog.dismiss()
        }

        // 다이얼로그를 보여줍니다.
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun performSignOut() {
        val accessToken = "Bearer ${AppData.appToken}"

        userService.signOut(accessToken).enqueue(object : Callback<UserResponse<SignOutResponse>> {
            override fun onResponse(
                call: Call<UserResponse<SignOutResponse>>,
                response: Response<UserResponse<SignOutResponse>>
            ) {
                // 로그에 서버 응답 내용 출력
                Log.d("SignOutResponse", "Response Code: ${response.code()}")
                Log.d("SignOutResponse", "Response Body: ${response.body()}")

                if (response.isSuccessful && response.body()?.isSuccess == true) {
                    // 회원 탈퇴 성공 후 처리
                    Toast.makeText(context, "회원탈퇴가 완료되었습니다.", Toast.LENGTH_SHORT).show()

                    // appToken 정보 초기화로 앱 저장 데이터 초기화
                    val sharedPreferences = requireContext().getSharedPreferences("userInfo", AppCompatActivity.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("appToken", "")
                    editor.apply()
                    // 회원가입 화면으로 이동
                    val intent = Intent(requireContext(), SignUpActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)

                    // 현재 프래그먼트를 종료
                    parentFragmentManager.popBackStack()
                } else {
                    Toast.makeText(context, "회원탈퇴에 실패했습니다: ${response.body()?.message ?: "Unknown error"}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserResponse<SignOutResponse>>, t: Throwable) {
                Log.e("SignOutError", "Error: ${t.message}", t)
                Toast.makeText(context, "서버와 통신 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

package com.example.main_infor

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton

class inforFragment : Fragment() {

    // Khai báo các biến để sử dụng trong Fragment
    private lateinit var imageView: ImageView
    private lateinit var button: FloatingActionButton
    // Khai báo launcher để nhận kết quả khi chọn ảnh
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment từ XML
        return inflater.inflate(R.layout.fragment_infor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tìm các view bằng ID
        val darkMode = view.findViewById<FrameLayout>(R.id.darkMode)
        val changeAvata = view.findViewById<FrameLayout>(R.id.changeAvata)
        val changeName = view.findViewById<FrameLayout>(R.id.changeName)
        val friendRequest = view.findViewById<FrameLayout>(R.id.friendRequest)
        val blockList = view.findViewById<FrameLayout>(R.id.blockList)
        val txtName = view.findViewById<TextView>(R.id.txtName)

        // Khởi tạo imageView và button
        imageView = view.findViewById(R.id.imgView)
        button = view.findViewById(R.id.floatingActionButton)

        // Đăng ký bộ nhận kết quả từ ImagePicker
        imagePickerLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result -> // Nhận kết quả khi người dùng chọn ảnh
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val data: Intent? = result.data
                val uri: Uri? = data?.data // Lấy URI của ảnh đã chọn
                imageView.setImageURI(uri) // Hiển thị ảnh trong ImageView
            }
        }

        // Thiết lập sự kiện click cho button để thay đổi avatar
        button.setOnClickListener {
            changeAvata() // Gọi hàm changeAvata
        }

        // Thiết lập onClickListener cho các FrameLayout
        darkMode.setOnClickListener {
            changeBackgroundColor(darkMode, "#D9D9D9", 150) // Thay đổi màu nền
            changeMode(view) // Gọi hàm changeMode
        }
        changeAvata.setOnClickListener {
            changeBackgroundColor(changeAvata, "#D9D9D9", 150) // Thay đổi màu nền
            changeAvata() // Gọi hàm changeAvata
        }
        changeName.setOnClickListener {
            changeBackgroundColor(changeName, "#D9D9D9", 150) // Thay đổi màu nền
            changeNameFunc(view) // Gọi hàm changeNameFunc
        }
        friendRequest.setOnClickListener {
            changeBackgroundColor(friendRequest, "#D9D9D9", 150) // Thay đổi màu nền
        }
        blockList.setOnClickListener {
            changeBackgroundColor(blockList, "#D9D9D9", 150) // Thay đổi màu nền
        }

        // Thiết lập đổi tên khi click vào txtName
        txtName.setOnClickListener {
            changeNameFunc(view) // Gọi hàm changeNameFunc
        }
    }

    // Hàm để thay đổi tên người dùng
    private fun changeNameFunc(view: View) {
        val edtChangeName = view.findViewById<EditText>(R.id.edtChangeName) // Tìm EditText
        val txtName = view.findViewById<TextView>(R.id.txtName) // Tìm TextView

        // Đặt văn bản hiện tại của txtName vào EditText
        edtChangeName.setText(txtName.text.toString())

        // Đưa con trỏ chuột đến cuối văn bản
        edtChangeName.setSelection(edtChangeName.text.length)

        // Ẩn TextView và hiển thị EditText
        txtName.visibility = View.INVISIBLE
        edtChangeName.visibility = View.VISIBLE

        // Đưa con trỏ chuột (focus) vào EditText
        edtChangeName.requestFocus()

        // Hiển thị bàn phím
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(edtChangeName, InputMethodManager.SHOW_IMPLICIT)

        // Xử lý nhấn phím Enter
        edtChangeName.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                // Cập nhật nội dung TextView và ẩn EditText
                txtName.text = edtChangeName.text.toString()
                edtChangeName.visibility = View.INVISIBLE
                txtName.visibility = View.VISIBLE

                // Ẩn bàn phím
                imm.hideSoftInputFromWindow(edtChangeName.windowToken, 0)

                true
            } else {
                false
            }
        }
    }

    // Hàm để thay đổi chế độ tối
    private fun changeMode(view: View) {
        val txtMode = view.findViewById<TextView>(R.id.txtCheckDarkMode)
        // Chuyển đổi trạng thái của chế độ tối
        if (txtMode.text.toString() == "Off") {
            txtMode.text = "On" // Bật chế độ tối
        } else {
            txtMode.text = "Off" // Tắt chế độ tối
        }
    }

    // Hàm để thay đổi avatar
    private fun changeAvata() {
        // Khởi chạy ImagePicker
        ImagePicker.with(this)
            .crop()  // Crop image (Optional), Check Customization for more option
            .compress(1024)  // Final image size will be less than 1 MB (Optional)
            .maxResultSize(1080, 1080)  // Final image resolution will be less than 1080 x 1080 (Optional)
            .createIntent { intent -> // Tạo intent để chọn ảnh
                imagePickerLauncher.launch(intent) // Khởi chạy launcher với intent
            }
    }

    // Hàm để thay đổi màu nền cho view
    private fun changeBackgroundColor(view: View, color: String, duration: Long) {
        val background = view.background
        // Lấy màu nền hiện tại
        val currentColor = if (background is ColorDrawable) background.color else Color.TRANSPARENT

        view.setBackgroundColor(Color.parseColor(color)) // Thay đổi màu nền
        // Trở về màu nền cũ sau một khoảng thời gian
        view.postDelayed({ view.setBackgroundColor(currentColor) }, duration)
    }
}

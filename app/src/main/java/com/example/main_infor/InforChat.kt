package com.example.main_infor

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InforChat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_infor_chat)


        val frmTopic = findViewById<FrameLayout>(R.id.frmTopic)
        val frmFastEmotion = findViewById<FrameLayout>(R.id.frmFastEmotion)
        val frmNickName = findViewById<FrameLayout>(R.id.frmNickName)
        val frmNotice = findViewById<FrameLayout>(R.id.frmNotice)
        val frmLink = findViewById<FrameLayout>(R.id.frmLink)
        val frmLimit = findViewById<FrameLayout>(R.id.frmLimit)
        val frmBlock = findViewById<FrameLayout>(R.id.frmBlock)
        val frmTrash = findViewById<FrameLayout>(R.id.frmTrash)

// Thiết lập onClickListener cho các FrameLayout
        frmTopic.setOnClickListener {
            changeBackgroundColor(frmTopic, "#D9D9D9", 150)
        }

        frmFastEmotion.setOnClickListener {
            changeBackgroundColor(frmFastEmotion, "#D9D9D9", 150)
        }

        frmNickName.setOnClickListener {
            changeBackgroundColor(frmNickName, "#D9D9D9", 150)
        }

        frmNotice.setOnClickListener {
            changeBackgroundColor(frmNotice, "#D9D9D9", 150)
        }

        frmLink.setOnClickListener {
            changeBackgroundColor(frmLink, "#D9D9D9", 150)
        }

        frmLimit.setOnClickListener {
            changeBackgroundColor(frmLimit, "#D9D9D9", 150)
        }

        frmBlock.setOnClickListener {
            changeBackgroundColor(frmBlock, "#D9D9D9", 150)
        }

        frmTrash.setOnClickListener {
            changeBackgroundColor(frmTrash, "#D9D9D9", 150)
        }
    }

    private fun changeBackgroundColor(view: View, color: String, duration: Long) {
        val background = view.background
        val currentColor = if (background is ColorDrawable) (background as ColorDrawable).color else Color.TRANSPARENT

        view.setBackgroundColor(Color.parseColor(color))
        view.postDelayed({ view.setBackgroundColor(currentColor) }, duration)
    }
}
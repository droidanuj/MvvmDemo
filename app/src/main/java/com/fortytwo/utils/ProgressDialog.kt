package com.bookaride.utils

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog


object ProgressDialog {

//    fun showProgress(
//        context: Context?
//    ): KProgressHUD? {
//        return KProgressHUD.create(context)
//            .setCancellable(false)
//            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//            .setBackgroundColor(Color.parseColor("#0069C4"))
//            .setAnimationSpeed(1)
//    }
//    fun showProgressOnButton(
//        context: Context?
//    ): KProgressHUD? {
//        return KProgressHUD.create(context)
//            .setCustomView(SpinView(context!!))
////            .setBackgroundColor(Color.parseColor("#0069C4"))
//            .setAnimationSpeed(1)
//    }

    fun setProgressDialog(context: Context): AlertDialog {
        val padding = 50
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.setPadding(padding, padding, padding, padding)
        linearLayout.gravity = Gravity.CENTER
        var params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.CENTER
        linearLayout.layoutParams = params

        val progressBar = ProgressBar(context)
        progressBar.isIndeterminate = true
        progressBar.setPadding(0, 0, padding, 0)
        progressBar.layoutParams = params
        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.CENTER
        val tvText = TextView(context)
//        tvText.text = message
        tvText.setTextColor(Color.parseColor("#000000"))
        tvText.textSize = 20.toFloat()
        tvText.layoutParams = params

        linearLayout.addView(progressBar)
        linearLayout.addView(tvText)
        linearLayout.background = null
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setView(linearLayout)

        val dialog = builder.create()
        val window = dialog.window
        if (window != null) {
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(dialog.window?.attributes)
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            dialog.window?.attributes = layoutParams
        }
        return dialog
    }

}
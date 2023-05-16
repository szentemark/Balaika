package com.example.balaika.ui.enums

import androidx.annotation.StringRes
import com.example.balaika.R

enum class BalaikaScreen(@StringRes val title: Int) {
    Playroom(title = R.string.page_playroom),
    AllSongs(title = R.string.page_all_songs),
    Settings(title = R.string.page_settings)
}
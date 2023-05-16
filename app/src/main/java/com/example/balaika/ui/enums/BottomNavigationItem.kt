package com.example.balaika.ui.enums

import androidx.annotation.DrawableRes
import com.example.balaika.R

enum class BottomNavigationItem(val screen: BalaikaScreen, @DrawableRes val icon: Int) {
    Playroom(screen = BalaikaScreen.Playroom, icon = R.drawable.baseline_music_note_24),
    AllSongs(screen = BalaikaScreen.AllSongs, icon = R.drawable.baseline_format_list_bulleted_24),
    Settings(screen = BalaikaScreen.Settings, icon = R.drawable.baseline_settings_24)
}
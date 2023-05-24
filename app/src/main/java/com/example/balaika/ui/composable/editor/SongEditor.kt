package com.example.balaika.ui.composable.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.balaika.R

@Composable
fun SongEditor() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        // title
        TextRow(
            label = R.string.editor_title,
            value = "No soy el aire",
            onValueChange = {  }
        )
        // author
        TextRow(
            label = R.string.editor_author,
            value = "Gaby Moreno",
            onValueChange = {  }
        )
        // scrumming
        val scrummingOptions = mapOf(
            1 to R.string.editor_scrumming_split,
            2 to R.string.editor_scrumming_mixed,
            3 to R.string.editor_scrumming_scrum
        )
        RadioRow(options = scrummingOptions, selectedValue = 1) { }
        // pick
        SwitchRow(label = R.string.editor_pick, true) {  }
        // left hand heavy
        SwitchRow(label = R.string.editor_left_hand_heavy, false) {  }
        // feature song
        SwitchRow(label = R.string.editor_feature_song, false) {  }
        // show in playroom
        SwitchRow(label = R.string.editor_playroom_ready, false) {  }
    }
}
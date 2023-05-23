package com.example.balaika.ui.composable.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.balaika.R

@OptIn(ExperimentalMaterial3Api::class)
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
        // TODO scrumming - 1-3 value
        // pick
        SwitchRow(label = R.string.editor_pick) {  }
        // left hand heavy
        SwitchRow(label = R.string.editor_left_hand_heavy) {  }
        // feature song
        SwitchRow(label = R.string.editor_feature_song) {  }
        // show in playroom
        SwitchRow(label = R.string.editor_playroom_ready) {  }
    }
}
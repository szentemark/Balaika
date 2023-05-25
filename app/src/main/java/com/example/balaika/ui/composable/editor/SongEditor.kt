package com.example.balaika.ui.composable.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import com.example.balaika.R
import com.example.balaika.model.room.entity.Song
import com.example.balaika.ui.viewmodel.BalaikaViewModel

@Composable
fun SongEditor(
    song: Song,
    newlyCreatedSong: Boolean,
    viewModel: BalaikaViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        val focusRequester = remember { FocusRequester() }
        // title
        TextRow(
            label = R.string.editor_title,
            value = song.title,
            onValueChange = { viewModel.updateSong { song -> song.copy(title = it.trim()) } },
            modifier = Modifier.focusRequester(focusRequester)
        )
        if (newlyCreatedSong) {
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
        }
        // author
        TextRow(
            label = R.string.editor_author,
            value = song.author,
            imeAction = ImeAction.Done,
            onValueChange = { viewModel.updateSong { song -> song.copy(author = it.trim()) } }
        )
        // scrumming
        val scrummingOptions = mapOf(
            1 to R.string.editor_scrumming_split,
            2 to R.string.editor_scrumming_mixed,
            3 to R.string.editor_scrumming_scrum
        )
        RadioRow(options = scrummingOptions, selectedValue = song.scrumming) {
            viewModel.updateSong { song -> song.copy(scrumming = it) }
        }
        // pick
        SwitchRow(label = R.string.editor_pick, checked = song.pick) {
            viewModel.updateSong { song -> song.copy(pick = it) }
        }
        // left hand heavy
        SwitchRow(label = R.string.editor_left_hand_heavy, checked = song.leftHandHeavy) {
            viewModel.updateSong { song -> song.copy(leftHandHeavy = it) }
        }
        // feature song
        SwitchRow(label = R.string.editor_feature_song, checked = song.featureSong) {
            viewModel.updateSong { song -> song.copy(featureSong = it) }
        }
        // show in playroom
        SwitchRow(label = R.string.editor_playroom_ready, checked = song.showInPlayroom) {
            viewModel.updateSong { song -> song.copy(showInPlayroom = it) }
        }
    }
}
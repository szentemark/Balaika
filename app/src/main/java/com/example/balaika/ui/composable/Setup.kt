package com.example.balaika.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.balaika.R
import com.example.balaika.model.BalaikaDataStore
import com.example.balaika.ui.composable.editor.SwitchRow
import com.example.balaika.ui.viewmodel.BalaikaViewModel

@Composable
fun Setup(viewModel: BalaikaViewModel) {
    val uiState = viewModel.uiState.collectAsState().value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        // only feature songs
        SwitchRow(label = R.string.setup_feature_only, checked = uiState.setupFeatureOnly) {
            viewModel.updateSetup(BalaikaDataStore.DataType.FEATURE_ONLY, it)
        }
        Spacer(modifier = Modifier.size(12.dp))
        // only hand pick songs
        SwitchRow(label = R.string.setup_hand_pick_only, checked = uiState.setupHandPickOnly) {
            viewModel.updateSetup(BalaikaDataStore.DataType.HAND_PICK_ONLY, it)
        }
        Spacer(modifier = Modifier.size(12.dp))
        // no scrumming songs
        SwitchRow(label = R.string.setup_no_scrumming, checked = uiState.setupNoScrumming) {
            viewModel.updateSetup(BalaikaDataStore.DataType.NO_SCRUMMING, it)
        }
        Spacer(modifier = Modifier.size(24.dp))
    }
}